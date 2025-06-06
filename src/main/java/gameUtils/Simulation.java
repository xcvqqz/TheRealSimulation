package gameUtils;
import actions.Action;
import actions.ControlRespawnAction;
import actions.MoveAction;
import actions.SpawnAction;
import static gameUtils.SimulationConstants.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Simulation {

    private final GameBoard gameBoard;

    public static AtomicInteger simulationTurnCounter = new AtomicInteger(0);
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
        turnActions.add(new ControlRespawnAction(gameBoard));
        turnActions.add(new MoveAction(gameBoard));
    }

    private void initSimulation() {
        for (Action action : initActions) {
            action.execute();
        }
    }

    private void nextTurn(){
        for (Action action : turnActions){
            action.execute();
        }
    }

   private void renderSimulation(){
        mapConsoleRenderer.render();
       System.out.println(RENDERER_VISUAL_LINE);
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
                nextTurn();
                printSimulationTurnCounter();
                renderSimulation();
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
        });
       simulationThread.start();
   }

   private void incrementSimulationTurnCounter(){
        simulationTurnCounter.incrementAndGet();
   }
   private void printSimulationTurnCounter(){
       System.out.println(TURN_COUNTER_INFO_MESSAGE + simulationTurnCounter.incrementAndGet());
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