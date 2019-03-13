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
        rrob(processorsList, timeOfSimulation);

        sc.close();
    }

    private static void rrob(List<Process> processList, int timeOfSimulation) {
        double sum = 0;
        Queue<Process> processQueue = new LinkedList<>();
        for (int i = 0; i < timeOfSimulation; i++) {
            processQueue.add(processList.get(i));
        }

        int queueSize = processQueue.size(), quantum = 2;

        int[] bt = new int[queueSize];
        int[] wt = new int[queueSize];
        int[] service_time = new int[queueSize];
        int[] at = new int[queueSize];


        wt[0] = 0;
        service_time[0] = 0;

        for(int i = 0; i < queueSize; ++i){
            bt[i] = processQueue.remove().getProcessingTime();
        }

        int rem_bt[] = new int[queueSize];
        for (int i = 0 ; i < queueSize ; i++)
            rem_bt[i] =  bt[i];

        int t = 0; // Current time

        // Keep traversing processes in round robin manner
        // until all of them are not done.
        while(true)
        {
            boolean done = true;

            // Traverse all processes one by one repeatedly
            for (int i = 0 ; i < queueSize; i++)
            {
                // If burst time of a process is greater than 0
                // then only need to process further
                if (rem_bt[i] > 0)
                {
                    done = false; // There is a pending process

                    if (rem_bt[i] > quantum)
                    {
                        // Increase the value of t i.e. shows
                        // how much time a process has been processed
                        t += quantum;

                        // Decrease the burst_time of current process
                        // by quantum
                        rem_bt[i] -= quantum;
                    }

                    // If burst time is smaller than or equal to
                    // quantum. Last cycle for this process
                    else
                    {
                        // Increase the value of t i.e. shows
                        // how much time a process has been processed
                        t = t + rem_bt[i];

                        // Waiting time is current time minus time
                        // used by this process
                        wt[i] = t - bt[i];

                        // As the process gets fully executed
                        // make its remaining burst time = 0
                        rem_bt[i] = 0;
                    }
                }
            }

            // If all processes are done
            if (done)
                break;
        }

        for(int i = 0; i < queueSize; ++i){
            sum += wt[i];
        }

        System.out.println("[RROB] Average awaiting time = " + sum / processList.size());
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
}