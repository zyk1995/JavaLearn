package com.zyk;

public class Interiew {

    public static void main(String[] args) {
        int n  = -5; // 011 //100  101
        //00000000000000000000000000000101
        //11111111111111111111111111111101
        //00000000000000000000000000000011
        boolean flag = true;
        if (n < 0) {
            flag = false;
            n = -n;
        }
        int cnt = 0;
        while(n > 0) {
            int mod = n % 2;

            if (mod == 1) {
                cnt++;
            }
            n = n / 2;
        }
        if (!flag) {
            cnt = 31 - cnt ;
        }
        System.out.println(cnt);
    }
}
