package entity;


import entity.Creature.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GameBoard{


    public HashMap<Coordinates, Entity> getCoordinatesEntityMap() {
        return coordinatesEntityMap;
    }

    private HashMap<Coordinates, Entity> coordinatesEntityMap = new HashMap<>();
    private final int maxColumn;
    private final int maxRow;

    public GameBoard(int maxColumn, int maxRow) {
            this.maxColumn = maxColumn;
            this.maxRow = maxRow;
    }

    public int getMaxColumn() {
        return maxColumn;
    }

    public int getMaxRow() {
        return maxRow;
    }

    public boolean coordinateIsNotEmpty(Coordinates coordinates){
        return coordinatesEntityMap.containsKey(coordinates);
    }

    public void setNewEntityForMap(){
//        coordinatesEntityMap.put(new Coordinates(3, 3), new Herbivore(new Coordinates(3, 3),1, 10));
        coordinatesEntityMap.put(new Coordinates(3, 3), new Predator(new Coordinates(3, 3), 10, 3, 10));
        coordinatesEntityMap.put(new Coordinates(0, 1), new Herbivore(new Coordinates(0, 1), 5, 2));
        coordinatesEntityMap.put(new Coordinates(1, 2), new Herbivore(new Coordinates(1, 2),20,2));
    }


    public List<Coordinates> getBoards(){
        ArrayList<Coordinates> listAllCoordinates = new ArrayList<>();
        for(int column = 0; column < getMaxColumn(); column++){
            for(int row = 0; row < getMaxRow(); row++) {
                Coordinates cor = new Coordinates(column, row);
                listAllCoordinates.add(cor);
            }
        }
        return  listAllCoordinates;
    }

    public <T extends Entity> List<T> getEntities(Class<T> entityType) {
        List<T> result = new ArrayList<>();
        for (Map.Entry<Coordinates, Entity> entry : getCoordinatesEntityMap().entrySet()) {
            Entity entity = entry.getValue();
            if (entityType.isInstance(entity)) {
                result.add(entityType.cast(entity));
            }
        }
        return result;
    }

    public Entity getEntityAt(Coordinates current) {
        return getCoordinatesEntityMap().get(current);

    }


    //       COLUMN
//          0 1 2 3 4
//        0 * * * * *
//        1 * * * * *
//   ROW  2 * G * * *
//        3 * * * H *
//        4 * * * * *
//





}
