package com.tokopedia.testproject.problems.algorithm.continousarea;

import java.util.Stack;

/**
 * Created by hendry on 18/01/19.
 */
public class Solution {
    public static int maxContinuousArea(int[][] matrix) {
       // TODO, return the largest continuous area containing the same integer, given the 2D array with integers
        // below is stub
        int result = maxHist(matrix[0]);

        for (int i = 1; i < 4; i++)
        {

            for (int j = 0; j < 4; j++)
                if (matrix[i][j] == 1) matrix[i][j] += matrix[i - 1][j];

            result = Math.max(result, maxHist(matrix[i]));
        }

        return result;
    }

    static int maxHist(int row[])
    {
        Stack<Integer> result = new Stack<Integer>();
        int top_val;
        int max_area = 0;
        int area = 0;
        int i = 0;


        while (i < 4)
        {
            if (result.empty() || row[result.peek()] <= row[i])
                result.push(i++);

            else
            {
                top_val = row[result.peek()];
                result.pop();
                area = top_val * i;

                if (!result.empty())
                    area = top_val * (i - result.peek() - 1 );
                max_area = Math.max(area, max_area);
            }
        }

        while (!result.empty())
        {
            top_val = row[result.peek()];
            result.pop();
            area = top_val * i;

            if (!result.empty())
                area = top_val * (i - result.peek() - 1 );

            max_area = Math.max(area, max_area);
        }
        return max_area;
    }
}
