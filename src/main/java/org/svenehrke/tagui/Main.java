package org.svenehrke.tagui;

import org.opendolphinx.extension.InMemoryJavaFXDolphinStarter;

import java.util.function.Consumer;

public class Main {

	public static void main(String[] args) {

		TagUIJavaFXApplicationParameter.tagUIConfig = new TagUIConfig(
			readAddEntryHandler()
		);

		new InMemoryJavaFXDolphinStarter().start(MainApplication.class);
	}

	private static Consumer<String> readAddEntryHandler() {
		return s -> System.out.println("addHandler: received: " + s);
	}


}