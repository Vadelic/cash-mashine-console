package com.vadelic.atm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vadelic on 20.12.2015
 */
public class CurrencyManipulatorFactory {
    private CurrencyManipulatorFactory() {

    }

    private static HashMap<String, CurrencyManipulator> manipulators = new HashMap<>();

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {

        if (!manipulators.containsKey(currencyCode))
            manipulators.put(currencyCode, new CurrencyManipulator(currencyCode));

        return manipulators.get(currencyCode);
    }

    public static ArrayList<CurrencyManipulator> getAllCurrencyManipulators(){
        ArrayList<CurrencyManipulator> result = new ArrayList<>();
        for (Map.Entry<String,CurrencyManipulator> cm :manipulators.entrySet()){
        result.add(cm.getValue());
    }
        return  result;
    }
}
