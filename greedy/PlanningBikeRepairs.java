package greedy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * Mike runs a bike repair service for students.
 * Students can plan a time to bring in their bike on his website, where they also enter a description of what is broken.
 * Mike is very good at his job and he can flawlessly predict how long each repair will take.
 * To cater to the need of students to have a working bike at all time,
 * he promises his customers to finish the repair as soon as possible after the bike is brought in.

 * Now Mike wants to know how many of his employees to schedule for a day.
 * He has the list of repairs for this day, and he wants you to develop an algorithm to tell him how many people he needs.
 * For each repair you get the start time, as well as the duration.
 */
public class PlanningBikeRepairs {
    public static int fixMyBikesPlease(int n, int[] starttimes, int[] durations) {
        List<TuplePlanningBikeRepairs> repairs = new ArrayList<>();
        for (int i = 1; i < starttimes.length; i++) {
            repairs.add(new TuplePlanningBikeRepairs(starttimes[i], durations[i]));
        }

        Collections.sort(repairs);

        int employees = 0;
        int depth = 0;
        for (int i = 1; i < repairs.size(); i++) {
            int count = 1;
            TuplePlanningBikeRepairs currJob = repairs.get(i);
            for (int j = 0; j < i; j++) {
                TuplePlanningBikeRepairs prevJob = repairs.get(j);
                if (prevJob.startTime + prevJob.duration > currJob.startTime) {
                    count++;
                }
            }

            if (depth < count) depth = count;
        }

        return depth;
    }
}

class TuplePlanningBikeRepairs implements Comparable<TuplePlanningBikeRepairs> {
    int startTime;
    int duration;

    public TuplePlanningBikeRepairs(int startTime, int duration) {
        this.startTime = startTime;
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TuplePlanningBikeRepairs that = (TuplePlanningBikeRepairs) o;
        return startTime == that.startTime && duration == that.duration;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, duration);
    }

    @Override
    public int compareTo(TuplePlanningBikeRepairs tuplePlanningBikeRepairs) {
        int res = this.startTime - tuplePlanningBikeRepairs.startTime;
        if (res == 0) return this.duration - tuplePlanningBikeRepairs.duration;
        return res;
    }
}

class PlanningBikeRepairsTest {

    @Test
    public void example() {
        int n = 6;
        int[] starttimes = {0, 6, 3, 1, 8, 3, 1};
        int[] durations = {0, 1, 5, 2, 3, 3, 4};
        Assertions.assertEquals(3, PlanningBikeRepairs.fixMyBikesPlease(n, starttimes, durations));
    }
}
