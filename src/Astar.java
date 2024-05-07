import java.util.*;

public class Astar {
    private String start;
    private String target;
    private Set<String> dict;

    private int visitedNode;

    public Astar(String start, String target, Set<String> dict) {
        this.start = start;
        this.target = target;
        this.dict = dict;
        this.visitedNode = 0;
    }

    private static int heuristic(String current, String target) {
        int mismatchCount = 0;
        for (int i = 0; i < current.length(); i++) {
            if (current.charAt(i) != target.charAt(i)) {
                mismatchCount++;
            }
        }
        return mismatchCount;
    }

    // Generate all valid transformations of a word by changing one letter at a time
    private static List<String> findNeighbors(String word, Set<String> dictionary) {
        List<String> neighbors = new ArrayList<>();
        char[] chars = word.toCharArray();
        for (int i = 0; i < word.length(); i++) {
            char oldChar = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c != oldChar) {
                    chars[i] = c;
                    String newWord = new String(chars);
                    if (dictionary.contains(newWord)) {
                        neighbors.add(newWord);
                    }
                }
            }
            chars[i] = oldChar;
        }
        return neighbors;
    }

    // A* Algorithm
    public List<String> getResult(String start, String target, Set<String> dictionary, int visitedNode) {
        PriorityQueue<WordStepAstar> frontier = new PriorityQueue<>(Comparator.comparingInt(ws -> ws.getTotalCost()));
        Map<String, Integer> costSoFar = new HashMap<>();
        frontier.add(new WordStepAstar(start, 0, heuristic(start, target), null));
        costSoFar.put(start, 0);

        while (!frontier.isEmpty()) {
            WordStepAstar current = frontier.poll();
            this.visitedNode++;

            if (current.getWord().equals(target)) {
                return makePath(current);
            }

            for (String neighbor : findNeighbors(current.getWord(), dictionary)) {
                int newCost = current.getCost() + 1;  // Each step costs 1
                if (!costSoFar.containsKey(neighbor) || newCost < costSoFar.get(neighbor)) {
                    int priority = newCost + heuristic(neighbor, target);
                    frontier.add(new WordStepAstar(neighbor, newCost, priority, current));
                    costSoFar.put(neighbor, newCost);
                }
            }
        }

        return null;  // No path found
    }

    private static List<String> makePath(WordStepAstar target) {
        LinkedList<String> path = new LinkedList<>();
        WordStepAstar step = target;
        while (step != null) {
            path.addFirst(step.getWord());
            step = step.getPred();
        }
        return path;
    }

    public void mainAstar() {
        long startTime = System.nanoTime();
        List<String> result = getResult(start, target, dict, visitedNode);
        long endTime = System.nanoTime();
        long executionTime
                = (endTime - startTime) / 1000000;

        if (result != null) {
            for(String res : result) {
                System.out.println(res);
            }
        } else {
            System.out.println("No path found.");
        }
        System.out.println("Banyak node dikunjungi : " + this.visitedNode);
        System.out.println("Execution time : "+ executionTime + "ms");

    }
}
