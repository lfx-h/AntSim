package cvut.fel.zmelidom.scene.handlers;

import cvut.fel.zmelidom.AntSimulation;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class ObjectGUIHandler {
    private final Rectangle ant, queenAnt, wall;
    private final Circle foodShape, nest;

    public ObjectGUIHandler(int x, int y){
        int nodeSize = AntSimulation.nodeSize;
        ant = new Rectangle(x * nodeSize, y* nodeSize, nodeSize * 2, nodeSize * 2);
        queenAnt = new Rectangle(x * nodeSize, y * nodeSize, nodeSize * 3, nodeSize * 3);

        foodShape = new Circle((x+1) * nodeSize, (y+1) * nodeSize, nodeSize);
        foodShape.setFill(Color.GREEN);

        nest = new Circle((x+1) * nodeSize, (y+1) * nodeSize, nodeSize);
        nest.setFill(Color.PURPLE);

        wall = new Rectangle((x+1) * nodeSize, (y+1) * nodeSize, nodeSize*2, nodeSize*2);
        wall.setFill(Color.CHOCOLATE);
    }

    public Rectangle getAnt() {
        return ant;
    }

    public Rectangle getQueenAnt() {
        return queenAnt;
    }

    public Rectangle getWall() {
        return wall;
    }

    public Circle getFoodShape() {
        return foodShape;
    }

    public Circle getNest() {
        return nest;
    }
}
