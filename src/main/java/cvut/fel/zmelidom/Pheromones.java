package cvut.fel.zmelidom;

public class Pheromones{
    private boolean isPheromone;
    private int pheromoneCount;

    public Pheromones(){
        isPheromone = false;
        pheromoneCount = 0;
    }

    public void addPheromones(int p) {
        if ((getPheromoneCount() + p) > AntSimulation.MAX_PHEROMONE_COUNT) {
            setPheromoneCount(AntSimulation.MAX_PHEROMONE_COUNT);
        } else {
            setPheromoneCount(getPheromoneCount() + p);
        }
    }
    public void reducePheromoneCount(double p) {
        if ((getPheromoneCount() - p) <= 0) {
            removePheromones();
        } else {
            setPheromoneCount((int) (getPheromoneCount() - p));
        }
    }

    public void decayPheromones() {
        if (isPheromone){
            setPheromoneCount(getPheromoneCount() - 1);
            if(getPheromoneCount()==0) {
                removePheromones();
            }
        }
    }

    public int getPheromoneCount() {
        return pheromoneCount;
    }

    public void setPheromoneCount(int pheromoneCount) {
        this.pheromoneCount = pheromoneCount;
    }

    public void setPheromone(boolean pheromone) {
        isPheromone = pheromone;
    }

    public void removePheromones() {
        setPheromoneCount(0);
        setPheromone(false);
    }
}
