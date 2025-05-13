import actions.SpawnAction;
import entity.Coordinates;
import entity.Creature.Predator;
import entity.GameBoard;
import entity.Creature.Herbivore;
import entity.Creature.Grass;

public class Main {


    public static void main(String[] args) {

        GameBoard gameBoard = new GameBoard(10,10);

       new SpawnAction(gameBoard).execute();


        int x = 123;
    }
}



