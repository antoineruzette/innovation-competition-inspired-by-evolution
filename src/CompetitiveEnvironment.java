import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CompetitiveEnvironment {
    private List<Innovator> groups;
    private int timeSteps;
    private List<String[]> logData;

    public CompetitiveEnvironment(int numberOfGroups, int timeSteps) {
        this.timeSteps = timeSteps;
        this.groups = new ArrayList<>();
        this.logData = new ArrayList<>();
        
        // Add header to logData
        logData.add(new String[]{"TimeStep", "GroupID", "Innovation", "Competition", "Fitness"});
        
        for (int i = 0; i < numberOfGroups; i++) {
            double initialInnovation = Math.random() * 10;
            double initialCompetition = Math.random() * 5;
            double rI = 0.1 + Math.random() * 0.1; // Small variation in innovation rate
            double rC = 0.05 + Math.random() * 0.05; // Small variation in competition rate
            groups.add(new Innovator(i, initialInnovation, initialCompetition, rI, rC));
        }
    }

    public void simulate() {
        for (int t = 0; t < timeSteps; t++) {
            for (Innovator group : groups) {
                group.passTime();
                logData.add(new String[]{
                    Integer.toString(t),
                    Integer.toString(group.getId()),
                    Double.toString(group.getInnovation()),
                    Double.toString(group.getCompetition()),
                    Double.toString(group.getFitness())
                });
            }
            logState(t);
        }
        exportToCSV("./data/simulation_data.csv");
    }

    private void logState(int timeStep) {
        System.out.println("Time step " + timeStep);
        for (Innovator group : groups) {
            System.out.println(group);
        }
        System.out.println();
    }

    private void exportToCSV(String filename) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)))) {
            for (String[] line : logData) {
                writer.println(String.join(",", line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Simulation data exported to " + filename);
    }

    public static void main(String[] args) {
        CompetitiveEnvironment landscape = new CompetitiveEnvironment(5, 200); // 5 research groups, 50 time steps
        landscape.simulate();
    }
}
