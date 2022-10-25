package io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ParseFile {

    private final File file;
    public final ContentReader reader;

    public ParseFile(File file) {
        this.file = file;
        reader = new ContentReader(file);
    }

    public void saveContent(String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(content);
        writer.close();
    }
}
