package com.vadelic.atm.command;

import com.vadelic.atm.exception.InterruptOperationException;

/**
 * Created by ����� on 20.12.2015.
 */
public interface Command {
    void execute() throws InterruptOperationException;
}
