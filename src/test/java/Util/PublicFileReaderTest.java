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


public class PublicFileReaderTest {
    private PublicFileReader publicFileReader;
    private File indexHtml;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void SetUp() throws IOException {
        publicFileReader = new PublicFileReader(folder.getRoot().getAbsolutePath() + "/");

        indexHtml = folder.newFile("Index.html");
        PrintWriter writer = new PrintWriter(indexHtml.getAbsoluteFile(), "UTF-8");
        writer.println("The first line");
        writer.println("The second line");
        writer.close();
    }

    @Test
    public void canReadAFile() throws IOException {
        String expectedBody = "The first line\nThe second line\n";
        assertThat(publicFileReader.read("Index.html"), is(expectedBody));
    }

    @Test
    public void createMIMEType() {
        String expectedMIME = "text/html; charset=utf-8";
        assertThat(publicFileReader.MIMEType("Index.html"), is(expectedMIME));
    }

}
