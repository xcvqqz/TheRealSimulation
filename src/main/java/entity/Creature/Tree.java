package entity.Creature;


import entity.Coordinates;
import entity.Entity;

//дерево - неподвижный объект.
public class Tree extends Entity {

    private Coordinates coordinates;

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Tree(Coordinates coordinates) {
        this.coordinates = coordinates;
    }









}
