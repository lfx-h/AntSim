package cvut.fel.zmelidom;


public class Nest {
    public static int nestStrength;
    private boolean homeNode;

    public Nest() {
        nestStrength = 1;
        homeNode = false;
    }

    public boolean isHomeNode() {
        return homeNode;
    }

    public void setHomeNode(boolean homeNode) {
        this.homeNode = homeNode;
    }

    public void buildNest(Node[][] map, Node node, int n) {
        for (int i = -n; i <= n; i++) {
            for (int j = -n; j <= n; j++) {
                if (Pathing.inRange(node.getX() + i, node.getY() + j) && !map[node.getX()+i][node.getY()+j].wall.isWall()) {
                    map[node.getX() + i][node.getY() + j].nest.setHomeNode(true);
                    AntSimulation.sceneGenerator.getRoot().getChildren().add(map[node.getX() + i][node.getY() + j].objectGUIHandler.getNest());
                }
            }
        }
    }

    public static void increaseNest(Node node, Node[][] location) {
        setNestStrength(getNestStrength() + 1);
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (Pathing.inRange(node.getX() + i, node.getY() + j)) {
                    if (!location[node.getX() + i][node.getY() + j].nest.isHomeNode()) {
                        location[node.getX() + i][node.getY() + j].nest.setHomeNode(true);
                        AntSimulation.sceneGenerator.getRoot().getChildren().add(location[node.getX() + i][node.getY() + j].objectGUIHandler.getNest());
                        nestStrength++;
                    }
                }
            }
        }
    }

    public static int getNestStrength() {
        return nestStrength;
    }

    public static void setNestStrength(int x) {
        nestStrength = x;
    }
}


