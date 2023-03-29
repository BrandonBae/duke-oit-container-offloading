package tcp;

import app.client.EchoClient;
import org.junit.*;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TcpTests {
    private EchoClient client;

    //hardcode VM Ip for now
    //private String vmIp =
    /*
    @Before
    public void setup() {
        client = new EchoClient();
        try {
            //client.startConnection("127.0.0.1", 6666);
            client.startConnection("152.3.54.23", 6666);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenClient_whenServerEchosMessage_thenCorrect() {
        String resp1 = null;
        String resp2 = null;
        String resp3 = null;
        String resp4 = null;
        try {
            resp1 = client.sendMessage("hello");
            resp2 = client.sendMessage("world");
            resp3 = client.sendMessage("!");
            resp4 = client.sendMessage(".");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertEquals("hello", resp1);
        assertEquals("world", resp2);
        assertEquals("!", resp3);
        assertEquals("good bye", resp4);
    }

    @After
    public void tearDown() throws IOException {
        client.stopConnection();
    }

     */
}
