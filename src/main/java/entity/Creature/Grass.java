package entity.Creature;


import entity.Coordinates;
import entity.Entity;

//трава - ресурс для травоядных
public class Grass extends Entity {

    private Coordinates coordinates;

    public Grass(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

}
