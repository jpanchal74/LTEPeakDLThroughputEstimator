package com.example.jigneshpanchal.ltepeakdlthroughputestimator;

/**
 * Created by v548433 on 08/01/2017.
 */

public class RequiredMinimumDeviceCategory {
    static final int CategoryLookUp[][]=
            {
                    {0,     1000,       0,          0},
                    {1,     10296,      0,          0},
                    {3,     102048,     0,          0},
                    {4,     150752,     0,          0},
                    {6,     301504,     0,          0},
                    {9,     452256,     0,          0},
                    {12,    603008,     0,          0},
                    {16,    1051360,    978960,     0},
                    {18,    1206016,    1174752,    0},
                    {19,    1658272,    1566336,    0},
                    {20,    2019360,    1948064,    0},
                    {17,    25065984,   0,          25065984}
            };

    public  RequiredMinimumDeviceCategory() {
    }

    public int findMinDeviceCategory(int totaltput){
        int j, cat=0;
        for (j = 0; j < CategoryLookUp.length; j++) {
            if (CategoryLookUp[j][1] >= totaltput) {
                cat = CategoryLookUp[j][0];
                break;
            }
        }
        return cat;
    }

    public int DeviceCategoryDLThroughput(int cat){
        int j, tput=0;
        if (cat < 0){
            tput = 0;
        }
        else {
            for (j = 0; j < CategoryLookUp.length; j++) {
                if (CategoryLookUp[j][0] == cat) {
                    tput = CategoryLookUp[j][1];
                    break;
                }
            }
        }
        return tput;
    }
}
