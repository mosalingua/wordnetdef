package mosa.wordnet.test;

import mosa.wordnet.db.tables.WNDictionary;

/**
 * @autor Adrian Moldovan (adrian.moldovan00@gmail.com)
 */
public class WNDictionaryTest {
    public static void main(String[] args) {
	WNDictionary obj = new WNDictionary();
	obj.setWordLemma("shape");
	obj.setDefinitions("the spatial arrangement of something as distinct from its substance)");
	System.out.println(WNDictionary.createTable().toString());
	System.out.println(obj.insertSQL().toString());
	System.out.println(obj.updateSQL().toString());
	System.out.println(obj.deleteSQL().toString());
    }
}