package dynamicprogramming;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * A weighted interval schedule is a schedule of jobs where each of the jobs has a weight attached.
 * Every job 𝑗𝑖, for 0<𝑖≤𝑛 has a start time 𝑠𝑖, an end time 𝑓𝑖, and has a weight of 𝑣𝑖.
 * The goal is to find a schedule where all jobs are compatible, there are no two jobs that overlap,
 * while maximizing the weight of the schedule.
 * In this exercise you are tasked to find the predecessors of jobs as our dynamic programming approach
 * to solving the problem requires them.
 * Given the following jobs:
 * s f v
 * - - -
 * 0 3 3
 * 1 4 5
 * 3 8 7
 * We expect [??, 0, 0, 1] as our output, where ?? is ignored.
 * As you can see we use zero to denote there is no predecessor.
 * Since we ignore the 0-index value in our arrays, this works out great!
 */

public class WeightedIntervalScheduling {

    static int[] computeOptMemoized;
    static Interval[] jobs;
    static int[] p;
    static List<Integer> solution;

    public static List<Integer> solve(int n, int[] s, int[] f, int[] v) {
        // We will be using memoized recursion and trace back to find the set of intervals.
        computeOptMemoized = new int[n + 1];
        computeOptMemoized[0] = 0;

        // Sort array by increasing final time.
        jobs = new Interval[n + 1];
        for (int i = 1; i <= n; i++) {
            jobs[i] = new Interval(s[i], f[i], v[i]);
        }

        Arrays.sort(jobs, 1, n + 1);

        // Compute p[j] - largest index such that i and j are disjoint.
        Pair[] startTimes = new Pair[n];
        Pair[] endTimes = new Pair[n];

        for (int i = 1; i <= n; i++) {
            startTimes[i - 1] = new Pair(i, s[i]);
            endTimes[i - 1] = new Pair(i, f[i]);
        }

        Arrays.sort(startTimes);
        Arrays.sort(endTimes);

        int j = 0;
        p = new int[n + 1];
        for (int i = 0; i < n; i++) {
            if (endTimes[j].value > startTimes[i].value) {
                // this interval has no predecessor.
                p[startTimes[i].index] = 0;
            } else {
                for (; j < n - 1; j++) {
                    // find the largest index with an interval
                    // whose end time is smaller than our current interval.
                    if (endTimes[j + 1].value > startTimes[i].value) break;
                }

                p[startTimes[i].index] = endTimes[j].index;
            }
        }

        // Compute OPT using memoized recursion.
//        int opt = computeOpt(n);

        // Compute OPT using iterative solution.
        int[] mem = new int[n + 1];
        mem[0] = 0;

        // Either skip this job and get mem[j-1]
        // or include this job and get the optimal value for the previous compatible job.
        for (j = 1; j <= n; j++) {
            if (mem[j] == 0) {
                mem[j] = Math.max(v[j] + mem[p[j]], mem[j-1]);
            }
        }

        // Trace back the solution.
        LinkedList<Integer> jobs = new LinkedList<>();
        int i = n;
        while (i > 0){
            if (v[i] + mem[p[i]] >= mem[i - 1]) {
                jobs.addFirst(i);
                i = p[i];
            } else {
                i--;
            }
        }

        return jobs;

    }

    // OPT(j) = max(v_j + OTP(p[j]), OTP(j-1))
    public static int computeOpt(int j){
        if (j == 0) return 0;

        if (computeOptMemoized[j] != 0) return computeOptMemoized[j];

        else {
            computeOptMemoized[j] = Math.max(jobs[j].weight + computeOpt(p[j]), computeOpt(j - 1));
            return computeOptMemoized[j];
        }
    }

    public static void findSolution(int j) {
        if (j == 0) return;
        else if (jobs[j].weight + computeOptMemoized[p[j]] > computeOptMemoized[j - 1]) {
            solution.add(j);
            findSolution(p[j]);
        }
        else {
            findSolution(j - 1);
        }
    }
}

class Interval implements Comparable<Interval>{
    int start;
    int end;
    int weight;

    public Interval(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Interval interval) {
        int res = this.end - interval.end;
        if (res == 0) return this.start - interval.start;
        return res;
    }
}

class Pair implements Comparable<Pair> {
    int index;
    int value;

    public Pair(int index, int value) {
        this.index = index;
        this.value = value;
    }

    @Override
    public int compareTo(Pair pair) {
        int res = this.value - pair.value;
        if (res == 0) return this.index - pair.index;
        return res;
    }
}

class TestWeightedIntervalScheduling {

    @Test
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    public void example() {
        int[] s = {0, 0, 1, 3};
        int[] f = {0, 3, 4, 8};
        int[] v = {0, 3, 5, 7};
        int[] p = {0, 0, 0, 1};
        int[] solution = WeightedIntervalScheduling.solve(3, s, f, v);
        solution[0] = 0;
        Assertions.assertArrayEquals(p, solution);
    }
}

