import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.FileNotFoundException;

public class Day2_Part2 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> allInputs = Day2_Part2.sourceInput();
        int sum = Day2_Part2.getSumId(allInputs);
        System.out.println("Sum is : " + sum);
    }
    
    public static List<String> sourceInput() throws FileNotFoundException {
        List<String> allInputs = new ArrayList<String>();
        File textFile = new File("./input.txt");        
        Scanner scn = new Scanner(textFile);

        while (scn.hasNext()) {
            allInputs.add(scn.nextLine());
        }

        scn.close();
        return allInputs;
    }

    public static int getSumId(List<String> allInputs) {
        int sum = 0;

        Map<String, Integer> maxTable = new HashMap<>();

        for (int i = 0; i < allInputs.size(); ++i) {
            String[] gameArr = allInputs.get(i).split(": ");
            String[] setArr = gameArr[1].split("; ");

            maxTable.put("green", 0);
            maxTable.put("blue", 0);
            maxTable.put("red", 0);

            for (String set : setArr) {
                String[] ballsArr = set.split(", ");

                for (String balls : ballsArr) {
                    String[] valueArr = balls.split(" ");
                    maxTable.put(valueArr[1] ,(Math.max(maxTable.get(valueArr[1]), Integer.parseInt(valueArr[0]))));
                }
            }

            sum += maxTable.get("green") * maxTable.get("red") * maxTable.get("blue");
        }
        
        return sum;
    }
}
