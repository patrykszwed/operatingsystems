import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

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

    public static void main(String[] args) {
        int numberOfRequests, numberOfTracks;
        Scanner sc = new Scanner(System.in);
        System.out.println("How many requests do you want to simulate?");
        numberOfRequests = sc.nextInt();
        System.out.println("How many tracks does the disk have?");
        numberOfTracks = sc.nextInt();
        init(requestsList, numberOfRequests, numberOfTracks);

//        fcfs(processorsList);
//        sjf(processorsList);
//        sjfp(processorsList);
//        rrob(processorsList, quantum);

        sc.close();
    }
}
