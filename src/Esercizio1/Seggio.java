package Esercizio1;
import java.util.*;
public class Seggio {
	
	
	int N = 100;
	private int a; // contatore candidato 1
	private int b; // contatore candidato 2
	private int c; // contatore candidato 3
	private int d; // contatore candidato 4
	private int quorum= 51;
	private int voto; // variabile in cui inserire il voto (numero intero da  1 a 4)
	private int TotVoti; // conatore voti totali
	Vector <Integer> v = new Vector<Integer>(); // vettore in cui inserire i voti
	Vector <elettore> l = new Vector<elettore>(); // vettore in cui inserire gli elettori che hanno già votato
	boolean libero = false; // flag per il seggio
	Random random = new Random(); // oggetto per creare voti random
	
	
	
	public synchronized void indici () {
		System.out.println("Nuove elezioni, iniziano le votazioni");
		libero = true; // quando iniziano le elezioni libera il seggio e sveglia tutti
		notifyAll();
	}
	
	public synchronized void eleggi () {
		while (libero==false || TotVoti<quorum) {
			if (libero==false) {
				System.out.println("Seggio occupato!");
			} else {
				System.out.println("Quorum non ancora raggiunto, elettori dovete continuare a votare!");
			}
			try {
				wait();
			}catch (InterruptedException e) {e.printStackTrace();}
		}
		libero = false; // quando si sveglia occupa il seggio e conta i voti
		for (Integer n : v) {
			if (n==1) {a++;}
			if (n==2) {b++;}
			if (n==3) {c++;}
			if (n==4) {d++;}
		}
		if (a>b && a>c && a>d) {
			System.out.println("Il nuovo sindaco è il candidato a con "+ a + " voti");
		}
		if (b>a && b>c && b>d) {
			System.out.println("Il nuovo sindaco è il candidato b con "+ b + " voti");
		}
		if (c>b && c>a && c>d) {
			System.out.println("Il nuovo sindaco è il candidato c con "+ c + " voti");
		}
		if (d>b && d>c && d>a) {
			System.out.println("Il nuovo sindaco è il candidato d con "+ d + " voti");
		}
		//dopo aver eletto il nuovo sindaco pulisce tutti i contatori e i vettori 
		a=0;
		b=0;
		c=0;
		d=0;
		TotVoti=0;
		v.clear();
		l.clear();
	}
	
	public synchronized void EntraVota () {
		elettore el = (elettore) Thread.currentThread();
		while (libero==false) {
			try {
				wait();
			}catch (InterruptedException e) {e.printStackTrace();}
		}
		libero=false; //quando il thread si sveglia occupa il seggio
		if (l.contains(el)) { // controllo se l'elettore ha già votato
			libero = true; //libera il seggio e sveglia tutti
			notifyAll();
		} else {
			voto = random.nextInt(4)+1; // creo il voto in maniera randomica
			v.add(voto); // aggiungo il voto al vettore dei voti
			System.out.println(el.getName()+"ha votato");
			l.add(el); // aggiungo l'elettore al vettore degli elettori che hanno già votato
			TotVoti++; // incremento il contatore dei voti
			libero = true; // libera il seggio e 
			notifyAll();  // sveglia tutti
		}
	}
}
