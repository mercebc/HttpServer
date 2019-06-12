package Handlers.PublicFileHandlers;

import Request.Request;
import Response.Response;
import Util.PublicFileManager;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ImageHandlerTest {

    private ImageHandler imageHandler;
    private Response response;
    private Request request;
    private PublicFileManager publicFileManager;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void SetUp() throws InvocationTargetException, IllegalAccessException, IOException {

        File imageFolder = folder.newFolder("images");
        File image = new File(imageFolder.getAbsolutePath() + "/wellies.png");

        PrintWriter writer = new PrintWriter(image.getAbsoluteFile(), "UTF-8");
        writer.println("T/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxQTEhUTExQWFhUXGCEYGRgYGR4dIRsaGyAgHyId");
        writer.close();

        publicFileManager = new PublicFileManager(folder.getRoot().getAbsolutePath() + "/");
        imageHandler = new ImageHandler(publicFileManager);
        request =  new Request("GET", "images/wellies.png","HTTP/1.1", new HashMap<>(), "");
        response = imageHandler.respondToRequest(request);
    }

    @Test
    public void responseOK() {
        assertThat(response.getStatusLine(), is("HTTP/1.1 200 OK"));
    }

    @Test
    public void responseHasHTMLinBody(){
        String bodyExpected = "T/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxQTEhUTExQWFhUXGCEYGRgYGR4dIRsaGyAgHyId\n";
        assertThat(response.getBody(), is(bodyExpected));
    }

    @Test
    public void responseWithContentLengthHeaders(){
        assertThat(response.getHeaders().get("Content-Length"), is("78"));
    }

    @Test
    public void responseWithContentTypeHeaders(){
        assertThat(response.getHeaders().get("Content-Type"), is("image/png; base64"));
    }

}