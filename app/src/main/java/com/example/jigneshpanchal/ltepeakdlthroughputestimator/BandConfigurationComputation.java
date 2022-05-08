package com.example.jigneshpanchal.ltepeakdlthroughputestimator;

/**
 * Created by v548433 on 07/31/2017.
 */

public class BandConfigurationComputation {

    static final int maxB2Bandwidth=20, maxB5Bandwidth=10, maxB13Bandwidth=10, maxB46Bandwidth=20, maxB48Bandwidth=20, maxB66Bandwidth=20;
    static final int ThroughputLookUp[][]=
            {
                    {2645, 36696, 0, 36696},
                    {4645, 73712, 0, 73712},
                    {22565, 48936, 0, 48936},
                    {42565, 97896, 0, 97896},
                    {210245, 0, 0, 61170},
                    {410245, 0, 0, 122370},
                    {26410, 73712, 0, 73712},
                    {46410, 146856, 0, 146856},
                    {225610, 97896, 0, 97896},
                    {425610, 195816, 0, 195816},
                    {2102410, 0, 0, 122370},
                    {4102410, 0, 0, 244770},
                    {26415, 110136, 0, 110136},
                    {46415, 220296, 0, 220296},
                    {225615, 149776, 0, 149776},
                    {425615, 299856, 0, 299856},
                    {2102415, 0, 0, 187220},
                    {4102415, 0, 0, 374820},
                    {26420, 149776, 0, 149776},
                    {46420, 299856, 0, 299856},
                    {225620, 195816, 0, 201936},
                    {425620, 391656, 0, 403008},
                    {2102420, 0, 0, 244770},
                    {4102420, 0, 0, 489570}
            };


    public  BandConfigurationComputation () {
     }

    public int MaxBandwidth(int band) {
        int maxBandwidth=0;
        switch (band){
            case 2:
                maxBandwidth=maxB2Bandwidth;
                break;
            case 5:
                maxBandwidth=maxB5Bandwidth;
                break;
            case 13:
                maxBandwidth=maxB13Bandwidth;
                break;
            case 46:
                maxBandwidth=maxB46Bandwidth;
                break;
            case 48:
                maxBandwidth=maxB48Bandwidth;
                break;
            case 66:
                maxBandwidth=maxB66Bandwidth;
                break;
        }
        return maxBandwidth;
    }

     public int NumberOfComponentCarriers(int band, int bandwidth) {
         int maxBandwidth=MaxBandwidth(band);
         return (int)(Math.ceil((double)bandwidth/(double)maxBandwidth));
     }

    public int NumberOfMIMOLayers(int sectortx, int devicerx) {
        return Math.min(sectortx,devicerx);
    }

    public int DLThroughput(int band, int nl, int mod, int cc, int bandwidth, int dlratio) {
        int lookupkey=0, col = 0, j=0, found = 0, ncc=0, tput=0;
        String str = "";

        //if(band == 46) col=1;
        //if(band == 46) col=2;
        //else col=1;

        // R14 Alternate TBS based throughput is on Col 3
        col = 3;

        int AllocatedBW = 0;
        int bw[] = new int[cc];

        if (cc >= 2) {
            //From 0 to CC-2
            for (ncc = 0; ncc < (cc - 1); ncc++) {
                bw[ncc] = MaxBandwidth(band);
                AllocatedBW = AllocatedBW + bw[ncc];
                str = "" + nl + mod + bw[ncc] + "";
                lookupkey = Integer.valueOf(str);
                for (j = 0; j < ThroughputLookUp.length; j++) {
                    if (ThroughputLookUp[j][0] == lookupkey) {
                        found = 1;
                        tput += ThroughputLookUp[j][col];
                        break;
                    }
                }
            }
        }

        //Last component carrier
        bw[ncc]=bandwidth-AllocatedBW;
        str = "" + nl + mod + bw[ncc] + "";
        lookupkey = Integer.valueOf(str);
        for (j = 0; j < ThroughputLookUp.length; j++) {
            if (ThroughputLookUp[j][0] == lookupkey) {
                found = 1;
                tput += ThroughputLookUp[j][col];
                break;
            }
        }


        return (tput*dlratio/100);
    }

    public String ComponentLetter(int band, int cc, int bandwidth, int cont) {
        String str="";
        if (cont == 1) {
            switch (cc) {
                case 1:
                    str = "A";
                    break;
                case 2:
                    if (MaxBandwidth(band) <= 10) str = "B";
                    else str = "C";
                    break;
                case 3:
                    str = "D";
                    break;
                case 4:
                    str = "E";
                    break;
                case 5:
                    str = "F";
                    break;
            }
            return (""+band+str);
        }else{
            switch (cc) {
                case 1:
                    str = band+"A";
                    break;
                case 2:
                    str = band+"A-"+band+"A";
                    break;
                case 3:
                    str = band+"A-"+band+"A-"+band+"A";
                    break;
                case 4:
                    str = band+"A-"+band+"A-"+band+"A-"+band+"A";
                    break;
                case 5:
                    str = band+"A-"+band+"A-"+band+"A-"+band+"A-"+band+"A";
                    break;
            }
            return (str);
        }
    }
}


/*
        If NumCC = 1 Then
            Range("I20").Value = Val(CStr(MIMOLayers) & CStr(QAM) & ContiguousAggregatedBW)
            Range("J20").Value = CStr(Band) & "A"
            Range("K20").Value = Application.WorksheetFunction.VLookup(Range("I20").Value, Sheet6.Range("G2:I33"), 3, False)
        ElseIf NumCC > 1 Then
            AllocatedBW = 0
            Range("K20").Value = 0

            For i = 0 To (NumCC - 2)
            BWCC(i) = MaxCarrierBW
            AllocatedBW = AllocatedBW + MaxCarrierBW
            Range("I20").Value = Val(CStr(MIMOLayers) & CStr(QAM) & CStr(BWCC(i)))
            Range("K20").Value = Range("K20").Value + Application.WorksheetFunction.VLookup(Range("I20").Value, Sheet6.Range("G2:I33"), 3, False)
            Next i

            BWCC(i) = ContiguousAggregatedBW - AllocatedBW
            Range("I20").Value = Val(CStr(MIMOLayers) & CStr(QAM) & CStr(BWCC(i)))
            Range("K20").Value = Range("K20").Value + Application.WorksheetFunction.VLookup(Range("I20").Value, Sheet6.Range("G2:I33"), 3, False)

            If NumCC = 2 Then
            If MaxCarrierBW <= 10 Then
            LetterCC = "B"
            Else
            LetterCC = "C"
            End If
            ElseIf NumCC = 3 Then
            LetterCC = "D"
            ElseIf NumCC = 4 Then
            LetterCC = "E"
            ElseIf NumCC = 5 Then
            LetterCC = "F"
            ElseIf NumCC = 8 Then
            LetterCC = "i"
        End If

        Range("J20").Value = CStr(Band) & LetterCC

*/