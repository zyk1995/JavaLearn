package com.zyk.test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Solution {

    public int numUniqueEmails(String[] emails) {
        Integer result = 0;
        Map<String, Integer> map = new HashMap<>();
        for (String email : emails) {
            int posMarkPlus = email.indexOf('+');
            int posMarkDot = email.indexOf('.');
            int posMarkAt = email.indexOf('@');

            String tureEmail = email.substring(0, posMarkPlus).replace(".", "")
                    .concat(email.substring(posMarkAt - 1, email.length()));

            map.computeIfAbsent(tureEmail, k -> map.getOrDefault(tureEmail, 0) + 1 );
        }

        for (Map.Entry<String, Integer> entry: map.entrySet()) {
            result+=entry.getValue();
        }

        return result;
    }

    public static void main(String[] args) {

    }
}
