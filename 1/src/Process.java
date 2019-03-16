import java.util.Objects;

public class Process implements  Comparable<Process>{

    private int arrivalTime;
    private int serviceTime;

    public Process(int arrivalTime, int processingTime) {
        this.arrivalTime = arrivalTime;
        this.serviceTime = processingTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Process process = (Process) o;
        return arrivalTime == process.arrivalTime &&
                serviceTime == process.serviceTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(arrivalTime, serviceTime);
    }

    @Override
    public int compareTo(Process o) {
        if(this.getServiceTime() > o.getServiceTime())
            return 1;
        if(this.getServiceTime() < o.getServiceTime())
            return -1;
        return 0;
    }
}