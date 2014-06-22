package org.svenehrke.tagui;

import javafx.collections.ObservableList;
import org.opendolphin.core.ModelStoreEvent;
import org.opendolphin.core.PresentationModel;
import org.opendolphin.core.client.ClientDolphin;

public class ModelStoreBinder {

	private final ClientDolphin clientDolphin;

	public ModelStoreBinder(ClientDolphin clientDolphin) {
		this.clientDolphin = clientDolphin;
	}

	public void bind(ObservableList<PresentationModel> pms, String pmType) {

		clientDolphin.getModelStore().addModelStoreListener(pmType, event -> {
			if (ModelStoreEvent.Type.ADDED.equals(event.getType())) {
				pms.addAll(event.getPresentationModel());
			} else if (ModelStoreEvent.Type.REMOVED.equals(event.getType())) {
				pms.removeAll(event.getPresentationModel());
			}
		});

	}
}
