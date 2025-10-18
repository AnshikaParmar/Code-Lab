package Cryptography;
public class CaesarCipher_second2 {
	    public static void main(String[] args) {
	        String phrase = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
	        int key = 15;

	        // Encrypt the message
	        String encrypted = encrypt(phrase, key);
	        System.out.println("Encrypted message:");
	        System.out.println(encrypted);

	        // Decrypt the message
	        String decrypted = decrypt(encrypted, key);
	        System.out.println("\nDecrypted message:");
	        System.out.println(decrypted);
	    }

	    public static String encrypt(String input, int key) {
	        StringBuilder result = new StringBuilder(input);
	        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	        String shifted = alphabet.substring(key) + alphabet.substring(0, key);

	        for (int i = 0; i < result.length(); i++) {
	            char currChar = result.charAt(i);
	            if (Character.isLetter(currChar)) {
	                boolean isLower = Character.isLowerCase(currChar);
	                char upperChar = Character.toUpperCase(currChar);
	                int idx = alphabet.indexOf(upperChar);
	                if (idx != -1) {
	                    char newChar = shifted.charAt(idx);
	                    if (isLower) newChar = Character.toLowerCase(newChar);
	                    result.setCharAt(i, newChar);
	                }
	            }
	        }
	        return result.toString();
	    }

	    public static String decrypt(String input, int key) {
	        StringBuilder result = new StringBuilder(input);
	        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	        String shifted = alphabet.substring(key) + alphabet.substring(0, key);

	        for (int i = 0; i < result.length(); i++) {
	            char currChar = result.charAt(i);
	            if (Character.isLetter(currChar)) {
	                boolean isLower = Character.isLowerCase(currChar);
	                char upperChar = Character.toUpperCase(currChar);
	                int idx = shifted.indexOf(upperChar);
	                if (idx != -1) {
	                    char newChar = alphabet.charAt(idx);
	                    if (isLower) newChar = Character.toLowerCase(newChar);
	                    result.setCharAt(i, newChar);
	                }
	            }
	        }
	        return result.toString();
	    }
	}
