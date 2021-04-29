package hu.nive.ujratervezes.io.phonebook;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Phonebook {

    public void exportPhonebook(Map<String, String> contacts, String output) {
        if (contacts == null || output == null) {
            throw new IllegalArgumentException();
        }

        List<String> contactsList = exportContactsToListFromHashMap(contacts);

        try  {
            FileWriter writer = new FileWriter("output.txt");
            for(String str: contactsList) {
                writer.write(str + System.lineSeparator());
            }
            writer.close();

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
