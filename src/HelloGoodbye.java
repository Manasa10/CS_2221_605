// Java code for the above approach
import java.io.*;

class HelloGoodbye {

    // Function to convert number
// to binary of N bits
    static int[] convertToBinary(int num,
                                 int length)
    {

        // Vector to store the number
        int[] bits = new int[length];
        System.out.println("here"+bits[0]+" "+bits[1]);
        if (num == 0) {
            return bits;
        }

        int i = length - 1;
        while (num != 0) {
            bits[i--] = (num % 2);

            // Integer division
            // gives quotient
            num = num / 2;
        }
        return bits;
    }

    // Function to generate all
// N bit binary numbers
    static int[][] getAllBinary(int n)
    {
        int[][] binary_nos = new int[(int)Math.pow(2,n)][];
        int k = 0;

        // Loop to generate the binary numbers
        for (int i = 0; i < Math.pow(2, n); i++) {
            int[] bits = convertToBinary(i, n);
            binary_nos[k++]= bits;
        }

        return binary_nos;
    }

    // Driver code
    public static void main (String[] args)
    {
//
        String st = "abc";
        char[] rr = {'a', 'b', 'c', 'd', 'e'};
        long a = 0L;
        long b = 8L;
        System.out.println(1^2^3^4^5^6^0);

    }
};

