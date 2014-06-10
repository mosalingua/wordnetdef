package mosa.wordnet.html;

import mosa.wordnet.util.StringUtil;

/**
 * @autor Adrian Moldovan (adrian.moldovan00@gmail.com)
 */
public class HtmlTag {
    private String name;
    private String clazz;
    private String value;

    public HtmlTag(String name, String cls, String val) {
	this.name = name;
	this.clazz = cls;
	this.value = val;
    }

    public void addValue(String val) {
	if (this.value == null) {
	    this.value = "";
	}
	this.value += val;
    }

    public String getName() {
	return this.name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getClazz() {
	return this.clazz;
    }

    public void setClazz(String clazz) {
	this.clazz = clazz;
    }

    public String getValue() {
	return this.value;
    }

    public void setValue(String value) {
	this.value = value;
    }

    @Override
    public String toString() {
	return StringUtil.isEmpty(this.value) ? "" : "<" + this.name
		+ (this.clazz != null ? " class=\"" + this.clazz + "\"" : "")
		+ ">" + this.value + "</" + this.name + ">";
    }
}
