import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: java_study_demo
 * @BelongsPackage: PACKAGE_NAME
 * @Author: keer
 * @CreateTime: 2020-04-13 14:39
 * @Description:
 */
public class Main {
    public static void main(String[] args) throws IOException {
//        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
//        String a = rd.readLine();
//        int caseCount = Integer.valueOf(a);
//        for (int i = 0; i < caseCount; i++) {
//            int len = Integer.valueOf(rd.readLine());
//            System.out.println(callNum(len, 3));
//        }
//        System.out.println(callNum(4, 3));
        StringBuffer str=new StringBuffer("azgd");
        System.out.println(str.codePointAt(1));
        System.out.println(str.codePointBefore(1));
        System.out.println(str.codePointCount(1,2));
        System.out.println(str.offsetByCodePoints(0,2));
//        System.out.println(str.co);
//        System.out.println(str.co);
//        System.out.println(str.co);

    }

    public static int lampCount(int count, String path) {
        int sum = 0;
        for (int i = 0; i < count; i++) {
            if (path.charAt(i) == '.') {
                sum++;
                i += 2;
            }
            System.out.println(i);
        }
        return sum;

    }


    public static String callNum(int sum, int count) {
        StringBuilder res = new StringBuilder();
        CircleLink head = new CircleLink(1);
        CircleLink p = head;
        //环状链表
        for (int i = 1; i < sum; i++) {
            CircleLink temp = new CircleLink(i + 1);
            p.next = temp;
            temp.next = head;
            p = temp;
        }
        CircleLink pro = head;
        p = pro.next;
        int num = 2;
        for (; pro != p; ) {
            if (num % count == 0) {
                res.append(p.value + " ");
                pro.next = p.next;
                p = p.next;
                num = 1;
            } else {
                pro = p;
                p = p.next;
                num++;
            }
        }
        res.append(p.value);
        return res.toString();
    }

    public static String formatePrint(String str) {
        int count = str.length() / 8;
        if (str.length() % 8 > 0) {
            ++count;
        }
        int start = 0;
        int end = 8;
        StringBuilder builder = new StringBuilder();
        for (int j = 0; j < count; j++) {
            end = end > str.length() ? str.length() : end;
            String current = str.substring(start, end);

            builder.append(current);
            if (current.length() < 8) {
                for (int k = 0; k < 8 - current.length(); k++) {
                    builder.append("0");
                }
            }
            start += 8;
            end += 8;
            builder.append("\n");
        }
        return builder.toString().trim();
    }

    /**
     * 集五福
     *
     * @param list
     * @return
     */
    public static int wfCount(List<String> list) {
        int[] count = new int[5];
        for (String str : list) {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '1') {
                    count[i]++;
                }
            }
        }
        int minCount = count[0];
        for (int c : count) {
            if (minCount > c) {
                minCount = c;
            }
        }
        return minCount;
    }

    Map<String, String> phone = new HashMap<String, String>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};

    List<String> output = new ArrayList<String>();

    public void backtrack(String combination, String next_digits) {
        // if there is no more digits to check
        if (next_digits.length() == 0) {
            // the combination is done
            output.add(combination);
        }
        // if there are still digits to check
        else {
            // iterate over all letters which map
            // the next available digit
            String digit = next_digits.substring(0, 1);
            String letters = phone.get(digit);
            for (int i = 0; i < letters.length(); i++) {
                String letter = phone.get(digit).substring(i, i + 1);
                // append the current letter to the combination
                // and proceed to the next digits
                backtrack(combination + letter, next_digits.substring(1));
            }
        }
    }

    public List<String> letterCombinations(String digits) {
        if (digits.length() != 0) {
            backtrack("", digits);
        }
        return output;
    }

}

class CircleLink {
    int value;
    CircleLink next;

    public CircleLink(int value) {
        this.value = value;
        this.next = null;
    }
}