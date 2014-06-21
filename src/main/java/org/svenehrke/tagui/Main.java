package org.svenehrke.tagui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.tbee.javafx.scene.layout.MigPane;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void init() throws Exception {
	}

	@Override
	public void stop() throws Exception {
	}

	@Override
	public void start(Stage stage) throws Exception {
		final MigPane pane = new MigPane(
			"wrap 4, inset 10, debug" // Layout Constraints
		);

		Scene scene = new Scene(pane, 500, 200, Color.DODGERBLUE);

		ListView<String> listView = new ListView<>();
		listView.getItems().addAll("Item1", "Item2", "Item3", "Item4", "Item5", "Item6", "Item7");

		final TextField descriptionTextField = new TextField("Item1");

		final Text text = new Text(140, 120, "");
		Button addButton = new Button("+");
		Button deleteButton = new Button("-");
		addButton.setOnAction(arg0 -> text.setText("It is now: " + new SimpleDateFormat("hh:mm:ss").format(new Date())));

		TableView<TagAndValue> tv = new TableView<>();
		tv.getColumns().addAll(tagColumn(), valueColumn());
		tv.getItems().addAll(items());

		pane.add(listView, "grow, spany 2");
		pane.add(addButton, "grow");
		pane.add(descriptionTextField, "wrap");

		pane.add(deleteButton, "skip 1, aligny top, grow");
		pane.add(tv, "wrap");

		stage.setTitle("Tag UI");
		stage.setScene(scene);
		stage.show();
	}

	private ObservableList<TagAndValue> items() {
		return FXCollections.observableArrayList(new TagAndValue("url", "http://www.svenehrke.de"));
	}

	private TableColumn valueColumn() {
		TableColumn<TagAndValue, String> result = new TableColumn<>("Value");
		result.setCellValueFactory(param -> param.getValue().valueProperty());
		return result;
	}

	private TableColumn tagColumn() {
		TableColumn<TagAndValue, String> result = new TableColumn<>("Tag");
		result.setCellValueFactory(param -> param.getValue().tagProperty());
		return result;
	}
}