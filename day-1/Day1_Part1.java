import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day1_Part1 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> allInputs = Day1_Part1.sourceInput();
        int sum = Day1_Part1.getCalibrationValues(allInputs);
        System.out.println(String.format("TOTAL SUM IS : %d.", sum));
    }

    public static List<String> sourceInput() throws FileNotFoundException {
        List<String> allInputs = new ArrayList<String>();

        try {
            File textFile = new File("./input.txt");
            Scanner scn = new Scanner(textFile);

            while (scn.hasNextLine()) {
                allInputs.add(scn.nextLine());
            }

            scn.close();

        } catch (Exception ex) {
            throw ex;
        }

        return allInputs;
    }

    public static int getCalibrationValues(List<String> allInputs) {
        int sum = 0;

        for (int i = 0; i < allInputs.size(); ++i) {
            String tempStr = allInputs.get(i);
            int left = -1;
            int right = -1;
            System.out.println(tempStr);

            for (int j = 0, k = tempStr.length() - 1; j < tempStr.length(); ++j, --k) {
                if (Character.isDigit(tempStr.charAt(j)) && left == -1) {
                    left = j;
                }

                if (Character.isDigit(tempStr.charAt(k)) && right == -1) {
                    right = k;
                }
            }

            sum += Integer.parseInt(String.format("%c%c", tempStr.charAt(left), tempStr.charAt(right)));
        }

        return sum;
    }
}
