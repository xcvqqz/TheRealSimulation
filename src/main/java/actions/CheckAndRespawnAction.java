package actions;

import entity.Coordinates;
import entity.Creature.Grass;
import entity.Creature.Herbivore;
import entity.Entity;
import entity.GameBoard;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class CheckAndRespawnAction extends Action {

    private GameBoard gameBoard;
    private static final int MAX_RESPAWN_ATTEMPTS = 100;
    private final Random random = new Random();



    public CheckAndRespawnAction(GameBoard gameBoard){
        this.gameBoard = gameBoard;
    }

    @Override
    public void execute() {
        if (!isEntityAvailable(Herbivore.class)) {
            Coordinates herbivoreCoords = getRandomCoordinatesForRespawnEntity();
            if (herbivoreCoords != null) {
                gameBoard.getCoordinatesEntityMap().put(herbivoreCoords,
                        new Herbivore(herbivoreCoords, 6, 3));
            }
        }
        if (!isEntityAvailable(Grass.class)) {
            Coordinates grassCoords = getRandomCoordinatesForRespawnEntity();
            if (grassCoords != null) {
                gameBoard.getCoordinatesEntityMap().put(grassCoords,
                        new Grass(grassCoords));
            }
        }
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



    private Coordinates getRandomCoordinatesForRespawnEntity() {
        if (gameBoard == null) {
            throw new IllegalStateException("GameBoard не может быть null");
        }
        int maxRow = gameBoard.getMaxRow();
        int maxColumn = gameBoard.getMaxColumn();
        int attempts = 0;

        while (attempts < MAX_RESPAWN_ATTEMPTS) {
            int randomRow = random.nextInt(maxRow);
            int randomColumn = random.nextInt(maxColumn);
            Coordinates temp = new Coordinates(randomRow, randomColumn);

            if (!gameBoard.getCoordinatesEntityMap().containsKey(temp)) {
                return temp;
            }
            attempts++;
        }
        return null;
    }
}
