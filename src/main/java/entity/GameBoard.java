package entity;


import entity.Creature.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GameBoard{

    private final HashMap<Coordinates, Entity> coordinatesEntityMap = new HashMap<>();
    private final int maxColumn;
    private final int maxRow;

    public GameBoard(int maxColumn, int maxRow) {
        this.maxColumn = maxColumn;
        this.maxRow = maxRow;
    }

    public HashMap<Coordinates, Entity> getCoordinatesEntityMap() {
        return coordinatesEntityMap;
    }
    public int getMaxColumn() {
        return maxColumn;
    }
    public int getMaxRow() {
        return maxRow;
    }

    public Entity getEntityAt(Coordinates current) {
        return getCoordinatesEntityMap().get(current);
    }

    public Coordinates getCoordinates (Entity entity){
        for(Map.Entry<Coordinates, Entity> entry :  coordinatesEntityMap.entrySet()){
            if(entry.getValue().equals(entity)){
                return  entry.getKey();
            }
        }
        throw new IllegalArgumentException();
    }
}

