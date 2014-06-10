package mosa.wordnet.test;

import mosa.wordnet.html.HtmlTag;

/**
 * @autor Adrian Moldovan (adrian.moldovan00@gmail.com)
 */
public class HtmlTest {
    public static void main(String[] args) {
	HtmlTag definition = new HtmlTag("span", "definition", null);
	definition
		.addValue("any spatial attributes (especially as defined by outline)");
	System.out.println(definition);

	HtmlTag sample = new HtmlTag("span", "samplePhrase", null);
	sample.addValue("\"he could barely make out their shapes\"");

	HtmlTag synonimes = new HtmlTag("span", "synonimes", null);
	synonimes.addValue("syn: form, configuration, contour, conformation");

	HtmlTag li = new HtmlTag("li", null, null);
	li.addValue(definition.toString());
	li.addValue(sample.toString());
	li.addValue(synonimes.toString());
	HtmlTag ul = new HtmlTag("ul", null, null);
	ul.addValue(new HtmlTag("span", "typeWord", "Noun").toString());
	ul.addValue(li.toString());

	System.err.println(ul);
    }
}
