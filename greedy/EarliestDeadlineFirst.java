package greedy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Arrays;
import java.util.Objects;

/**
 * Implement the “Earliest Deadline First” algorithm you’ve just read about.
 * Note that the data is not yet sorted.
 */
public class EarliestDeadlineFirst {
    /**
     * @param n the number of jobs
     * @param t the times of jobs 1 through n, NB: you should ignore t[0]
     * @param d the deadlines of the jobs 1 through n. NB: you should ignore deadlines[0]
     * @return the minimised maximum lateness L.
     */
    public static int solve(int n, int[] t, int[] d) {
        // Sort the data increasing by deadline times.
        TupleEarliestDeadlineFirst[] deadlines = new TupleEarliestDeadlineFirst[n + 1];
        for (int i = 1; i < d.length; i++) {
            deadlines[i] = new TupleEarliestDeadlineFirst(d[i], t[i]);
        }

        // Sorting the array.
        Arrays.sort(deadlines, 1, deadlines.length);

        // Compute the total lateness of the jobs.
        int lateness = Integer.MIN_VALUE;
        int startTime = 0;
        for (int i = 1; i < deadlines.length; i++) {
            int currLateness = ((startTime + deadlines[i].time) - deadlines[i].deadline);
            if (lateness < currLateness) lateness = currLateness;
            startTime += deadlines[i].time;
        }

        return lateness;
    }
}

class TupleEarliestDeadlineFirst implements Comparable<TupleEarliestDeadlineFirst> {
    int deadline;
    int time;

    public TupleEarliestDeadlineFirst(Integer deadline, Integer time) {
        this.deadline = deadline;
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TupleEarliestDeadlineFirst tuple = (TupleEarliestDeadlineFirst) o;
        return time == tuple.time && deadline == tuple.deadline;
    }

    @Override
    public int hashCode() {
        return Objects.hash(deadline, time);
    }

    @Override
    public int compareTo(TupleEarliestDeadlineFirst tupleEarliestDeadlineFirst) {
        int res = this.deadline - tupleEarliestDeadlineFirst.deadline;
        if (res == 0) return this.time - tupleEarliestDeadlineFirst.time;
        return res;
    }
}


class EarliestDeadlineFirstTest {
    @Test
    @Timeout(500)
    public void example() {
        int n = 5;
        int[] t = {0, 1, 2, 1, 3, 1};
        int[] d = {0, 5, 1, 1, 1, 4};
        /* We order by deadline as the book tells us to.
        So we get the following order of jobs: 23451
        f(2) = 2, so late 1.
        f(3) = 3, so late 2.
        f(4) = 6, so late 5.
        f(5) = 7, so late 3.
        f(1) = 8, so late 3.

        Thus, the answer is 5.
         */
        Assertions.assertEquals(5, EarliestDeadlineFirst.solve(n, t, d));
    }
}