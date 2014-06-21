package org.svenehrke.tagui;

import javafx.application.Application;

public class Main {

	public static void main(String[] args) {

		TagUIJavaFXApplicationParameter.tagUIConfig = new TagUIConfig(
			s -> System.out.println("addHandler: received: " + s)
		);

		Application.launch(MainApplication.class, args);
	}


}