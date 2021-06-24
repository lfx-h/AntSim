package cvut.fel.zmelidom.scene;
import cvut.fel.zmelidom.AntSimulation;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class SceneGenerator {
    private Group root;
    private Text antCountText;

    public Scene generateScene(){
        root = new Group();
        antCountText = new Text(AntSimulation.WIDTH - 100, 50, "Ants: " + AntSimulation.antCount);
        antCountText.setFill(Color.LIGHTGREY);
        antCountText.setFont(Font.font("Accidental Presidency", 20));
        root.getChildren().add(antCountText);
        return new Scene(root, AntSimulation.WIDTH, AntSimulation.HEIGHT, Color.BLACK);
    }

    public Group getRoot() {
        return root;
    }

    public void setAntCountText(String antCountText) {
        this.antCountText.setText(antCountText);
    }
}
