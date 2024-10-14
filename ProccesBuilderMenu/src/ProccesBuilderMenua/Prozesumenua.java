package ProccesBuilderMenua;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

// TODO: Auto-generated Javadoc
/**
 * The Class Prozesumenua.
 */
public class Prozesumenua {

	/** The Constant CALCULADORA. */
	//calc da kalkulagailuaren prozesua eta notepad ohar blokarena
	private static final String CALCULADORA = "calc";

	/** The Constant NOTEPAD. */
	private static final String NOTEPAD = "notepad";

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		menu();
	}

	/**
	 * 
	 * Menu bat erakusten du, erabiltzaileak prozesuak sortzeko aukera bat hauta dezan
	 * edo aplikaziotik irten.

	 */
	private static void menu() {
		Scanner scanner = new Scanner(System.in);
		int opcion;

		do {
			System.out.println("\nAukerak:");
			System.out.println("1. Kalkulagailuaren prozesu seme bat sortu");
			System.out.println("2. Ohar noten prozesu seme bat sortu");
			System.out.println("3. Sortu prozesu sem bat Java aplikazio batena");
			System.out.println("4. Irten");
			System.out.print("Aukeratu: ");

			while (!scanner.hasNextInt()) {
				System.out.println("Sartu zenbaki bat, mesedez.");
				scanner.next();
			}

			opcion = scanner.nextInt();
			scanner.nextLine(); 

			switch (opcion) {
			case 1:
				sortuKalkulagailua();
				break;

			case 2:
				sortuNotepad();
				break;

			case 3:
				sortuJarProzesua(scanner);
				break;

			case 4:
				System.out.println("Irteten...");
				break;

			default:
				System.out.println("Aukera desegokia, saiatu berriro.");
				break;
			}

		} while (opcion != 4);

		scanner.close();
	}

	/**
	 * Kalkulagailua aplikaziorako prozesu bat sortzen du.
	 */
	private static void sortuKalkulagailua() {
		try {
			SortuProzesua(CALCULADORA);
			System.out.println("Kalkulagailua abiarazi da.");
		} catch (IOException e) {
			System.out.println("Errorea kalkulagailua irekitzean: " + e.getMessage());
		}
	}

	/**
	 * 
	 *	Notepad aplikaziorako prozesu bat sortzen du.
	 */
	private static void sortuNotepad() {
		try {
			SortuProzesua(NOTEPAD);
			System.out.println("Ohar bloka abiarazi da.");
		} catch (IOException e) {
			System.out.println("Errorea Ohar Bloka irekitzerakoan: " + e.getMessage());
		}
	}

	/**
	 * Java aplikazio bat exekutatzeko prozesu bat sortzen du JAR fitxategi zehatz batetik.
	 *
	 * @param scanner, erabiltzaileen sarrerak irakurtzeko erabiltzen den eskanerra.
	 */
	private static void sortuJarProzesua(Scanner scanner) {
		try {
			System.out.print("Idatzi JAR fitxategiaren izena edota ruta osoa: ");
			String rutaJar = scanner.nextLine();

			File jarFile = new File(rutaJar);
			if (!jarFile.exists()) {
				System.out.println("Errorea: jar fitxategia ez da aurkitu. Ziurtatu bidea zuzena dela.");
			} else {
				System.out.println("Jar fitxategia aurkitu da: " + rutaJar);

				//CMD ireki java -jar egiteko
				ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "java -jar \"" + rutaJar + "\"");
				pb.start();
				System.out.println("JAR prozesua abiarazi da.");
			}
		} catch (IOException e) {
			System.out.println("Errorea jar fitxategia exekutatzean: " + e.getMessage());
		}
	}

	/**
	 * Prozesu berri bat sortzen du zehaztutako agindua erabiliz.
	 *
	 * @param comando, guk sartutako prozesua
	 * @throws IOException 
	 */
	private static void SortuProzesua(String... comando) throws IOException {
		ProcessBuilder pb = new ProcessBuilder(comando);
		pb.start();
	}
}
