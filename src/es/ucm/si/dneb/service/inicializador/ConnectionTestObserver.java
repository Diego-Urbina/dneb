package es.ucm.si.dneb.service.inicializador;

import java.util.Observable;
import java.util.concurrent.Semaphore;

public class ConnectionTestObserver extends Observable{
	
	
	private volatile boolean connected=false;
	
	
	
	public void setConnected(boolean connected) {
		
		this.connected = connected;
		this.setChanged();
		this.notifyObservers();
	}

	public boolean isConnected() {
		return connected;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (this.connected ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConnectionTestObserver other = (ConnectionTestObserver) obj;
		if (this.connected != other.connected)
			return false;
		return true;
	}
	
	
}
