package entity.Creature;


import entity.Coordinates;
import entity.Entity;

//камень - неподвижный объект
public class Rock extends Entity {

   private Coordinates coordinates;

    public Rock(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }


}
