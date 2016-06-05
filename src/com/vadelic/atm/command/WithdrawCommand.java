package com.vadelic.atm.command;

import com.vadelic.atm.CashMachine;
import com.vadelic.atm.ConsoleHelper;
import com.vadelic.atm.CurrencyManipulator;
import com.vadelic.atm.CurrencyManipulatorFactory;
import com.vadelic.atm.exception.InterruptOperationException;
import com.vadelic.atm.exception.NotEnoughMoneyException;

import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.TreeMap;

/**
 * Created by ����� on 20.12.2015.
 */
public class WithdrawCommand implements Command {
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw_en");


    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));



        //Select code
        String code = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);

        //Select amount
        ConsoleHelper.writeMessage("enter the required amount of money?");
        int amount;
        do {
            try {
                amount = Integer.parseInt(ConsoleHelper.readString());
                if (manipulator.isAmountAvailable(amount)) break;
                else ConsoleHelper.writeMessage(res.getString("not.enough.money"));

            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage(res.getString("invalid.data"));
            }
        } while (true);

        //Give money
        try {
            TreeMap<Integer,Integer> resultMoney = new TreeMap<Integer, Integer>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2-o1;
                }
            });
            resultMoney.putAll(manipulator.withdrawAmount(amount));

            ConsoleHelper.writeMessage(resultMoney.toString());
        } catch (NotEnoughMoneyException e) {
            ConsoleHelper.writeMessage(res.getString("no.bills"));
        } 


    }
}