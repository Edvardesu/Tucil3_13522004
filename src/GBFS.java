import java.util.Scanner;
import java.util.*;
import java.io.File;

public class GBFS {
    private String start;
    private String target;
    private List<String> dict;

    public GBFS(String start, String target, List<String> dict) {
        this.start = start;
        this.target = target;
        this.dict = dict;
    }

    public static char getCharFromString(String str, int index) {
        return str.charAt(index);
    }

    public static String setCharFromString(String str, int index, char ch) {
        StringBuffer string = new StringBuffer(str);
        string.setCharAt(index, ch);
        String result = new String(string);
        return result;
    }

    public static List<String> findNeighbors(String word, List<String> dictionary) {
        List<String> neighbors = new ArrayList<>();
        for (String dictWord : dictionary) {
            if (dictWord.length() != word.length()) {
                continue;
            }
            int diffCount = 0;
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) != dictWord.charAt(i)) {
                    diffCount++;
                }
                if (diffCount > 1) {
                    break;
                }
            }
            if (diffCount == 1) {
                neighbors.add(dictWord);
            }
        }
        return neighbors;
    }


    public static PriorQueue countDif(String word, List<String> lstr) {
        PriorQueue pq = new PriorQueue();
        for (String str : lstr) {
            int diffCount = 0;
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) != str.charAt(i)) {
                    diffCount++;
                }
            }
            pq.enqueue(str,diffCount);
        }
        return pq;
    }

    public static void makeResult(PriorQueue pq, List<String> lstr) {
        String res = pq.getTopVal();
        if (res != null) {
            lstr.add(res);
        }
    }


    public void mainGBFS()
    {
        long startTime = System.nanoTime();
        Set<String> visited = new HashSet<>();
        boolean found= true;

        visited.add(start);

        List<String> resList = new ArrayList<>();
        resList.add(start);


        while (!resList.get(resList.size() - 1).equals(target)) {
            start = resList.get(resList.size() - 1);
            List<String> neighbors = findNeighbors(start, dict);
            neighbors.removeIf(visited::contains); // Remove already visited neighbors
            if (neighbors.isEmpty()) {
                System.out.println("No path found.");
                found = false;
                break;
            }

            PriorQueue pqueue = countDif(target, neighbors);
            String nextNode = pqueue.getTopVal();
            if (nextNode == null || visited.contains(nextNode)) {
                System.out.println("No further progress can be made.");
                found = false;
                break;
            }

            visited.add(nextNode);
            resList.add(nextNode);
        }
        long endTime = System.nanoTime();
        long executionTime = (endTime - startTime) / 1000000;

        System.out.println("RESULT LIST:");
        if (found) {
            for (String res : resList) {
                System.out.println(res);
            }
        }

        System.out.println("Visited nodes: " + visited.size());
        System.out.println("Execution time : " + executionTime + "ms");
    }
}
