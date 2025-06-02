package entity;
import actions.Action;
import actions.CheckAndRespawnAction;
import actions.MoveAction;
import actions.SpawnAction;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Simulation {

    private final GameBoard gameBoard;
    private final AtomicBoolean isRunning = new AtomicBoolean(false);
    private final List<Action> initActions;
    private final List<Action> turnActions;
    private final MapConsoleRenderer mapConsoleRenderer;
    private Thread simulationThread;

    public Simulation(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        this.initActions = new ArrayList<>();
        this.turnActions = new ArrayList<>();
        this.mapConsoleRenderer = new MapConsoleRenderer(gameBoard);
    }

    private void addActionsList(){
        initActions.add(new SpawnAction(gameBoard));
        turnActions.add(new CheckAndRespawnAction(gameBoard));
        turnActions.add(new MoveAction(gameBoard));
    }


    private void initSimulation() {
        for (Action action : initActions) {
            action.execute();
        }
    }

    private void turnSimulation(){
        for (Action action : turnActions){
            action.execute();
        }
    }

   private void renderSimulation(){
        mapConsoleRenderer.render();
       System.out.println("-------------------------");
   }

   public void startSimulation(){
        if(isRunning.get()){
            return;
        }

        isRunning.set(true);
        simulationThread = new Thread(() -> {
            addActionsList();
            initSimulation();
            while (isRunning.get()){
                turnSimulation();
                renderSimulation();
                try{
                    Thread.sleep(2000);
                } catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
        });
       simulationThread.start();
   }

    public void pauseSimulation() {
        if (!isRunning.get()) {
            return;
        }

        isRunning.set(false);
        if (simulationThread != null) {
            simulationThread.interrupt();
        }
    }


}



    /* nextTurn() - просимулировать и отрендерить один ход*/

    /* startSimulation() - запустить бесконечный цикл симуляции и рендеринга*/

    /* pauseSimulation() - приостановить бесконечный цикл симуляции и рендеринга*/

    /* initActions - действия, совершаемые перед стартом симуляции. Пример - расставить объекты и существа на карте*/

        /*turnActions - действия, совершаемые каждый ход. Пример - передвижение существ, добавить травы или травоядных,
        если их осталось мало*/

