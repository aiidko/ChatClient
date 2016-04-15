package Client;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    private BufferedReader in;
    private PrintWriter out;
    private Socket socket;


    public Client() {
        Scanner scan = new Scanner(System.in);
        Config.getProp();
        try {
            socket = new Socket(Config.HOST,Config.PORT);
            in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream(), StandardCharsets.UTF_8));
            out = new PrintWriter(new OutputStreamWriter(
                    socket.getOutputStream(), StandardCharsets.UTF_8), true);
            new Resender().start();

            String str = "";
            while (!str.equals("/exit")) {
                str = scan.nextLine();
                out.println(str);
            }
        } catch (Exception e) {
            out.println("Good bye!");
        } finally {
            close();
        }
    }

    private void close() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            System.err.println("Something went wrong.");
        }
    }

    private class Resender extends Thread {

        public Resender() {

        }

        @Override
        public void run() {
            try {
                while (true) {
                    if (in.ready()) {
                        String str = in.readLine();
                        System.out.println(str);
                        if (str.equals("server:kicked")) {
                            break;
                        }

                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
