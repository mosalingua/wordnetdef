package mosa.wordnet.html;

import mosa.wordnet.util.StringUtil;

/**
 * @autor Adrian Moldovan (adrian.moldovan00@gmail.com)
 * @deschiption class to write html definition
 * @example <ul>
 *          <span class="typeWord">Noun</span>
 *          <li>
 *          <span class="definition">any spatial attributes (especially as
 *          defined by outline) </span> <span
 *          class="samplePhrase">"he could barely make out their shapes"</span>
 *          <span class="synonimes">syn: form, configuration, contour,
 *          conformation</span></li>
 *          </ul>
 */
public class HtmlDefinition {

    // Adjective - a
    // Noun - n
    // Verb - v
    // Adverb - r
    // s - Adjective too??

    private static final String TYPE_WORD_ADJECTIVE = "Adjective";
    private static final String TYPE_WORD_NOUN = "Noun";
    private static final String TYPE_WORD_VERB = "Verb";
    private static final String TYPE_WORD_ADVERB = "Adverb";

    private static final String TAG_UL = "ul";
    private static final String TAG_LI = "li";
    private static final String TAG_SPAN = "span";

    private static final String CLASS_TYPEWORD = "typeWord";
    private static final String CLASS_SAMPLEPHRASE = "samplePhrase";
    private static final String CLASS_DEFINITION = "definition";
    private static final String CLASS_SYNONYMS = "synonyms";

    private HtmlTag ulAdjectiveTag;
    private HtmlTag ulNoumTag;
    private HtmlTag ulVerbTag;
    private HtmlTag ulAdverdTag;

    private static final String ONE_TAB = "\r\n\t";
    private static final String TWO_TAB = "\r\n\t\t";
    private static final String NEW_LINE = "\r\n";

    public HtmlDefinition(final String pos, final String definition,
	    final String sampleset, final String synonym) {
	addValue(pos, definition, sampleset, synonym);
    }

    public void addValue(final String pos, final String definition,
	    final String sampleset, final String synonym) {
	HtmlTag li = new HtmlTag(HtmlDefinition.TAG_LI, null, null);
	if (pos.trim().equals("a")) {
	    createDefinitionAdj();
	    li.addValue((StringUtil.isNotEmpty(definition) ? HtmlDefinition.TWO_TAB
		    : "")
		    + new HtmlTag(HtmlDefinition.TAG_SPAN,
			    HtmlDefinition.CLASS_DEFINITION, definition)
			    .toString());
	    li.addValue((StringUtil.isNotEmpty(sampleset) ? HtmlDefinition.TWO_TAB
		    : "")
		    + new HtmlTag(HtmlDefinition.TAG_SPAN,
			    HtmlDefinition.CLASS_SAMPLEPHRASE, sampleset)
			    .toString());
	    li.addValue((StringUtil.isNotEmpty(synonym) ? HtmlDefinition.TWO_TAB
		    : "")
		    + new HtmlTag(HtmlDefinition.TAG_SPAN,
			    HtmlDefinition.CLASS_SYNONYMS, synonym).toString());
	    this.ulAdjectiveTag.addValue(HtmlDefinition.ONE_TAB + li.toString()
		    + HtmlDefinition.NEW_LINE);
	} else if (pos.trim().equals("n")) {
	    createDefinitionNom();
	    li.addValue((StringUtil.isNotEmpty(definition) ? HtmlDefinition.TWO_TAB
		    : "")
		    + new HtmlTag(HtmlDefinition.TAG_SPAN,
			    HtmlDefinition.CLASS_DEFINITION, definition)
			    .toString());
	    li.addValue((StringUtil.isNotEmpty(sampleset) ? HtmlDefinition.TWO_TAB
		    : "")
		    + new HtmlTag(HtmlDefinition.TAG_SPAN,
			    HtmlDefinition.CLASS_SAMPLEPHRASE, sampleset)
			    .toString());
	    li.addValue((StringUtil.isNotEmpty(synonym) ? HtmlDefinition.TWO_TAB
		    : "")
		    + new HtmlTag(HtmlDefinition.TAG_SPAN,
			    HtmlDefinition.CLASS_SYNONYMS, synonym).toString());
	    this.ulNoumTag.addValue(HtmlDefinition.ONE_TAB + li.toString()
		    + HtmlDefinition.NEW_LINE);
	} else if (pos.trim().equals("r")) {
	    createDefinitionAdv();
	    li.addValue((StringUtil.isNotEmpty(definition) ? HtmlDefinition.TWO_TAB
		    : "")
		    + new HtmlTag(HtmlDefinition.TAG_SPAN,
			    HtmlDefinition.CLASS_DEFINITION, definition)
			    .toString());
	    li.addValue((StringUtil.isNotEmpty(sampleset) ? HtmlDefinition.TWO_TAB
		    : "")
		    + new HtmlTag(HtmlDefinition.TAG_SPAN,
			    HtmlDefinition.CLASS_SAMPLEPHRASE, sampleset)
			    .toString());
	    li.addValue((StringUtil.isNotEmpty(synonym) ? HtmlDefinition.TWO_TAB
		    : "")
		    + new HtmlTag(HtmlDefinition.TAG_SPAN,
			    HtmlDefinition.CLASS_SYNONYMS, synonym).toString());
	    this.ulAdverdTag.addValue(HtmlDefinition.ONE_TAB + li.toString()
		    + HtmlDefinition.NEW_LINE);
	} else if (pos.trim().equals("v")) {
	    createDefinitionVer();
	    li.addValue((StringUtil.isNotEmpty(definition) ? HtmlDefinition.TWO_TAB
		    : "")
		    + new HtmlTag(HtmlDefinition.TAG_SPAN,
			    HtmlDefinition.CLASS_DEFINITION, definition)
			    .toString());
	    li.addValue((StringUtil.isNotEmpty(sampleset) ? HtmlDefinition.TWO_TAB
		    : "")
		    + new HtmlTag(HtmlDefinition.TAG_SPAN,
			    HtmlDefinition.CLASS_SAMPLEPHRASE, sampleset)
			    .toString());
	    li.addValue((StringUtil.isNotEmpty(synonym) ? HtmlDefinition.TWO_TAB
		    : "")
		    + new HtmlTag(HtmlDefinition.TAG_SPAN,
			    HtmlDefinition.CLASS_SYNONYMS, synonym).toString());
	    this.ulVerbTag.addValue(HtmlDefinition.ONE_TAB + li.toString()
		    + HtmlDefinition.NEW_LINE);
	} else if (pos.trim().equals("s")) {
	    createDefinitionAdj();
	    li.addValue((StringUtil.isNotEmpty(definition) ? HtmlDefinition.TWO_TAB
		    : "")
		    + new HtmlTag(HtmlDefinition.TAG_SPAN,
			    HtmlDefinition.CLASS_DEFINITION, definition)
			    .toString());
	    li.addValue((StringUtil.isNotEmpty(sampleset) ? HtmlDefinition.TWO_TAB
		    : "")
		    + new HtmlTag(HtmlDefinition.TAG_SPAN,
			    HtmlDefinition.CLASS_SAMPLEPHRASE, sampleset)
			    .toString());
	    li.addValue((StringUtil.isNotEmpty(synonym) ? HtmlDefinition.TWO_TAB
		    : "")
		    + new HtmlTag(HtmlDefinition.TAG_SPAN,
			    HtmlDefinition.CLASS_SYNONYMS, synonym).toString());
	    this.ulAdjectiveTag.addValue(HtmlDefinition.ONE_TAB + li.toString()
		    + HtmlDefinition.NEW_LINE);
	}
    }

    private void createDefinitionAdj() {
	if (this.ulAdjectiveTag != null) {
	    return;
	}
	this.ulAdjectiveTag = new HtmlTag(HtmlDefinition.TAG_UL, null, null);
	this.ulAdjectiveTag.addValue(HtmlDefinition.ONE_TAB
		+ (new HtmlTag(HtmlDefinition.TAG_SPAN,
			HtmlDefinition.CLASS_TYPEWORD,
			HtmlDefinition.TYPE_WORD_ADJECTIVE).toString()));
    }

    private void createDefinitionNom() {
	if (this.ulNoumTag != null) {
	    return;
	}
	this.ulNoumTag = new HtmlTag(HtmlDefinition.TAG_UL, null, null);
	this.ulNoumTag.addValue(HtmlDefinition.ONE_TAB
		+ (new HtmlTag(HtmlDefinition.TAG_SPAN,
			HtmlDefinition.CLASS_TYPEWORD,
			HtmlDefinition.TYPE_WORD_NOUN).toString()));
    }

    private void createDefinitionVer() {
	if (this.ulVerbTag != null) {
	    return;
	}
	this.ulVerbTag = new HtmlTag(HtmlDefinition.TAG_UL, null, null);
	this.ulVerbTag.addValue(HtmlDefinition.ONE_TAB
		+ (new HtmlTag(HtmlDefinition.TAG_SPAN,
			HtmlDefinition.CLASS_TYPEWORD,
			HtmlDefinition.TYPE_WORD_VERB).toString()));
    }

    private void createDefinitionAdv() {
	if (this.ulAdverdTag != null) {
	    return;
	}
	this.ulAdverdTag = new HtmlTag(HtmlDefinition.TAG_UL, null, null);
	this.ulAdverdTag.addValue(HtmlDefinition.ONE_TAB
		+ (new HtmlTag(HtmlDefinition.TAG_SPAN,
			HtmlDefinition.CLASS_TYPEWORD,
			HtmlDefinition.TYPE_WORD_ADVERB).toString()));
    }

    @Override
    public String toString() {
	return (this.ulNoumTag != null ? this.ulNoumTag.toString()
		+ HtmlDefinition.NEW_LINE : "")
		+ (this.ulVerbTag != null ? this.ulVerbTag.toString()
			+ HtmlDefinition.NEW_LINE : "")
		+ (this.ulAdverdTag != null ? this.ulAdverdTag.toString()
			+ HtmlDefinition.NEW_LINE : "")
		+ (this.ulAdjectiveTag != null ? this.ulAdjectiveTag.toString()
			+ HtmlDefinition.NEW_LINE : "");
    }
}