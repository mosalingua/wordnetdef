package mosa.wordnet.ini;

import java.util.ArrayList;
import java.util.List;

/**
 * @autor Adrian Moldovan (adrian.moldovan00@gmail.com)
 * @description Model of an INI file.
 */
public class IniFile {

    private final List<IniSection> iniSections;

    public IniFile() {
	this.iniSections = new ArrayList<IniSection>();
    }

    public List<IniSection> getIniSections() {
	return this.iniSections;
    }

    public void addIniSection(final IniSection iniSection) {
	this.iniSections.add(iniSection);
    }

    public IniSection getIniSection(final String name) {
	for (IniSection iniSection : this.iniSections) {
	    if (iniSection.getName().equals(name)) {
		return iniSection;
	    }
	}
	return null;
    }
}
