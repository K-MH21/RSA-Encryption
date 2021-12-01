import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainClass {

    final static ArrayList<String> Alphabet_Values = new ArrayList<>();
    static String dmessage="";
    static String emessage="";
    static String alphabet;

    public static void main(String[] args) throws IOException {
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
                    + "\n(2) Decrypt a massage"
                    + "\n(3) Exit the System");
            switch (new Scanner(System.in).nextInt()) {
                case 1: encrypt();				break;
                case 2: decrypt();				break;
                case 3: System.exit(0);
            }
        }
    }

    //Self-explanatory
    private static long MOD(long a, long m) {
        if (!(m > 0)) throw new InputMismatchException("Wrong m value");
        return ((a % m) + m) % m;
    }

    /*
     * Encryption method, Will take a .txt file as an input
     * and will produce a .rsa file as an output.
     */
    private static void encrypt() throws IOException {
        //	Scanner read;
        //	try {read = new Scanner(new File("message.txt"));}
        //	catch (FileNotFoundException e) {System.out.println("File is missing"); return;}
        long e = 5; //read.nextLong(); 	TODO Throw an exception if e or n is wrong
        long n = 17*7; //read.nextLong();  TODO Need to check which value comes first in the message.txt
        Integer blockLength = 0;
        while (n > blockLength) {
            int power = 0;
            blockLength = (int) (blockLength + (alphabet.length() - 1) * Math.pow(10, power));
            power += 2;
        }
        blockLength = blockLength.toString().length()/2;
        String Emessage = "ST 5 ST";
         emessage = "";
        String temp = "";
        //	while (read.hasNextLine()) message += read.nextLine();
        for (int i = 0, j = 1; i<Emessage.length(); i++, j++) {
            int s = 0;
            while (Emessage.charAt(i) != alphabet.charAt(s))	s++;
            temp += Alphabet_Values.get(s);
            if (j == blockLength) {
                System.out.println(Long.valueOf(temp));
                j = 0;
                emessage += MOD((long) Math.pow(Long.valueOf(temp), e), n);



                temp = "";
            }
        }
        
        // TODO create an encrypted file
/*
FileWriter fileWriter = new FileWriter("emessage.rsa");
        fileWriter.write(emessage);
        fileWriter.close();*/

        System.out.println(emessage);
    }

    private static void decrypt() throws FileNotFoundException {
        
        Scanner kb = new Scanner(System.in);
        System.out.print("Enter d's value: ");
        long d = kb.nextLong();
        System.out.print("Enter n's value: ");
        long n = kb.nextLong();

        File file = new File ("emessage.rsa");
        Scanner scanner = new Scanner(file);
        String encrypted = scanner.next();

      


        for (int j = 0 ; j < encrypted.length(); j += 2) {
            long temp ;
            long character = temp = Long.valueOf(encrypted.substring(j,j+2));


            for (int i = 1; i < d; i++) {    //TODO calculate mod for large numbers
                if (temp > n)
                    temp = temp % n;

                temp = temp * character;
            }

            if (temp > n)
                temp = temp % n;

            dmessage += temp;   // The decrypted message
        }

        String Dmessage = "";
        for (int i = 0; i<dmessage.length(); i = i+2) {
            Dmessage += (alphabet.charAt(Integer.parseInt(dmessage.substring(i,i+2)))); // TODO get the alphabet from the index
        }

        System.out.println("\nThe encrypted message: "+Dmessage);
        dmessage = "";
    }
}