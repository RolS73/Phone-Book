package hu.nive.ujratervezes.io.phonebook;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Phonebook {

    public void exportPhonebook(Map<String, String> contacts, String output) throws IOException {
        if (contacts == null || output == null) {
            throw new IllegalArgumentException();
        } else if (Files.exists(Path.of(output))) {
            Files.delete(Path.of(output));
        }

        List<String> contactsList = exportContactsToListFromHashMap(contacts);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {
            for (String s : contactsList) {
                writer.write(s);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> exportContactsToListFromHashMap(Map<String, String> contacts) {
        List<String> contactsList = new ArrayList<>();

        for (Map.Entry<String, String> entry : contacts.entrySet()) {
            contactsList.add(entry.getKey() + ": " + entry.getValue());
        }

        return contactsList;
    }
}
