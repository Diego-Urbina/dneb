package es.ucm.si.dneb.service.inicializador;

import java.util.concurrent.Semaphore;

public class ThreadCon extends Thread{
	
	private ConnectionTestObserver contestob;
	private Semaphore sem= new Semaphore(0);
	private ServicioInicializador servIni;
	
	public ThreadCon(ConnectionTestObserver contestob,ServicioInicializador servIni){
		this.contestob=contestob;
		this.servIni=servIni;
	}
	
	@Override
	public void run(){
		
		while(true){
			contestob.setConnected(this.servIni.testConnection());
			
			contestob.notifyObservers();
			try {
				synchronized(sem){
					sem.wait(2000);
				}
			} catch (InterruptedException exc) {
				
				exc.printStackTrace();
			}
		}
	}

}
