package cvut.fel.zmelidom.scene.handlers;

import cvut.fel.zmelidom.Ant;
import cvut.fel.zmelidom.Node;
import cvut.fel.zmelidom.scene.ImageStream;
import javafx.scene.Group;

public class AntGUIHandler {
    private Group root;
    private ImageStream imgHandler;

    public AntGUIHandler(Group root){
        this.root = root;
        imgHandler = new ImageStream();
    }

    public void addAntToScene(Ant ant, Node[][] map) {
        if (!root.getChildren().contains(map[ant.getX()][ant.getY()].objectGUIHandler.getAnt())){
            if(ant.getType()==0) {
                root.getChildren().add(map[ant.getX()][ant.getY()].objectGUIHandler.getAnt());
                map[ant.getX()][ant.getY()].objectGUIHandler.getAnt().setFill(imgHandler.getAntImage());
            }else if(ant.getType()==1){
                root.getChildren().add(map[ant.getX()][ant.getY()].objectGUIHandler.getQueenAnt());
                map[ant.getX()][ant.getY()].objectGUIHandler.getQueenAnt().setFill(imgHandler.getQueenAntImage());
            }
        }
    }

    public void removeAntFromScene(Ant ant, Node[][] map){
        if(ant.getType()==0) {
            root.getChildren().remove(map[ant.getX()][ant.getY()].objectGUIHandler.getAnt());
        }else if(ant.getType()==1){
            root.getChildren().remove(map[ant.getX()][ant.getY()].objectGUIHandler.getQueenAnt());

        }
    }
}
