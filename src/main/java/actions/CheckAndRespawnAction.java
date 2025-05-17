package actions;

import entity.Coordinates;
import entity.Creature.Grass;
import entity.Creature.Herbivore;
import entity.Entity;
import entity.GameBoard;
import java.util.ArrayList;
import java.util.Map;

public class CheckAndRespawnAction extends Action {

    private GameBoard gameBoard;
    private final Coordinates respawnHerbivoreCoordinates = getRandomCoordinatesForRespawnEntity();
    private final Coordinates respawnGrassCoordinates = getRandomCoordinatesForRespawnEntity();


    public CheckAndRespawnAction(GameBoard gameBoard){
        this.gameBoard = gameBoard;
    }

    private <T extends Entity> boolean isEntityAvailable(Class<T> typeOfEntity){
        ArrayList<T> entityList = new ArrayList<>();
        for(Map.Entry<Coordinates, Entity> entity : gameBoard.getCoordinatesEntityMap().entrySet()){
            if(typeOfEntity.isInstance(entity.getValue())){
                entityList.add((T) entity.getValue());
            }
        }
        if(entityList.isEmpty()){
            return false;
        } else {
            return true;
        }
    }

    private void respawnHerbivoreForGameBoard(){
        gameBoard.getCoordinatesEntityMap().put(respawnHerbivoreCoordinates, new Herbivore(respawnHerbivoreCoordinates,  6, 3));
    }
    private void respawnGrassForGameBoard(){
        gameBoard.getCoordinatesEntityMap().put(respawnGrassCoordinates, new Grass(respawnGrassCoordinates));
    }


    private Coordinates getRandomCoordinatesForRespawnEntity(){
        Coordinates result;
        while(true){
            int randomColumn = (int) (Math.random() * gameBoard.getMaxColumn()) + 1;
            int randomRaw = (int) (Math.random() * gameBoard.getMaxRow()) + 1;
            Coordinates temp = new Coordinates(randomColumn, randomRaw);
            if(!gameBoard.getCoordinatesEntityMap().containsKey(temp)){
                result = temp;
                break;
            }
        }
        return result;
    }



    @Override
    public void execute() {

    }


}
