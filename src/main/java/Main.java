import entity.Coordinates;
import entity.Creature.Predator;
import entity.GameBoard;
import entity.Creature.Herbivore;
import entity.Creature.Grass;

public class Main {


    public static void main(String[] args) {

        GameBoard gameBoard = new GameBoard(5,5);
        gameBoard.setNewEntityForMap();
        new Predator(new Coordinates(3, 3), 1, 10, 10).makeMove(gameBoard);



        int x = 123;
    }
}



