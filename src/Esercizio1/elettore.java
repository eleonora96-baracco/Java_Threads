package Esercizio1;

public class elettore extends Thread {
	private Seggio seggio; // oggetto conteso
	
	public elettore(String nome, Seggio seggio) {
		super(nome); // ereditato dalla classe thread 
		this.seggio= seggio;
		//start();
	}
	public synchronized void run() {
		while (true) {
			synchronized(seggio) {
				seggio.EntraVota();
			}
		}
	}
}
