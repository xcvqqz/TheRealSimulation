package entity.notMoveable;


import entity.Coordinates;

//дерево - неподвижный объект.
public class Tree extends CreatureNotMoveable {


    public Coordinates coordinates;

    public Tree(Coordinates coordinates) {
        super(coordinates);
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }






}
