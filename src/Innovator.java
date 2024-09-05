public class Innovator {
    private int id;
    private double innovation;
    private double competition;
    private double fitness;
    private double rI;
    private double rC;
    private static final double alpha = 0.5;
    private static final double beta = 0.2;
    private static final double gamma = 0.3;
    private static final double delta = 0.1;

    public Innovator(int id, double initialInnovation, double initialCompetition, double rI, double rC) {
        this.id = id;
        this.innovation = initialInnovation;
        this.competition = initialCompetition;
        this.rI = rI;
        this.rC = rC;
        this.fitness = evaluateFitness();
    }

    public void passTime() {
        innovate();
        compete();
        updateFitness();
    }

    private void innovate() {
        this.innovation += rI * innovation * (1 - delta * competition);
        this.fitness = evaluateFitness();
    }

    private void compete() {
        this.competition += rC * competition * (gamma * fitness - (innovation / (innovation + 1)));
        this.fitness = evaluateFitness();
    }

    private void updateFitness() {
        this.fitness += alpha * innovation - beta * competition;
    }

    private double evaluateFitness() {
        return this.innovation - this.competition;
    }

    public int getId() {
        return id;
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
        return "Group " + id + " - Innovation: " + innovation + ", Competition: " + competition + ", Fitness: " + fitness;
    }
}
