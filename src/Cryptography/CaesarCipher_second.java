package Cryptography;
public class CaesarCipher_second {
    public static void main(String[] args) {
        String phrase = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        int key1 = 21;
        int key2 = 8;
        System.out.println(encryptTwoKeys(phrase, key1, key2));
    }

    public static String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shifted1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        String shifted2 = alphabet.substring(key2) + alphabet.substring(0, key2);

        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            if (Character.isLetter(currChar)) {
                boolean isLower = Character.isLowerCase(currChar);
                char upperChar = Character.toUpperCase(currChar);
                int idx = alphabet.indexOf(upperChar);
                if (idx != -1) {
                	// Even positions use key1, odd positions use key2
                    char newChar = (i % 2 == 0) ? shifted2.charAt(idx) : shifted1.charAt(idx);
                    if (isLower) newChar = Character.toLowerCase(newChar);
                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        return encrypted.toString();
    }
}
