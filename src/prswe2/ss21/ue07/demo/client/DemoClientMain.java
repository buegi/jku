package prswe2.ss21.ue07.demo.client;

import java.io.IOException;

import prswe2.ss21.ue07.demo.inout.In;
import prswe2.ss21.ue07.demo.inout.Out;

public class DemoClientMain {

    public static void main(String[] args) throws IOException, InterruptedException {

        Out.println("Client application start ");

        String name = args[0];  // use application argument to start with name
        DemoClient client = DemoClient.create(name);
        Thread clientThread = new Thread(() -> {
            try {
                client.start();
            } catch (IOException e1) {
                System.out.println("Not able to start client " + name);
            }
        });

        clientThread.start();

        clientThread.join();

        Out.println("Client application terminated ");
    }
}