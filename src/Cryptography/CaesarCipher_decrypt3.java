package Cryptography;

import java.io.*;
import java.net.*;

public class CaesarCipher_decrypt3 {

    private int key1;
    private int key2;

    // Constructor to initialize keys
    public CaesarCipher_decrypt3(int key1, int key2) {
        this.key1 = key1;
        this.key2 = key2;
    }

    // Decrypt a single character using a key
    private char decryptChar(char ch, int key) {
        if (Character.isLetter(ch)) {
            char base = Character.isUpperCase(ch) ? 'A' : 'a';
            return (char) ((ch - base - key + 26) % 26 + base);
        }
        return ch; // Non-letter characters remain unchanged
    }

    // Decrypt full string using two keys
    public String decrypt(String text) {
        StringBuilder decrypted = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            int key = (i % 2 == 0) ? key1 : key2;
            decrypted.append(decryptChar(ch, key));
        }
        return decrypted.toString();
    }

    // Count letter frequencies
    private int[] countLetters(String text) {
        int[] counts = new int[26];
        for (char ch : text.toLowerCase().toCharArray()) {
            if (Character.isLetter(ch)) counts[ch - 'a']++;
        }
        return counts;
    }

    // Find index of most frequent letter
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

    // Guess key assuming 'e' is most frequent
    private static int getKey(String s) {
        CaesarCipher_decrypt3 temp = new CaesarCipher_decrypt3(0, 0);
        int[] freqs = temp.countLetters(s);
        int maxDex = temp.maxIndex(freqs);
        int key = maxDex - 4; // 'e' index = 4
        if (key < 0) key += 26;
        return key;
    }

    // Break the two-key cipher automatically
    public static CaesarCipher_decrypt3 breakTwoKeyCipher(String encrypted) {
        StringBuilder evenChars = new StringBuilder();
        StringBuilder oddChars = new StringBuilder();

        for (int i = 0; i < encrypted.length(); i++) {
            if (i % 2 == 0) evenChars.append(encrypted.charAt(i));
            else oddChars.append(encrypted.charAt(i));
        }

        int key1 = getKey(evenChars.toString());
        int key2 = getKey(oddChars.toString());

        return new CaesarCipher_decrypt3(key1, key2);
    }

    // Helper method: read file from URL
    public static String readFromURL(String urlString) throws IOException {
        StringBuilder content = new StringBuilder();
        URL url = new URL(urlString);
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        String line;
        while ((line = br.readLine()) != null) {
            content.append(line).append("\n");
        }
        br.close();
        return content.toString();
    }

    // MAIN: decrypt from URL and print first five words
    public static void main(String[] args) throws IOException {
        String url = "https://www.dukelearntoprogram.com/java/mysteryTwoKeysQuiz.txt";

        // 1. Read encrypted text from URL
        String encrypted = readFromURL(url);

        // 2. Break the cipher to guess keys
        CaesarCipher_decrypt3 cc = breakTwoKeyCipher(encrypted);
        System.out.println("Guessed keys: key1 = " + cc.key1 + ", key2 = " + cc.key2);
    }
}

