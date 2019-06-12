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

public class CssHandlerTest {
    private CssHandler cssHandler;
    private Response response;
    private Request request;
    private PublicFileManager publicFileManager;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void SetUp() throws InvocationTargetException, IllegalAccessException, IOException {
        File cssFolder = folder.newFolder("css");
        File css = new File(cssFolder.getAbsolutePath() + "/app-stylesheet.css");

        PrintWriter writer = new PrintWriter(css.getAbsoluteFile(), "UTF-8");
        writer.println("*,:after,:before{box-sizing:inherit}");
        writer.close();

        publicFileManager = new PublicFileManager(folder.getRoot().getAbsolutePath() + "/");
        cssHandler = new CssHandler(publicFileManager);
        request =  new Request("GET", "/","HTTP/1.1", new HashMap<>(), "");
        response = cssHandler.respondToRequest(request);
    }

    @Test
    public void responseOK() {
        assertThat(response.getStatusLine(), is("HTTP/1.1 200 OK"));
    }

    @Test
    public void responseHasHTMLinBody(){
        String bodyExpected = "*,:after,:before{box-sizing:inherit}\n";
        assertThat(response.getBody(), is(bodyExpected));
    }

    @Test
    public void responseWithContentLengthHeaders(){
        assertThat(response.getHeaders().get("Content-Length"), is("37"));
    }

    @Test
    public void responseWithContentTypeHeaders(){
        assertThat(response.getHeaders().get("Content-Type"), is("text/css; charset=utf-8"));
    }

}
