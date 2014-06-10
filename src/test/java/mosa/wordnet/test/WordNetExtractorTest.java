package mosa.wordnet.test;

import mosa.wordnet.WordNetExtractor;
import mosa.wordnet.db.tables.Words;
import mosa.wordnet.util.CommandLineParameters;

/**
 * @autor Adrian Moldovan (adrian.moldovan00@gmail.com)
 */
public class WordNetExtractorTest {

    public static void main(String[] args) throws Exception {

	CommandLineParameters cmdParams = new CommandLineParameters(args);
	cmdParams
		.setConfigFile("E:\\workspace\\WordNetDefinitionExtractor\\config\\config.ini");
	cmdParams.setOnlyPhraseWithWord("true");
	cmdParams.setMinimumDefinitions("3");
	cmdParams.setFrequencyMin("0");
	if (cmdParams.validate()) {
	    WordNetExtractor extractor = new WordNetExtractor(cmdParams);
	    Words word = new Words();
	    word.setLemma("shape");
	    word.setWordid(118504);
	    extractor.insertLemma(word);
	    System.exit(0);
	} else {
	    CommandLineParameters.usage();
	    System.exit(-1);
	}
    }
}
