package Esercizio2;
import java.io.*;
import java.net.*;

class ServerGioco extends Thread {
	// dichiaro e inizializzo la socket, il reader e il writer
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private boolean indovinato = false; // flag 
	private int NumSegreto; // variabile in cui inserire il numero segreto
	public ServerGioco (Socket s) throws IOException {
		socket = s;
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		OutputStreamWriter ow = new OutputStreamWriter(socket.getOutputStream());
		out = new PrintWriter(new BufferedWriter(ow), true);
		start();
	}
	
	public void run() {
		NumSegreto = (int)(Math.random()*100); // creo il numero segreto
		try {
			while(!indovinato) {
				int num = Integer.parseInt(in.readLine()); // leggo ciò che mi manda il client, lo converto ad intero
				if (num<NumSegreto) {
					out.println("A");
				} else if (num>NumSegreto) {
					out.println("B");
				} else {
					out.println("OK");
					indovinato = true; // il numero è stato indovinato quindi si esce dal ciclo
				}
			}
		}  catch (IOException e) {
			System.out.println("Errore di connessione");
		}
		// si sconnette il client e si chiudono il reader e il writer
		try {
			out.close();
			in.close();
			socket.close();
		}catch (IOException e) {
			System.out.println("Errore di connessione");
		}
	
		
	}
}


public class ServerGiocoMain {
	static final int PORT = 3345;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket serverSock = new ServerSocket(PORT);
		System.out.println("Server "+ serverSock + " inizializzato");
		// inizia un ciclo infinito perchè vogliamo che il server sia sempre attivo
		while(true) {
			Socket clientSock = serverSock.accept();
			System.out.println(" ");
			try {
				new ServerGioco(clientSock);
			} catch(IOException e) {
				System.out.println("Errore di connessione");
			}
			serverSock.close();
		}

	}

}
