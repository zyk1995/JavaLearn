package com.zyk.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Solution {
    private double radius;
    private double x_center;
    private double y_center;
    private Random random;

    public Solution(double radius, double x_center, double y_center) {
        this.radius = radius;
        this.x_center = x_center;
        this.y_center = y_center;
        random = new Random();
    }

    public double[] randPoint() {
        double x = random.nextDouble() * (2 * radius) - radius;
        double y = random.nextDouble() * (2 * radius) - radius;
        while (true)  {
            if (x * x + y * y <= radius * radius)  {
                return new double[] {x_center + x, y_center+y};
            }
        }
    }

    public int heightChecker(int[] heights) {
        int[] expects = Arrays.copyOf(heights, heights.length);
        Arrays.sort(expects);
        int result = 0;

        for (int i = 0; i < heights.length; i++) {
            if (heights[i] != expects[i] ) {
                result ++;
            }
        }

        return result;
    }


    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (arr1, arr2) -> {
            if (arr1[0] == arr2[0])  {
                return arr2[1] - arr1[1];
            } else {
                return arr1[0] - arr2[0];
            }
        });

        int[] secDim = new int[envelopes.length];
        for (int i = 0; i < envelopes.length; i++) {
            secDim[i] = envelopes[i][1];
        }

        Arrays.copyOf(envelopes, 10);

        return 0;
    }

    public static void main(String[] args) {

    }
}
