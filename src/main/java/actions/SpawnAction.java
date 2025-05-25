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

    public SpawnAction (GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        this.entityList = new ArrayList<>();
    }

    @Override
    public void execute() {
        createEntityList();
        addEntityOnGameBoard();
    }


    private void addEntityOnGameBoard(){
        for(Entity entity : entityList){
            if (!gameBoard.getCoordinatesEntityMap().containsKey(entity.getCoordinates())) {
                gameBoard.getCoordinatesEntityMap().put(entity.getCoordinates(), entity);
            }
        }
    }

    private void createEntityList(){
        List<Coordinates> coordinatesForEntity = generateRandomCoordinatesForEntity();

        if (coordinatesForEntity.size() < 5) {
            throw new IllegalStateException("Не удалось сгенерировать достаточно уникальных координат");
        }

        entityList.add(createEntity(Predator.class, coordinatesForEntity.get(0)));
        entityList.add(createEntity(Herbivore.class, coordinatesForEntity.get(1)));
        entityList.add(createEntity(Grass.class, coordinatesForEntity.get(2)));
        entityList.add(createEntity(Tree.class, coordinatesForEntity.get(3)));
        entityList.add(createEntity(Rock.class, coordinatesForEntity.get(4)));
        entityList.add(createEntity(Herbivore.class, coordinatesForEntity.get(5)));
        entityList.add(createEntity(Grass.class, coordinatesForEntity.get(6)));
    }

    private ArrayList<Coordinates> generateRandomCoordinatesForEntity() {
        ArrayList<Coordinates> coordinatesForEntityList = new ArrayList<>();
        int maxAttempts = 100;
        for (int i = 0; i < maxAttempts; i++) {
            int randomColumn = (int) (Math.random() * gameBoard.getMaxColumn()) + 1;
            int randomRaw = (int) (Math.random() * gameBoard.getMaxRow()) + 1;
            Coordinates newCoordinates = new Coordinates(randomColumn, randomRaw);
            if (!coordinatesForEntityList.contains(newCoordinates)) {
                coordinatesForEntityList.add(newCoordinates);
            }
            if (coordinatesForEntityList.size() > 10){
                break;
            }
        }
        return  coordinatesForEntityList;
    }

     private <T extends Entity> Entity createEntity(Class<T> typeOfEntity, Coordinates coordinates) {
         return switch (typeOfEntity.getSimpleName()) {
             case "Predator" -> new Predator(coordinates, 10, 3, 3);
             case "Herbivore" -> new Herbivore(coordinates, 6, 3);
             case "Grass" -> new Grass(coordinates);
             case "Tree" -> new Tree(coordinates);
             case "Rock" -> new Rock(coordinates);
             default -> throw new IllegalStateException("Unexpected value: " + typeOfEntity.getSimpleName());
         };
     }
}
