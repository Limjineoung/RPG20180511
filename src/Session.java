import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.Socket;

class Session extends Thread {
    private Socket socket;
    private volatile boolean isCancelled;
    private Gson gson = new GsonBuilder().create();
    private Window onSessionListener;

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
        try (InputStream is = socket.getInputStream();
             DataInputStream dis = new DataInputStream(is)) {
            while (!isCancelled) {
                if(dis.available() > -1) {
                    int len = dis.readInt();
                    int data = readn(dis,buf,len);
                    if (data == -1) {
                        break;
                    }

                    String jsonData = new String(buf, 0, len);
                    Protocol.FromUpdateProtocol(jsonData);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Session Thread exit");
    }

    public void send (String msg) throws IOException {
        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        System.out.println(msg);
        dos.writeInt(msg.getBytes().length);
        dos.write(msg.getBytes());
    }

    private int readn(InputStream is, byte[] buf, int size) throws IOException {
        int left = size;
        int offset = 0;

        while (left > 0) {
            int len = is.read(buf, offset, left);
            if (len == -1)
                return -1;
            left -= len;
            offset += len;
        }
        return size;
    }

    public void setOnSessionListener(Window onSessionListener) {
        this.onSessionListener = onSessionListener;
    }
}
