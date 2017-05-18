package Class;

/**
 * Created by Eugene on 18-May-17.
 */
public class Main {
    public static void main(String[] args) {
        int items = 4;
        for (int i = 0; i < Math.pow(2,4); i++) {
//            System.out.println(intToBinary(i, 3));
            System.out.println(intToBinaryShifting(i, items));
        }
    }

    public static String intToBinary (int n, int numOfBits) {
        String binary = "";
        for(int i = 0; i < numOfBits; ++i, n/=2) {
            int j = n % 2;
            switch (j) {
                case 0:
                    binary = "0" + binary;
                    break;
                case 1:
                    binary = "1" + binary;
                    break;
            }
        }

        return binary;
    }
    public static String intToBinaryShifting(int n, int numOfBits){
        int bitmask = 1;
        String binary = "";
//        while (n>0){
            for(int i = 0; i < numOfBits; ++i) {
                int j = n & bitmask;
                binary = j + binary;
                n = n >> 1;
            }
//        }
        return binary;
    }
}
