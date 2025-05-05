package entity;


import entity.Creature.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class GameBoard {


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
        coordinatesEntityMap.put(new Coordinates(3, 3), new Herbivore(new Coordinates(3, 3),1, 10));
//        setEntity(new Coordinates(2, 1), new Grass(new Coordinates(2, 1)));
//        setEntity(new Coordinates(0, 3), new Tree(new Coordinates(0, 3)));

        coordinatesEntityMap.put(new Coordinates(2, 2), new Predator(new Coordinates(2, 2), 10, 3, 4));
        coordinatesEntityMap.put(new Coordinates(1, 2), new Tree(new Coordinates(1, 2)));
    }


    public ArrayList<Coordinates> getBoards(){
        ArrayList<Coordinates> listAllCoordinates = new ArrayList<>();
        for(int column = 0; column < getMaxColumn(); column++){
            for(int row = 0; row < getMaxRow(); row++) {
                Coordinates cor = new Coordinates(column, row);
                listAllCoordinates.add(cor);
            }
        }
        return  listAllCoordinates;
    }





    //       COLUMN
//          0 1 2 3 4
//        0 * * * * *
//        1 * * * * *
//   ROW  2 * * P * *
//        3 * * * H *
//        4 * * * * *
//





}
