package com.ai.algorithm.dynamic;

import java.util.HashMap;
import java.util.Map;

/**
 * 最长不重复子串的长度 leetcode 3
 * Given a string, find the length of the longest substring without repeating characters.
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class LengthOfLongestSubstring {
    /**
     * 借助Map记录每个字符最后一次出现的位置
     * @param s
     * @return
     */
    public int solution(String s) {
        Map<Character,Integer> map=new HashMap<>(s.length());
        int ans=0;
        for (int j=0,front=0;j<s.length();j++) {
            if(map.containsKey(s.charAt(j))) {
                //出现重复字符，front调整到此字符上一次出现的位置+1的位置
                front=Math.max(map.get(s.charAt(j))+1,front);
            }
            ans=Math.max(ans,j-front+1);
            //更新字符的位置
            map.put(s.charAt(j),j);
        }
        return ans;
    }

    /**
     * 嘉定字符串中的字符都是ASCII码，可以利用数组实现
     * @param s
     * @return
     */
    public int solution2(String s) {
        int[] index=new int[256];
        int ans=0;
        for (int j=0,front=0;j<s.length();j++) {
            if(index[s.charAt(j)]!=0) {
                front=Math.max(index[s.charAt(j)],front);
            }
            ans=Math.max(ans,j-front+1);
            //为了方便比较，index中记录的是当前字符的下一个位置
            index[s.charAt(j)]=j+1;
        }
        return ans;
    }
}