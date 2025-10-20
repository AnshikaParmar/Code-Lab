package Cryptography;

public class CaesarCipher_decrypt2 {

    private int key1;
    private int key2;

    // Constructor with keys
    public CaesarCipher_decrypt2(int key1, int key2) {
        this.key1 = key1;
        this.key2 = key2;
    }

    // Decrypt a single character with a given key
    private char decryptChar(char ch, int key) {
        if (Character.isLetter(ch)) {
            char base = Character.isUpperCase(ch) ? 'A' : 'a';
            return (char) ((ch - base - key + 26) % 26 + base);
        }
        return ch; // Keep punctuation/space
    }

    // Decrypt a string with two keys
    public String decrypt(String text) {
        StringBuilder decrypted = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            int key = (i % 2 == 0) ? key1 : key2; // key1 for even, key2 for odd
            decrypted.append(decryptChar(ch, key));
        }
        return decrypted.toString();
    }

    // Helper: count letters in a string
    private int[] countLetters(String text) {
        int[] counts = new int[26];
        for (char ch : text.toLowerCase().toCharArray()) {
            if (Character.isLetter(ch)) {
                counts[ch - 'a']++;
            }
        }
        return counts;
    }

    // Helper: find index of max value in an array
    private int maxIndex(int[] values) {
        int max = 0;
        int index = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > max) {
                max = values[i];
                index = i;
            }
        }
        return index;
    }

    // Break the two-key Caesar Cipher
    public static CaesarCipher_decrypt2 breakTwoKeyCipher(String encrypted) {
        StringBuilder evenChars = new StringBuilder();
        StringBuilder oddChars = new StringBuilder();

        for (int i = 0; i < encrypted.length(); i++) {
            if (i % 2 == 0) {
                evenChars.append(encrypted.charAt(i));
            } else {
                oddChars.append(encrypted.charAt(i));
            }
        }

        // Frequency analysis to guess key for even and odd
        int key1 = getKey(evenChars.toString());
        int key2 = getKey(oddChars.toString());

        return new CaesarCipher_decrypt2(key1, key2);
    }

    // Guess the key for a single string
    private static int getKey(String s) {
        int[] freqs = new CaesarCipher_decrypt2(0, 0).countLetters(s);
        int maxDex = new CaesarCipher_decrypt2(0, 0).maxIndex(freqs);
        int key = maxDex - 4; // assuming 'e' is the most frequent letter
        if (key < 0) key += 26;
        return key;
    }

    // Test method
    public static void main(String[] args) {
        String encrypted = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";

        // Break the cipher
        CaesarCipher_decrypt2 cc = breakTwoKeyCipher(encrypted);

        System.out.println("Guessed keys: key1 = " + cc.key1 + ", key2 = " + cc.key2);
        System.out.println("Decrypted message:");
        System.out.println(cc.decrypt(encrypted));
    }
}

