import java.util.Arrays;
import java.util.Random;

public class FlowShopSimulation {
    // Method to generate random jobs with random processing times for each machine
    public static int[][] generateRandomJobs(int numJobs, int numMachines, int minProcessingTime, int maxProcessingTime) {
        Random random = new Random();
        int[][] jobs = new int[numJobs][numMachines];

        for (int i = 0; i < numJobs; i++) {
            for (int j = 0; j < numMachines; j++) {
                jobs[i][j] = random.nextInt(maxProcessingTime - minProcessingTime + 1) + minProcessingTime;
            }
        }

        return jobs;
    }

    // Simulation method remains unchanged
    public static void simulateFlowShop(int[][] jobs) {
        int numJobs = jobs.length;
        int numMachines = jobs[0].length;

        if (numJobs == 0 || numMachines == 0) {
            System.out.println("Invalid number of jobs or machines.");
            return;
        }

        int[] machineTime = new int[numMachines]; // To keep track of completion time for each machine
        int[] jobTime = new int[numJobs]; // To keep track of completion time for each job

        // Initialize machine time
        Arrays.fill(machineTime, 0);

        // Start time monitor
        long startTime = System.nanoTime();

        // Process jobs
        System.out.println("Job Progression:");
        for (int i = 0; i < numJobs; i++) {
            if (jobs[i].length != numMachines) {
                System.out.println("Invalid job data. Each job should have processing time for each machine.");
                return;
            }
            for (int j = 0; j < numMachines; j++) {
                // Update the completion time of the current job on the current machine
                int completionTime = Math.max(machineTime[j], jobTime[i]) + jobs[i][j];
                machineTime[j] = completionTime;
                jobTime[i] = completionTime;

                // Print job progression
                System.out.print("Job " + (i + 1) + " at Machine " + (j + 1) + ": ");
                for (int k = 0; k <= j; k++) {
                    System.out.print("X");
                }
                System.out.println();
            }
        }

        // End time monitor
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000; // Convert to milliseconds

        // Output the completion time for each job
        System.out.println("\nCompletion Time for Each Job:");
        for (int i = 0; i < numJobs; i++) {
            System.out.println("Job " + (i + 1) + ": " + jobTime[i]);
        }

        // Output total duration
        System.out.println("\nTotal Duration: " + duration + " milliseconds");
    }


    public static void main(String[] args) {
        // Example input: Number of jobs, number of machines, min processing time, max processing time
        int numJobs = 3000000;
        int numMachines = 10;
        int minProcessingTime = 1;
        int maxProcessingTime = 10;

        // Generate random jobs
        int[][] jobs = generateRandomJobs(numJobs, numMachines, minProcessingTime, maxProcessingTime);

        // Simulate flow shop with generated random jobs
        simulateFlowShop(jobs);
    }
}

