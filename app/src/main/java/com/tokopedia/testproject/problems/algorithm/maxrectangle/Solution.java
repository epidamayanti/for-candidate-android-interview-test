package com.tokopedia.testproject.problems.algorithm.maxrectangle;


import java.util.Stack;

public class Solution {
    public static int maxRect(int[][] matrix) {
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
        int top_value;
        int max = 0;
        int area = 0;
        int i = 0;

        while (i < 4)
        {
            if (result.empty() || row[result.peek()] <= row[i])
                result.push(i++);

            else
            {
                top_value = row[result.peek()];
                result.pop();
                area = top_value * i;

                if (!result.empty())
                    area = top_value * (i - result.peek() - 1 );
                max = Math.max(area, max);
            }
        }

        while (!result.empty())
        {
            top_value = row[result.peek()];
            result.pop();
            area = top_value * i;

            if (!result.empty())
                area = top_value * (i - result.peek() - 1 );

            max = Math.max(area, max);
        }
        return max;
    }
}
