import java.util.*;
import java.io.*;

public class UCS {
    private String start;
    private String target;
    private Set<String> dict;

    private int visitedNode;

    public UCS(String start, String target, Set<String> dict) {
        this.start = start;
        this.target = target;
        this.dict = dict;
        this.visitedNode = 0;
    }

    public List<String> findNeighbors(String word, Set<String> dictionary) {

        List<String> neighbors = new ArrayList<>();
        char[] chars = word.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char oldChar = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == oldChar) {
                    continue;
                }
                chars[i] = c;
                String newWord = new String(chars);
                if (dictionary.contains(newWord)) {
                    neighbors.add(newWord);
                }
            }
            chars[i] = oldChar;
        }
        return neighbors;
    }

    public List<String> getResult(Set<String> dictionary, String start, String end, int visitedNode) {
        PriorityQueue<WordStep> pq = new PriorityQueue<>(Comparator.comparingInt(ws -> ws.cost));
        Map<String, String> wordFrom = new HashMap<>();
        Set<String> visited = new HashSet<>();

        pq.add(new WordStepUCS(start, 0));
        wordFrom.put(start, null);

        while (!pq.isEmpty()) {
            WordStep current = pq.poll();
            this.visitedNode++;

            if (current.word.equals(end)) {
                return reconstructPath(wordFrom, end);
            }

            if (!visited.contains(current.word)) {
                visited.add(current.word);
                for (String neighbor : findNeighbors(current.word, dictionary)) {

                    if (!visited.contains(neighbor)) {
                        pq.add(new WordStepUCS(neighbor, current.cost + 1));
                        wordFrom.put(neighbor, current.word);
                    }
                }
            }
        }

        return Collections.emptyList(); // tidak ketemu path
    }

    public List<String> reconstructPath(Map<String, String> parent, String end) {
        List<String> path = new ArrayList<>();
        for (String i = end; i != null; i = parent.get(i)) {
            path.add(i);
        }
        Collections.reverse(path);
        return path;
    }

    public void mainUCS() {
        long startTime = System.nanoTime();
        List<String> path = getResult(dict, start, target, visitedNode);
        long endTime = System.nanoTime();
        long executionTime
                = (endTime - startTime) / 1000000;

        if (path != null) {
            for(String res : path) {
                System.out.println(res);
            }
        } else {
            System.out.println("No path found.");
        }
        System.out.println("Banyak node dikunjungi : " + this.visitedNode);
        System.out.println("Execution time : "+ executionTime + "ms");
    }
}
