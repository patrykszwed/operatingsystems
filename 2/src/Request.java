import java.util.Objects;

public class Request implements Comparable<Request> {
    private int numberOfTrack;

    public Request(int numberOfTrack) {
        this.numberOfTrack = numberOfTrack;
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
        Request request = (Request) o;
        return numberOfTrack == request.numberOfTrack;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfTrack);
    }

    @Override
    public int compareTo(Request o) {
        if(this.getNumberOfTrack() > o.getNumberOfTrack())
            return 1;
        if(this.getNumberOfTrack() < o.getNumberOfTrack())
            return -1;
        return 0;
    }
}
