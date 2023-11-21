package greedy;

public class AWrongSchedule {
    /**
     * @return An array of timings that will not give an optimal solution with the proposed soring.
     */
    public static Timings[] counterExample() {
        Timings[] counterExample = new Timings[4];
        counterExample[0] = new Timings(50, 5);
        counterExample[1] = new Timings(30, 10);
        counterExample[2] = new Timings(5, 50);
        counterExample[3] = new Timings(20, 20);

        return counterExample;
    }
}

class Timings implements Comparable<Timings> {

    private final int hardwareTime;

    private final int softwareTime;

    public Timings(int hardwareTime, int softwareTime) {
        this.hardwareTime = hardwareTime;
        this.softwareTime = softwareTime;
    }

    @Override
    public int compareTo(Timings other) {
        return -Integer.compare(this.hardwareTime, other.hardwareTime);
    }

    public int getHardwareTime() {
        return hardwareTime;
    }

    public int getSoftwareTime() {
        return softwareTime;
    }
}
