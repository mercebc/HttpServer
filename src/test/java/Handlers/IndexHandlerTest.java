package Handlers;

import Core.Request;
import Core.Response;
import Util.PublicFileReader;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class IndexHandlerTest {
    private IndexHandler indexHandler;
    private Response response;
    private Request request;
    private PublicFileReader publicFileReader;
    private File index;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void SetUp() throws InvocationTargetException, IllegalAccessException, IOException {
        index = folder.newFile("Index.html");
        PrintWriter writer = new PrintWriter(index.getAbsoluteFile(), "UTF-8");
        writer.println("The first line");
        writer.println("The second line");
        writer.close();

        publicFileReader = new PublicFileReader(folder.getRoot().getAbsolutePath() + "/");
        indexHandler = new IndexHandler(publicFileReader);
        request =  new Request("GET", "/","HTTP/1.1", new HashMap<>(), "");
        response = indexHandler.respondToRequest(request);
    }

    @Test
    public void responseOK() {
        assertThat(response.getStatusLine(), is("HTTP/1.1 200 OK"));
    }

    @Test
    public void responseHasHTMLinBody(){
        String bodyExpected = "The first line\nThe second line\n";
        assertThat(response.getBody(), is(bodyExpected));
    }

    @Test
    public void responseWithContentLengthHeaders(){
        assertThat(response.getHeaders().get("Content-Length"), is("31"));
    }

    @Test
    public void responseWithContentTypeHeaders(){
        assertThat(response.getHeaders().get("Content-Type"), is("text/html; charset=utf-8"));
    }

}