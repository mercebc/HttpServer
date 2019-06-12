package Util;

import Handlers.PublicFileHandlers.CssHandler;
import Handlers.PublicFileHandlers.ImageHandler;
import Handlers.ResponseHandler;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class PublicFileManager {
    String path;
    private Map<String, ResponseHandler> files = new HashMap<>();

    public Map<String, ResponseHandler> getFiles() {
        return files;
    }

    public PublicFileManager(String path) {
        this.path = path;
    }

    private void addFile(String resource, ResponseHandler responseHandler){
        files.put(resource, responseHandler);
    }

    public void config(){
        addFile("/images/wellies.png", new ImageHandler(this));
        addFile("/css/app-stylesheet.css", new CssHandler(this));
    };

    public String read(String filename) throws IOException {

        byte[] encoded = Files.readAllBytes(Paths.get(path + filename));
        return new String(encoded, Charset.defaultCharset());
    }

    public String MIMEType(String type) {
        if (type == "png") {
            return "image/png; base64";
        }else if(type == "css"){
            return "text/css; charset=utf-8";
        }
        return "text/html; charset=utf-8";
    }
}
