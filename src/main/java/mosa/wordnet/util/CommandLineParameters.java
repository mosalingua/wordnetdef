package mosa.wordnet.util;

import java.io.File;
import java.util.Hashtable;

import mosa.wordnet.Version;
import mosa.wordnet.ini.IniSection;

import org.apache.log4j.Logger;

/**
 * @autor Adrian Moldovan (adrian.moldovan00@gmail.com)
 */
public class CommandLineParameters {
    public static final Logger LOGGER = Logger
	    .getLogger(CommandLineParameters.class);

    private final static String KEY_FREQUENCY_MIN = "frequency_min";
    private final static String KEY_MINIMUM_DEFINITIONS = "minimum_definitions";
    private final static String KEY_ONLY_PHRASE_WITH_WORD = "only_phrase_with_word";
    private final static String KEY_CONFIG_FILE = "config_file";

    // using string values for using null value
    private String frequencyMin;
    private String minimumDefinitions;
    private String onlyPhraseWithWord;
    private String configFile;
    private final String[] params;

    public CommandLineParameters(String[] params) {
	this.params = params;
	parse();

	try {
	    if (StringUtil.isNotEmpty(this.configFile)
		    && new File(this.configFile).exists()) {
		loadDefaultValues(Config.getInstance(this.configFile)
			.getGeneral());
	    }

	} catch (Exception e) {
	    // do nothing - this is for exists function
	}
    }

    private void loadDefaultValues(IniSection section) {
	this.frequencyMin = section.getIniValue(
		CommandLineParameters.KEY_FREQUENCY_MIN.replace('_', '.'))
		.getValue();
	this.minimumDefinitions = section
		.getIniValue(
			CommandLineParameters.KEY_MINIMUM_DEFINITIONS.replace(
				'_', '.')).getValue();
	this.onlyPhraseWithWord = section.getIniValue(
		CommandLineParameters.KEY_ONLY_PHRASE_WITH_WORD.replace('_',
			'.')).getValue();
    }

    private void parse() {
	if (this.params == null) {
	    return;
	}
	Hashtable<String, String> paramsHash = new Hashtable<String, String>();
	for (int i = 0; i < this.params.length; i++) {
	    if (this.params[i].startsWith("-")) {
		String key = this.params[i].substring(1,
			this.params[i].length());
		String value = null;
		if ((i + 1) < this.params.length) {
		    value = this.params[i + 1];
		}
		paramsHash.put(key, value != null ? value : "");
	    }
	}

	if (paramsHash.containsKey(CommandLineParameters.KEY_CONFIG_FILE)) {
	    this.configFile = paramsHash
		    .get(CommandLineParameters.KEY_CONFIG_FILE);
	}

	if (paramsHash.containsKey(CommandLineParameters.KEY_FREQUENCY_MIN)) {
	    this.frequencyMin = paramsHash
		    .get(CommandLineParameters.KEY_FREQUENCY_MIN);
	}
	if (paramsHash
		.containsKey(CommandLineParameters.KEY_MINIMUM_DEFINITIONS)) {
	    this.minimumDefinitions = paramsHash
		    .get(CommandLineParameters.KEY_MINIMUM_DEFINITIONS);
	}
	if (paramsHash
		.containsKey(CommandLineParameters.KEY_ONLY_PHRASE_WITH_WORD)) {
	    this.onlyPhraseWithWord = paramsHash
		    .get(CommandLineParameters.KEY_ONLY_PHRASE_WITH_WORD);
	}
    }

    /**
     * validate the parameters
     * 
     * @return true or false
     */
    public boolean validate() {
	if (StringUtil.isEmpty(this.configFile)) {
	    CommandLineParameters.LOGGER
		    .error(CommandLineParameters.KEY_CONFIG_FILE
			    + " is missing.");
	    return false;
	}
	File f = new File(this.configFile);
	if (!f.exists()) {
	    CommandLineParameters.LOGGER
		    .error(CommandLineParameters.KEY_CONFIG_FILE
			    + " not exist.");
	    return false;
	}

	if (StringUtil.isEmpty(this.frequencyMin)) {
	    CommandLineParameters.LOGGER
		    .error(CommandLineParameters.KEY_FREQUENCY_MIN
			    + " is missing.");
	    return false;
	}
	try {
	    Integer.parseInt(this.frequencyMin);
	} catch (Exception e) {
	    CommandLineParameters.LOGGER
		    .error(CommandLineParameters.KEY_FREQUENCY_MIN
			    + " invalid format.");
	    return false;
	}

	if (StringUtil.isEmpty(this.minimumDefinitions)) {
	    CommandLineParameters.LOGGER
		    .error(CommandLineParameters.KEY_MINIMUM_DEFINITIONS
			    + " is missing.");
	    return false;
	}
	try {
	    Integer.parseInt(this.minimumDefinitions);
	} catch (Exception e) {
	    CommandLineParameters.LOGGER
		    .error(CommandLineParameters.KEY_MINIMUM_DEFINITIONS
			    + " invalid format.");
	    return false;
	}

	if (StringUtil.isEmpty(this.onlyPhraseWithWord)) {
	    this.onlyPhraseWithWord = "true";
	}
	if (!"true".equals(this.onlyPhraseWithWord)
		&& !"false".equals(this.onlyPhraseWithWord)) {

	    CommandLineParameters.LOGGER
		    .error(CommandLineParameters.KEY_ONLY_PHRASE_WITH_WORD
			    + " invalid format.");
	    return false;
	}

	return true;
    }

    public static void usage() {
	CommandLineParameters.LOGGER.info(Version.APP_NAME + " v"
		+ Version.VERSION);
	CommandLineParameters.LOGGER
		.info("Usage: "
			+ Version.APP_NAME
			+ " [-config_file name] [-frequency_min min] [-minimum_definitions minDef] [-only_phrase_with_word [true | false]]");
    }

    public String getFrequencyMin() {
	return this.frequencyMin;
    }

    public void setFrequencyMin(String frequencyMin) {
	this.frequencyMin = frequencyMin;
    }

    public String getMinimumDefinitions() {
	return this.minimumDefinitions;
    }

    public void setMinimumDefinitions(String minimumDefinitions) {
	this.minimumDefinitions = minimumDefinitions;
    }

    public String getOnlyPhraseWithWord() {
	return this.onlyPhraseWithWord;
    }

    public void setOnlyPhraseWithWord(String onlyPhraseWithWord) {
	this.onlyPhraseWithWord = onlyPhraseWithWord;
    }

    public String getConfigFile() {
	return this.configFile;
    }

    public void setConfigFile(String configFile) {
	this.configFile = configFile;
    }
}