package Util;

import Handlers.PublicFileHandlers.PublicFileHandler;
import Handlers.ResponseHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class PublicFileManager {
    String path;
    private Map<String, ResponseHandler> files = new HashMap<>();
    private Map<String,String> MIME = new HashMap<>();

    public PublicFileManager(String path) {
        this.path = path;

        MIME.put("png", "image/png");
        MIME.put("css", "text/css; charset=utf-8");
        MIME.put("html", "text/html; charset=utf-8");
    }

    public Map<String, ResponseHandler> getFiles() {
        return files;
    }

    private void addFile(String resource){
        files.put(resource, new PublicFileHandler(this));
    }

    public void config(){
        addFile("/images/wellies.png");
        addFile("/css/app-stylesheet.css");
    }

    public byte[] read(String filename) throws IOException {
        return Files.readAllBytes(Paths.get(path + filename));
    }

    public String MIMEType(String filename) {
        String type = getType(filename);
        return MIME.get(type);
    }

    public String getType(String filename) {
        int lastIndexOf = filename.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return "";
        }
        return filename.substring(lastIndexOf + 1);
    }
}
