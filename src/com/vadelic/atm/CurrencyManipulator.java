package com.vadelic.atm;

import com.vadelic.atm.exception.NotEnoughMoneyException;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by vadelic on 20.12.2015
 */
public class CurrencyManipulator {
    private String currencyCode;
    private HashMap<Integer, Integer> denominations = new HashMap<>();
    private boolean hasMoney = false;

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;

    }

    public int getTotalAmount() {
        int result = 0;
        for (Map.Entry<Integer, Integer> each : denominations.entrySet()) {
            result += (each.getKey() * each.getValue());
        }

        return result;
    }

    public void addAmount(int denomination, int count) {
        denominations.put(denomination, count);
        hasMoney = true;
    }

    public boolean hasMoney() {
        return hasMoney;
    }

    public boolean isAmountAvailable(int expectedAmount) {

        return this.getTotalAmount() >= expectedAmount;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        TreeMap<Integer, Integer> sortedMap = new TreeMap<Integer, Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        sortedMap.putAll(denominations);


        TreeMap<Integer, Integer> cacheMoney = new TreeMap<>();
        int summa = 0;
        for (Map.Entry<Integer, Integer> each : sortedMap.entrySet()) {
            for (int i = 1; i <= each.getValue(); i++) {
                if (summa + each.getKey() <= expectedAmount) {
                    summa += each.getKey();

                    if (!cacheMoney.containsKey(each.getKey()))
                        cacheMoney.put(each.getKey(), 1);
                    else {
                        int temp = cacheMoney.get(each.getKey());
                        cacheMoney.put(each.getKey(), temp + 1);
                    }
                } else {
                    break;
                }
            }
        }

        if (summa == expectedAmount) {
            for (Map.Entry<Integer, Integer> each : cacheMoney.entrySet()) {
                int temp = denominations.get(each.getKey());
                denominations.put(each.getKey(), temp - each.getValue());
            }
        } else
            throw new NotEnoughMoneyException();

//        ConsoleHelper.writeMessage(sortedMap.toString());
//        ConsoleHelper.writeMessage(denominations.toString());
        return cacheMoney;
    }

}
