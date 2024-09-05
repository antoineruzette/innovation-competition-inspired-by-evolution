public class Simulator {

    public static void main(String[] args) {
        Researcher researcher = new Researcher(1, 0, 0, 0, 0, 0);
        System.out.println(researcher.getId());
        System.out.println(researcher.getTime());
        System.out.println(researcher.getKnowledge());
        System.out.println(researcher.getResources());
        System.out.println(researcher.getInnovationScore());
        System.out.println(researcher.getFitness());
    }
    
}
