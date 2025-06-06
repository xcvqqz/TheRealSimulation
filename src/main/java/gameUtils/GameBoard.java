package gameUtils;


import entity.Entity;

import java.util.*;

public class GameBoard{
    private final HashMap<Coordinates, Entity> coordinatesEntityMap = new HashMap<>();
    private final int length;
    private final int width;

    public GameBoard() {
        this.length = SimulationConstants.GAMEBOARD_LENGTH;
        this.width = SimulationConstants.GAMEBOARD_WIDTH;
    }

    public HashMap<Coordinates, Entity> getCoordinatesEntityMap() {
        return coordinatesEntityMap;
    }
    public int getLength() {
        return length;
    }
    public int getWidth() {
        return width;
    }

    public Entity getEntityAt(Coordinates coordinates) {
        return getCoordinatesEntityMap().get(coordinates);
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
        for(int column = 0; column < getLength()+1; column++){
            for(int row = 0; row < getWidth()+1; row++) {
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