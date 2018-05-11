import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

class Session extends Thread {
    private Socket socket;
    private volatile boolean isCancelled;
    private Gson gson = new GsonBuilder().create();

    Session(String ip, int port) throws IOException {
        isCancelled = false;
        socket = new Socket(ip, port);
    }

    void cancel() {
        isCancelled = true;
    }


    @Override
    public void run() {
        byte[] buf = new byte[128];
        try (InputStream is = socket.getInputStream()) {
            while (!isCancelled) {
                int len = is.read(buf);
                if (len == -1) {
                    break;
                }

                String message = new String(buf, 0, len);
                System.out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Session Thread exit");
    }

    public void send (String msg) throws IOException {
        OutputStream os = socket.getOutputStream();
        os.write(msg.getBytes());
    }
}
