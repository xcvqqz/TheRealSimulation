package actions;

import entity.Coordinates;
import entity.Creature.Grass;
import entity.Creature.Herbivore;
import entity.Entity;
import entity.GameBoard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class CheckAction extends Action {

    private GameBoard gameBoard;

    public CheckAction(GameBoard gameBoard){
        this.gameBoard = gameBoard;
    }

    private boolean isGrassAvailable(){
        ArrayList<Grass> grassList = new ArrayList<>();
        for(Map.Entry<Coordinates, Entity> grass : gameBoard.getCoordinatesEntityMap().entrySet()){
            if(grass.getValue() instanceof Grass){
                grassList.add((Grass) grass.getValue());
            }
        }
        if(grassList.isEmpty()){
            return false;
        } else {
            return true;
        }
    }

    private boolean isHerbivoreAvailable(){
        ArrayList<Grass> grassList = new ArrayList<>();
        for(Map.Entry<Coordinates, Entity> grass : gameBoard.getCoordinatesEntityMap().entrySet()){
            if(grass.getValue() instanceof Grass){
                grassList.add((Grass) grass.getValue());
            }
        }
        if(grassList.isEmpty()){
            return false;
        } else {
            return true;
        }
    }



    @Override
    public void execute() {

    }


}
