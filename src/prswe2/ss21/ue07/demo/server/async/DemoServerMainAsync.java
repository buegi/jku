package prswe2.ss21.ue07.demo.server.async;

import java.io.IOException;

import prswe2.ss21.ue07.demo.inout.In;

public class DemoServerMainAsync {

    public static void main(String[] args) throws IOException {

        System.out.println("Server starting");
        DemoServerAsync server = new DemoServerAsync();
        new Thread(() -> {
            try {
                server.start();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }).start();
        System.out.println("Server started");

        In.readLine();

        server.terminate();
        System.out.println("Server terminated");
    }
}