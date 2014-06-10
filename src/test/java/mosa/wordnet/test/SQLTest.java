package mosa.wordnet.test;

import java.io.File;
import java.util.ArrayList;

import mosa.wordnet.db.ConnectionDetails;
import mosa.wordnet.db.SQL;
import mosa.wordnet.util.Config;

/**
 * @autor Adrian Moldovan (adrian.moldovan00@gmail.com)
 * @description test for SQL class
 */
public class SQLTest {

    public static void main(String[] args) throws Exception {
	SQL sql = new SQL(new ConnectionDetails(Config.getInstance(
		"config" + File.separator + "config.ini").getWordnetDB()));

	String query1 = "SELECT * FROM morphs limit 100";
	String query2 = "SHOW TABLES";
	ArrayList<String[]> tables = sql.getDataObject(query1);
	for (int i = 0; i < tables.size(); i++) {
	    String[] table = tables.get(i);
	    for (int j = 0; j < table.length; j++) {
		System.out.print(table[j]);
		if (j < (table.length - 1)) {
		    System.out.print("\t");
		}
	    }
	    System.out.println("");
	}
    }
}
