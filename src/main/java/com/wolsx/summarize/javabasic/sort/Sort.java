package com.wolsx.summarize.javabasic.sort;

import org.junit.Test;

/**
 * Created by hy on 7/14/16.
 */
public class Sort {

    /**
     * 选择排序
     */
    @Test
    public void bubbleSort() {

        int [] array = {4,1,5,6,778,8,889,121,1,2};
        int length = array.length;

        for (int i = 0; i <= length - 1; i++)
        {
            for (int j = length - 1; j > i; j--)
            {
                if (array[j] < array[j - 1])
                {
                    int temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
        }
    }


}

