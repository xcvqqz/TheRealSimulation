import actions.MoveAction;
import actions.SpawnAction;
import entity.Coordinates;
import entity.Creature.Predator;
import entity.GameBoard;
import entity.Creature.Herbivore;
import entity.Creature.Grass;

public class Main {


    public static void main(String[] args) {

        GameBoard gameBoard = new GameBoard(5,5);

        new SpawnAction(gameBoard).execute();
       MoveAction moveAction = new MoveAction(gameBoard);
       moveAction.execute();
        moveAction.execute();
        moveAction.execute();
        moveAction.execute();



        int x = 123;
    }
}



