import java.util.Objects;

public class RequestWithDeadline implements Comparable<RequestWithDeadline> {
    private int numberOfTrack;
    private int deadline;

    public RequestWithDeadline(int numberOfTrack, int deadline) {
        this.numberOfTrack = numberOfTrack;
        this.deadline = deadline;
    }

    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    public int getNumberOfTrack() {
        return numberOfTrack;
    }

    public void setNumberOfTrack(int numberOfTrack) {
        this.numberOfTrack = numberOfTrack;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestWithDeadline that = (RequestWithDeadline) o;
        return numberOfTrack == that.numberOfTrack &&
                deadline == that.deadline;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfTrack, deadline);
    }

    @Override
    public int compareTo(RequestWithDeadline o) {
        if(this.getDeadline() < o.getDeadline())
            return -1;
        if(this.getDeadline() > o.getDeadline())
            return 1;
        return Integer.compare(this.getNumberOfTrack(), o.getNumberOfTrack());
    }
}
