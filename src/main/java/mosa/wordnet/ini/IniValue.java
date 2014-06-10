package mosa.wordnet.ini;

/**
 * @autor Adrian Moldovan (adrian.moldovan00@gmail.com)
 * @description Model of entries associated with a key in a map configuration
 *              for log file setup.
 */
public class IniValue {

    private final String key;
    private final String value;

    public IniValue(final String key, final String value) {
	this.key = key;
	this.value = value;
    }

    public String getKey() {
	return this.key;
    }

    public String getValue() {
	return this.value;
    }
}