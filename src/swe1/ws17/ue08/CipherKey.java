package swe1.ws17.ue08;

public class CipherKey {

    private String key = "I LOVE ME";
    private static final String keyindex = "!ABCDEFGHIJKLMNOPQRSTUVWXYZ ";

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        for (int i = 0; i < key.length(); i++) {
            if (key.charAt(i) < 32 || key.charAt(i) > 126 || key.isEmpty() || key == null || key == "") {
                System.out.println("No valid value(s) entered!");
                return;
            }
        }
        this.key = key;
    }

    public String encrypt(String message) {
        for (int i = 0; i < message.length(); i++) {
            if (message.charAt(i) < 32 || message.charAt(i) > 126 || message.isEmpty() || message == null
                    || message == "") {
                System.out.println("No valid value(s) entered!");
                return null;
            }
        }

        String encryptedmessage = "";
        int[] keyasc = new int[message.length()];
        int[] messageasc = new int[message.length()];
        int[] ascvalue = new int[message.length()];
        String newkey = key;

        for (int j = 0; j < message.length(); j++) {
            if (j >= newkey.length()) {
                newkey = newkey + key;
            }

            messageasc[j] = (int) message.charAt(j);
            keyasc[j] = (int) keyindex.indexOf(newkey.charAt(j));
            ascvalue[j] = messageasc[j] + keyasc[j];

            if (ascvalue[j] > 126) {
                ascvalue[j] = ascvalue[j] - 95;
            }

            encryptedmessage += (char) ascvalue[j];
        }
        key = newkey;
        return encryptedmessage;

    }

    public String decrypt(String message) {
        // String originalmessage = message;
        String decryptedmessage = "";
        int[] keyasc = new int[message.length()];
        int[] messageasc = new int[message.length()];
        int[] ascvalue = new int[message.length()];
        String newkey = key;

        for (int j = 0; j < message.length(); j++) {
            if (j >= newkey.length()) {
                newkey = newkey + key;
            }

            messageasc[j] = (int) message.charAt(j);
            keyasc[j] = (int) keyindex.indexOf(newkey.charAt(j));
            ascvalue[j] = messageasc[j] - keyasc[j];

            if (ascvalue[j] > 126) {
                ascvalue[j] = ascvalue[j] - 95;
            }

            decryptedmessage += (char) ascvalue[j];
        }

        return decryptedmessage;
    }
}