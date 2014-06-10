package mosa.wordnet.test;

import java.io.File;
import java.util.ArrayList;

import mosa.wordnet.db.ConnectionDetails;
import mosa.wordnet.db.SQL;
import mosa.wordnet.html.HtmlDefinition;
import mosa.wordnet.util.Config;

/**
 * @autor Adrian Moldovan (adrian.moldovan00@gmail.com)
 */
public class HtmlDescriptionTest {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
	String lemma = "main";
	String query1 = "SELECT"
		+ "`casedwords`.`cased`     AS `cased`,"
		+ "`synsets`.`pos`          AS `pos`,"
		+ "`synsets`.`definition`   AS `definition`,"
		+ "`samplesets`.`sampleset` AS `sampleset`"
		+ "FROM ((((`words`"
		+ "LEFT JOIN `senses` `s`"
		+ "ON ((`words`.`wordid` = `s`.`wordid`)))"
		+ "LEFT JOIN `casedwords`"
		+ "ON (((`words`.`wordid` = `casedwords`.`wordid`)"
		+ "AND (`s`.`casedwordid` = `casedwords`.`casedwordid`))))"
		+ "LEFT JOIN `synsets`"
		+ "ON ((`s`.`synsetid` = `synsets`.`synsetid`)))"
		+ "LEFT JOIN `samplesets`"
		+ "ON ((`s`.`synsetid` = `samplesets`.`synsetid`))) WHERE lemma='"
		+ lemma + "'";

	SQL wordnetSQL = new SQL(new ConnectionDetails(Config.getInstance(
		"config" + File.separator + "config.ini").getWordnetDB()));

	ArrayList<String[]> list = wordnetSQL.getDataObject(query1);
	HtmlDefinition desc = null;
	for (int i = 0; i < list.size(); i++) {
	    String[] row = list.get(i);
	    if (desc == null) {
		desc = new HtmlDefinition(row[1], row[2], row[3], "");
	    } else {
		desc.addValue(row[1], row[2], row[3], "");
	    }
	}
	System.out.println(desc);
    }
}