package mosa.wordnet.test;

import java.io.File;
import java.util.List;

import mosa.wordnet.ini.*;

/**
 * @autor Adrian Moldovan (adrian.moldovan00@gmail.com)
 * @description test for reading from ini file
 */
public class IniFileParserTest {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

	IniFile inifile = IniFileParser.read(new File("config" + File.separator
		+ "config.ini"));
	List<IniSection> listS = inifile.getIniSections();

	for (int i = 0; i < listS.size(); i++) {
	    IniSection iniS = listS.get(i);
	    List<IniValue> listV = iniS.getIniValues();
	    System.out.println(iniS.getName());
	    for (int j = 0; j < listV.size(); j++) {
		IniValue iniV = listV.get(j);
		System.out.println(iniV.getKey() + "=" + iniV.getValue());
	    }
	}
    }
}