package com.rafatars.classes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Util {

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
    
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public static double roundBig(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static double roundUsingTruncate(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        String truncated = String.format("%." + places + "f", value);
        return Double.parseDouble(truncated);
    }

    public static double roundUsingString(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        String stringValue = Double.toString(value);
        int dotIndex = stringValue.indexOf('.');
        int endIndex = dotIndex + places + 1;

        if (endIndex < stringValue.length()) {
            return Double.parseDouble(stringValue.substring(0, endIndex));
        } else {
            return value;
        }
    }

    public static ArrayList<Double[]> roundBigArray(ArrayList<Double[]> values, int places) {
        if (places < 0) throw new IllegalArgumentException();

        ArrayList<Double[]> roundedValues = new ArrayList<>();
        
        for (Double[] value : values) {
            value[0] = roundUsingString(value[0], places);
            value[1] = roundUsingString(value[1], places);

            roundedValues.add(value);
        }
        
        return roundedValues;
    }
    
}