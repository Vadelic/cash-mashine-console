package com.vadelic.atm;

import com.vadelic.atm.command.CommandExecutor;
import com.vadelic.atm.exception.InterruptOperationException;

import java.util.Locale;


/**
 * Created by Komyshenets on 20.12.2015
 */
public class CashMachine {
    public enum Operation {
        LOGIN,
        INFO,
        DEPOSIT,
        WITHDRAW,
        EXIT;

        public static Operation getAllowableOperationByOrdinal(Integer i) {
            switch (i) {
                case 0:
                    return LOGIN;
                case 1:
                    return INFO;
                case 2:
                    return DEPOSIT;
                case 3:
                    return WITHDRAW;
                case 4:
                    return EXIT;
                default:
                    throw new IllegalArgumentException(i.toString());
            }
        }
    }

    static {
        ForTest.tempoTest();

    }
    public static final String RESOURCE_PATH = "com.vadelic.atm.resources.";

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);

        try {
            CommandExecutor.execute(Operation.LOGIN);

            Operation choose;
            do {
                choose = ConsoleHelper.askOperation();
                CommandExecutor.execute(choose);

            } while (choose != Operation.EXIT);

        } catch (InterruptOperationException e) {

            ConsoleHelper.writeMessage("Machine finished");
        }


    }
}
