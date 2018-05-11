import java.io.IOException;

public class Protocol {
    public static void MoveProtocol(String direction) throws IOException {
        Window.session.send("{\"Move\",\"body\":{\"direction\":\"" + direction + "\"}}");
    }
}
