package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.function.Predicate;

public class ContentReader {

    private final File file;

    public ContentReader(File file) {
        this.file = file;
    }

    public String getContent() {
        return content(
                Objects::nonNull
        );
    }

    public String getContentWithoutUnicode() {
        return content(
                filter -> filter < 0x80
        );
    }

    private synchronized String content(Predicate<Character> filter) {
        StringBuilder output = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            int data;
            while ((data = reader.read()) != -1) {
                if (filter.test((char) data)) {
                    output.append((char) data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}
