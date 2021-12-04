import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainClass {

    final static ArrayList<String> Alphabet_Values = new ArrayList<>();
    static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789.?!,;:-()[]{}'\" \n";
    static Scanner kb = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //Preparing Alphabet_Values and alphabet
        for (Integer i = 0; i < alphabet.length(); i++)
            if (i < 10)	Alphabet_Values.add("0" + i.toString());
            else		Alphabet_Values.add(i.toString());

        //Main menu
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

    //Self-explanatory
    private static long MOD(long a, long m) {
        if (!(m > 0)) throw new InputMismatchException("Wrong m value");
        return ((a % m) + m) % m;
    }


    private static long MODEXP(long a, long b, long m) {
        if (b > 10)	return MOD((long) Math.pow(a, 10), m) * MODEXP(a, b - 10, m);
        return MOD((long) Math.pow(a, b), m);
    }

    private static long MODEXPBase(long a, long b, long m) {
        if (a > m/2) a -= m;
        return MOD(MODEXP(a, b, m), m);
    }

    /*
     * Encryption method will take a .txt file as an input
     * and will produce an .rsa file as an output.
     */
    private static void encrypt() throws IOException {
        //Getting the message
        Scanner read;
        try {read = new Scanner(new File("message.txt"));}
        catch (FileNotFoundException e) {System.out.println("File is missing"); return;}

        //Getting the values
        String dmessage = "";
        long e = read.nextLong();
        long n = read.nextLong();
        read.nextLine();
    	while (read.hasNextLine()) dmessage += read.nextLine() + "\n";
    	dmessage = dmessage.substring(0, dmessage.length() - 1);

        //Finding the blockLength
        Integer blockLength = 0;
        for (int power = 0; n > blockLength + (alphabet.length() - 1) * Math.pow(10, power); power += 2)
            blockLength += (int) ((alphabet.length() - 1) * Math.pow(10, power));
        blockLength = blockLength.toString().length()/2;

        //Making sure that every block is equal to each other.
        while (dmessage.length() % blockLength != 0) dmessage += "X";

        //Encrypting the message
        String emessage = "";
        {
            String temp;
            int i, j;
            for (temp = "", i = 0, j = 1; i < dmessage.length(); i++, j++) {
                int s;
                for (s = 0; dmessage.charAt(i) != alphabet.charAt(s); s++);
                temp += Alphabet_Values.get(s);
                if (j == blockLength) {
                    j = 0;
                    emessage += MODEXPBase(Long.valueOf(temp), e, n)+" ";
                    temp = "";
                }
            }
        }

        //Creating the rsa file and writing on it
        FileWriter fileWriter = new FileWriter("message.rsa");
        fileWriter.write(emessage);
        fileWriter.close();

        //End of the method
        System.out.println("The message has been encrypted successfully");
    }

    private static void decrypt() throws IOException {


        System.out.print("Enter d's value: ");
        long d = kb.nextLong();
        System.out.print("Enter n's value: ");
        long n = kb.nextLong();
        String dmessage="";

        Scanner scanner;
        try {scanner = new Scanner(new File("message.rsa"));}
        catch (FileNotFoundException e) {System.out.println("File is missing"); return;}

        String encrypted = "";

 //TODO read character by character
        while (scanner.hasNext()){
            encrypted = scanner.next();
            long temp ;
            long character = temp = Long.valueOf(encrypted);



            for (int i = 1; i < d; i++) {    //TODO calculate mod for large numbers
                if (temp > n)
                    temp = temp % n;

                temp = temp * character;
            }

            if (temp > n)
                temp = temp % n;


            if (temp < 10)
                dmessage += "0" + temp;   //TODO add 0 to the left of any char has index less than 10 to read it correctly from the alphabet String

            else
                dmessage += String.valueOf(temp);


        }
        
        String Dmessage = "";
        for (int i = 0; i < dmessage.length(); i = i+2) {
            Dmessage += (alphabet.charAt(Integer.parseInt(dmessage.substring(i,i+2)))); // TODO get the alphabet by using the index from the alphabet string
        }
        System.out.println("Your encrypted file has been crated.");
        dmessage = "";

        FileWriter fileWriter = new FileWriter("message.dec");
        fileWriter.write(Dmessage);
        fileWriter.close();
        scanner.close();
    }
}