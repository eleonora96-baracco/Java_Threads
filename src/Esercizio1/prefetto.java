package Esercizio1;

public class prefetto extends Thread {
	private Seggio seggio;
	public prefetto (String nome, Seggio seggio) {
		super(nome);
		this.seggio = seggio;
		start();
	}
	public synchronized void run() {
		while(true) {
			seggio.indici();
	    	seggio.eleggi();
		// aspetta prima di indirre nuovamente le elezioni
			try { sleep((int)(Math.random() * 400));
			} catch (InterruptedException e) {e.printStackTrace();}
		}
	}
}
