package org.svenehrke.tagui;

import javafx.beans.property.SimpleObjectProperty;
import org.opendolphin.core.client.ClientAttribute;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.ref.WeakReference;

/**
 * <p>JavaFX property wrapper around an attribute.</p>
 */
public class MyClientAttributeWrapper<T> extends SimpleObjectProperty<T> {
	private final WeakReference<ClientAttribute> attributeRef;
	private final String name;

	public MyClientAttributeWrapper(ClientAttribute attribute) {
		this.attributeRef = new WeakReference<ClientAttribute>(attribute);
		// we cache the attribute's propertyName as the property's name
		// because the value does not change and we want to avoid
		// dealing with null values from WR
		this.name = attribute.getPropertyName();
		attribute.addPropertyChangeListener("value", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
				fireValueChangedEvent();
			}
		});
		// the dirtyness may also change and shall call a re-render as the consumer may rely on that
		attribute.addPropertyChangeListener("dirty", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
				fireValueChangedEvent();
			}
		});
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void set(T value) {
		ClientAttribute attribute = attributeRef.get();
		if (attribute != null) attribute.setValue(value);
	}

	@Override
	public T get() {
		ClientAttribute attribute = attributeRef.get();
		return attribute != null ? (T) attribute.getValue() : null;
	}
}
