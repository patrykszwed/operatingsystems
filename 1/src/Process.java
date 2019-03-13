import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Random;

public class Process implements  Comparable<Process>{

    private int startTime;
    private int processingTime;

    public Process(int startTime, int processingTime) {
        this.startTime = startTime;
        this.processingTime = processingTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Process process = (Process) o;
        return startTime == process.startTime &&
                processingTime == process.processingTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, processingTime);
    }

    @Override
    public int compareTo(Process o) {
        if(this.getProcessingTime() > o.getProcessingTime())
            return 1;
        if(this.getProcessingTime() < o.getProcessingTime())
            return -1;
        return 0;
    }
}