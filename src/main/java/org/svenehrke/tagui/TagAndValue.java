package org.svenehrke.tagui;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TagAndValue {
	private final StringProperty tag;
	private final StringProperty value;

	public TagAndValue(String tag, String value) {
		this.tag = new SimpleStringProperty(tag);
		this.value = new SimpleStringProperty(value);
	}

	public String getTag() {
		return tag.get();
	}

	public StringProperty tagProperty() {
		return tag;
	}

	public String getValue() {
		return value.get();
	}

	public StringProperty valueProperty() {
		return value;
	}
}
