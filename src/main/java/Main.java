import gameUtils.GameBoard;
import simulation.Simulation;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        Simulation simulation = new Simulation(new GameBoard());
        simulation.processUserInput();

    }
}





