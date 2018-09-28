import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

/**
 * The Class Mastermind.
 */
public class Mastermind {

	/** Temps au lancement du jeu. */
	private long secsEarly = 0;
	
	/** Temps à la fin du jeu. */
	private long secsEnd = 0;
	
	/** Nom d'emplacement des fichiers. */
	private String savestr = "history.txt"; 
	
	/** Fichier d'historique des parties. */
	private File f = new File(savestr);
	
	/** Ecriture dans le fichier. */
	private PrintWriter out = null;
	
	/** Initialisation du scanner. */
	Scanner sc = new Scanner(System.in);
	
	/** The array user. */
	int[] arrayUser = new int[4];
	
	/** The array random. */
	int[] arrayRandom = new int[4];
	
	/** The array final. */
	String[] arrayFinal = new String[4];
	
	/** The exact digit. 
	 * Le jeu s'arrête si la variable vaut 4.*/
	int exactDigit = 0;
	
	/** Compteur d'essais. */
	int compt = 1;
	
	/**
	 * Instantiates a new mastermind.
	 */
	public Mastermind() {
		
		play();
		
	}
	
	/**
	 * Play the game.
	 * Check if file exist,
	 * Generate random number,
	 * Get user number
	 */
	private void play() {
		
		try {
			checkIfFileExist();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		generateRandom();
		getNumberUser();
		
	}
	
	/**
	 * Generate random number between 0000 et 9999.
	 */
	private void generateRandom() {
		
		// Timer au début du jeu
		this.secsEarly = (new Date().getTime())/1000;
		
		// Initialisation du compteur à 1
		this.compt = 1;
		
		// Initialisation du tableau de random numbers
		this.arrayRandom = new int[4];
		
		Random r = new Random();
		int randomNumber = r.nextInt(10000);
		String realNumber = String.format("%04d", randomNumber);
		
		System.out.println("Le nombre à trouver est " + realNumber);
		
		//
		this.out.println("----------------------------------------------------");
	    this.out.println("Le nombre à trouver est: " + realNumber);
		
		// Remplissage du tableauRandom avec le nombre random
		for (int i=0; i < arrayRandom.length; i++)
		{
			arrayRandom[i] = Character.getNumericValue(realNumber.charAt(i));
		}
		
	}
	
	/**
	 * Gets the number user.
	 *
	 * @return the number user
	 */
	private String getNumberUser() {

		// Tableau où est stocké le nombre saisi par l'utilisateur
		this.arrayUser = new int[4];
		
		System.out.println("\nEssai " + this.compt + ": ");
		String str = sc.nextLine();
		System.out.println("Vous avez saisi : " + str);
		
		this.out.println("Essai " + compt + ": " + str);
		
		checkNumberUser(str);
		
		return str;
		
	}
	
	/**
	 * Check number user.
	 *
	 * @param userNumber the user number
	 */
	private void checkNumberUser(String userNumber) {
		
		// Vérification si la saisie est un nombre de 4 caractères
		if (userNumber.matches("[a-zA-Z]+") || userNumber.length() != 4) {
			
			System.out.println("Nombre incorrect !");
			getNumberUser();
			
		}else {
			
			// Remplissage du tableauUser avec la saisie de l'utilisateur
			for(int i = 0; i < 4; i++) {
				this.arrayUser[i] = Character.getNumericValue(userNumber.charAt(i));
			}
			
			checkFinalResult();
			
		}
		
	}
	
	/**
	 * Check final result.
	 */
	private void checkFinalResult() {
	
		this.arrayFinal = new String[4];
		
		this.exactDigit = 0;
		
		// On parcourt les 2 tableaux et on vérifie chaque entrée
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				
				if (this.arrayUser[i] == this.arrayRandom[j]) {
					if (i == j) {
						this.exactDigit++;
						this.arrayFinal[i] = " + ";
						
						break;
					}else {
						if (this.arrayFinal[i] != " + ") {
							this.arrayFinal[i] = " 1 ";
						}
					}
				}else {
					if (this.arrayFinal[i] != " + " && this.arrayFinal[i] != " 1 ") {
						this.arrayFinal[i] = " - ";
					}
				}
				
			}
		}
		
		displayResult();
	
	}
	
	/**
	 * Display result.
	 */
	private void displayResult() {
		
		// Affichage du résultat final après chaque essai
		for(int k = 0; k < 4; k++) {
			System.out.print(this.arrayFinal[k]);
		}
		
		if (this.exactDigit == 4) {
			this.secsEnd = (new Date().getTime())/1000;
			System.out.println("\nBravo ! Vous avez gagné en " + this.compt + " coups !");
			// Soustraction entre secsEnd et secsEarly pour avoir le temps en secondes du jeu
			System.out.println("Et vous avez mis " + (this.secsEnd - this.secsEarly) + " secondes à trouver!");
			
			this.out.println("Gagné en " + this.compt + " coups et " + (this.secsEnd - this.secsEarly) + " secondes");
			this.out.close();
			askReplay();
		}else {
			this.compt++;
			if (this.compt == 9) {
				System.out.println("Vous avez perdu !");
				
				this.out.println("Perdu");
				this.out.close();
				askReplay();
			}else {
				getNumberUser();
			}
		}
		
	}
	
	/**
	 * Ask replay.
	 */
	private void askReplay() {
		
		System.out.println("Voulez-vous rejouer? (Y/N)");
		String rep = sc.nextLine();
		
		if (rep.equals("Y") || rep.equals("y")) {
			play();
		}else {
			System.out.println("Fin du jeu !");
		}
		
	}
	
	/**
	 * Check if file exist.
	 * If yes, use this file and print directly after the existant.
	 * If not, create new file history.txt.
	 *
	 * @throws FileNotFoundException the file not found exception
	 */
	private void checkIfFileExist() throws FileNotFoundException {
		
		if ( f.exists() && !f.isDirectory() ) {
		    this.out = new PrintWriter(new FileOutputStream(new File(savestr), true));
		}
		else {
		    this.out = new PrintWriter(savestr);
		}
		
	}
	
}