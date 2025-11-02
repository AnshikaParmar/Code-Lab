package Arraylist;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class CharacterFrequency4 {
    private ArrayList<String> characters;
    private ArrayList<Integer> counts;

    public CharacterFrequency4() {
        characters = new ArrayList<>();
        counts = new ArrayList<>();
    }

    public void update(String person) {
        int index = characters.indexOf(person);
        if (index == -1) {
            characters.add(person);
            counts.add(1);
        } else {
            counts.set(index, counts.get(index) + 1);
        }
    }

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

            if (line.contains(".")) {
                String possibleName = line.split("\\.")[0].trim();

                if (!possibleName.isEmpty() &&
                    !possibleName.startsWith("SCENE") &&
                    !possibleName.startsWith("ACT")) {
                    update(possibleName);
                }
            }
        }

        scanner.close();
    }

    public void countCharactersInRange(String filePath, int min, int max) throws Exception {
        findAllCharacters(filePath);

        int countInRange = 0;
        for (int i = 0; i < characters.size(); i++) {
            int speakingParts = counts.get(i);
            if (speakingParts >= min && speakingParts <= max) {
                countInRange++;
            }
        }

        System.out.println("Characters with between " + min + " and " + max + " speaking parts: " + countInRange);
    }

    public static void main(String[] args) throws Exception {
        CharacterFrequency4 cr = new CharacterFrequency4();
        cr.countCharactersInRange("https://www.dukelearntoprogram.com/java/errors.txt", 10, 15);
    }
}

