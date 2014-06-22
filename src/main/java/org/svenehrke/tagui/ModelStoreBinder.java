package org.svenehrke.tagui;

import javafx.collections.ObservableList;
import org.opendolphin.core.ModelStoreEvent;
import org.opendolphin.core.PresentationModel;

import static org.opendolphinx.extension.javafxclient.JavaFXApplicationParameters.clientDolphin;

public class ModelStoreBinder {

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
