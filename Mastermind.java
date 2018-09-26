import java.util.Random;
import java.util.Scanner;

public class Mastermind {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int compt = 1;
		int exactDigit = 0;
		
		Random r = new Random();
		int randomNumber = r.nextInt(9999);
		String realNumber = String.format("%04d", randomNumber);
		Scanner sc = new Scanner(System.in);
		int[] arrayRandom = new int[4];
		String[] arrayFinal = new String[4];
		
		for (int i=0; i<arrayRandom.length; i++)
		{
		  arrayRandom[i] = Character.getNumericValue(realNumber.charAt(i));
		}
		
		System.out.println("Bienvenue dans le jeu du Mastermind !\nUn nombre aléatoire de 4 chiffres a été généré, vous avez 8 essais pour le trouver !\nBonne chance !");
		System.out.println("\nVoici le nombre aléatoire : " + realNumber);
		
		do {
			
			exactDigit = 0;
			int[] arrayUser = new int[4];
			
			System.out.println("\nEssai " + compt + ": ");
			String str = sc.nextLine();
			System.out.println("Vous avez saisi : " + str);
			
			if (str.matches("[a-zA-Z]+") || str.length() != 4) {
				
				System.out.println("Nombre incorrect !");
				continue;
				
			}else {
				
				for(int i = 0; i < 4; i++) {
					arrayUser[i] = Character.getNumericValue(str.charAt(i));
				}
				
				for(int i = 0; i < 4; i++) {
					for(int j = 0; j < 4; j++) {
						
						if (arrayUser[i] == arrayRandom[j]) {
							if (i == j) {
								exactDigit++;
								arrayFinal[i] = " + ";
								break;
							}else {
								arrayFinal[i] = " 1 ";
								break;
							}
						}else {
							arrayFinal[i] = " - ";
						}
						
					}
				}
				
				for(int k = 0; k < 4; k++) {
					System.out.print(arrayFinal[k]);
				}
				
				if (exactDigit == 4) {
					System.out.println("\nBravo ! Vous avez gagné en " + compt + " coups !");
					break;
				}
				
			}
			compt++;
			
		} while (compt < 9);
	}

}
