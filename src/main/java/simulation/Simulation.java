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
        System.out.println("----------------------------------");
    }

    private void printSimulationTurnCounter() {
        System.out.println("Turn: " + simulationTurnCounter.incrementAndGet());
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
        System.out.println("Simulation paused");
    }

    public void resumeSimulation() {
        if (!isRunning.get() || !isPaused.get()) {
            return;
        }
        isPaused.set(false);
        System.out.println("Simulation resumed");
    }

    public void stopSimulation() {
        isRunning.set(false);
        isPaused.set(false);
        resetSimulation();

        System.out.println("Simulation stopped");
        System.out.println("To restart the game, you need to type \"start\"");
    }


    public void exitSimulation() {
        isRunning.set(false);
        isPaused.set(false);
        if (simulationThread != null) {
            simulationThread.interrupt();
        }
        System.out.println("Simulation end. GoodBye!");
    }

    private void resetSimulation(){
        gameBoard.getEntities().clear();
        turnActions.clear();
        initActions.clear();
        simulationTurnCounter.set(0);
    }


    public void processUserInput() {

            System.out.println("\nCommands: [start] [pause] [resume] [stop]");
            System.out.print("Enter command: ");

            while (true) {
                String command = scanner.nextLine().trim().toLowerCase();

                switch (command) {
                    case "start":
                        startSimulation();
                        break;
                    case "pause":
                        pauseSimulation();
                        break;
                    case "resume":
                        resumeSimulation();
                        break;
                    case "stop":
                        stopSimulation();
                        processUserInput();
                        break;
                    case "exit":
                        exitSimulation();
                        scanner.close();
                        System.exit(0);
                        return;
                    default:
                        System.out.println("Unknown command!");
                }
            }
        }
    }