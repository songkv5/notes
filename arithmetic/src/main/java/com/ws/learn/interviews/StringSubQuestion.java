package com.ws.learn.interviews;

public class StringSubQuestion {
    public static void main(String[] args) {
        String source = "asdadsd";

    }

    public static int maxSubStrLength(String source) {
        if (source == null) {
            return 0;
        }
        char[] chars = source.toCharArray();
        int endIndex = source.length();
        char[] startChars = new char[36];
        int startCharIndex = 0;
        char[][] subchars = new char[36][36];
        char[] tmp = new char[36];
        int subarrindex = 0;

        for (int i = 0; i < endIndex; i ++) {
            for (int j = i; j < endIndex; j ++) {
                if (contain(tmp, chars[j])) {
                    System.arraycopy(tmp, 0, subchars[subarrindex], 0, j - i);
                    subchars[subarrindex] = tmp;
                }
                tmp[startCharIndex] = chars[j];

            }


        }
        return endIndex;
    }

    public static boolean contain(char[] chars, char c) {
        for (char tmp : chars) {
            if (tmp == c) {
                return true;
            }
        }
        return false;
    }
}
