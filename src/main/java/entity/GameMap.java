package entity;


import entity.moveable.Herbivore;
import entity.moveable.Predator;
import entity.notMoveable.Grass;
import entity.notMoveable.Tree;

import java.util.HashMap;
import java.util.Map;


public class GameMap {


    public HashMap<Coordinates, Entity> coordinatesEntityMap = new HashMap<>();
    public Coordinates coordinates;


    public HashMap<Coordinates, Entity> getCoordinatesEntityMap() {
        return coordinatesEntityMap;
    }

    public boolean coordinateIsEmpty(Coordinates coordinates){

        for(Map.Entry<Coordinates, Entity> item : getCoordinatesEntityMap().entrySet()){
            Coordinates coordinatesItem = item.getKey();
            if(coordinatesItem == coordinates){
                return true;
            }
        }
        return false;
    }

    public Entity getEntity(Coordinates coordinates){
        Entity entity = null;
        for (Map.Entry<Coordinates, Entity> item : getCoordinatesEntityMap().entrySet()){
            Coordinates coordinatesItem = item.getKey();
            if (coordinatesItem == coordinates) {
                entity = item.getValue();
            }
        }
        return entity;
    }

    public void setNewEntityForMap(){
        coordinatesEntityMap.put(new Coordinates(2, WidthOfMap.A),  new Herbivore(new Coordinates(2, WidthOfMap.A),1, 100));
        coordinatesEntityMap.put(new Coordinates(3, WidthOfMap.A), new Grass(new Coordinates(3, WidthOfMap.A)));
        coordinatesEntityMap.put(new Coordinates(1, WidthOfMap.A), new Tree(new Coordinates(1, WidthOfMap.A)));
    }



}
