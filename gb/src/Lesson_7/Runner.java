package Lesson_7;

import Lesson_7.client.Client;
import Lesson_7.server.MServer;
import javafx.application.Application;


public class Runner {
    public static void main(String[] args) {
        new Thread(() -> new MServer()).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        new Thread(() -> Application.launch(Client.class, args)).start();
    }
}