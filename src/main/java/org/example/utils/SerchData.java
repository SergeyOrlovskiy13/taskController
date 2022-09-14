package org.example.utils;

import lombok.Data;

@Data
public class SerchData {
    private int date1;
    private int data2;


    public boolean chekData(int date1, int data2){
        if (date1>data2)
            return false;
        return true;
    }
}
