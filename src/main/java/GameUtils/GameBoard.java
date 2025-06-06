package GameUtils;


import GameUtils.Coordinates;
import entity.Entity;

import java.util.*;


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

    public Coordinates getRandomFreeCoordinates(){
        List<Coordinates> freeCoordinates = new ArrayList<>();
        for(int column = 0; column < getMaxColumn()+1; column++){
            for(int row = 0; row < getMaxRow()+1; row++) {
                Coordinates current = new Coordinates(column, row);
                if(!getCoordinatesEntityMap().containsKey(current)){
                    freeCoordinates.add(current);
                }
            }
        }
        Collections.shuffle(freeCoordinates);
        return freeCoordinates.get(0);
    }
}