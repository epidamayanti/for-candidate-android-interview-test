package com.tokopedia.testproject.problems.androidView.waterJugSimulation;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.min;

public class Solution {

    public static List<WaterJugAction> simulateWaterJug(int jug1, int jug2, int target) {
        // TODO, simulate the smallest number of action to do the water jug problem
        // below is stub, replace with your implementation!
        int first = 0, second =0;

        first = pour(jug2,jug1,target);
        second = pour(jug1,jug2,target);

        if(first < second){
            return pour_simulate_second(jug2,jug1,target);
        }
        else{
            return pour_simulate_first(jug1,jug2,target);
        }
    }

    static int pour(int fromCap, int toCap, int d){
        int from = fromCap;
        int to = 0;
        int step = 1;

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
        }
        return step;
    }

    static List<WaterJugAction> pour_simulate_first(int jug1, int jug2, int target){

        List<WaterJugAction> list = new ArrayList<>();
        int from = jug1;
        int to = 0;
        int step = 1;

        list.add(new WaterJugAction(WaterJugActionEnum.FILL, 1));

        while(from != target && to != target){
            int temp = min(from, jug2 - to);

            // Pour "temp" litres from "from" to "to"
            to   += temp;
            from -= temp;
            step++;
            list.add(new WaterJugAction(WaterJugActionEnum.POUR, 2));

            if (from == target || to == target)
                break;

            // If first jug becomes empty, fill it
            if (from == 0)
            {
                from = jug1;
                step++;
                list.add(new WaterJugAction(WaterJugActionEnum.FILL, 1));
            }

            // If second jug becomes full, empty it
            if (to == jug2)
            {
                to = 0;
                step++;
                list.add(new WaterJugAction(WaterJugActionEnum.EMPTY, 2));

            }
        }
        return list;
    }

    static List<WaterJugAction> pour_simulate_second(int jug1, int jug2, int target){

        List<WaterJugAction> list = new ArrayList<>();
        int from = jug1;
        int to = 0;
        int step = 1;

        list.add(new WaterJugAction(WaterJugActionEnum.FILL, 2));

        while(from != target && to != target){
            int temp = min(from, jug2 - to);

            // Pour "temp" litres from "from" to "to"
            to   += temp;
            from -= temp;
            step++;
            list.add(new WaterJugAction(WaterJugActionEnum.POUR, 1));

            if (from == target || to == target)
                break;

            // If first jug becomes empty, fill it
            if (from == 0)
            {
                from = jug1;
                step++;
                list.add(new WaterJugAction(WaterJugActionEnum.FILL, 2));
            }

            // If second jug becomes full, empty it
            if (to == jug2)
            {
                to = 0;
                step++;
                list.add(new WaterJugAction(WaterJugActionEnum.EMPTY, 1));

            }
        }
        return list;
    }

}
