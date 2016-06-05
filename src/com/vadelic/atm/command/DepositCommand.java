package com.vadelic.atm.command;

import com.vadelic.atm.CashMachine;
import com.vadelic.atm.ConsoleHelper;
import com.vadelic.atm.CurrencyManipulator;
import com.vadelic.atm.CurrencyManipulatorFactory;
import com.vadelic.atm.exception.InterruptOperationException;

import java.util.ResourceBundle;

/**
 * Created by ����� on 20.12.2015.
 */
public class DepositCommand implements Command {

    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "deposit_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));

        String code = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
        String[] denomination = ConsoleHelper.getValidTwoDigits(code);
        manipulator.addAmount(Integer.parseInt(denomination[0]), Integer.parseInt(denomination[1]));
        
    }
}
