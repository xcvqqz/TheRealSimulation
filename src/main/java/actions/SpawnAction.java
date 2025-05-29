package actions;


import entity.Coordinates;
import entity.Creature.*;
import entity.Entity;
import entity.GameBoard;

import java.util.ArrayList;
import java.util.List;

public class SpawnAction extends Action {

    private GameBoard gameBoard;
    private ArrayList<Entity> entityList;
    private ArrayList<Coordinates> coordinatesList;

    public SpawnAction (GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        this.entityList = new ArrayList<>();
        this.coordinatesList = new ArrayList<>();
    }

    @Override
    public void execute() {
        createEntityList();
        addEntityOnGameBoard();
    }


    private void addEntityOnGameBoard(){
        coordinatesList = generateRandomCoordinates();
        for(int i = 0; i < entityList.size(); i++){
            gameBoard.getCoordinatesEntityMap().put(coordinatesList.get(i), entityList.get(i));
        }
    }

    private void createEntityList(){
        entityList.add(createEntity(Predator.class));
        entityList.add(createEntity(Herbivore.class));
        entityList.add(createEntity(Grass.class));
        entityList.add(createEntity(Tree.class));
        entityList.add(createEntity(Rock.class));
        entityList.add(createEntity(Herbivore.class));
        entityList.add(createEntity(Grass.class));
    }

    private ArrayList<Coordinates> generateRandomCoordinates() {
        ArrayList<Coordinates> resultList = new ArrayList<>();
        int maxAttempts = 100;
        for (int i = 0; i < maxAttempts; i++) {
            int randomColumn = (int) (Math.random() * gameBoard.getMaxColumn()) + 1;
            int randomRaw = (int) (Math.random() * gameBoard.getMaxRow()) + 1;
            Coordinates newCoordinates = new Coordinates(randomColumn, randomRaw);
            if (!resultList.contains(newCoordinates)) {
                resultList.add(newCoordinates);
            }
            if (resultList.size() > 10){
                break;
            }
        }
        return  resultList;
    }

     private <T extends Entity> Entity createEntity(Class<T> typeOfEntity) {
         return switch (typeOfEntity.getSimpleName()) {
             case "Predator" -> new Predator(10, 3, 3);
             case "Herbivore" -> new Herbivore(6, 3);
             case "Grass" -> new Grass();
             case "Tree" -> new Tree();
             case "Rock" -> new Rock();
             default -> throw new IllegalStateException("Unexpected value: " + typeOfEntity.getSimpleName());
         };
     }
}
