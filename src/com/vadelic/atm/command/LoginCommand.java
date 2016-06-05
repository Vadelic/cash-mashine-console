package com.vadelic.atm.command;

import com.vadelic.atm.CashMachine;
import com.vadelic.atm.ConsoleHelper;
import com.vadelic.atm.exception.InterruptOperationException;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Created by ����� on 27.12.2015.
 */
public class LoginCommand implements Command {
    private static ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login_en");


    @Override
    public void execute() throws InterruptOperationException {
        String login;
        String pin;
        ConsoleHelper.writeMessage(res.getString("before"));
        do {
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            do {
                login = ConsoleHelper.readString();
                if (login.length() != 12) ConsoleHelper.writeMessage(res.getString("try.again.login"));
            } while (login.length() != 12);

            do {
                pin = ConsoleHelper.readString();
                if (pin.length() != 4) ConsoleHelper.writeMessage(res.getString("try.again.pin"));
            } while (pin.length() != 4);

            try {
                if (validCreditCards.getString(login).equals(pin)) break;
                else {
                    ConsoleHelper.writeMessage(res.getString("invalid.pin")+"\n");
                }
            } catch (MissingResourceException e) {
                ConsoleHelper.writeMessage(res.getString("invalid.login")+"\n");
            }
        } while (true);

        ConsoleHelper.writeMessage(String.format(res.getString("success.format"),login));
    }
}
