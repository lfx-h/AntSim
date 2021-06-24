package cvut.fel.zmelidom.scene;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class ImageStream {
    private ImagePattern antImage;
    private ImagePattern queenAntImage;

    public ImageStream(){
        antImage = new ImagePattern(new Image(getClass().getResourceAsStream("/images/ant.png")));
        queenAntImage = new ImagePattern(new Image(getClass().getResourceAsStream("/images/queenAnt.png")));
    }

    public ImagePattern getAntImage() {
        return antImage;
    }

    public ImagePattern getQueenAntImage() {
        return queenAntImage;
    }
}
