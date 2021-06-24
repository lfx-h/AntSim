package cvut.fel.zmelidom;

import java.util.Random;

public class Rain {
    private boolean rain;
    private int rainTime;
    private int kills;

    public Rain() {
        rain = false;
        rainTime = 0;
    }

    //kills anything on random node that isn't a part of the ant nest
    public void randomDrop(Node[][] map) {
        for(int i=0;i<100;i++) {
            int randX = new Random().nextInt(AntSimulation.BOUNDS - 1);
            int randY = new Random().nextInt(AntSimulation.BOUNDS - 1);
            if (map[randX][randY].getAntID() != null && !map[randX][randY].nest.isHomeNode()) {
                map[randX][randY].getAntID().setLifeTime(0);
                map[randX][randY].setAntID(null);

                kills++;
                AntSimulation.antCount--;
            }
        }
    }

    public int getRainTime() {
        return rainTime;
    }

    public void setRainTime(int rainTime) {
        this.rainTime = rainTime;
    }

    public int getKills() {
        return kills;
    }

    public boolean begin() {
        int rand = new Random().nextInt(5000);
        if (rand < AntSimulation.RAIN_PROBABILITY) {
            kills = 0;
            setRain(true);
            return true;
        }
        return false;
    }

    public boolean isRain() {
        return rain;
    }

    public void setRain(boolean rain) {
        this.rain = rain;
    }


}
