package Esercizio2;
import java.io.*;
import java.net.*;

public class ClientGioco {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader in;
		PrintWriter out;
		boolean indovinato = false;
		
		try {
			Socket socket = new Socket("127.0.0.1", 3345); //gli passo la porta dove risiede il processo server
			System.out.println("Client inizializzato!");
			System.out.println("Client socket: " + socket);
			// inizializzo gli stream di input e di output
			InputStreamReader ir = new InputStreamReader(socket.getInputStream());
			in = new BufferedReader(ir);
			OutputStreamWriter ow = new OutputStreamWriter(socket.getOutputStream());
			out = new PrintWriter(new BufferedWriter(ow), true);
			
			System.out.println("Inizia il gioco: ...provo ad indovinare...");
			// inizia il gioco che non si ferma fino a che non viene indovinato il numero
			while (!indovinato) {
				int num = (int)(Math.random()*100); // cerco di indovianre in maniera randomica
				out.println(num); // mando il numero al server
				//ora dobbiamo leggere la risposta del server che ci viene data sottoforma di stringa
				String risposta = in.readLine();
				if (risposta.equals("A")) {
					System.out.println("MMM...il numero che cerchiamo è più grande");
				}
				if (risposta.equals("B")) {
					System.out.println("MMM...il numero che cerchiamo è più piccolo");
				}
				if (risposta.equals("OK")) {
					System.out.println("Bravo hai indovinato, il numero era " + num);
					indovinato = true;
				}
			}
			//chiudo input e output e la socket
			in.close();
			out.close();
			socket.close();
			
		} catch (IOException e) {
			System.out.println ("Errore nel socket " + e);
		}
	}
}
