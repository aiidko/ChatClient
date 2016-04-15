package Client;



import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Bot extends Thread {
    private BufferedReader in;
    private PrintWriter out;

    @Override
    public void run() {
        Scanner scan = new Scanner(System.in);
        Config.getProp();
        try {
            Socket socket = new Socket(Config.HOST, Config.PORT);
            in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(
                    socket.getOutputStream()), true);
            new Resender().start();

            String str = "";
            for(int i=0;i<Config.MESSAGE_COUNT;i++) {
                Thread.sleep(Config.MESSAGE_INTERVAl);
                str= String.valueOf(Math.random() * 10000);
                out.println(str);
            }
            Thread.sleep(1000);
            out.println("/exit");
        } catch (Exception e) {
            out.println("Good bye!");
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
