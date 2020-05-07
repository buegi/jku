package swe1.ws17.ue08;

import swe1.input.Input;

public class CipherKeyTest {

    public static void main(String args[]) {
        CipherKey msg = new CipherKey();
        int menu = 10;
        do {
            System.out.println("1) Change Key");
            System.out.println("2) Encrypt");
            System.out.println("3) Decrypt");
            System.out.println("0) Quit");
            menu = Input.readInt();

            switch (menu) {

                case 0:
                    System.out.println("Bye!");
                    break;

                case 1:
                    System.out.print("Enter new Key: ");
                    String key = Input.readString();
                    msg.setKey(key);
                    break;

                case 2:
                    System.out.print("Enter the Message: ");
                    String encrypt = Input.readString();
                    System.out.println("Encrypted Message:" + msg.encrypt(encrypt));
                    break;

                case 3:
                    System.out.print("Enter the Message: ");
                    String decrypt = Input.readString();
                    System.out.println("Decrypted Message:" + msg.encrypt(decrypt));
                    break;

            }
        } while (menu > 0);

    }
}