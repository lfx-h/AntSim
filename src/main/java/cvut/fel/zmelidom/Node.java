package cvut.fel.zmelidom;

import cvut.fel.zmelidom.scene.handlers.ObjectGUIHandler;

public class Node {
    private int x;
    private int y;
    private Ant antID;
    public Pheromones pheromones;
    public Wall wall;
    public Food food;
    public Nest nest;
    public ObjectGUIHandler objectGUIHandler;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        antID = null;
        objectGUIHandler = new ObjectGUIHandler(x, y);
        pheromones = new Pheromones();
        food = new Food();
        nest = new Nest();
        wall = new Wall(this);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Ant getAntID() {
        return antID;
    }

    public void setAntID(Ant antID) {
        this.antID = antID;
    }

}



