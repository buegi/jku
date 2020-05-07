package algodat1.ue03;

public class VRBVMApp {

    public static void main(String[] args) {

        VRBVM vrbvm = new VRBVM(20, 0, 125);

        vrbvm.insertCoin(10);
        vrbvm.insertCoin(50);
        vrbvm.insertCoin(50);
        vrbvm.insertCoin(10);

    }
}