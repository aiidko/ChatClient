package Client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {
    public static int PORT;
    public static String HOST;
    public static int MESSAGE_INTERVAl;
    public static int MESSAGE_COUNT;

    public static void getProp()
    {
        Properties properties = new Properties();
        FileInputStream propertiesFile = null;
        try {
            propertiesFile = new FileInputStream("Setting/settings");
            properties.load(propertiesFile);

            PORT = Integer.parseInt(properties.getProperty("PORT"));
            MESSAGE_INTERVAl = Integer.parseInt(properties.getProperty("MESSAGE_INTERVAl"));
            MESSAGE_COUNT = Integer.parseInt(properties.getProperty("MESSAGE_COUNT"));
            HOST = properties.getProperty("HOST");


        } catch (FileNotFoundException ex) {
            System.err.println("Properties config file not found");
        } catch (IOException ex) {
            System.err.println("Error while reading file");
        } finally {
            try {
                propertiesFile.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }
}
