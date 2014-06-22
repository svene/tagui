package org.svenehrke.tagui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.fxmisc.easybind.EasyBind;
import org.opendolphin.core.PresentationModel;
import org.opendolphin.core.client.ClientAttribute;
import org.opendolphin.core.client.ClientDolphin;
import org.opendolphin.core.client.ClientPresentationModel;

import java.util.Arrays;

public class PMContext {

	public static final String PM_TYPE_ITEM = "item";
	public static final String ATT_DESCRIPTION = "description";
	ClientDolphin clientDolphin;

	ObservableList<PresentationModel> itemPMs = FXCollections.observableArrayList();
	ObservableList<String> descriptions;

	public PMContext(ClientDolphin clientDolphin) {
		this.clientDolphin = clientDolphin;
	}

	public PMContext initialize() {

		new ModelStoreBinder(clientDolphin).bind(itemPMs, PM_TYPE_ITEM);
		descriptions = EasyBind.map(itemPMs, pm -> (String) (pm.getAt(PMContext.ATT_DESCRIPTION).getValue()));

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
