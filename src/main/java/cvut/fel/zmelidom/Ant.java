package cvut.fel.zmelidom;

import java.util.*;

public class Ant {
    private int x;
    private int y;
    public int rand;
    private Stack<Node> pathHistory;
    private int lifeTime;
    private int loopPos;
    private Node attractiveTemp;
    private List<Node> homePath;
    private int lastDrop;
    private AntState state;
    private Pathing pathing;
    private int type; //0=ant, 1=queen

    public Ant(){
        x = 0;
        y = 0;
        rand = 0;
        type = 0;
        lifeTime = 1;
        loopPos = 0;
        pathHistory = new Stack<Node>();
        homePath = new LinkedList<Node>();
        pathing = new Pathing(this);
    }

    public Ant(Node home){
        this();
        state = AntState.SEARCHING;
        pathHistory.add(home);
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(int lifeTime) {
        this.lifeTime = lifeTime;
    }


    //method contains main pathing algorithms
    private void moveAnt(Node[][] map) {
        int newX;
        int newY;
        int[] moveRange = {-1, 0, 1};
        int[] wallMoveRange = {0, 1};

        do {
            if (getX() == 0) {
                newX = getX() + pathing.moveNext(wallMoveRange);
            } else if (getX() == AntSimulation.BOUNDS - 1) {
                newX = getX() - pathing.moveNext(wallMoveRange);
            } else {
                newX = getX() + pathing.moveNext(moveRange,0);
            }
            if (getY() == 0) {
                newY = getY() + pathing.moveNext(wallMoveRange);
            } else if (getY() == AntSimulation.BOUNDS - 1) {
                newY = getY() - pathing.moveNext(wallMoveRange);
            } else {
                newY = getY() + pathing.moveNext(moveRange,1);
            }
            if (pathHistory.isEmpty()) {
                break;
            }
            if(map[newX][newY].wall.isWall()){
                newX = pathHistory.peek().getX();
                newY = pathHistory.peek().getY();
                break;
            }
            if (!Pathing.isBounds(map[getX()][getY()])) {
                for (Node i : pathing.illegalNodes(map)) {
                    if (i == map[newX][newY]) {
                        newX = getX();//reset new coordinates
                        newY = getY();
                        break;
                    }
                }
            }
            //keeps on looking for valid pathing option until while loop breaks
        } while (pathHistory.peek() == map[newX][newY] || map[newX][newY] == map[getX()][getY()]);
        pathHistory.add(map[getX()][getY()]);
        setXY(map, map[newX][newY]);
       /* setX(newX);
        setY(newY);*/
    }

    //check for highest pheromone count in neighboring nodes
    private Node attractiveNode(Node[][] node) {
        Node bestNode = node[getX()][getY()];
        for (int i = -2; i < 3; i++) {
            for (int j = -2; j < 3; j++) {
                if (Pathing.inRange(getX() + i, getY() + j) && pathHistory.peek() != node[getX() + i][getY() + j]) {
                    if (bestNode.pheromones.getPheromoneCount() < node[getX() + i][getY() + j].pheromones.getPheromoneCount()) {
                        bestNode = node[getX() + i][getY() + j];
                    }
                }
            }
        }
        return bestNode;
    }


    //90% probability to move to given node
    private void probabilisticMove(Node[][] map, Node temp) {
        rand = new Random().nextInt(10);
        if (rand > 1) {
            pathHistory.add(map[getX()][getY()]);
            /*setX(temp.getX());
            setY(temp.getY());*/
            setXY(map, temp);
        } else {
            moveAnt(map);
        }
    }


    //main move method
    public void move(Node[][] map) {
        switch(state){
            case SEARCHING:
                if (AntSimulation.rain.isRain() && !map[getX()][getY()].nest.isHomeNode()) {
                    state = AntState.RETURNING;
                }else{
                    attractiveTemp = attractiveNode(map);
                    if (!pathing.lookAround(map)) {
                        if (attractiveTemp != map[getX()][getY()]) {//near attractive node exists
                            probabilisticMove(map, attractiveTemp);
                            map[getX()][getY()].pheromones.reducePheromoneCount(AntSimulation.CONSUME_AMOUNT);
                        } else {
                            moveAnt(map);
                        }
                    }
                }
                if (map[getX()][getY()].food.isFood()) {
                    map[getX()][getY()].food.removeFood();
                    AntSimulation.sceneGenerator.getRoot().getChildren().remove(map[getX()][getY()].objectGUIHandler.getFoodShape());
                    map[getX()][getY()].pheromones.addPheromones(2*lastDrop);
                    homePath = pathing.removeLoops(pathHistory);
                    lastDrop = AntSimulation.DROPPED_AMOUNT;
                    loopPos = 0;
                    state = AntState.CARRYING;
                }
                break;
            case CARRYING:
                if (!map[getX()][getY()].nest.isHomeNode() && loopPos < homePath.size()) {
                    pathHistory.add(map[getX()][getY()]);
                   /*setX(homePath.get(loopPos).getX());
                    setY(homePath.get(loopPos).getY());*/
                    setXY(map, homePath.get(loopPos));
                    loopPos++;
                } else {
                    moveAnt(map);
                }

                if (map[getX()][getY()].nest.isHomeNode()) {
                    AntSimulation.nest.increaseNest(map[getX()][getY()], map);
                    homePath = new LinkedList<Node>();
                    state = AntState.SEARCHING;
                } else {
                    if (lastDrop > 5) {
                        map[getX()][getY()].pheromones.addPheromones(lastDrop);
                        lastDrop = lastDrop - 2;
                        /*if(map[getX()][getY()].getPheromoneCount()>0 && !map[getX()][getY()].isPheromone()){
                            AntSimulation.root.getChildren().add(map[getX()][getY()].objectGUIHandler.pheromone);
                            map[getX()][getY()].setPheromone(true);
                        }*/
                    }
                }
                break;
            case RETURNING:
                if(!AntSimulation.rain.isRain()) {
                    state = AntState.SEARCHING;
                    break;
                }
                if (!map[getX()][getY()].nest.isHomeNode() && pathHistory.size()>1 && AntSimulation.rain.isRain()) {
                    /*setX(pathHistory.peek().getX());
                    setY(pathHistory.peek().getY());*/
                    setXY(map, map[pathHistory.peek().getX()][pathHistory.peek().getY()]);
                    pathHistory.pop();
                }
                break;
        }
    }

    public int getX() {
        return x;
    }

    public void setXY(Node[][] map, Node node){
        map[getX()][getY()].setAntID(null);
        this.x = node.getX();
        this.y = node.getY();
        node.setAntID(this);

    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Stack<Node> getPathHistory() {
        return pathHistory;
    }
}