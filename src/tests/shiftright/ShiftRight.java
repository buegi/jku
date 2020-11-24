package tests.shiftright;

public class ShiftRight {

    public static void main(String[] args) {
        int[] test = {1, 2, 3, 4, 5};
        int places = 2;

        printArray(test);
        int[] shifted = shiftWithDrop(test, places);

        printArray(shifted);
    }

    static int[] shiftWithDrop(int[] field, int places) {
        // shift right
        for (int i = 0; i < places;i ++) {
            for (int index = field.length - 2; index >= 0; index--) {
                field[index + 1] = field[index];
            }
            field[i] = 0;
        }
        return field;
    }

    static void printArray(int[] field) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < field.length; i++) {
            sb.append(field[i] + ", ");
        }
        System.out.println(sb.toString());
    }
}