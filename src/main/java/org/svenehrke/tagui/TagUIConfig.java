package org.svenehrke.tagui;

import java.util.function.Consumer;

public class TagUIConfig {

	Consumer<String> addEntryHandler;

	public TagUIConfig(Consumer<String> addEntryHandler) {
		this.addEntryHandler = addEntryHandler;
	}
}
