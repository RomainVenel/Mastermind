import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Mastermind {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		boolean replay = true;
		long secsEarly = 0;
		long secsEnd = 0;
		String savestr = "filename.txt"; 
		File f = new File(savestr);

		PrintWriter out = null;
		if ( f.exists() && !f.isDirectory() ) {
		    out = new PrintWriter(new FileOutputStream(new File(savestr), true));
		}
		else {
		    out = new PrintWriter(savestr);
		}
			
		do {
			
			// Timer au d�but du jeu
			secsEarly = (new Date().getTime())/1000;
			
			// Compteur de coups
			int compt = 1;
		
			// Le jeu s'arr�te si la variable vaut 4
			int exactDigit = 0;
			
			// G�n�re un nombre al�atoire entre 0000 et 9999
			Random r = new Random();
			int randomNumber = r.nextInt(10000);
			String realNumber = String.format("%04d", randomNumber);
			
			// append the sequence
			out.println("----------------------------------------------------");
		    out.println("Le nombre � trouver est: " + realNumber);
			
			// R�cup�re la saisie de l'utilisateur
			Scanner sc = new Scanner(System.in);
			
			// Tableau o� est stock� le nombre al�atoire
			int[] arrayRandom = new int[4];
		
			// Tableau o� est stock� le nombre saisi par l'utilisateur
			int[] arrayUser = new int[4];
			
			// Tableau o� est stock� le r�sultat � la fin de chaque essai
			String[] arrayFinal = new String[4];
			
			// Remplissage du tableauRandom avec le nombre random
			for (int i=0; i < arrayRandom.length; i++)
			{
			  arrayRandom[i] = Character.getNumericValue(realNumber.charAt(i));
			}
			
			System.out.println("Bienvenue dans le jeu du Mastermind !\nUn nombre al�atoire de 4 chiffres a �t� g�n�r�, vous avez 8 essais pour le trouver !\nBonne chance !");
			System.out.println("\nVoici le nombre al�atoire : " + realNumber);
			
			do {
			
				exactDigit = 0;
				
				System.out.println("\nEssai " + compt + ": ");
				String str = sc.nextLine();
				System.out.println("Vous avez saisi : " + str);
				
				// append the sequence
			     out.println("Essai " + compt + ": " + str);

				// V�rification si la saisie est un nombre de 4 caract�res
				if (str.matches("[a-zA-Z]+") || str.length() != 4) {
					
					System.out.println("Nombre incorrect !");
					continue;
					
				}else {
					
					// Remplissage du tableauUser avec la saisie de l'utilisateur
					for(int i = 0; i < 4; i++) {
						arrayUser[i] = Character.getNumericValue(str.charAt(i));
					}
					
					// On parcourt les 2 tableaux et on v�rifie chaque entr�e
					for(int i = 0; i < 4; i++) {
						for(int j = 0; j < 4; j++) {
							
							if (arrayUser[i] == arrayRandom[j]) {
								if (i == j) {
									exactDigit++;
									arrayFinal[i] = " + ";
									
									break;
								}else {
									if (arrayFinal[i] != " + ") {
										arrayFinal[i] = " 1 ";
									}
								}
							}else {
								if (arrayFinal[i] != " + " && arrayFinal[i] != " 1 ") {
									arrayFinal[i] = " - ";
								}
							}
							
						}
					}
					
					// Affichage du r�sultat final apr�s chaque essai
					for(int k = 0; k < 4; k++) {
						System.out.print(arrayFinal[k]);
					}
					
					if (exactDigit == 4) {
						System.out.println("\nBravo ! Vous avez gagn� en " + compt + " coups !");
						// Timer � la fin du jeu
						secsEnd = (new Date().getTime())/1000;
						System.out.println("Vous avez mis " + (secsEnd - secsEarly) + " secondes !");
						out.println("Gagn� en " + compt + " coups et " + (secsEnd - secsEarly) + " secondes");
						out.close();
						break;
					}else {
						// R�initialisation du tableau final
						arrayFinal = new String[4];
					}
					
				}
				compt++;
				
				
				
			} while (compt < 9);
		
			if (compt >= 9 ) {
				System.out.println("\nVous avez perdu !");
				// Timer � la fin du jeu
				secsEnd = (new Date().getTime())/1000;
				System.out.println("\nVous avez mis " + (secsEnd - secsEarly) + " secondes !");
				out.println("Perdu !");
				out.close();
			}
			
			System.out.println("Voulez-vous rejouer ? 1 pour oui, 2 pour non");
			int rep = sc.nextInt();
			
			if (rep == 1) {
				replay = true;
			}else {
				replay = false;
				System.out.println("Fin du jeu !");
			}
			
		}while(replay == true);
		
	}

}
