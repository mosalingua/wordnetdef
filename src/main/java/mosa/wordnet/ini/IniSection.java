package mosa.wordnet.ini;

import java.util.ArrayList;
import java.util.List;

/**
 * @autor Adrian Moldovan (adrian.moldovan00@gmail.com)
 * @description Model of keys to map configuration for settings.
 */
public class IniSection {

    private final String name;
    private final List<IniValue> iniValues;

    public IniSection(final String name) {
	this.name = name;
	this.iniValues = new ArrayList<IniValue>();
    }

    public List<IniValue> getIniValues() {
	return this.iniValues;
    }

    public void addIniValue(final IniValue iniValue) {
	this.iniValues.add(iniValue);
    }

    public String getName() {
	return this.name;
    }

    public IniValue getIniValue(final String key) {
	for (IniValue iniValue : this.iniValues) {
	    if (iniValue.getKey().equals(key)) {
		return iniValue;
	    }
	}
	return null;
    }
}