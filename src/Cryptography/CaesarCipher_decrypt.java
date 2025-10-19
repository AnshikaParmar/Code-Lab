package Cryptography;
public class CaesarCipher_decrypt {

    private int key1;
    private int key2;

    // Constructor to initialize keys
    public CaesarCipher_decrypt(int key1, int key2) {
        this.key1 = key1;
        this.key2 = key2;
    }

    // Decrypt a single character with a given key
    private char decryptChar(char ch, int key) {
        if (Character.isLetter(ch)) {
            char base = Character.isUpperCase(ch) ? 'A' : 'a';
            return (char) ((ch - base - key + 26) % 26 + base);
        }
        return ch; // Non-letter characters are unchanged
    }

    // Decrypt a string encrypted with two keys
    public String decrypt(String text) {
        StringBuilder decrypted = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            int key = (i % 2 == 0) ? key1 : key2; // key1 for even indices, key2 for odd
            decrypted.append(decryptChar(ch, key));
        }

        return decrypted.toString();
    }

    // Test the decryption
    public static void main(String[] args) {
        String encrypted = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        int key1 = 14;
        int key2 = 24;

        CaesarCipher_decrypt cc = new CaesarCipher_decrypt(key1, key2);
        String decrypted = cc.decrypt(encrypted);

        System.out.println("Decrypted message:");
        System.out.println(decrypted);
    }
}

