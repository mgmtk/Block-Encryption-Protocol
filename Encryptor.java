/*
 * Mitchell Mesecher CS327 Section 1
 * This work complies with the JMU honor code
 * 
 */
import java.util.HashMap;

public class Encryptor {

    private int shiftNumber;
    private String messageToDecrypt;
    private HashMap<Character, Integer> map;
    private Character[] alf = new Character[26];

    public Encryptor(String message, HashMap<Character, Integer> alf_To_Num,
            int shiftNumber) {
        this.shiftNumber = shiftNumber;
        this.messageToDecrypt = message;
        this.map = alf_To_Num;
        initializeArray();
    }

    private void initializeArray() {
        int j = 0;
        for (char i = '\u0041'; i <= '\u005A'; i++) {
            alf[j++] = i;
        }

    }

    public String encrypt() {
        String encryption = "";
        int shift = 0;
        for (int i = 0; i < messageToDecrypt.length(); i++) {

            if (!(messageToDecrypt.charAt(i) == ' ')) {

                shift = (map.get(messageToDecrypt.charAt(i)) + shiftNumber)
                        % 26;

                if (shift < 0) {
                    shift += 26;
                }

                encryption += alf[shift];
            }
        }
        return encryption;

    }

    public String decrypt() {
        String encryption = "";
        int shift = 0;
        for (int i = 0; i < messageToDecrypt.length(); i++) {

            if (!(messageToDecrypt.charAt(i) == ' ')) {

                shift = (map.get(messageToDecrypt.charAt(i)) - shiftNumber)
                        % 26;

                if (shift < 0) {
                    shift += 26;
                }

                encryption += alf[shift];
            }
        }
        return encryption;

    }

}
