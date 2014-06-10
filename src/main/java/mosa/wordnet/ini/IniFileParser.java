package mosa.wordnet.ini;

import java.io.*;

import org.apache.log4j.Logger;

/**
 * @autor Adrian Moldovan (adrian.moldovan00@gmail.com)
 * @description functions to parse ini files
 */
public class IniFileParser {

    private static final Logger LOGGER = Logger.getLogger(IniFileParser.class);

    private static final String DEFAULT_ENCODING = "ISO-8859-1";

    public static IniFile read(final File file) throws IOException {
	IniFile iniFile = new IniFile();

	BufferedReader reader = new BufferedReader(new InputStreamReader(
		new FileInputStream(file), IniFileParser.DEFAULT_ENCODING));
	try {
	    IniSection iniSection = null;
	    for (String temp; (temp = reader.readLine()) != null;) {
		if (temp.startsWith("[") && temp.endsWith("]")) {
		    // section header
		    String sectionStr = temp.substring(1, temp.length() - 1);

		    iniSection = new IniSection(sectionStr);
		    iniFile.addIniSection(iniSection);

		    if (IniFileParser.LOGGER.isDebugEnabled()) {
			IniFileParser.LOGGER.debug("Added section \""
				+ iniSection + "\"");
		    }
		} else {// values
		    if (!temp.trim().equals("") && temp.contains("=")) {
			if (iniSection != null) {
			    String[] tokens = temp.split("=");

			    String key = tokens[0];
			    String value = null;
			    if (tokens.length > 1) {// to allow null values
				value = tokens[1];
			    }

			    IniValue iniValue = new IniValue(key, value);
			    iniSection.addIniValue(iniValue);

			    if (IniFileParser.LOGGER.isDebugEnabled()) {
				IniFileParser.LOGGER.debug("Added key=\"" + key
					+ "\" value=\"" + value
					+ "\" for header \"" + iniSection
					+ "\"");
			    }
			}
		    }
		}
	    }
	} finally {
	    reader.close();
	}
	return iniFile;
    }
}