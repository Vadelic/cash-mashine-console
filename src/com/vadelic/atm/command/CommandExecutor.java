package com.vadelic.atm.command;

import com.vadelic.atm.CashMachine;
import com.vadelic.atm.exception.InterruptOperationException;

import java.util.HashMap;

import static com.vadelic.atm.CashMachine.Operation.*;

/**
 * Created by Komyshenets on 20.12.2015
 */
public class CommandExecutor {
    private static HashMap<CashMachine.Operation, Command> map = new HashMap<>();

    static {
        map.put(LOGIN, new LoginCommand());
        map.put(INFO, new InfoCommand());
        map.put(DEPOSIT, new DepositCommand());
        map.put(WITHDRAW, new WithdrawCommand());
        map.put(EXIT, new ExitCommand());
    }

    public static final void execute(CashMachine.Operation operation) throws InterruptOperationException {
        map.get(operation).execute();
    }
}
