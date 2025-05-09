package actions;


import entity.Coordinates;
import entity.Creature.*;
import entity.Entity;
import entity.GameBoard;

import java.util.ArrayList;

public class SpawnAction<T extends Entity> extends Actions {

    private GameBoard gameBoard;
    private T entity;
    private ArrayList<T> entityList;

    public SpawnAction (Coordinates coordinates, <T> creature) {
        execute();
        entityList = new ArrayList<>();

    }

    public void addNewEntity(ArrayList<? extends Creature> entityList){

        ArrayList<Coordinates> coordinatesList = generateRandomCoordinatesForEntity(3);
        entityList.add(new Herbivore(new Coordinates(1,2), 1, 2));
        entityList.add(new Tree(new Coordinates(), 1, 2));
        entityList.add(new Grass(new Coordinates(), 1, 2));
    }


    private ArrayList<Coordinates> generateRandomCoordinatesForEntity() {
        ArrayList<Coordinates> resultList = new ArrayList<>();
        int maxAttempts = 100;
        for (int i = 0; i < maxAttempts; i++) {
            int randomColumn = (int) (Math.random() * 10) + 1;
            int randomRaw = (int) (Math.random() * 10) + 1;
            Coordinates newCoordinates = new Coordinates(randomColumn, randomRaw);
            if (!resultList.contains(newCoordinates)) {
                resultList.add(newCoordinates);
            }
            if (resultList.size() > 5){
                break;
            }
        }
        return  resultList;
    }


     private <T extends Entity> Entity createEntity(Class<T> typeOfEntity, Coordinates coordinates) {
         return switch (typeOfEntity) {
             case Predator.class -> new Predator(coordinates, 10, 2, 3);
             case Herbivore.class -> new Herbivore(coordinates, 6, 3);
             case Grass.class -> new Grass(coordinates);
             case Tree.class -> new Tree(coordinates);
             case Rock.class -> new Rock(coordinates);
         };
     }

    @Override
    public void execute() {

    }



    public void private <T> setCreature(Coordinates coordinates, <T>creature){
        creature.setCoordinates(coordinates);
        coordinatesEntityMap.put(coordinates, creature);
    }


}
