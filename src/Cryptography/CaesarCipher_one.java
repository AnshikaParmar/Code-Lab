package Cryptography;
public class CaesarCipher_one {
	    public static void main(String[] args) {
	        String phrase = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
	        int key = 15;
	        System.out.println(encrypt(phrase, key));
	        
	    }

	    public static String encrypt(String input, int key) {
	        StringBuilder encrypted = new StringBuilder(input);
	        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	        String shifted = alphabet.substring(key) + alphabet.substring(0, key);

	        for (int i = 0; i < encrypted.length(); i++) {
	            char currChar = encrypted.charAt(i);
	            if (Character.isLetter(currChar)) {
	                boolean isLower = Character.isLowerCase(currChar);
	                char upperChar = Character.toUpperCase(currChar);
	                int idx = alphabet.indexOf(upperChar);
	                if (idx != -1) {
	                    char newChar = shifted.charAt(idx);
	                    if (isLower) newChar = Character.toLowerCase(newChar);
	                    encrypted.setCharAt(i, newChar);
	                }
	            }
	        }
	        return encrypted.toString();
	    }
	}
