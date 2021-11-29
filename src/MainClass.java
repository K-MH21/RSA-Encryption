import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainClass {
	
	final static ArrayList<String> Alphabet_Values = new ArrayList<String>();
	static String alphabet;
	
	public static void main(String[] args) {
		//Preparing Alphabet_Values and alphabet
		{
			String UC = "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z".replaceAll(" ", "");
			String LC = "a b c d e f g h i j k l m n o p q r s t u v w x y z".replaceAll(" ", "");
			String N = "0123456789";
			String special = ".?!,;:-()[]{}'\" \n";
			alphabet = UC + LC + N + special;
			for (Integer i = 0; i < alphabet.length(); i++) {
				if (i < 10)	Alphabet_Values.add("0" + i.toString());
				else		Alphabet_Values.add(i.toString());
			}
		}
		//Main menu
        while (true) {
            System.out.println("What do you want to do?"
                    + "\n(1) Encrypt a message"
                    + "\n(2) Decreypt a massage"
                    + "\n(3) Exit the System");
            switch (new Scanner(System.in).nextInt()) {
            case 1: encrypt();				break;
            case 2: Decrypt();				break;
            case 3: System.exit(0);
            }
        }
	}
	
	//Self-expalantory
	private static long MOD(long a, long m) {
		if (!(m > 0)) throw new InputMismatchException("Wrong m value");
		return ((a % m) + m) % m;
	}
	
	/*
	 * Encryption method, Will take a .txt file as an input
	 * and will produce a .rsa file as an output.
	 */
	private static void encrypt() {
	//	Scanner read;
	//	try {read = new Scanner(new File("message.txt"));}
	//	catch (FileNotFoundException e) {System.out.println("File is missing"); return;}
		long e = 13; //read.nextLong(); 	TODO Throw an exception if e or n is wrong
		long n = 2537; //read.nextLong();  TODO Need to check which value comes first in the message.txt
		Integer blockLength = 0;
		while (n > blockLength) {
			int power = 0;
			blockLength = (int) (blockLength + (alphabet.length() - 1) * Math.pow(10, power));
			power += 2;
		}
		blockLength = blockLength.toString().length()/2;
		String Emessage = "STOP";
		String dmessage = "";
		String temp = "";
	//	while (read.hasNextLine()) message += read.nextLine();
		for (int i = 0, j = 1; i<Emessage.length(); i++, j++) {
			int s = 0;
			while (Emessage.charAt(i) != alphabet.charAt(s))	s++;
			temp += Alphabet_Values.get(s);
			if (j == blockLength) {
				System.out.println(Long.valueOf(temp));
				j = 0;
				dmessage += MOD((long) Math.pow(Long.valueOf(temp), e), n);
				temp = "";
			}
		}
		System.out.println(dmessage);
	}

	private static void Decrypt() {
		
	}
}
