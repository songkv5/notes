package com.ws.learn.interviews;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author willis
 * @chapter
 * @section
 * @description
 * @since 2018年10月24日 00:41
 */
public class StringCutQuestion {
    static Pattern pattern = Pattern.compile("[\\d]+");

    public static void main(String[] args) {
        String input = "111111123456";
        List<String> result = splitNums(input, 7, 600, "*");
        if (result == null || result.isEmpty()) {
            System.out.println("无法切割满足要求的字符串");
        } else {
            result.forEach(item -> {
                System.out.println(item);
            });
        }
    }

    /**
     * @param input       待处理字符串
     * @param numberCount 要分成几段
     * @param maxValue    最大值
     * @param separator   分隔符
     * @return
     */
    public static List<String> splitNums(String input, int numberCount, int maxValue, String separator) {
        if (!pattern.matcher(input).matches()) {
            return null;
        }
        if (input.length() < numberCount) {
            return null;
        }
        List<String> result = null;
        int length = input.length();
        //不和法的情况
        if (input == null || length < numberCount) {
            return null;
        }
        if (input == null || input.equals("")) {
            return null;
        }
        //算出最大值是几位数，缩小切割范围
        int digitCount = digitCount(maxValue);
        if (numberCount == 1) {
            int v = Integer.parseInt(input);
            if (v > maxValue) {
                return null;
            }
            result.add(input);
        } else if (numberCount == 2) {
            result = new ArrayList<String>();
            String left = "";
            String right = "";
            for (int i = 0; i < digitCount; i++) {
                int numlength = i + 1;
                if (input.length() >= numlength) {
                    left = input.substring(0, numlength);
                    right = input.substring(i + 1, length);
                    int leftNum = Integer.parseInt(left);
                    if (right.length() > 0) {
                        int rightNum = Integer.parseInt(right);
                        if (leftNum <= maxValue && rightNum <= maxValue) {
                            result.add(left + separator + right);
                        }
                    }
                }
            }
        } else {
            String left = "";
            String right = "";
            result = new ArrayList<String>();
            for (int i = 0; i < digitCount; i++) {
                int numlength = i + 1;
                if (input.length() >= numlength) {
                    left = input.substring(0, numlength);
                    right = input.substring(i + 1, length);
                    List<String> rightSplits = splitNums(right, numberCount - 1, maxValue, separator);
                    if (rightSplits != null && !rightSplits.isEmpty()) {
                        for (String rightSplit : rightSplits) {
                            String tmp = left + separator + rightSplit;
                            result.add(tmp);
                        }
                    }
                }
            }
        }
        return result;
    }


    /**
     * 计算给出数字是多少位数
     *
     * @param number
     * @return
     */
    private static int digitCount(int number) {
        int count = 1;
        int numberTmp = number;
        while (numberTmp > 0) {
            numberTmp /= 10;
            count = numberTmp > 0 ? count + 1 : count;
        }
        return count;
    }
}
