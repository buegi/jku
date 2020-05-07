package swe1.ws17.ue08;

import swe1.input.Input;

public class MessageCompressorTest {

    public static void main(String args[]) {
        MessageCompressor comp = new MessageCompressor();
        System.out.print("Please insert message: ");
        String message = Input.readString();
        comp.compressMessage(message);
    }
}