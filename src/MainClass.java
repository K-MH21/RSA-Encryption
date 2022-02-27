import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class MainClass {

    final static ArrayList<String> Alphabet_Values = new ArrayList<>();
    static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789.?!,;:-()[]{}'\" \n";
    static Scanner kb = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        // Preparing Alphabet_Values and alphabet
        for (Integer i = 0; i < alphabet.length(); i++)
            if (i < 10)	Alphabet_Values.add("0" + i.toString());
            else		Alphabet_Values.add(i.toString());

        // Main menu
        while (true) {
            System.out.println("What do you want to do?"
                    + "\n(1) Encrypt a message"
                    + "\n(2) Decrypt a massage"
                    + "\n(3) Exit the System");
            switch (kb.nextInt()) {
                case 1: encrypt();				break;
                case 2: decrypt();				break;
                case 3:
                    System.out.println("System has been terminated");
                    System.exit(0);
            }
        }
    }

    /*
     * Encryption method will take a .txt file as an input
     * and will produce an .rsa file as an output.
     */
    private static void encrypt() throws IOException {
        // Getting the message
        Scanner read;
        try {read = new Scanner(new File("message.txt"));}
        catch (FileNotFoundException e) {System.out.println("ERROR: File is missing"); return;}

        //Getting the values
        String dmessage = "";
        long e = read.nextLong();
        long n = read.nextLong();
        read.nextLine();
        while (read.hasNextLine()) dmessage += read.nextLine() + "\n";
        dmessage = dmessage.substring(0, dmessage.length() - 1);

        // Finding the blockLength
        int blockLength;
		BigInteger block = BigInteger.valueOf(alphabet.length() - 1);
		for (int power = 2; BigInteger.valueOf(n).compareTo(block) == 1; power += 2)
			block = block.add(BigInteger.valueOf((long) ((alphabet.length() - 1) * Math.pow(10, power))));
		blockLength = (String.valueOf(block).length() - 2) / 2;

        // Making sure that every block are equal in length.
        while (dmessage.length() % blockLength != 0) dmessage += "X";

        // Encrypting the message
        String emessage = "";

            String temp;
            int i, j;
            for (temp = "", i = 0, j = 1; i < dmessage.length(); i++, j++) {
                int s;
                for (s = 0; dmessage.charAt(i) != alphabet.charAt(s); s++);
                temp += Alphabet_Values.get(s);
                if (j == blockLength) {
                    j = 0;
                    String checker = BigInteger.valueOf(Long.valueOf(temp)).modPow(BigInteger.valueOf(e), BigInteger.valueOf(n)) + "";
                    while (checker.length() != blockLength * 2) checker = "0" + checker;
                    emessage += checker;
                    temp = "";
                }
            }

        // Creating the rsa file and writing on it
        FileWriter fileWriter = new FileWriter("message.rsa");
        fileWriter.write(emessage);
        fileWriter.close();

        // End of the method
        System.out.println("The message has been encrypted successfully");
    }

	private static void decrypt() throws IOException {

		Scanner scanner;
		try {
			scanner = new Scanner(new File("message.rsa"));
		} catch (FileNotFoundException e) {
			System.out.println("File is missing");
			return;
		}

		System.out.print("Enter d's value: ");
		long d = kb.nextLong();
		System.out.print("Enter n's value: ");
		long n = kb.nextLong();
		String dmessage = "";

		String encrypted = "";
		int blockLength;
		{
			BigInteger block = BigInteger.valueOf(alphabet.length() - 1);
			for (int power = 2; BigInteger.valueOf(n).compareTo(block) == 1; power += 2)
				block = block.add(BigInteger.valueOf((long) ((alphabet.length() - 1) * Math.pow(10, power))));
			blockLength = String.valueOf(block).length() - 2;
		}

		// read character by character
		while (scanner.hasNextLine())
			encrypted += scanner.nextLine();

		for (int j = 0; j < encrypted.length(); j += blockLength) {
			BigInteger temp = BigInteger.valueOf(Long.parseLong(encrypted.substring(j, j + blockLength)));
			BigInteger character;

			character = temp.modPow(BigInteger.valueOf(d), BigInteger.valueOf(n));

			if (character.toString().length() < blockLength)
				dmessage += "0".repeat(blockLength - character.toString().length()) + character;

			else
				dmessage += String.valueOf(character);

		}

		String Dmessage = "";
		for (int i = 0; i < dmessage.length(); i = i + 2) {
			Dmessage += (alphabet.charAt(Integer.parseInt(dmessage.substring(i, i + 2)))); // get the alphabet by using
																							// the index from the
																							// alphabet string
		}
		
		dmessage = "";

		FileWriter fileWriter = new FileWriter("message.dec");
		fileWriter.write(Dmessage);
		fileWriter.close();
		scanner.close();

		System.out.println("Your encrypted file has been crated.");
	}
}