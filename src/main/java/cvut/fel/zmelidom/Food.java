package cvut.fel.zmelidom;

public class Food{
    private boolean food;

    public Food(){
        food = false;
    }

    public boolean isFood() {
        return food;
    }

    public void addFood() {
        this.food = true;
    }

    public void removeFood() {
        this.food = false;
        AntSimulation.sceneGenerator.getRoot().getChildren().remove(this.food);
    }

    public void createFoodClump(Node[][] map, Node node, int n) {
        for (int i = -n; i <= n; i++) {
            for (int j = -n; j <= n; j++) {
                if (Pathing.inRange(node.getX() + i, node.getY() + j)) {
                    map[node.getX() + i][node.getY() + j].food.addFood();
                    AntSimulation.sceneGenerator.getRoot().getChildren().add(map[node.getX() + i][node.getY() + j].objectGUIHandler.getFoodShape());
                }
            }
        }
    }

}
