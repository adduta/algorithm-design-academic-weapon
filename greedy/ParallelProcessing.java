package greedy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class ParallelProcessing {
    /**
     * We have m processors and n jobs.
     * Each job 𝑖 in {1,…,𝑛} has a processing time of exactly 1 hour.
     * Furthermore, each job 𝑖 has an integer deadline 𝑑𝑖 in hours.
     * The aim is to find a start time 𝑠𝑖 and processor 𝑝𝑖 for each job such that
     * no jobs are run at the same processor at the same time and such that
     * the maximum lateness over all jobs is minimized.
     * The lateness is the time a job finishes compared to its deadline, defined here by 𝑠𝑖 + 1 - 𝑑𝑖.
     * The objective thus is to minimize 𝑚𝑎𝑥𝑖{𝑠𝑖+1−𝑑𝑖}.
     */

    /**
     * @param n the number of jobs
     * @param m the number of processors
     * @param deadlines the deadlines of the jobs 1 through n. NB: you should ignore deadlines[0]
     * @return the minimised maximum lateness.
     */
    public static int solve(int n, int m, int[] deadlines) {
        Job[] totalJobs = new Job[deadlines.length];
        Arrays.sort(deadlines, 1, deadlines.length);

        int processors = m;
        int startTime = 0;
        for (int i = 1; i < deadlines.length; i++) {
            totalJobs[i] = new Job(startTime, deadlines[i]);
            processors--;

            // Reset the available processors and move to the next time slot.
            if (processors == 0) {
                startTime += 1;
                processors = m;
            }
        }

        int lateness = Integer.MIN_VALUE;
        for (int i = 1; i < totalJobs.length; i++) {
            int currLateness = (totalJobs[i].startTime + 1 - totalJobs[i].deadline);
            if (lateness < currLateness) lateness = currLateness;
        }

        return lateness;
    }

}

class Job implements Comparable<Job> {
    int startTime;
    int deadline;

    public Job(int startTime, int deadline) {
        this.startTime = startTime;
        this.deadline = deadline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return startTime == job.startTime && deadline == job.deadline;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, deadline);
    }


    @Override
    public int compareTo(Job job) {
        int res = this.deadline - job.deadline;
        if (res == 0) return this.startTime - job.startTime;

        return res;
    }
}

class ParallelProcessingTest {

    @Test
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    public void example() {
        int n = 5;
        int m = 2;
        int[] deadlines = {0, 3, 1, 1, 1, 2};
        Assertions.assertEquals(1, ParallelProcessing.solve(n, m, deadlines));
    }
}
