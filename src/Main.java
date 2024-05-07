import java.util.List;
import java.util.*;
import java.util.Scanner;
import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
        // pass the path to the file as a parameter
        File file = new File(
                "D:\\OneDrive - Institut Teknologi Bandung\\COLLEGE\\SEMESTER 4\\Strategi Algoritma\\tucil3\\src\\words_alpha.txt");
        Scanner sc = new Scanner(file);

        List<String> dictionaryL = new ArrayList<>();
        Set<String> dictionaryS = new HashSet<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (!line.isEmpty()) {
                dictionaryL.add(line);
                dictionaryS.add(line);
            }
        }
        sc.close(); // Close the file scanner

        System.out.println("Enter Start");
        Scanner myObj1 = new Scanner(System.in);
        String start = myObj1.nextLine().trim();
        if (!dictionaryL.contains(start)) {
            System.out.println("Start word not in dictionary");
            return;
        }
        if (!dictionaryS.contains(start)) {
            System.out.println("Start word not in dictionary");
            return;
        }

        System.out.println("Enter Target");
        Scanner myObj2 = new Scanner(System.in);
        String target = myObj2.nextLine().trim();

        if (!dictionaryL.contains(target)) {
            System.out.println("Target word not in dictionary");
            return;
        }
        if (!dictionaryS.contains(target)) {
            System.out.println("Target word not in dictionary");
            return;
        }

        if (start.length() != target.length()) {
            System.out.println("Start and target words must be of the same length.");
        }

        if (start.equals(target)) {
            System.out.println("Start and target word sudah sama");
            return ;
        }

        UCS ucs = new UCS(start, target, dictionaryS);
        GBFS gbfs = new GBFS(start, target, dictionaryL);
        Astar astar = new Astar(start, target, dictionaryS);

//        List<String> path = UCS.getResult(dictionaryS, start, target);

        System.out.println("Pick your algorithm!!!");
        System.out.println("=== Type in UCS / GBFS / A* ===");
        Scanner nyar = new Scanner(System.in);
        String option = nyar.nextLine().trim();
        myObj1.close();
        myObj2.close();
        nyar.close();
        if (option.equals("UCS")) {
            ucs.mainUCS();
        } else if (option.equals("GBFS")) {
            gbfs.mainGBFS();
        } else if (option.equals("A*")) {
            astar.mainAstar();
        }

    }
}
