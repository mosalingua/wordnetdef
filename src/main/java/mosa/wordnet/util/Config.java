package mosa.wordnet.util;

import java.io.File;
import java.io.IOException;

import mosa.wordnet.ini.*;

import org.apache.log4j.Logger;

/**
 * @autor Adrian Moldovan (adrian.moldovan00@gmail.com)
 */
public class Config {
    public static final Logger LOGGER = Logger.getLogger(Config.class);

    public static final String INISECTION_GENERAL = "GENERAL";
    public static final String INISECTION_WORDNETDB = "WORDNET_DB";
    public static final String INISECTION_MOSADB = "MOSA_DB";

    public static final String CONFIG_FILE_PATH = "config" + File.separator
	    + "config.ini";

    private IniSection general;
    private IniSection wordnetDB;
    private IniSection mosaDB;
    private static Config instance;

    private Config(String fileName) {
	try {
	    IniFile iniFile = IniFileParser.read(new File(fileName));
	    this.general = iniFile.getIniSection(Config.INISECTION_GENERAL);
	    this.wordnetDB = iniFile.getIniSection(Config.INISECTION_WORDNETDB);
	    this.mosaDB = iniFile.getIniSection(Config.INISECTION_MOSADB);
	} catch (IOException e) {
	    Config.LOGGER.fatal(e, e);
	}
    }

    public static Config getInstance(String fileName) {
	if (Config.instance == null) {
	    Config.instance = new Config(fileName);
	}
	return Config.instance;
    }

    public IniSection getGeneral() {
	return this.general;
    }

    public IniSection getWordnetDB() {
	return this.wordnetDB;
    }

    public IniSection getMosaDB() {
	return this.mosaDB;
    }
}