import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class Day1_Part2 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> allInputs = Day1_Part2.sourceInput();
        Map<String, Integer> strNums = Day1_Part2.getNumStrList();

        int sum = Day1_Part2.getCalibrationValues(allInputs, strNums);
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

    public static int getCalibrationValues(List<String> allInputs, Map<String, Integer> strNums) {
        int sum = 0;

        for (int i = 0; i < allInputs.size(); ++i) {
            String tempStr = allInputs.get(i);
            int left = -1;
            int right = -1;
            boolean isLeftWord = false;
            boolean isRightWord = false;

            for (int j = 0, k = tempStr.length() - 1; j < tempStr.length(); ++j, --k) {
                char tempLeftChar = tempStr.charAt(j);
                char tempRightChar = tempStr.charAt(k);

                if (Character.isDigit(tempLeftChar) && left == -1) {
                    left = j;
                }

                if (Character.isDigit(tempRightChar) && right == -1) {
                    right = k;
                }

                for (String str : strNums.keySet()) {
                    char firstCharNum = str.charAt(0);
                    char lastCharNum = str.charAt(str.length() - 1);

                    if (firstCharNum == tempLeftChar && j + str.length() - 1 < tempStr.length() &&
                            left == -1 && str.compareTo(tempStr.substring(j, j + str.length())) == 0) {
                        left = strNums.get(str);
                        isLeftWord = true;
                    }

                    if (lastCharNum == tempRightChar && k - str.length() + 1 > 0 && right == -1 &&
                            str.compareTo(tempStr.substring(k - str.length() + 1, k + 1)) == 0) {
                        right = strNums.get(str);
                        isRightWord = true;
                    }

                }
            }

            if (isLeftWord && isRightWord) {
                sum += Integer.parseInt(String.format("%d%d", left, right));
                continue;
            }

            if (isLeftWord) {
                sum += Integer.parseInt(String.format("%d%c", left, tempStr.charAt(right)));
                continue;
            }
            
            if (isRightWord) {
                sum += Integer.parseInt(String.format("%c%d", tempStr.charAt(left), right));
                continue;
            }

            sum += Integer.parseInt(String.format("%c%c", tempStr.charAt(left), tempStr.charAt(right)));
        }

        return sum;
    }

    public static Map<String, Integer> getNumStrList() {
        Map<String, Integer> strNums = new HashMap<String, Integer>();
        strNums.put("one", 1);
        strNums.put("two", 2);
        strNums.put("three", 3);
        strNums.put("four", 4);
        strNums.put("five", 5);
        strNums.put("six", 6);
        strNums.put("seven", 7);
        strNums.put("eight", 8);
        strNums.put("nine", 9);

        return strNums;
    }
}
