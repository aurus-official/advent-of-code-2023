import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.FileNotFoundException;

public class Day2_Part1 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> allInputs = Day2_Part1.sourceInput();
        int sum = Day2_Part1.getSumId(allInputs);
        System.out.println("\nSum is : " + sum);
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

        Map<String, Integer> restrictions = new HashMap<>();
        restrictions.put("blue", 14);
        restrictions.put("green", 13);
        restrictions.put("red", 12);


        for (int i = 0; i < allInputs.size(); ++i) {
            String[] gameArr = allInputs.get(i).split(": ");
            String[] setArr = gameArr[1].split("; ");
            boolean setValid = true;
            boolean gameValid = true;

            System.out.print(gameArr[0].split(" ")[1] + " : ");

            for (String set : setArr) {
                String[] ballsArr = set.split(", ");

                for (String balls : ballsArr) {
                    String[] valueArr = balls.split(" ");
                    
                    if (restrictions.get(valueArr[1]) < Integer.parseInt(valueArr[0])) {
                        System.out.print(valueArr[1] + ", ");
                        setValid = false;
                        break;
                    }
                }

                if (!setValid) {
                    System.out.println();
                    gameValid = false;
                    break;
                }

            }

            if (!gameValid) {
                continue;
            }

            int id = Integer.parseInt(gameArr[0].split(" ")[1]);
            sum += id;
        }
        
        return sum;
    }
}
