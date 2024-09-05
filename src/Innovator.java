// import java.util.Random;

public class Innovator {
    private double innovation;
    private double competition;
    private double fitness;
    private double rI;
    private double rC;
    private static final double alpha = 0.5;
    private static final double beta = 0.2;
    private static final double gamma = 0.3;
    private static final double delta = 0.1;
    // private static final Random random = new Random();

    public Innovator(double initialInnovation, double initialCompetition, double rI, double rC) {
        this.innovation = initialInnovation;
        this.competition = initialCompetition;
        this.rI = rI;
        this.rC = rC;
        this.fitness = evaluateFitness();
    }

    public void innovate() {
        this.innovation += rI * innovation * (1 - delta * competition);
        this.fitness = evaluateFitness();
    }

    public void compete() {
        this.competition += rC * competition * (gamma * fitness - (innovation / (innovation + 1)));
        this.fitness = evaluateFitness();
    }

    public void updateFitness() {
        this.fitness += alpha * innovation - beta * competition;
    }

    public void passTime() {
        innovate();
        compete();
        updateFitness();
    }

    private double evaluateFitness() {
        return this.innovation - this.competition;
    }

    public double getInnovation() {
        return innovation;
    }

    public double getCompetition() {
        return competition;
    }

    public double getFitness() {
        return fitness;
    }

    @Override
    public String toString() {
        return "Innovation: " + innovation + ", Competition: " + competition + ", Fitness: " + fitness;
    }
}
