package Util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PublicFileReader {
    String path;

    public PublicFileReader(String path) {
       this.path = path;
    }

    public String read(String filename) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path + filename));
        return new String(encoded, Charset.defaultCharset());
    }
}
