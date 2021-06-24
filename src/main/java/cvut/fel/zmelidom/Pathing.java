package cvut.fel.zmelidom;

import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

public class Pathing {
    private Ant ant;

    public Pathing(Ant ant) {
        this.ant = ant;
    }

    public int moveNext(int[] set) {
        int rand = new Random().nextInt(set.length);
        return set[rand];

    }

    public int moveNext(int[] set, int axis) {
        int a, oldA;
        if (axis == 0) {
            a = ant.getX();
            oldA = ant.getPathHistory().peek().getX();
        } else {
            a = ant.getY();
            oldA = ant.getPathHistory().peek().getY();
        }
        int rand = new Random().nextInt(set.length);
        int straight = new Random().nextInt(2);

        if (a > oldA) {
            a = 1;
        } else if (a < oldA) {
            a = -1;
        } else {
            a = 0;
        }
        if (straight == 1) {
            return a;
        } else {
            return set[rand];
        }
    }

    public boolean lookAround(Node[][] map) {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (Pathing.inRange(ant.getX() + i, ant.getY() + j)) {
                    if (map[ant.getX() + i][ant.getY() + j].food.isFood()) {
                        ant.getPathHistory().add(map[ant.getX()][ant.getY()]);
                        ant.setX(ant.getX() + i);
                        ant.setY(ant.getY() + j);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public LinkedList<Node> removeLoops(Stack<Node> temp) {
        LinkedList<Node> newPath = new LinkedList<Node>();
        int step;
        while (!temp.isEmpty()) {
            newPath.add(temp.peek());
            step = 0;
            for (Node i : newPath) {
                if (temp.peek() == i) {
                    break;
                }
                step++;
            }
            while (newPath.size() > step + 1) {
                newPath.removeLast();
            }
            temp.pop();
        }
        return newPath;
    }

    public static boolean inRange(int x, int y) {
        if (x < 0 || x > AntSimulation.BOUNDS - 1 || y < 0 || y > AntSimulation.BOUNDS - 1) {
            return false;
        }
        return true;
    }

    public static boolean isBounds(Node node) {
        if (node.getX() == AntSimulation.BOUNDS - 1 || node.getX() == 0 || node.getY() == AntSimulation.BOUNDS - 1 || node.getY() == 0) {
            return true;
        }
        return false;
    }

    public Node[] illegalNodes(Node[][] map) {
        Node[] array = new Node[9 + 4];
        int count = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                //if our move is diagonal and if i,j are not diagonal coordinates
                if (Math.abs(i) + Math.abs(j) == 1 &&
                        Math.abs(ant.getX() - ant.getPathHistory().peek().getX()) == Math.abs(ant.getY() - ant.getPathHistory().peek().getY())) {
                    if (inRange(ant.getPathHistory().peek().getX() + 2 * i, ant.getPathHistory().peek().getY() + 2 * j)) {
                        array[count] = map[ant.getPathHistory().peek().getX() + 2 * i][ant.getPathHistory().peek().getY() + 2 * j];
                        count++;
                    }
                }
                if (inRange(ant.getPathHistory().peek().getX() + i, ant.getPathHistory().peek().getY() + j)) {
                    array[count] = map[ant.getPathHistory().peek().getX() + i][ant.getPathHistory().peek().getY() + j];
                    count++;
                }
            }
        }
        return array;
    }
}
