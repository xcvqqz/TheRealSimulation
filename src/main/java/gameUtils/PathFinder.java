package gameUtils;

import entity.Entity;

import java.util.*;

public class PathFinder {
    private GameBoard gameBoard;
    private Map<Coordinates, Coordinates> parentsPath;
    private ArrayList<Coordinates> visitedPath;
    private Queue<Coordinates> pathQueue;

    public PathFinder(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        this.parentsPath = new HashMap<>();
        this.visitedPath = new ArrayList<>();
        this.pathQueue = new ArrayDeque<>();
    }

    public <T extends Entity> List<Coordinates> searchFood(Coordinates start, Class<T> typeOfFood) {
        pathQueue.clear();
        visitedPath.clear();
        parentsPath.clear();

        pathQueue.add(start);
        visitedPath.add(start);
        parentsPath.put(start, null);

        while (!pathQueue.isEmpty()) {
            Coordinates current = pathQueue.remove();

            Entity entity = gameBoard.getEntityAt(current);
            if (entity != null && typeOfFood.isInstance(entity)) {
                return reconstructPath(current);
            }

            for (Coordinates neighbor : getNeighboursCoordinates(current, typeOfFood)) {
                if (!visitedPath.contains(neighbor)) {
                    visitedPath.add(neighbor);
                    parentsPath.put(neighbor, current);
                    pathQueue.add(neighbor);
                }
            }
        }
        return Collections.emptyList();
    }

    private LinkedList<Coordinates> reconstructPath(Coordinates goal) {
        LinkedList<Coordinates> path = new LinkedList<>();
        Coordinates current = goal;

        while (current != null) {
            path.addFirst(current);
            current = parentsPath.get(current);
        }

        if (!path.isEmpty()) {
            path.removeFirst();
        }

        return path;
    }

    private Coordinates[] getShiftDirections() {
        return new Coordinates[]{
                new Coordinates(-1, 0),
                new Coordinates(1, 0),
                new Coordinates(0, -1),
                new Coordinates(0, 1)
        };
    }

    private HashSet<Coordinates> getNeighboursCoordinates(Coordinates currentCoordinates, Class<? extends Entity> clazz) {
        HashSet<Coordinates> result = new HashSet<>();
        for (Coordinates direction : getShiftDirections()) {
            int nearColumn = currentCoordinates.column() + direction.column();
            int nearRow = currentCoordinates.row() + direction.row();
            Coordinates nearCoordinate = new Coordinates(nearColumn, nearRow);
            if (isValidCell(gameBoard, nearCoordinate, clazz)) {
                result.add(nearCoordinate);
            }
        }
        return result;
    }

    private boolean isValidCell(GameBoard gameBoard, Coordinates coordinates, Class<? extends Entity> clazz){
        return gameBoard.isValidateCoordinate(coordinates)
                && ((gameBoard.getEntities().get(coordinates) == null) ||
                gameBoard.getEntityAt(coordinates).getClass() == clazz);
    }

}