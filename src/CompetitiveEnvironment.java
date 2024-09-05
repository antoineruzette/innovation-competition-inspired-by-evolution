import java.util.ArrayList;
import java.util.List;

public class CompetitiveEnvironment {
    private List<Innovator> groups;
    private int timeSteps;

    public CompetitiveEnvironment(int numberOfGroups, int timeSteps) {
        this.timeSteps = timeSteps;
        this.groups = new ArrayList<>();
        for (int i = 0; i < numberOfGroups; i++) {
            double initialInnovation = Math.random() * 10;
            double initialCompetition = Math.random() * 5;
            double rI = 0.1 + Math.random() * 0.1; // Small variation in innovation rate
            double rC = 0.05 + Math.random() * 0.05; // Small variation in competition rate
            groups.add(new Innovator(initialInnovation, initialCompetition, rI, rC));
        }
    }

    public void simulate() {
        for (int t = 0; t < timeSteps; t++) {
            for (Innovator group : groups) {
                group.passTime();
            }
            logState(t);
        }
    }

    private void logState(int timeStep) {
        System.out.println("Time step " + timeStep);
        for (Innovator group : groups) {
            System.out.println(group);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        CompetitiveEnvironment landscape = new CompetitiveEnvironment(5, 50); // 5 research groups, 50 time steps
        landscape.simulate();
    }
}
