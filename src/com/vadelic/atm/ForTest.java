package com.vadelic.atm;

import com.vadelic.atm.command.CommandExecutor;
import com.vadelic.atm.exception.InterruptOperationException;
import com.vadelic.atm.exception.NotEnoughMoneyException;

import java.util.Comparator;
import java.util.TreeMap;

/**
 * Created by ����� on 26.12.2015.
 */
public class ForTest {
    public static void tempoTest(){
        CurrencyManipulator temp = CurrencyManipulatorFactory.getManipulatorByCurrencyCode("USD");
        temp.addAmount(100, 1);
        temp.addAmount(500, 2);
        temp.addAmount(50, 12);
        temp.addAmount(200, 3);


        try {

            CommandExecutor.execute(CashMachine.Operation.INFO);
            TreeMap<Integer,Integer> resultMoney = new TreeMap<Integer, Integer>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2-o1;
                }
            });
            resultMoney.putAll(temp.withdrawAmount(600));

            ConsoleHelper.writeMessage(resultMoney.toString());

        } catch (InterruptOperationException e) {

        } catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage("No matching bills");
        }

    }
}
