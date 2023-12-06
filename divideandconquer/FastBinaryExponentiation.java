package divideandconquer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FastBinaryExponentiation {
    /**
     * Computes the matrix C, containing the values for a^b, for all values in a and b. Note that
     * you may not use math.pow but should implement your own fast binary exponentiation algorithm.
     *
     * @param a array containing the bases
     * @param b array containing the exponents
     * @return matrix C where entry (i,j) contains a_i^b_j
     */
    public static int[][] computeC(int[] a, int[] b) {
        int[][] comp = new int[a.length][b.length];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                int el = binaryPower(a[i], b[j]);
                comp[i][j] = el;
            }
        }

        return comp;
    }

    public static int binaryPower(int a, int b) {
        if (b == 0) return 1;
        int result = binaryPower(a, b / 2);

        if (b % 2 == 0) return result * result;
        return result * result * a;
    }
}

class FastBinaryExponentiationTest {

    @Test
    public void testExampleA() {
        int[] a = new int[] {3, -2, 6};
        int[] b = new int[] {2, 5, 8};
        int[][] c = FastBinaryExponentiation.computeC(a, b);
        int[][] expected = new int[][] {{9, 243, 6561}, {4, -32, 256}, {36, 7776, 1679616}};
        Assertions.assertArrayEquals(expected, c);
    }
}
