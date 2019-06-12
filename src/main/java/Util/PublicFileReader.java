package Util;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
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

    public String readImage(String filename) throws IOException {
        File welliespng = Paths.get(path + filename).toFile();
        BufferedImage image = ImageIO.read(welliespng);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", outputStream);
        return Base64.encode(outputStream.toByteArray());
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
