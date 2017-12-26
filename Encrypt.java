/*
 * Mitchell Mesecher CS327 Section 1
 * This work complies with the JMU honor code
 * 
 */
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Encrypt {
    

    static HashMap<Character, Integer> alf_To_Num = makeMap();
    static boolean D = false;
    static boolean E = false;
    static boolean F = false;
    static boolean CryptIn = false;
    static boolean inputType = false;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Encryptor encrypt;
        String message = "";
        int shiftNumber = 0;
        String decision;
        String fileName = "";

        System.out.println("Do u want to Encrypt(E) or Decrypt(D)?");
        while (!CryptIn) {

            if (scan.hasNextLine()) {
                decision = scan.nextLine();
                if (decision.equalsIgnoreCase("D")) {
                    CryptIn = true;
                    D = true;
                } else if (decision.equalsIgnoreCase("E")) {
                    CryptIn = true;
                    E = true;
                } else {
                    System.out.println("Enter Encrypt(E) or Decrypt(D)");
                }

            }

        }
        System.out.println("Enter Text(T) or File(F)");
        while (!inputType) {
            if (scan.hasNextLine()) {
                decision = scan.nextLine();
                if (decision.equalsIgnoreCase("T")) {
                    System.out.println("Enter a message");
                    if (scan.hasNextLine()) {
                        message = scan.nextLine();
                        if (checkMessage(message)) {
                            System.out.println(
                                    "Enter a shift number (-26 -> 26)");
                            if (scan.hasNextInt()) {
                                shiftNumber = scan.nextInt();
                                inputType = checkShift(shiftNumber);
                            } else {
                                System.out.println("Incorrect Shift Number");
                            }
                        } else {

                            System.out.println("Enter Text(T) or File(F)");
                        }

                    }
                } else if (decision.equalsIgnoreCase("F")) {
                    F = true;
                    inputType = true;
                    System.out.println("Enter a file name");
                    if (scan.hasNextLine()) {
                        fileName = scan.nextLine();

                    }
                } else {
                    System.out.println("Enter Text(T) or File(F)");

                }

            }

        }

        boolean correctInput = true;
        if (F) {
            try {
                scan = new Scanner(new File(fileName));
                if (scan.hasNextLine()) {
                    message = scan.nextLine();
                    if (!checkMessage(message))
                        correctInput = false;

                }
                if (scan.hasNextInt()) {
                    shiftNumber = scan.nextInt();
                    if (!checkShift(shiftNumber))
                        correctInput = false;
                }

            } catch (IOException e) {

                System.out.println("File Not Found");
                System.exit(0);
            }

        }

        encrypt = new Encryptor(message, alf_To_Num, shiftNumber);
        if (E && correctInput) {

            String encryptedMessage = encrypt.encrypt();
            System.out.println("Encrytped Message: " + encryptedMessage);

        } else if (D && correctInput) {

            String decryptedMessage = encrypt.decrypt();
            System.out.println("Decrypted Message: " + decryptedMessage);
        } else {
            System.out.println("File Format Incorrect");
            System.out.println("First Line Message; Second Line Shift Num");
        }
        scan.close();
    }

    private static HashMap<Character, Integer> makeMap() {
        HashMap<Character, Integer> alf_To_Num = new HashMap<Character, Integer>();
        int j = 0;
        for (char i = '\u0041'; i <= '\u005A'; i++) {
            alf_To_Num.put(i, j);
            alf_To_Num.put((char) (i + '\u0020'), j++);

        }
        return alf_To_Num;

    }

    private static boolean checkMessage(String message) {
        for (int i = 0; i < message.length(); i++) {
            if (D && message.charAt(i) == ' ') {
                System.out.println("Message to decrypt cannot contain spaces");
                return false;
            }

            if (!alf_To_Num.keySet().contains(message.charAt(i))
                    && message.charAt(i) != ' ') {
                System.out.println("Invalid character " + message.charAt(i)
                        + " at pos " + ++i);
                System.out.println("Acceptable characters(A-Z) (a-z)");
                return false;
            }
        }
        return true;
    }

    private static boolean checkShift(int shiftNum) {

        if (shiftNum < -26 || shiftNum > 26) {
            System.out.println("Incorrect Shift Interval");

            return false;
        }
        return true;
    }

}
