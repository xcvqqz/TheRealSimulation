package simulation;
import actions.Action;
import actions.ControlRespawnAction;
import actions.MoveAction;
import actions.SpawnAction;
import gameUtils.GameBoard;
import gameUtils.MapConsoleRenderer;

import static parameters.Constants.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Simulation {
    private final GameBoard gameBoard;
    public static AtomicInteger simulationTurnCounter = new AtomicInteger(0);
    private final AtomicBoolean isRunning = new AtomicBoolean(false);
    private final AtomicBoolean isPaused = new AtomicBoolean(false);
    private final List<Action> initActions;
    private final List<Action> turnActions;
    private final MapConsoleRenderer mapConsoleRenderer;
    private Thread simulationThread;
    private final Scanner scanner;

    public Simulation(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        this.initActions = new ArrayList<>();
        this.turnActions = new ArrayList<>();
        this.mapConsoleRenderer = new MapConsoleRenderer(gameBoard);
        this.scanner = new Scanner(System.in);
    }

    private void addActionsList() {
        initActions.add(new SpawnAction(gameBoard));
        turnActions.add(new ControlRespawnAction(gameBoard));
        turnActions.add(new MoveAction(gameBoard));
    }

    private void initSimulation() {
        for (Action action : initActions) {
            action.execute();
        }
    }

    private void nextTurn() {
        for (Action action : turnActions) {
            action.execute();
        }
    }

    private void renderSimulation() {
        mapConsoleRenderer.render();
        System.out.println(RENDERER_VISUAL_LINE);
    }

    private void printSimulationTurnCounter() {
        System.out.println(TURN_COUNTER_INFO_MESSAGE_ONE + simulationTurnCounter.incrementAndGet() + TURN_COUNTER_INFO_MESSAGE_TWO);
    }
    public void startSimulation() {
        if (isRunning.get()) {
            return;
        }

        isRunning.set(true);
        isPaused.set(false);

        simulationThread = new Thread(() -> {
            addActionsList();
            initSimulation();
            while (isRunning.get()) {
                if (!isPaused.get()) {
                    nextTurn();
                    printSimulationTurnCounter();
                    renderSimulation();
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        simulationThread.start();
    }

    public void pauseSimulation() {
        if (!isRunning.get() || isPaused.get()) {
            return;
        }
        isPaused.set(true);
        System.out.println(PAUSED_INFO_MESSAGE);
    }

    public void resumeSimulation() {
        if (!isRunning.get() || !isPaused.get()) {
            return;
        }
        isPaused.set(false);
        System.out.println(RESUMED_INFO_MESSAGE);
    }

    public void stopSimulation() {
        isRunning.set(false);
        isPaused.set(false);
        resetSimulation();
        if (simulationThread != null) {
            simulationThread.interrupt();
        }

        System.out.println(STOPPED_INFO_MESSAGE + "\n" + RESTART_INFO_MESSAGE);
    }

    public void exitSimulation() {
        isRunning.set(false);
        isPaused.set(false);
        if (simulationThread != null) {
            simulationThread.interrupt();
        }
        System.out.println(EXIT_INFO_MESSAGE);
    }

    private void resetSimulation(){
        gameBoard.getEntities().clear();
        turnActions.clear();
        initActions.clear();
        simulationTurnCounter.set(0);
    }

    public void processUserInput() {

            System.out.println(START_MENU_INFO_MESSAGE);

            while (true) {
                String command = scanner.nextLine().trim().toLowerCase();

                switch (command) {
                    case START:
                        startSimulation();
                        break;
                    case PAUSE:
                        pauseSimulation();
                        break;
                    case RESUME:
                        resumeSimulation();
                        break;
                    case STOP:
                        stopSimulation();
                        processUserInput();
                        break;
                    case EXIT:
                        exitSimulation();
                        scanner.close();
                        System.exit(0);
                        return;
                    default:
                        System.out.println(UNKNOWN_COMMAND_MESSAGE);
                }
            }
        }
    }