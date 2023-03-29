package docker;

import app.client.EchoClient;
import app.docker.DockerHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class DockerTests {
    private DockerHandler handler = new DockerHandler("", "./docker_test");;

    /*
    @Before
    public void setup() {
        handler = new DockerHandler("", "./docker_test");
    }*/

    @Test
    public void givenClient_whenServerEchosMessage_thenCorrect() {

    }
    /*
    @After
    public void tearDown() throws IOException {
        client.stopConnection();
    }
    */
}
