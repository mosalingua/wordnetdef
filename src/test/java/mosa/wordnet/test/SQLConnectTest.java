package mosa.wordnet.test;

import java.io.File;

import mosa.wordnet.db.ConnectionDetails;
import mosa.wordnet.db.SQLConnect;
import mosa.wordnet.util.Config;

/**
 * @autor Adrian Moldovan (adrian.moldovan00@gmail.com)
 * @description test for connection to mysql database
 */
public class SQLConnectTest {

    public static void main(String[] args) throws Exception {

	ConnectionDetails conD = new ConnectionDetails(Config.getInstance(
		"config" + File.separator + "config.ini").getWordnetDB());
	SQLConnect sqlC = new SQLConnect(conD);
	sqlC.connect();

	// to check if is connected.
	// Teste with SHOW PROCESSLIST; from external program
	while (true) {
	    Thread.sleep(3 * 60 * 1000); // 3 min
	}
    }
}