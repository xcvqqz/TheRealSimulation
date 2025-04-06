package entity.notMoveable;


import entity.Coordinates;

//камень - неподвижный объект
public class Rock extends CreatureNotMoveable {

    public Coordinates coordinates;


    public Rock(Coordinates coordinates) {
        super(coordinates);
    }
}
