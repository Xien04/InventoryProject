package com.company;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Database {
    private String filename;

    /**
     * Used to instantiate the database class.
     * @param filename filename of the file to be used.
     */
    public Database(String filename) {
        this.filename = filename;
    }

    /**
     * Used to retrieve the contents of the file.
     * @return a collection of the retrieved contents.
     */
    public ArrayList<LinkedHashMap<String, String>> retrieve() {
        ArrayList<LinkedHashMap<String, String>> records = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename));) {
            String line;

            // read each line of the file.
            while((line = reader.readLine()) != null) {
                LinkedHashMap<String, String> record = new LinkedHashMap<>();
                Pattern pattern = Pattern.compile("\\b(.+?)='(.+?)'");
                Matcher match = pattern.matcher(line);

                // match the contents of the line with the regex pattern.
                while(match.find()) {
                    record.put(match.group(1), match.group(2));
                }

                // append a new record to the current set of records.
                records.add(record);
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
        finally {
            // return the set of records.
            return records;
        }
    }

    /**
     * Used to update the content of a file.
     * @param records records that will be updated on the file.
     * @param <T> a generic type
     */
    public <T> void update(ArrayList<T> records) {
        try (FileOutputStream fileOut = new FileOutputStream(filename);) {
            StringBuffer inputBuffer = new StringBuffer();

            // iterate through the records and append the contents to a buffer.
            records.forEach(record -> inputBuffer.append(record.toString() + '\n'));
            // replace the content of the file with the contents of the buffer.
            fileOut.write(inputBuffer.toString().getBytes());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
