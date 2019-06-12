package Util;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class PublicFileManagerTest {
    private PublicFileManager publicFileManager;
    private File indexHtml;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void SetUp() throws IOException {
        publicFileManager = new PublicFileManager(folder.getRoot().getAbsolutePath() + "/");

        indexHtml = folder.newFile("Index.html");
        PrintWriter writer = new PrintWriter(indexHtml.getAbsoluteFile(), "UTF-8");
        writer.println("The first line");
        writer.println("The second line");
        writer.close();
    }

    @Test
    public void canReadAFile() throws IOException {
        byte[] expectedBody = "The first line\nThe second line\n".getBytes();
        assertThat(publicFileManager.read("Index.html"), is(expectedBody));
    }

    @Test
    public void createHtmlMIMEType() {
        String expectedMIME = "text/html; charset=utf-8";
        assertThat(publicFileManager.MIMEType("Index.html"), is(expectedMIME));
    }

    @Test
    public void createCssMIMEType() {
        String expectedMIME = "text/css; charset=utf-8";
        assertThat(publicFileManager.MIMEType("css/app-stylesheet.css"), is(expectedMIME));
    }

    @Test
    public void createImageMIMEType() {
        String expectedMIME = "image/png; base64";
        assertThat(publicFileManager.MIMEType("/images/wellies.png"), is(expectedMIME));
    }

    @Test
    public void getsFileExtension() {
        String filename = "wellies.png";
        assertThat(publicFileManager.getType(filename), is("png"));
    }

}
