package lottery.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ObservableBean {

	protected PropertyChangeSupport changeSupport;

	public ObservableBean() {
		// TODO Auto-generated constructor stub
	}

	public synchronized void addPropertyChangeListener(
			PropertyChangeListener listener) {
		if (listener == null) {
			return;
		}
		if (changeSupport == null) {
			changeSupport = new PropertyChangeSupport(this);
		}
		changeSupport.addPropertyChangeListener(listener);
	}

	public synchronized void removePropertyChangeListener(
			PropertyChangeListener listener) {
		if (listener == null || changeSupport == null) {
			return;
		}
		changeSupport.removePropertyChangeListener(listener);
	}

	public synchronized void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		if (listener == null) {
			return;
		}
		if (changeSupport == null) {
			changeSupport = new PropertyChangeSupport(this);
		}
		changeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public synchronized void removePropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		if (listener == null || changeSupport == null) {
			return;
		}
		changeSupport.removePropertyChangeListener(propertyName, listener);
	}

	public synchronized void firePropertyChange(String propertyName,
			Object oldValue, Object newValue) {
		if (changeSupport == null) {
			return;
		}
		changeSupport.firePropertyChange(propertyName, oldValue, newValue);
	}

	public synchronized void firePropertyChange(PropertyChangeEvent event) {
		if (changeSupport == null) {
			return;
		}
		changeSupport.firePropertyChange(event);
	}

}
