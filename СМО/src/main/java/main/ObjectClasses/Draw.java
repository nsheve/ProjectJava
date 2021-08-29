package main.ObjectClasses;

import java.text.DecimalFormat;

public class Draw {
    public static StringBuilder draw(int count, int space){
        StringBuilder forTableCount = new StringBuilder("|");
        int num = count;
        int length = (num == 0) ? 1 : 0;
        while(num != 0){
            length++;
            num /= 10;
        }
        int numberS = space - length;
        int left = 0;
        int right = 0;
        if(numberS % 2 != 0){
            left = numberS / 2 + 1;
        } else {
            left = numberS/2;
        }
        right = numberS - left;

        forTableCount.append(" ".repeat(Math.max(0, left)));
        forTableCount.append(count);
        forTableCount.append(" ".repeat(Math.max(0, right)));

        return  forTableCount;
    }

    public static StringBuilder draw(double time, int space){
        StringBuilder forTable = new StringBuilder("|");
        DecimalFormat df = new DecimalFormat("###.###");
        String[] dfS = df.format(time).split(",");
        String numberGener = "";
        if(dfS.length == 2) {
            numberGener = dfS[0] + dfS[1];
        }
        else
            numberGener = dfS[0];
        int num = numberGener.length();
        int numberS = space - num;
        int left = 0;
        int right = 0;
        if(numberS % 2 != 0){
            left = numberS / 2 + 1;
        } else {
            left = numberS/2;
        }
        right = numberS - left;

        forTable.append(" ".repeat(Math.max(0, left)));
        if(dfS.length == 2) {
            forTable.append(dfS[0] + "," + dfS[1]);
        }
        else
            forTable.append(dfS[0]);
        forTable.append(" ".repeat(Math.max(0, right)));
        return forTable;
    }

    public static StringBuilder draw(String action, int space){
        StringBuilder forTableAction = new StringBuilder("|");
        int length = action.length();
        int numberS = space - length; //22
        int left = 0;
        int right = 0;
        if(numberS % 2 != 0){
            left = numberS / 2 + 1;
        } else {
            left = numberS/2;
        }
        right = numberS - left;

        forTableAction.append(" ".repeat(Math.max(0, left)));
        forTableAction.append(action);
        forTableAction.append(" ".repeat(Math.max(0, right)));

        return  forTableAction;
    }
}
