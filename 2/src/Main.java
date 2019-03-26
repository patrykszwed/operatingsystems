import java.util.*;

public class Main {

    private static List<Request> requestsList = new LinkedList<>();

    public static void init(List<Request> requestsList, int numberOfRequests, int numberOfTracks) {
        Random rand = new Random();
        Request request;
        for (int i = 0; i < numberOfRequests; ++i) {
            request = new Request(rand.nextInt(numberOfTracks) + 1);
            requestsList.add(request);
        }
    }

    public static void initRequestsWithDeadline(List<RequestWithDeadline> requestsListWithDeadline, int numberOfRequests, int numberOfTracks) {
        Random rand = new Random();
        Request request;
        for (int i = 0; i < numberOfRequests; ++i) {
            request = new Request(rand.nextInt(numberOfTracks) + 1);
            requestsList.add(request);
        }
    }

    public static void main(String[] args) {
        int numberOfRequests, numberOfTracks, maxDeadline;
        Scanner sc = new Scanner(System.in);
        System.out.println("How many requests do you want to simulate?");
        numberOfRequests = sc.nextInt();
        System.out.println("How many tracks does the disk have?");
        numberOfTracks = sc.nextInt();
        System.out.println("What is the maximum value of deadline?");
        maxDeadline = sc.nextInt();
        init(requestsList, numberOfRequests, numberOfTracks);
        initRequestsWithDeadline(requestsList)

        fcfs(requestsList);
        sstf(requestsList);
        scan(requestsList, numberOfTracks);
        cscan(requestsList, numberOfTracks);
        edf(requestsList);
        fdscan(requestsList);

        sc.close();
    }

    public static void fcfs(List<Request> requestsList){
        System.out.println("[FCFS] Average rotation time = " + findAvgRotationTime(requestsList) + " rotations.");
    }

    public static void sstf(List<Request> requestsList){
        int noRequests = requestsList.size();
        List<Boolean> isRequestServed = new LinkedList<>();
        for(int i = 0; i < noRequests; ++i){
            isRequestServed.add(false);
        }

        boolean oneRequestServed;
        int min, minIndex = 0, i = 0, noOfTrack = 0;  // i - position of disk head
        float sum = 0;

        while(true){
            oneRequestServed = false;
            min = Integer.MAX_VALUE;
            for (int j = 0; j < noRequests; j++) {
                if(!isRequestServed.get(j)){
                    int distance = Math.abs(requestsList.get(j).getNumberOfTrack() - i);
                    if(distance < min){
                        min = distance;
                        minIndex = j;
                        noOfTrack = requestsList.get(j).getNumberOfTrack();
                        oneRequestServed = true;
                    }
                }
            }
            isRequestServed.set(minIndex, true);
            if(!oneRequestServed)
                break;
            sum += min;
            i = noOfTrack;
        }

        System.out.println("[SSTF] Average rotation time = " + sum / noRequests + " rotations.");
    }

    public static void scan(List<Request> requestsList, int numberOfTracks){
        int middleTrack = requestsList.size() / 2, noRequests = requestsList.size(), elementsLeft = requestsList.size();
        List<Request> sortedList = new LinkedList<>(requestsList);
        Collections.sort(sortedList);
        //sortedList.forEach(elem -> System.out.println("elem.getNoTrack() = " + elem.getNumberOfTrack()));

        float sum = 0;
        int previousTrack = sortedList.get(middleTrack).getNumberOfTrack();   // !!!can break if in the middle there is no request

        for(int i = middleTrack; i < noRequests; ++i){  // from middle up to the end of disk
            sum += Math.abs(previousTrack - sortedList.get(i).getNumberOfTrack());
            previousTrack = sortedList.get(i).getNumberOfTrack();
        }

        sum += Math.abs(numberOfTracks - middleTrack);    // from end to middle

        for(int i = middleTrack; i >= 0; --i){  // from middle to beginning
            sum += Math.abs(previousTrack - sortedList.get(i).getNumberOfTrack());
            previousTrack = sortedList.get(i).getNumberOfTrack();
        }

        System.out.println("[SCAN] Average rotation time = " + sum / noRequests + " rotations.");
    }

    public static void cscan(List<Request> requestsList, int numberOfTracks){
        int middleTrack = requestsList.size() / 2, noRequests = requestsList.size(), elementsLeft = requestsList.size();
        List<Request> sortedList = new LinkedList<>(requestsList);
        Collections.sort(sortedList);
        //sortedList.forEach(elem -> System.out.println("elem.getNoTrack() = " + elem.getNumberOfTrack()));

        float sum = 0;
        int previousTrack = sortedList.get(middleTrack).getNumberOfTrack();   // !!!can break if in the middle there is no request

        for(int i = middleTrack; i < noRequests; ++i){  // from middle up to the end of disk
            sum += Math.abs(previousTrack - sortedList.get(i).getNumberOfTrack());
            previousTrack = sortedList.get(i).getNumberOfTrack();
        }

        sum += numberOfTracks;    // from end to beginning

        for(int i = 0; i <= middleTrack; i++){  // from beginning to middle
            sum += Math.abs(previousTrack - sortedList.get(i).getNumberOfTrack());
            previousTrack = sortedList.get(i).getNumberOfTrack();
        }

        System.out.println("[CSCAN] Average rotation time = " + sum / noRequests + " rotations.");
    }

    public static float findAvgRotationTime(List<Request> requestsList) {
        int noRequests = requestsList.size();
        float sum = 0;
        int previousTrack = requestsList.get(0).getNumberOfTrack();

        for (int i = 1; i < noRequests; ++i) {
            sum += Math.abs(previousTrack - requestsList.get(i).getNumberOfTrack());
            previousTrack = requestsList.get(i).getNumberOfTrack();
        }

        return sum / noRequests;
    }

    public static void edf(List<RequestWithDeadline> requestWithDeadlineList){

    }

    public static void fdscan(List<RequestWithDeadline> requestWithDeadlineList){

    }
}
