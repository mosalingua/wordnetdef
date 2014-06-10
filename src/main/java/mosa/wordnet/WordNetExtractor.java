package mosa.wordnet;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;

import mosa.wordnet.db.ConnectionDetails;
import mosa.wordnet.db.SQL;
import mosa.wordnet.db.tables.WNDictionary;
import mosa.wordnet.db.tables.Words;
import mosa.wordnet.db.tables.atables.AbstractDBObject;
import mosa.wordnet.html.HtmlDefinition;
import mosa.wordnet.ini.IniSection;
import mosa.wordnet.util.*;

import org.apache.log4j.Logger;

/**
 * @autor Adrian Moldovan (adrian.moldovan00@gmail.com)
 */
public class WordNetExtractor {

    public static final Logger LOGGER = Logger
	    .getLogger(WordNetExtractor.class);

    private final IniSection mosaINI;
    private final IniSection wordnetINI;
    private final IniSection generalINI;

    private final SQL mosaSQL;
    private final SQL wordnetSQL;

    private final CommandLineParameters cmdParams;

    public WordNetExtractor(CommandLineParameters params) throws Exception {
	this.cmdParams = params;
	this.mosaINI = Config.getInstance(this.cmdParams.getConfigFile())
		.getMosaDB();
	this.wordnetINI = Config.getInstance(this.cmdParams.getConfigFile())
		.getWordnetDB();
	this.generalINI = Config.getInstance(this.cmdParams.getConfigFile())
		.getGeneral();
	this.mosaSQL = new SQL(new ConnectionDetails(this.mosaINI));
	this.wordnetSQL = new SQL(new ConnectionDetails(this.wordnetINI));
    }

    public void process() throws Exception {
	this.mosaSQL.execute(WNDictionary.createTable().toString());// create
								    // table if
								    // not
	// exists

	ArrayList<AbstractDBObject> wordList = this.wordnetSQL
		.getDbObjectsByQuery("SELECT * FROM " + Words.TABLE_NAME,
			Words.class);
	int step = wordList.size() / 100;
	long time = System.currentTimeMillis();
	for (int i = 0; i < wordList.size(); i++) {
	    if ((i % step) == 0) {
		WordNetExtractor.LOGGER.info((i / step) + "%  - "
			+ ((System.currentTimeMillis() - time) / 1000) + "sec");
	    }
	    insertLemma((Words) wordList.get(i));
	}
    }

    public void insertLemma(Words word) throws Exception {
	if (word == null) {
	    return;
	}
	String query = "SELECT "
		+ "s.synsetid           AS synsetid,"
		+ "words.wordid         AS wordid,"
		+ "s.tagcount           AS tagcount,"
		+ "casedwords.cased     AS cased,"
		+ "synsets.pos          AS pos,"
		+ "synsets.definition   AS definition "
		+ "FROM ((((words "
		+ "LEFT JOIN senses s "
		+ "ON (words.wordid = s.wordid)) "
		+ "LEFT JOIN casedwords "
		+ "ON (words.wordid = casedwords.wordid AND s.casedwordid = casedwords.casedwordid)) "
		+ "LEFT JOIN synsets ON s.synsetid = synsets.synsetid)) "
		+ "WHERE words.lemma ='"
		+ StringUtil.getDBString(word.getLemma())
		+ "' ORDER BY s.tagcount DESC";
	ArrayList<String[]> list = this.wordnetSQL.getDataObject(query);
	HtmlDefinition desc = null;

	int minDef = Integer.parseInt(this.cmdParams.getMinimumDefinitions());
	// take first minimum definition
	int firstDef = minDef < list.size() ? minDef : list.size();
	for (int i = 0; i < firstDef; i++) {
	    String[] row = list.get(i);
	    String sample = getSamplePhrases(row[0], word.getLemma());
	    String syn = getSyn(row[0], word.getWordid());
	    if (desc == null) {
		desc = new HtmlDefinition(row[4], row[5], sample, syn);
	    } else {
		desc.addValue(row[4], row[5], sample, syn);
	    }
	}
	if (firstDef < list.size()) {
	    for (int i = firstDef; i < list.size(); i++) {
		String[] row = list.get(i);
		if (Integer.parseInt(row[2]) < Integer.parseInt(this.cmdParams
			.getFrequencyMin())) {
		    break;
		}
		String sample = getSamplePhrases(row[0], word.getLemma());
		String syn = getSyn(row[0], word.getWordid());
		if (desc == null) {
		    desc = new HtmlDefinition(row[4], row[5], sample, syn);
		} else {
		    desc.addValue(row[4], row[5], sample, syn);
		}
	    }
	}
	if (desc != null) {
	    WNDictionary dict = new WNDictionary();
	    dict.setWordLemma(word.getLemma());
	    dict.setDefinitions(desc.toString());
	    this.mosaSQL.execute(dict.insertSQL().toString());
	}
    }

    private String getSamplePhrases(String synsetid, String word)
	    throws Exception {
	String query = "SELECT "
		+ "GROUP_CONCAT(CONCAT('\"', sample, '\"') "
		+ "ORDER BY sampleid ASC SEPARATOR ', ') AS sampleset "
		+ "FROM samples "
		+ "WHERE synsetid="
		+ synsetid
		+ ("true".equals(this.cmdParams.getOnlyPhraseWithWord()) ? " AND sample LIKE '%"
			+ StringUtil.getDBString(word) + "%'"
			: "");
	ArrayList<String[]> list = this.wordnetSQL.getDataObject(query);
	if (list == null) {
	    return null;
	}
	String[] str = list.get(0);
	if (str == null) {
	    return null;
	}
	return str[0];
    }

    private String getSyn(String synsetid, int wordid) throws Exception {
	String query = "SELECT "
		+ "GROUP_CONCAT(DISTINCT w.lemma SEPARATOR ', ') AS lemma"
		+ " FROM ((senses s" + " JOIN semlinks l"
		+ " ON ((s.synsetid = l.synset1id)))" + "JOIN senses d"
		+ " ON ((l.synset2id = d.synsetid))" + "JOIN words w"
		+ " ON ((w.wordid=s.wordid))) WHERE s.synsetid = " + synsetid
		+ " AND w.wordid !=" + wordid;
	ArrayList<String[]> list = this.wordnetSQL.getDataObject(query);
	if (list == null) {
	    return null;
	}
	String[] str = list.get(0);
	if (str == null) {
	    return null;
	}
	return str[0];
    }

    public static void main(String[] args) {
	try {
	    // setup a default handler invoked when a thread abruptly terminates
	    // due to an uncaught exception
	    Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
		@Override
		public void uncaughtException(Thread t, Throwable exc) {
		    WordNetExtractor.LOGGER.fatal(t, exc);
		    WordNetExtractor.LOGGER
			    .fatal("The application will be forced to close now.");
		    System.exit(-1);
		}
	    });

	    CommandLineParameters cmdParams = new CommandLineParameters(args);
	    if (cmdParams.validate()) {
		WordNetExtractor main = new WordNetExtractor(cmdParams);
		main.process();
	    } else {
		CommandLineParameters.usage();
		System.exit(-1);
	    }
	} catch (Exception exc) {
	    WordNetExtractor.LOGGER.fatal(exc, exc);
	} finally {
	    System.exit(0);
	}
    }
}