package Main;

import Client.Client;
import Client.*;



import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("(B(ot) / C(lient))?");
            char answer = Character.toLowerCase(in.nextLine().charAt(0));
            if (answer == 'b') {
                System.out.println("How many bots do you need?");
                int count=  in.nextInt();
                for (int i = 0; i < count; i++)
                    new Bot().start();
            } else if (answer == 'c') {
                try {
                    new Client();
                } catch (Exception ex) {
                    System.err.println("Please try again in a few minutes.");
                }

            }
    }
}
