package Arraylist;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class CharacterFrequency2 {
    private ArrayList<String> characters; // Stores character names
    private ArrayList<Integer> counts;    // Stores number of speaking parts

    // Constructor
    public CharacterFrequency2() {
        characters = new ArrayList<>();
        counts = new ArrayList<>();
    }

    // Update the character count
    public void update(String person) {
        int index = characters.indexOf(person);
        if (index == -1) {
            characters.add(person);
            counts.add(1);
        } else {
            counts.set(index, counts.get(index) + 1);
        }
    }

    // Read file and count speaking parts
    public void findAllCharacters(String filePath) throws Exception {
        characters.clear();
        counts.clear();

        Scanner scanner;
        if (filePath.startsWith("http")) {
            scanner = new Scanner(new URL(filePath).openStream());
        } else {
            scanner = new Scanner(new File(filePath));
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();

            // Check if the line contains a period (possible speaking line)
            if (line.contains(".")) {
                String possibleName = line.split("\\.")[0].trim();

                // Ignore headings or empty names
                if (!possibleName.isEmpty() &&
                    !possibleName.startsWith("SCENE") &&
                    !possibleName.startsWith("ACT") &&
                    Character.isUpperCase(possibleName.charAt(0))) {
                    update(possibleName);
                }
            }
        }

        scanner.close();
    }

    // Find index of the character with the most lines below a given limit
    public int findIndexOfMaxUnderLimit(int limit) {
        int maxIndex = -1;
        int maxCount = 0;
        for (int i = 0; i < counts.size(); i++) {
            int count = counts.get(i);
            if (count < limit && count > maxCount) {
                maxCount = count;
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    // Print character with most speaking parts under 100 lines
    public void printMostSpeakingUnder100(String filePath) throws Exception {
        findAllCharacters(filePath);

        if (characters.isEmpty()) {
            System.out.println("No characters found in the file.");
            return;
        }

        int maxIndex = findIndexOfMaxUnderLimit(100);
        if (maxIndex == -1) {
            System.out.println("No character has fewer than 100 lines.");
        } else {
            System.out.println("Character with the most speaking parts (under 100 lines): " + characters.get(maxIndex));
            System.out.println("Number of speaking parts: " + counts.get(maxIndex));
        }
    }

    // Main method
    public static void main(String[] args) throws Exception {
        CharacterFrequency2 cf = new CharacterFrequency2();
        cf.printMostSpeakingUnder100("https://www.dukelearntoprogram.com/java/errors.txt");
    }
}


