import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class Researcher {
    private double knowledge;
    private double resources;
    private double innovationScore;
    private double fitness;
    private int time; // Time variable to track the time in the simulation
    private int id;   // Researcher ID
    private double competitionRate; // Competition rate to define fierceness
    private boolean isPredator; // Determines if the researcher is a predator
    private static final Random random = new Random();

    // Parameters for cyclicity
    private static final double PREY_GROWTH_RATE = 0.05;
    private static final double PREDATION_RATE = 0.1;
    private static final double CARRYING_CAPACITY = 100.0;

    public Researcher(int id, double knowledge, double resources, double competitionRate, boolean isPredator) {
        this.id = id;
        this.knowledge = knowledge;
        this.resources = resources;
        this.competitionRate = competitionRate;
        this.isPredator = isPredator;
        this.innovationScore = 0.0;
        this.fitness = evaluateFitness();
        this.time = 0; // Initialize time
    }

    public void mutate() {
        // Random mutation that can affect any attribute
        if (random.nextDouble() < 0.1) { // 10% chance of mutation
            this.knowledge += random.nextGaussian(); // Random change in knowledge
        }
        if (random.nextDouble() < 0.1) {
            this.resources += random.nextGaussian(); // Random change in resources
        }
        if (random.nextDouble() < 0.1) {
            this.innovationScore += random.nextGaussian(); // Random change in innovation
        }
        if (random.nextDouble() < 0.05) { // 5% chance to switch between prey and predator
            this.isPredator = !this.isPredator;
        }
        this.fitness = evaluateFitness();
    }

    public void preyOrPredate(Researcher other) {
        if (this.isPredator && !other.isPredator) {
            if (this.fitness > other.fitness) {
                double resourceTransfer = other.resources * PREDATION_RATE * competitionRate;
                this.resources += resourceTransfer;
                other.resources -= resourceTransfer;
                this.fitness = evaluateFitness();
                other.fitness = other.evaluateFitness();
            }
        } else if (!this.isPredator && other.isPredator) {
            // Prey attempts to survive
            if (random.nextDouble() < 0.3) {
                this.innovationScore += this.knowledge * 0.1;
                this.fitness = evaluateFitness();
            }
        }
    }

    public void growOrDecline() {
        if (!isPredator) {
            // Prey grows if under the carrying capacity
            if (this.resources < CARRYING_CAPACITY) {
                this.resources += this.resources * PREY_GROWTH_RATE;
            }
        } else {
            // Predators decline if prey is insufficient
            this.resources -= this.resources * 0.05; // Predators naturally decline if prey is not abundant
        }
        this.fitness = evaluateFitness();
    }

    public void innovate() {
        if (time % 5 == 0) {
            this.innovationScore += Math.random();
            this.fitness = evaluateFitness();
        }
    }

    public void passTime() {
        this.time++;
        mutate();
        growOrDecline(); // Introduce growth or decline in prey/predators
    }

    private double evaluateFitness() {
        return knowledge + resources + innovationScore; // Simplified fitness function
    }

    public void saveDataToFile(String fileName) {
        try (FileWriter fw = new FileWriter(fileName, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            out.println(id + "," + time + "," + knowledge + "," + resources + "," + innovationScore + "," + fitness + "," + (isPredator ? "Predator" : "Prey"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Getters and setters...

    public double getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(double knowledge) {
        this.knowledge = knowledge;
    }

    public double getResources() {
        return resources;
    }

    public void setResources(double resources) {
        this.resources = resources;
    }

    public double getInnovationScore() {
        return innovationScore;
    }

    public void setInnovationScore(double innovationScore) {
        this.innovationScore = innovationScore;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCompetitionRate() {
        return competitionRate;
    }

    public void setCompetitionRate(double competitionRate) {
        this.competitionRate = competitionRate;
    }

    public boolean isPredator() {
        return isPredator;
    }

    public void setPredator(boolean predator) {
        isPredator = predator;
    }

    // Main method for testing...

    public static void main(String[] args) {
        String fileName = "data/simulation_preypredator_data.csv";
        Researcher researcher1 = new Researcher(1, 10.0, 50.0, 1.0, false); // Prey with initial high resources
        Researcher researcher2 = new Researcher(2, 8.0, 30.0, 1.2, true);  // Predator
        Researcher researcher3 = new Researcher(3, 10.0, 50.0, 1.5, true); // Predator

        // Write the header for the CSV file
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            out.println("ID,Time,Knowledge,Resources,InnovationScore,Fitness,Type");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Simulate and save data
        for (int i = 0; i < 500; i++) {
            researcher1.passTime();
            researcher2.passTime();
            researcher3.passTime();

            researcher1.innovate();
            researcher2.innovate();
            researcher3.innovate();

            researcher1.preyOrPredate(researcher2);
            researcher2.preyOrPredate(researcher1);
            researcher1.preyOrPredate(researcher3);
            researcher3.preyOrPredate(researcher1);

            researcher2.preyOrPredate(researcher3);
            researcher3.preyOrPredate(researcher2);

            // Save data to file
            researcher1.saveDataToFile(fileName);
            researcher2.saveDataToFile(fileName);
            researcher3.saveDataToFile(fileName);
        }
    }
}
