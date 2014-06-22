package org.svenehrke.tagui;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Item {

	ObservableList<TagAndValue> tagAndValues = FXCollections.observableArrayList();
	StringProperty description = new SimpleStringProperty();

	public Item() {

		tagAndValues.addListener((Observable observable) -> {
			String s = tagAndValues.stream().filter( it -> it.tag.getValue().equals("description")).findFirst().orElse(new TagAndValue("-", "-")).tag.getValue();
			description.setValue(s);
		});
	}
}
