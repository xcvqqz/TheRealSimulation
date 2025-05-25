import actions.CheckAndRespawnAction;
import actions.MoveAction;
import actions.SpawnAction;
import entity.GameBoard;
import entity.MapConsoleRenderer;

public class Main {

    public static void main(String[] args) {

        GameBoard gameBoard = new GameBoard(9,9);
        gameBoard.setNewEntityForMap();
//
//
        CheckAndRespawnAction respawnAction = new CheckAndRespawnAction(gameBoard);

        MoveAction moveAction = new MoveAction(gameBoard);
        MapConsoleRenderer mapConsoleRenderer = new MapConsoleRenderer(gameBoard);

//        SpawnAction spawnAction = new SpawnAction(gameBoard);
//        spawnAction.execute();


        mapConsoleRenderer.render();
        moveAction.execute();
        System.out.println("----------------------------------");
        mapConsoleRenderer.render();
        System.out.println("----------------------------------");
        moveAction.execute();
        mapConsoleRenderer.render();
        System.out.println("----------------------------------");
        moveAction.execute();
        mapConsoleRenderer.render();
        System.out.println("----------------------------------");
        moveAction.execute();
        mapConsoleRenderer.render();
        System.out.println("----------------------------------");
        moveAction.execute();
        mapConsoleRenderer.render();
        System.out.println("----------------------------------");
        moveAction.execute();
        mapConsoleRenderer.render();
        System.out.println("----------------------------------");
        moveAction.execute();
        mapConsoleRenderer.render();
        respawnAction.execute();
        System.out.println("----------------------------------");
        mapConsoleRenderer.render();
        System.out.println("----------------------------------");
        moveAction.execute();
        mapConsoleRenderer.render();
        System.out.println("----------------------------------");
        moveAction.execute();
        mapConsoleRenderer.render();
        System.out.println("----------------------------------");
        moveAction.execute();
        mapConsoleRenderer.render();
        System.out.println("----------------------------------");
        moveAction.execute();
        mapConsoleRenderer.render();
        System.out.println("----------------------------------");
        moveAction.execute();
        mapConsoleRenderer.render();
        System.out.println("----------------------------------");
        moveAction.execute();
        mapConsoleRenderer.render();
        System.out.println("----------------------------------");
        moveAction.execute();
        mapConsoleRenderer.render();
        System.out.println("----------------------------------");
        moveAction.execute();
        mapConsoleRenderer.render();
        System.out.println("----------------------------------");
        respawnAction.execute();
        mapConsoleRenderer.render();
        System.out.println("----------------------------------");
        moveAction.execute();
        mapConsoleRenderer.render();
        System.out.println("----------------------------------");
        moveAction.execute();
        mapConsoleRenderer.render();
        System.out.println("----------------------------------");  //ТУТ ПИЗДЕЦ
        moveAction.execute();
        mapConsoleRenderer.render();
        System.out.println("----------------------------------");
        moveAction.execute();
        mapConsoleRenderer.render();
        System.out.println("----------------------------------");
        moveAction.execute();
        mapConsoleRenderer.render();
        System.out.println("----------------------------------");
        moveAction.execute();
        mapConsoleRenderer.render();
        System.out.println("----------------------------------");
        moveAction.execute();
        mapConsoleRenderer.render();
        System.out.println("----------------------------------");
        moveAction.execute();
        mapConsoleRenderer.render();
        System.out.println("----------------------------------");
        moveAction.execute();
        mapConsoleRenderer.render();
        System.out.println("----------------------------------");
        moveAction.execute();
        mapConsoleRenderer.render();
        System.out.println("----------------------------------");
        moveAction.execute();
        mapConsoleRenderer.render();
        System.out.println("----------------------------------");
        moveAction.execute();
        mapConsoleRenderer.render();
        System.out.println("----------------------------------");
        moveAction.execute();
        mapConsoleRenderer.render();
        System.out.println("----------------------------------");
        moveAction.execute();
        mapConsoleRenderer.render();
        System.out.println("----------------------------------");
        moveAction.execute();
        mapConsoleRenderer.render();
        System.out.println("----------------------------------");







        int x = 0;

    }
}



