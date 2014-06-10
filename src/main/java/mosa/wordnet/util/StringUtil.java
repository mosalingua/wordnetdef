package mosa.wordnet.util;

import org.apache.log4j.Logger;

/**
 * @autor Adrian Moldovan (adrian.moldovan00@gmail.com)
 * @description string functions
 */
public final class StringUtil {

    private static final Logger LOGGER = Logger.getLogger(StringUtil.class);

    private StringUtil() {
    }

    public static boolean isEmpty(final String str) {
	return (str == null) || (str.isEmpty());
    }

    public static boolean isNotEmpty(final String str) {
	return !StringUtil.isEmpty(str);
    }

    public static boolean isEmpty(final StringBuffer buff) {
	return (buff == null) || StringUtil.isEmpty(buff.toString());
    }

    public static boolean isNotEmpty(final StringBuffer buff) {
	return !StringUtil.isEmpty(buff);
    }

    public static boolean isEmpty(final String[] array) {
	return (array == null) || (array.length < 1);
    }

    public static boolean isNotEmpty(final String[] array) {
	return !StringUtil.isEmpty(array);
    }

    /**
     * Replace all occurrences of the search string with the replacement string.
     * <p>
     * This function was published by John O'Hanley
     * (http://www.javapractices.com/), distributed under Creative Commons Deed
     * 1.0 license (http://creativecommons.org/licenses/by-nc-sa/1.0/). <br>
     * This kind of license should be compatible with GNU GPL v2, at least for
     * official goText releases (non commercial, source available, and shared
     * with a commons-like license)
     * 
     * @param aInput
     *            is the original String which may contain substring aOldPattern
     * @param aOldPattern
     *            is the non-empty substring which is to be replaced
     * @param aNewPattern
     *            is the replacement for aOldPattern
     * @return string with replacement done
     */
    public static String replaceString(final String aInput,
	    final String aOldPattern, final String aNewPattern) {
	if (StringUtil.isEmpty(aInput) || (aNewPattern == null)
		|| (aOldPattern == null)) {
	    return aInput;
	}
	final StringBuffer result = new StringBuffer();
	// startIdx and idxOld delimit various chunks of aInput; these
	// chunks always end where aOldPattern begins
	int startIdx = 0;
	int idxOld = 0;
	while ((idxOld = aInput.indexOf(aOldPattern, startIdx)) >= 0) {
	    // grab a part of aInput which does not include aOldPattern
	    result.append(aInput.substring(startIdx, idxOld));
	    // add aNewPattern to take place of aOldPattern
	    result.append(aNewPattern);

	    // reset the startIdx to just after the current match, to see
	    // if there are any further matches
	    startIdx = idxOld + aOldPattern.length();
	}
	// the final chunk will go to the end of aInput
	result.append(aInput.substring(startIdx));
	return result.toString();
    }

    public static String getDBString(final String text) {
	String result = null;
	try {
	    result = text;
	    if (StringUtil.isEmpty(result)) {
		return "";
	    }

	    result = StringUtil.replaceString(result, "\\", "\\\\");
	    result = StringUtil.replaceString(result, "\"", "\\\"");
	    result = StringUtil.replaceString(result, "'", "''");
	    result = StringUtil.replaceString(result, "'", "\'");
	} catch (Exception e) {
	    StringUtil.LOGGER.fatal(e, e);
	    return "";
	}
	return result;
    }
}