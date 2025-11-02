package Arraylist;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class CharacterFrequency {
    private ArrayList<String> characters; // Stores character names
    private ArrayList<Integer> counts;    // Stores number of speaking parts

    // Constructor
    public CharacterFrequency() {
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

            // Check if line contains a period (possible character speaking line)
            if (line.contains(".")) {
                String possibleName = line.split("\\.")[0].trim();

                // Ignore scene or act headings and empty names
                if (!possibleName.isEmpty() &&
                    !possibleName.startsWith("SCENE") &&
                    !possibleName.startsWith("ACT")) {
                    update(possibleName);
                }
            }
        }

        scanner.close();
    }

    // Find index of character with most speaking parts
    public int findIndexOfMax() {
        int maxIndex = 0;
        for (int i = 1; i < counts.size(); i++) {
            if (counts.get(i) > counts.get(maxIndex)) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    // Print the character with the most speaking parts
    public void printMostSpeakingCharacter(String filePath) throws Exception {
        findAllCharacters(filePath);

        if (characters.size() == 0) {
            System.out.println("No characters found in the file.");
            return;
        }

        int maxIndex = findIndexOfMax();
        System.out.println("Character with the most speaking parts: " + characters.get(maxIndex));
        System.out.println("Number of speaking parts: " + counts.get(maxIndex));
    }

    // Main method
    public static void main(String[] args) throws Exception {
        CharacterFrequency cf = new CharacterFrequency();
        cf.printMostSpeakingCharacter("https://www.dukelearntoprogram.com/java/likeit.txt");
    }
}
