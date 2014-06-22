package org.svenehrke.tagui;

import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.opendolphin.core.PresentationModel;
import org.opendolphin.core.client.ClientAttribute;
import org.tbee.javafx.scene.layout.MigPane;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.opendolphinx.extension.javafxclient.JavaFXApplicationParameters.clientDolphin;

public class MainApplication extends Application {

	private TagUIConfig tagUIConfig;
	private PMContext pmContext;

	@Override
	public void init() throws Exception {

		this.tagUIConfig = TagUIJavaFXApplicationParameter.tagUIConfig;
	}

	@Override
	public void stop() throws Exception {
	}

	@Override
	public void start(Stage stage) throws Exception {

		pmContext = new PMContext(clientDolphin).initialize();

		final MigPane pane = new MigPane(
			"wrap 4, inset 10, debug" // Layout Constraints
		);

		Scene scene = new Scene(pane, 500, 200, Color.DODGERBLUE);

		TableView<PresentationModel> itemTV = new TableView<>();
		itemTV.getColumns().addAll(itemTableColumn());

		itemTV.getItems().addAll(pmContext.itemPMs);

		final TextField descriptionTextField = new TextField("Item1");

		Button addButton = new Button("+");
		Button deleteButton = new Button("-");
		addButton.setOnAction(arg0 -> {
			String s = "It is now: " + new SimpleDateFormat("hh:mm:ss").format(new Date());
			tagUIConfig.addEntryHandler.accept(s);
		});

		TableView<TagAndValue> tv = new TableView<>();
		tv.getColumns().addAll(tagColumn(), valueColumn());
		tv.getItems().addAll(items());

		pane.add(itemTV, "grow, spany 2");
		pane.add(addButton, "grow");
		pane.add(descriptionTextField, "grow, wrap");

		pane.add(deleteButton, "skip 1, aligny top, grow");
		pane.add(tv, "wrap");

		stage.setTitle("Tag UI");
		stage.setScene(scene);
		stage.show();
	}

	private TableColumn<PresentationModel, String> itemTableColumn() {
		TableColumn<PresentationModel, String> result = new TableColumn<>();

		result.setCellValueFactory(param -> {
			PresentationModel pm = param.getValue();
			ClientAttribute clientAttribute = (ClientAttribute) pmContext.clientDolphin.getAt(pm.getId()).getAt(PMContext.ATT_DESCRIPTION);
			return new MyClientAttributeWrapper(clientAttribute);
		});
		return result;
	}

	private ObservableList<TagAndValue> items() {
		return FXCollections.observableArrayList(new TagAndValue("url", "http://www.svenehrke.de"));
	}

	private TableColumn valueColumn() {
		TableColumn<TagAndValue, String> result = new TableColumn<>("Value");
		result.setCellValueFactory(param -> param.getValue().value);
		return result;
	}

	private TableColumn tagColumn() {
		TableColumn<TagAndValue, String> result = new TableColumn<>("Tag");
		result.setCellValueFactory(param -> param.getValue().tag);
		return result;
	}
}