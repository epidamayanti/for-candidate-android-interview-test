package com.tokopedia.testproject.problems.algorithm.waterJug;

import static java.lang.Math.min;

public class Solution {

    public static int minimalPourWaterJug(int jug1, int jug2, int target) {
        // TODO, return the smallest number of POUR action to do the water jug problem
        // below is stub, replace with your implementation!
        if (jug1 > jug2){
            jug1 = jug1 + jug2;
            jug2 = jug1 - jug2;
            jug1 = jug1 - jug2;
        }

        if (jug1 > jug2)
            return -1;

        if ((target % gcd(jug2,jug1)) != 0)
            return -1;

        return min(pour(jug2,jug1,target), pour(jug1,jug2,target));
        //return 3;
    }

    static int gcd(int a, int b){
        if(b == 0)
            return a;

        return gcd(b, (a%b));
    }

     static int pour(int fromCap, int toCap, int d){
        int from = fromCap;
        int to = 0;
        int step = 1;
        int steps = 1;

        while(from != d && to != d){
            int temp = min(from, toCap - to);
            to   += temp;
            from -= temp;
            step++;

            if (from == d || to == d)
                break;

            if (from == 0)
            {
                from = fromCap;
                step++;
            }

            if (to == toCap)
            {
                to = 0;
                step++;
            }
            steps++;

        }
        return steps;
    }
}
