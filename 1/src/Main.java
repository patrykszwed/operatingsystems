import java.util.*;

public class Main {

    private static List<Process> processorsList = new LinkedList<>();

    public static void init(List<Process> processList, int noProcesses, int timeUnit, int quantum) {
        Random rand = new Random();
        Process process;
        for (int i = 0; i < noProcesses; ++i) {
            process = new Process(rand.nextInt(noProcesses) + 1, rand.nextInt(quantum + 1) + 10, i);
            processList.add(process);
        }
    }

    public static void main(String[] args) {
        int noProcesses, timeUnit, quantum;
        Scanner sc = new Scanner(System.in);
        System.out.println("How many processes do you want to simulate?");
        noProcesses = sc.nextInt();
        System.out.println("How much is one time unit?");
        timeUnit = sc.nextInt();
        System.out.println("What value of quantum do you want to use?");
        quantum = sc.nextInt();
        init(processorsList, noProcesses, timeUnit, quantum);

        fcfs(processorsList);
        sjf(processorsList);
        sjfp(processorsList);
        rrob(processorsList, quantum);

        sc.close();
    }

    public static void fcfs(List<Process> processList) {
        Queue<Process> processQueue = new LinkedList<>(processList);

        System.out.println("[FCFS] Average waiting time = " + findAvgWaitingTime(processQueue) + " time units.");
    }

    public static void sjf(List<Process> processList) {
        Collections.sort(processList);
        Queue<Process> processQueue = new LinkedList<>(processList);

        System.out.println("[SJF] Average waiting time = " + findAvgWaitingTime(processQueue) + " time units.");
    }

    public static void sjfp(List<Process> processList) {
        List<Process> copyList = new ArrayList<>(processList);
        int initialSize = copyList.size();
        Map<Integer, Integer> initialServiceTimeMap = getInitialMap(copyList);
        Collections.sort(copyList);

        int time = 0, awaitingUnits = 0, waitTime = 0;
        Process currentProcess, headProcess;

        while (!copyList.isEmpty()) {
            currentProcess = copyList.get(0);
            copyList.remove(0);

            while (currentProcess.getServiceTime() > 0) {
                currentProcess.setServiceTime(currentProcess.getServiceTime() - 1);
                headProcess = null;
                time++;

                if (copyList.size() != 0)
                    headProcess = copyList.get(0);

                if (headProcess != null) {
                    if (headProcess.getServiceTime() < currentProcess.getServiceTime() && headProcess.getArrivalTime() >= time) {
                        copyList.add(currentProcess);
                        break;
                    }
                } else
                    break;
            }

            if (currentProcess.getServiceTime() == 0) {   // process is completed
                waitTime = time + 1 - currentProcess.getArrivalTime() - initialServiceTimeMap.get(currentProcess.getProcessId());
                awaitingUnits += waitTime;
            }
            Collections.sort(copyList);
        }
        processList.forEach(elem -> {
            elem.setServiceTime(initialServiceTimeMap.get(elem.getProcessId()));
        });
        System.out.println("[SJFP] Average waiting time = " + (float) (awaitingUnits / initialSize) + " time units.");
    }

    public static void rrob(List<Process> processList, int quantum) {
        int initialSize = processList.size();
        Map<Integer, Integer> initialServiceTimeMap = getInitialMap(processList);

        int time = 0, awaitingUnits = 0, waitTime;
        Process currentProcess;

        while (!processList.isEmpty()) {    // while process is done
            currentProcess = processList.get(0);
            processList.remove(0);

            while (currentProcess.getServiceTime() > quantum) {
                currentProcess.setServiceTime(currentProcess.getServiceTime() - quantum);
                time += quantum;
            }
            time += currentProcess.getServiceTime();
            waitTime = time - initialServiceTimeMap.get(currentProcess.getProcessId());
            awaitingUnits += waitTime;
        }
        System.out.println("[RROB] Average waiting time = " + (float) (awaitingUnits / initialSize) + " time units.");
    }

    private static Map<Integer, Integer> getInitialMap(List<Process> processList) {
        Map<Integer, Integer> initialServiceTimeMap = new HashMap<>();
        processList.forEach(elem -> {
            initialServiceTimeMap.put(elem.getProcessId(), elem.getServiceTime());
        });
        return initialServiceTimeMap;
    }

    private static void printProcesses(List<Process> processList) {
        processList.forEach(elem -> {
            System.out.println("==== Process with ID [" + elem.getProcessId() + "] ====");
            System.out.println("\t\tArrival time = " + elem.getArrivalTime());
            System.out.println("\t\tService time = " + elem.getServiceTime());
        });
    }


    public static float findAvgWaitingTime(Queue<Process> processQueue) {
        int noProcesses = processQueue.size();
        int[] wt = new int[noProcesses];
        wt[0] = 0;
        float sum = 0;

        for (int i = 1; i < noProcesses; ++i) {
            wt[i] = processQueue.remove().getServiceTime() + wt[i - 1];
            sum += wt[i];
        }

        return sum / noProcesses;
    }

}