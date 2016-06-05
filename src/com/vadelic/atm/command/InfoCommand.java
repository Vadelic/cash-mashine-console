package com.vadelic.atm.command;

import com.vadelic.atm.CashMachine;
import com.vadelic.atm.ConsoleHelper;
import com.vadelic.atm.CurrencyManipulator;
import com.vadelic.atm.CurrencyManipulatorFactory;

import java.util.ResourceBundle;

import static com.vadelic.atm.ConsoleHelper.writeMessage;


public class InfoCommand implements Command {
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "info_en");


    @Override
    public void execute() {
        ConsoleHelper.writeMessage(res.getString("before"));
        boolean money = false;
        writeMessage("Total:");
        for (CurrencyManipulator each : CurrencyManipulatorFactory.getAllCurrencyManipulators()) {
            writeMessage(String.format("%s: %d", each.getCurrencyCode(), each.getTotalAmount()));
            money = money || each.hasMoney();
        }
        if (!money) writeMessage(res.getString("no.money"));

    }
}
