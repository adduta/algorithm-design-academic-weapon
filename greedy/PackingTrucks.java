package greedy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

/**
 * As the owner of a shipping company, you won a large contract requiring you to ship a certain amount of boxes 𝑛
 * to a certain destination.
 * The contract gives the weight of the boxes in order of their arrival.
 * This is also the order in which the boxes must be shipped.
 * To maximize profit, you want to minimize the number of trucks used.
 * Each truck can carry boxes up to a total of weight 𝑊.
 * Every box 𝑖 with 1 ≤ 𝑖 ≤ 𝑛 has a weight 𝑤𝑖≤𝑊.
 * Implement an algorithm that determines the minimal amount of trucks needed to ship the boxes to the destination.
 * If we can carry at most 48 weight units in one truck (𝑊=48) then the following shipment of boxes
 * should result in an output of 3.
 */
public class PackingTrucks {
    /**
     * @param n the number of packages
     * @param weights the weights of all packages 1 through n. Note that weights[0] should be
     *     ignored!
     * @param maxWeight the maximum weight a truck can carry
     * @return the minimal number of trucks required to ship the packages _in the given order_.
     */
    public static int minAmountOfTrucks(int n, int[] weights, int maxWeight) {
        int trucks = 1;
        int currWeight = 0;

        if (n == 0) return 0;

        for (int i = 1; i <= n; i++) {
            if (currWeight + weights[i] > maxWeight) {
                trucks++;
                currWeight = 0;
            }

            currWeight += weights[i];
        }

        return trucks;
    }
}

class TestSuite {

    @Test
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    public void example() {
        int n = 4;
        int[] weights = {0, 41, 29, 12, 26};
        int maxWeight = 48;
        Assertions.assertEquals(3, PackingTrucks.minAmountOfTrucks(n, weights, maxWeight));
    }
}