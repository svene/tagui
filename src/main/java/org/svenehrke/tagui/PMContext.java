package org.svenehrke.tagui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.opendolphin.core.PresentationModel;
import org.opendolphin.core.client.ClientAttribute;
import org.opendolphin.core.client.ClientPresentationModel;

import java.util.Arrays;

import static org.opendolphinx.extension.javafxclient.JavaFXApplicationParameters.clientDolphin;

public class PMContext {

	public static final String PM_TYPE_ITEM = "item";
	public static final String ATT_DESCRIPTION = "description";
	ObservableList<PresentationModel> itemPMs;

	public void initialize() {

		ModelStoreBinder modelStoreBinder = new ModelStoreBinder();
		itemPMs = FXCollections.observableArrayList();

		modelStoreBinder.bind(itemPMs, PM_TYPE_ITEM);

		// Construct client-side-only presentation model:
		PresentationModel pm = newItemPM("item_1");
		pm.getAt(ATT_DESCRIPTION).setValue("desc 1");

		pm = newItemPM("item_2");
		pm.getAt(ATT_DESCRIPTION).setValue("desc 2");
	}

	private ClientPresentationModel newItemPM(String pmId) {
		ClientPresentationModel pm1 = new ClientPresentationModel(pmId, Arrays.asList(new ClientAttribute(ATT_DESCRIPTION)));
		pm1.setPresentationModelType(PM_TYPE_ITEM);
		pm1.setClientSideOnly(true);
		clientDolphin.getModelStore().add(pm1);
		return pm1;
	}
}