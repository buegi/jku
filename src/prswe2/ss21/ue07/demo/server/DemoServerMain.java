package prswe2.ss21.ue07.demo.server;

import java.io.IOException;

import prswe2.ss21.ue07.demo.inout.In;

public class DemoServerMain {

    public static void main(String[] args) throws IOException {

        System.out.println("Server starting");

        DemoServer server = new DemoServer();
        new Thread(() -> {
            try {
                server.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        System.out.println("Server started, terminate with enter.");

        In.readLine();

        server.terminate();
        System.out.println("Server terminated");
    }
}