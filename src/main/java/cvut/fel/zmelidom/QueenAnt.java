package cvut.fel.zmelidom;

import java.util.Random;

public class QueenAnt extends Ant{

    public QueenAnt(){
        setType(1);
    }

    private void consumeFood(Node node){
        if(node.nest.isHomeNode()){
            node.nest.setHomeNode(false);
            AntSimulation.sceneGenerator.getRoot().getChildren().remove(node.objectGUIHandler.getNest());
        }
    }

    private void spawnAnt(Node[][] node){
        rand = new Random().nextInt(100);
        if(rand==1) {
            AntSimulation.antList.add(new Ant(node[getX()][getY()]));
            consumeFood(node[getX()][getY()]);
            System.out.println("Queen spawns a new ant!");
            AntSimulation.antCount++;
        }
    }

    private boolean inNest(Node[][] nest){
        rand = new Random().nextInt(10);
        for(int i=-2;i<3;i++){
            for(int j=-2;j<3;j++){
                if(Pathing.inRange(getX()+i, getY()+j) && rand>5){
                    if(nest[getX()+i][getY()+j].nest.isHomeNode()){
                        setX(getX()+i);
                        setY(getY()+j);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void moveQueen(Node[][] nest){
        inNest(nest);
        spawnAnt(nest);
    }

}
