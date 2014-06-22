package org.svenehrke.tagui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.opendolphin.core.PresentationModel;
import org.opendolphin.core.client.ClientAttribute;
import org.opendolphin.core.client.ClientDolphin;
import org.opendolphin.core.client.ClientPresentationModel;

import java.util.Arrays;

public class PMContext {

	public static final String PM_TYPE_ITEM = "item";
	public static final String ATT_DESCRIPTION = "description";
	ObservableList<PresentationModel> itemPMs = FXCollections.observableArrayList();
	private ClientDolphin clientDolphin;

	public PMContext(ClientDolphin clientDolphin) {
		this.clientDolphin = clientDolphin;
	}

	public PMContext initialize() {

		new ModelStoreBinder(clientDolphin).bind(itemPMs, PM_TYPE_ITEM);

		// Construct client-side-only presentation model:
		PresentationModel pm = newItemPM("item_1");
		pm.getAt(ATT_DESCRIPTION).setValue("desc 1");

		pm = newItemPM("item_2");
		pm.getAt(ATT_DESCRIPTION).setValue("desc 2");

		return this;
	}

	private ClientPresentationModel newItemPM(String pmId) {
		ClientPresentationModel pm1 = new ClientPresentationModel(pmId, Arrays.asList(new ClientAttribute(ATT_DESCRIPTION)));
		pm1.setPresentationModelType(PM_TYPE_ITEM);
		pm1.setClientSideOnly(true);
		clientDolphin.getModelStore().add(pm1);
		return pm1;
	}
}
