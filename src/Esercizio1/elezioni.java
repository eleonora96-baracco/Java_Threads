package Esercizio1;

public class elezioni {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Seggio seggio = new Seggio();
		prefetto p = new prefetto("Prefetto Sergio", seggio);
		for (int i=1; i<=100; i++) {
			elettore el = new elettore("Elettore " + i, seggio);
			el.start();
		}
		
		

	}

}
