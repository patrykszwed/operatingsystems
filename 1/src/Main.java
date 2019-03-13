import java.util.*;

public class Main {

    private static List<Process> processorsList = new LinkedList<>();

    public static void init(List<Process> processList, int timeOfSimulation) {
        Random rand = new Random();
        Process process;
        for (int i = 0; i < 100; ++i) {
            process = new Process(rand.nextInt(timeOfSimulation) + 1, rand.nextInt(500) + 1);
            processList.add(process);
        }

    }

    public static void main(String[] args) {
        int timeOfSimulation;
        Scanner sc = new Scanner(System.in);
        System.out.println("What is the time of simulation?");
        timeOfSimulation = sc.nextInt();
        init(processorsList, timeOfSimulation);

        fcfs(processorsList, timeOfSimulation);
        sjf(processorsList, timeOfSimulation);
        sjfp(processorsList, timeOfSimulation);

        sc.close();
    }

    public static void fcfs(List<Process> processList, int timeOfSimulation) {
        double sum = 0;
        Queue<Process> processQueue = new LinkedList<>();
        for (int i = 0; i < timeOfSimulation; i++) {
            processQueue.add(processList.get(i));
        }

        int queueSize = processQueue.size();

        int[] btime = new int[queueSize];
        int[] wtime = new int[queueSize];
        int[] service_time = new int[queueSize];
        int[] at = new int[queueSize];


        wtime[0] = 0;
        service_time[0] = 0;

        for(int i = 0; i < queueSize; ++i){
            btime[i] = processQueue.remove().getProcessingTime();
        }

        for(int i = 1; i < queueSize; ++i){
            service_time[i] = service_time[i - 1] + btime[i - 1];
            wtime[i] = service_time[i] - at[i];
            if(wtime[i] < 0)
                wtime[i] = 0;
            sum += wtime[i];
        }

        System.out.println("[FCFS] Average awaiting time = " + sum / processList.size());
    }

    public static void sjf(List<Process> processList, int timeOfSimulation) {
        double sum = 0;
        Queue<Process> processQueue = new PriorityQueue<>();

        for (int i = 0; i < timeOfSimulation; i++) {
            processQueue.add(processList.get(i));
        }

        int queueSize = processQueue.size();

        int[] btime = new int[queueSize];
        int[] wtime = new int[queueSize];
        int[] service_time = new int[queueSize];
        int[] at = new int[queueSize];


        wtime[0] = 0;
        service_time[0] = 0;

        for(int i = 0; i < queueSize; ++i){
            btime[i] = processQueue.remove().getProcessingTime();
        }

        for(int i = 1; i < queueSize; ++i){
            service_time[i] = service_time[i - 1] + btime[i - 1];
            wtime[i] = service_time[i] - at[i];
            if(wtime[i] < 0)
                wtime[i] = 0;
            sum += wtime[i];
        }

        System.out.println("[SJF] Average awaiting time = " + sum / processList.size());
    }

    public static void sjfp(List<Process> processList, int timeOfSimulation) {
        double sum = 0;
        Queue<Process> processQueue = new PriorityQueue<>();

        for (int i = 0; i < timeOfSimulation; i++) {
            processQueue.add(processList.get(i));
        }

        int queueSize = processQueue.size();

        int[] btime = new int[queueSize];
        int[] wtime = new int[queueSize];
        int[] service_time = new int[queueSize];
        int[] at = new int[queueSize];


        wtime[0] = 0;
        service_time[0] = 0;

        for(int i = 0; i < queueSize; ++i){
            btime[i] = processQueue.remove().getProcessingTime();
        }

        int time = 0;

        for(int i = 1; i < queueSize; ++i){
            service_time[i] = service_time[i - 1] + btime[i - 1];
            wtime[i] = service_time[i] - at[i];
            if(wtime[i] < 0)
                wtime[i] = 0;
            sum += wtime[i];
        }

        System.out.println("[SJFP] Average awaiting time = " + sum / processList.size());
    }

    public static void rotative(List<Process> processList) {
        processList.forEach(System.out::println);
    }
}