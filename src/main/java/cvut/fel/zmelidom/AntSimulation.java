package cvut.fel.zmelidom;

import java.util.LinkedList;
import java.util.List;

import cvut.fel.zmelidom.scene.handlers.AntGUIHandler;
import cvut.fel.zmelidom.scene.SceneGenerator;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;


public class AntSimulation extends Application {
    //KEY CONTROL VARIABLES-----------------------|
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    public static final int BOUNDS = 250;
    public static final int MAX_PHEROMONE_COUNT = 50000;
    public static final int CONSUME_AMOUNT = 50;
    public static final int DROPPED_AMOUNT = 1000;
    public static int antCount = 500;
    public static final int nodeSize = WIDTH / BOUNDS;
    public static final int RAIN_PROBABILITY = 1; //x/5000
    //--------------------------------------------|
    public static List<Ant> antList = new LinkedList<Ant>();
    private QueenAnt queenAnt;
    private Node[][] map = new Node[BOUNDS][BOUNDS];
    public static Rain rain;
    public static Nest nest;
    private AntGUIHandler antGUIHandler;
    private Scene scene;
    public static SceneGenerator sceneGenerator;

    @Override
    public void start(Stage stage) {
        //initializes the JFX scene
        sceneGenerator = new SceneGenerator();
        scene = sceneGenerator.generateScene();

        antGUIHandler = new AntGUIHandler(sceneGenerator.getRoot());

        stage.setScene(scene);
        stage.setTitle("Ant Simulation");
        stage.show();

        initMap();
        run();
    }

    private void run() {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.01), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //Rain handling
                sceneGenerator.setAntCountText("Ants: " + antCount);
                if (rain.isRain()) {
                    rain.randomDrop(map);
                    rain.setRainTime(rain.getRainTime() - 1);
                    if (rain.getRainTime() == 0) {
                        rain.setRain(false);
                        System.out.println("The rain stops... " + rain.getKills() + " ants died");
                    }
                } else {
                    if (rain.begin()) {
                        System.out.println("It begins to rain!");
                        rain.setRainTime(200);
                    }
                }

                /**BOARD UPDATE**/
                for (int i = 0; i < BOUNDS; i++) {
                    for (int j = 0; j < BOUNDS; j++) {
                        map[i][j].pheromones.decayPheromones();
                    }
                }

                //ant movement part of method
                for (int i = 0; i < antList.size(); i++) {
                    antGUIHandler.removeAntFromScene(antList.get(i), map);

                    if (antList.get(i).getLifeTime() > 0) {
                        antList.get(i).move(map);
                        antGUIHandler.addAntToScene(antList.get(i), map);
                    }
                }
                //visuals
                antGUIHandler.removeAntFromScene(queenAnt, map);
                queenAnt.moveQueen(map);
                antGUIHandler.addAntToScene(queenAnt, map);
            }
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    private void initNest() {
        nest = new Nest();
        nest.buildNest(map, map[0][0], 5);
    }

    private void initFood() {
        /*food.createFoodClump(map, map[100][179], 5);
        food.createFoodClump(map, map[230][79], 5);
        food.createFoodClump(map, map[60][150], 5);*/
        map[190][230].food.createFoodClump(map, map[190][230], 10);
    }

    private void initWalls() {
        /*walls.createWallTriangle(map, randomNode(map), 10);
        walls.createWallRectangle(map, randomNode(map), 10);
        walls.createWallRectangle(map, randomNode(map), 5);*/
        //walls.createWallRectangle(map, map[20][20], 10);
        Wall walls = new Wall(map[0][0]);
        //walls.createTunnels(map, 50);
        //walls.createWallRectangle(map, 2);
    }

    private void initRain() {
        rain = new Rain();
    }

    private void initAnts() {
        for (int i = 0; i < antCount; i++) {
            antList.add(new Ant(map[0][0]));
        }
        queenAnt = new QueenAnt();
    }

    public void initMap() {
        /**MAP**/
        for (int i = 0; i < BOUNDS; i++) {
            for (int j = 0; j < BOUNDS; j++) {
                map[i][j] = new Node(i, j);
            }
        }
        initNest();
        initFood();
        initRain();
        initAnts();
        initWalls();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
