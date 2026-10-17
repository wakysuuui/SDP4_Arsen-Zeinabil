package robots;

import java.util.*;

public class WarehouseMap {
    private final int width = 5;
    private final int height = 5;
    private final Set<Integer> obstacles = Set.of(7, 12, 17);

    public int cell(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IllegalArgumentException("Destination is outside the warehouse");
        }

        int cell = y * width + x;

        if (obstacles.contains(cell)) {
            throw new IllegalArgumentException("Destination is blocked");
        }

        return cell;
    }

    public List<Integer> route(int start, int destination) {
        cell(start % width, start / width);
        cell(destination % width, destination / width);

        Map<Integer, Integer> previous = new HashMap<>();
        Queue<Integer> queue = new ArrayDeque<>();

        queue.add(start);
        previous.put(start, start);

        while (!queue.isEmpty() && !previous.containsKey(destination)) {
            int current = queue.remove();
            int x = current % width;
            int y = current / width;

            int[][] offsets = {
                    {1, 0}, {-1, 0}, {0, 1}, {0, -1}
            };

            for (int[] offset : offsets) {
                int nx = x + offset[0];
                int ny = y + offset[1];

                if (nx < 0 || nx >= width || ny < 0 || ny >= height) {
                    continue;
                }

                int next = ny * width + nx;

                if (obstacles.contains(next) || previous.containsKey(next)) {
                    continue;
                }

                previous.put(next, current);
                queue.add(next);
            }
        }

        if (!previous.containsKey(destination)) {
            throw new IllegalStateException("No safe route");
        }

        LinkedList<Integer> result = new LinkedList<>();

        for (int cursor = destination; cursor != start; cursor = previous.get(cursor)) {
            result.addFirst(cursor);
        }

        return List.copyOf(result);
    }

    public boolean adjacent(int first, int second) {
        cell(first % width, first / width);
        cell(second % width, second / width);

        return Math.abs(first % width - second % width)
                + Math.abs(first / width - second / width) == 1;
    }
}