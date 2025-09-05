import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Moksha Patam
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *
 * Completed by: [YOUR NAME HERE]
 *
 */

public class MokshaPatam {
    public static final int MAX_ROLL = 6;

    static class BoardPosition {
        int position;
        int moves;

        public BoardPosition(int position, int moves) {
            this.position = position;
            this.moves = moves;
        }
    }

    /**
     * TODO: Complete this function, fewestMoves(), to return the minimum number of moves
     *  to reach the final square on a board with the given size, ladders, and snakes.
     */
    public static int fewestMoves(int boardsize, int[][] ladders, int[][] snakes) {
        int winningSquare = boardsize;
        Queue<BoardPosition> toVisit = new LinkedList<>();

        boolean[] visited = new boolean[winningSquare + 1];
        // Start at the starting point
        BoardPosition start = new BoardPosition(1, 0);
        toVisit.add(start);
        visited[1] = true;

        // Go through the maze
        while (!toVisit.isEmpty()) {
            // Find current position
            BoardPosition current = toVisit.poll();

            // If the current position is at the end, we are done and can return the solution
            if (current.position == winningSquare) {
                return current.moves;
            }

            for (int i = 1; i <= MAX_ROLL; i++) {
                int nextPosition = current.position + i;
                if (nextPosition > winningSquare) {
                    continue;
                }
                int finalPosition = applyLaddersAndSnakes(nextPosition, ladders, snakes);
                if (!visited[finalPosition]) {
                    // Add position to toVisit
                    toVisit.add(new BoardPosition(finalPosition, current.moves + 1));
                    // Mark as visited
                    visited[finalPosition] = true;
                }
            }

        }
        // If no solution is found return null
        return -1;
    }

    public static int applyLaddersAndSnakes(int position, int[][] ladders, int[][] snakes) {
        int finalPosition = position;
        for (int[] ladder : ladders) {
            int startLadder = ladder[0];
            int endLadder = ladder[1];
            if (startLadder == finalPosition) {
                finalPosition = endLadder;
                break;
            }
        }
        for (int[] snake : snakes) {
            int startSnake = snake[0];
            int endSnake = snake[1];
            if (startSnake == finalPosition) {
                finalPosition = endSnake;
                break;
            }
        }
        return finalPosition;
    }
}
