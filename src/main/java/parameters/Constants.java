package parameters;

import java.io.IOException;

public final class Constants {

    private Constants() throws IOException {
        throw new UnsupportedOperationException("Util ity class");
    }
    public static final String ANSI_RESET = "\u001B[0m";
    public static final int GAMEBOARD_LENGTH = 9;
    public static final int GAMEBOARD_WIDTH = 9;
    public static final int HERBIVORE_SPEED = 3;
    public static final int HERBIVORE_HEALTH = 9;
    public static final int PREDATOR_SPEED = 3;
    public static final int PREDATOR_HEALTH = 10;
    public static final int PREDATOR_ATTACK_POWER = 3;
    public static final String RENDERER_VISUAL_LINE = "\u001B[96m" + ANSI_RESET;
    public static final String TURN_COUNTER_INFO_MESSAGE_ONE = "------- Turn:";
    public static final String TURN_COUNTER_INFO_MESSAGE_TWO = " -------";
    public static final String PAUSED_INFO_MESSAGE = "Simulation paused";
    public static final String RESUMED_INFO_MESSAGE = "Simulation resumed";
    public static final String STOPPED_INFO_MESSAGE = "Simulation stopped";
    public static final String RESTART_INFO_MESSAGE = "To restart the game, you need to type \"start\"";
    public static final String EXIT_INFO_MESSAGE = "Simulation end. GoodBye!";
    public static final String START = "start";
    public static final String PAUSE = "pause";
    public static final String RESUME = "resume";
    public static final String STOP = "stop";
    public static final String EXIT = "exit";
    public static final String UNKNOWN_COMMAND_MESSAGE = "Unknown command!";
    public static final String START_MENU_INFO_MESSAGE = "\nCommands: [start] [pause] [resume] [stop]" + "\n" + "Enter command: ";


}
