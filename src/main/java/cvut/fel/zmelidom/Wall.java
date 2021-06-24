package cvut.fel.zmelidom;

import java.util.Random;

public class Wall{
    private boolean isWall;
    private Node node;

    public Wall(Node node){
        isWall = false;
        this.node = node;
    }

    public boolean isWall() {
        return isWall;
    }

    public void setWall(boolean wall) {
        isWall = wall;
    }

    public void createWallTriangle(Node[][] map, int n){
        for(int i=-n;i<=n*5;i++){
            for(int j=-n-i;j<=n;j++){
                if(Pathing.inRange(node.getX()+i, node.getY()+j) &&
                        !map[node.getX()+i][node.getY()+j].food.isFood() &&
                        !map[node.getX()+i][node.getY()+j].wall.isWall()) {
                    map[node.getX() + i][node.getY() + j].wall.setWall(true);
                    AntSimulation.sceneGenerator.getRoot().getChildren().add(map[node.getX() + i][node.getY() + j].objectGUIHandler.getWall());
                }
            }
        }
    }

    public void createWallRectangle(Node[][] map, int n){
        for(int i=-n;i<=n;i++){
            for(int j=-n*5;j<=n*5*10;j++){
                if(Pathing.inRange(node.getX()+i, node.getY()+j) &&
                        !map[node.getX()+i][node.getY()+j].food.isFood() &&
                        !map[node.getX()+i][node.getY()+j].wall.isWall()) {
                    map[node.getX() + i][node.getY() + j].wall.setWall(true);
                    AntSimulation.sceneGenerator.getRoot().getChildren().add(map[node.getX() + i][node.getY() + j].objectGUIHandler.getWall());
                }
            }
        }
    }

    public void createTunnels(Node[][] map, int density){
        int rand;
        for(int i=0;i<AntSimulation.BOUNDS;i++){
            for(int j=0;j<AntSimulation.BOUNDS;j++){
                rand = new Random().nextInt(density);
                if(Pathing.inRange(node.getX()+i, node.getY()+j) &&
                        !map[node.getX()+i][node.getY()+j].food.isFood() &&
                        !map[node.getX()+i][node.getY()+j].wall.isWall() && rand ==1) {
                    map[node.getX() + i][node.getY() + j].wall.setWall(true);
                    AntSimulation.sceneGenerator.getRoot().getChildren().add(map[node.getX() + i][node.getY() + j].objectGUIHandler.getWall());
                }
            }
        }
    }
}
