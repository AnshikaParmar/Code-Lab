package Arraylist;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class CharacterFrequency3 {
    private ArrayList<String> characters; // Stores character names
    private ArrayList<Integer> counts;    // Stores number of speaking parts

    public CharacterFrequency3() {
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

    // Read the file and count speaking parts
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

            // A speaking line usually has a period after the character name
            if (line.contains(".")) {
                String possibleName = line.split("\\.")[0].trim();

                // Ignore headings
                if (!possibleName.isEmpty() &&
                    !possibleName.startsWith("SCENE") &&
                    !possibleName.startsWith("ACT")) {
                    update(possibleName);
                }
            }
        }

        scanner.close();
    }

    // Find and print the character with the 3rd most speaking parts
    public void printThirdMostSpeaking(String filePath) throws Exception {
        findAllCharacters(filePath);

        ArrayList<Integer> sortedCounts = new ArrayList<>(counts);
        Collections.sort(sortedCounts, Collections.reverseOrder());

        if (sortedCounts.size() < 3) {
            System.out.println("Less than 3 characters found.");
            return;
        }

        int thirdMostCount = sortedCounts.get(2);
        int index = counts.indexOf(thirdMostCount);

        System.out.println("Character with 3rd most speaking parts: " + characters.get(index));
        System.out.println("Number of speaking parts: " + counts.get(index));
    }

    public static void main(String[] args) throws Exception {
        CharacterFrequency3 tp = new CharacterFrequency3();
        tp.printThirdMostSpeaking("https://www.dukelearntoprogram.com/java/errors.txt");
    }
}


