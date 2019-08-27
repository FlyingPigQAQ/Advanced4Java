package com.pigcanfly.advance;



/**
 * @author Tobby Quinn
 * @date 2019/08/25
 */
public class GenerateShortUrl {
    private static final char[] DICTS = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
            'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
            'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private static final int DICTS_LENGTH = DICTS.length;
    private static final long MAX = 3521614606207L;

    public static String encode(Long num) {
        if(num>MAX){
            throw new RuntimeException("overhead the limits");
        }
        if(num==0){
            return String.valueOf(DICTS[0]);
        }
        int tmp;
        StringBuilder sb = new StringBuilder();
        while (num != 0L) {
            tmp = (int) (num % DICTS_LENGTH);
            sb.append(DICTS[tmp]);
            num /= DICTS_LENGTH;
        }
        return sb.toString();
    }

    public static Long decode(String enc) {
        if (enc.equals("")) {
            throw new IllegalArgumentException("参数不能为空或空字符串");
        }
        long sum = 0;
        for (int i = 0; i < enc.length(); i++) {
            int index = 0;
            char c = enc.charAt(i);
            if ('a' <= c && c <= 'z') {
                index = c - 'a';
            } else if ('A' <= c && c <= 'Z') {
                index = 26 + (c - 'A');
            } else {
                index = 52 + (c - '0');
            }
            if (i == 0) {
                sum += index;
            } else {
                sum += index *Math.pow(62, i);
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        //500w耗时平均1.2s
        for (long i = 1; i < 5000000; i++) {
            if(decode(encode(i))!=i){
                System.out.println(i);
                break;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println((endTime-startTime)+"ms");
//        System.out.println(encode(3521614606207L));
//        System.out.println(decode("9999999"));
//        System.out.println(encode(62L));
//        System.out.println(encode(183L));
//
//        System.out.println(decode("ab"));
//        System.out.println(decode("7c"));
//
////        System.out.println(decode("a"));
////        System.out.println(decode("ab"));
//        System.out.println(decode("ab"));
//
//
//        System.out.println("------------");
//        System.out.println(Integer.MAX_VALUE);
//        System.out.println((int) Math.pow(2, 31));
//
//        System.out.println(Integer.MIN_VALUE);
//        System.out.println((int) Math.pow(2, 31));//-0

    }
}
