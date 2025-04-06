package entity.notMoveable;


import entity.Coordinates;

//трава - ресурс для травоядных
public class Grass extends CreatureNotMoveable {

    public Coordinates coordinates;

    public Grass(Coordinates coordinates) {
        super(coordinates);
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

}
