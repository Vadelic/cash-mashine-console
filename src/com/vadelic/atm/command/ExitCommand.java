package com.vadelic.atm.command;

import com.vadelic.atm.CashMachine;
import com.vadelic.atm.ConsoleHelper;
import com.vadelic.atm.exception.InterruptOperationException;

import java.util.ResourceBundle;

/**
 * Created by ����� on 20.12.2015.
 */
public class ExitCommand implements Command {
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "exit_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("exit.question.y.n"));
        if (ConsoleHelper.readString().equalsIgnoreCase(res.getString("yes"))) {
            ConsoleHelper.writeMessage(res.getString("thank.message"));
            throw new InterruptOperationException();
        } else
            CashMachine.main(null);


    }
}