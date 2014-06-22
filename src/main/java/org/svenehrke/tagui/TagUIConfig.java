package org.svenehrke.tagui;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.function.Consumer;

public class TagUIConfig {

	Consumer<String> addEntryHandler;
	ObservableList<Item> items = FXCollections.observableArrayList();
	ObservableList<StringProperty> descriptions;

	public TagUIConfig(Consumer<String> addEntryHandler) {
		this.addEntryHandler = addEntryHandler;

		Item it = new Item();
		it.tagAndValues.addAll(new TagAndValue("url", "http://www.svenehrke.de"));
		items.addAll(it);

	}
}
