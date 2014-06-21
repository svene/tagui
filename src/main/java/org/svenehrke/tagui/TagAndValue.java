package org.svenehrke.tagui;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TagAndValue {
	StringProperty tag;
	StringProperty value;

	public TagAndValue(String tag, String value) {
		this.tag = new SimpleStringProperty(tag);
		this.value = new SimpleStringProperty(value);
	}

}
