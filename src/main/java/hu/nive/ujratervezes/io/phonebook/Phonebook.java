package hu.nive.ujratervezes.io.phonebook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Phonebook {

    public void exportPhonebook(Map<String, String> contacts, String output) throws IOException {
        if (contacts == null || output == null) {
            throw new IllegalArgumentException();
        }

        List<String> contactsList = exportContactsToListFromHashMap(contacts);

        try  {
            initOutputPathFromString(output);

            for (String line : contactsList) {
                Files.writeString(Path.of(output), line + "\n", StandardOpenOption.APPEND);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initOutputPathFromString(String output) throws IOException {
        if (Files.exists(Path.of(output))) {
            Files.delete(Path.of(output));
            Files.createFile(Path.of(output));
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
