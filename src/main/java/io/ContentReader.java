package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.function.Predicate;

public class ContentReader {

    private final File file;

    public ContentReader(File file){
        this.file = file;
    }

    public String getContent() throws IOException {
        return content(
                Objects::nonNull
        );
    }

    public String getContentWithoutUnicode() throws IOException {
        return content(
                filter -> filter < 0x80
        );
    }

    private String content(Predicate<Character> filter) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder output = new StringBuilder();

        int data;
        while ((data = reader.read()) != -1) {
            if (filter.test((char) data)) {
                output.append((char) data);
            }
        }
        reader.close();
        return output.toString();
    }
}
