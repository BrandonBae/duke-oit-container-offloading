package tcp;

import app.client.EchoClient;
import org.junit.*;

import java.io.IOException;

public class TcpTests {
    private EchoClient client;

    //hardcode VM Ip for now
    //private String vmIp =

    @Before
    public void setup() {
        client = new EchoClient();
        try {
            client.startConnection("127.0.0.1", 4444);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() throws IOException {
        client.stopConnection();
    }
}
