import java.util.Random;
import java.util.Scanner;

public class Mastermind {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Compteur de coups
		int compt = 1;
		
		// Le jeu s'arrête si la variable vaut 4
		int exactDigit = 0;
		
		// Génère un nombre aléatoire entre 0000 et 9999
		Random r = new Random();
		int randomNumber = r.nextInt(9999);
		String realNumber = String.format("%04d", randomNumber);
		
		// Récupère la saisie de l'utilisateur
		Scanner sc = new Scanner(System.in);
		
		// Tableau où est stocké le nombre aléatoire
		int[] arrayRandom = new int[4];
		
		// Tableau où est stocké le nombre saisi par l'utilisateur
		int[] arrayUser = new int[4];
		
		// Tableau où est stocké le résultat à la fin de chaque essai
		String[] arrayFinal = new String[4];
		
		// Remplissage du tableauRandom avec le nombre random
		for (int i=0; i < arrayRandom.length; i++)
		{
		  arrayRandom[i] = Character.getNumericValue(realNumber.charAt(i));
		}
		
		System.out.println("Bienvenue dans le jeu du Mastermind !\nUn nombre aléatoire de 4 chiffres a été généré, vous avez 8 essais pour le trouver !\nBonne chance !");
		System.out.println("\nVoici le nombre aléatoire : " + realNumber);
		
		do {
			
			exactDigit = 0;
			
			System.out.println("\nEssai " + compt + ": ");
			String str = sc.nextLine();
			System.out.println("Vous avez saisi : " + str);
			
			// Vérification si la saisie est un nombre de 4 caractères
			if (str.matches("[a-zA-Z]+") || str.length() != 4) {
				
				System.out.println("Nombre incorrect !");
				continue;
				
			}else {
				
				// Remplissage du tableauUser avec la saisie de l'utilisateur
				for(int i = 0; i < 4; i++) {
					arrayUser[i] = Character.getNumericValue(str.charAt(i));
				}
				
				// On parcourt les 2 tableaux et on vérifie chaque entrée
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
				
				// Affichage du résultat final après chaque essai
				for(int k = 0; k < 4; k++) {
					System.out.print(arrayFinal[k]);
				}
				
				if (exactDigit == 4) {
					System.out.println("\nBravo ! Vous avez gagné en " + compt + " coups !");
					break;
				}else {
					// Réinitialisation du tableau final
					arrayFinal = new String[4];
				}
				
			}
			compt++;
			
		} while (compt < 9);
		
		if (compt >= 9 ) {
			System.out.println("\nVous avez perdu !");
		}
	}

}
