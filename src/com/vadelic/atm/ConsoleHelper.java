package com.vadelic.atm;

import com.vadelic.atm.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

/**
 * Created by ����� on 20.12.2015.
 */
public class ConsoleHelper {
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common_en");

    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        String result = null;
        try {
            result = bufferedReader.readLine();
            if (result.equalsIgnoreCase(res.getString("operation.EXIT"))) throw new InterruptOperationException();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        writeMessage(res.getString("choose.currency.code"));

        String result;
        do {
            result = readString();
            if (result.length() != 3) System.out.println(res.getString("invalid.code"));
        } while (result.length() != 3);

        return result.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
        String[] result;

        do {
            try {
                result = readString().split(" ");
                int par = Integer.parseInt(result[0]);
                int value = Integer.parseInt(result[1]);
                break;
            } catch (NumberFormatException e) {
                writeMessage(res.getString("invalid.data"));
            }
        } while (true);
        return result;
    }

    public static CashMachine.Operation askOperation() throws InterruptOperationException {
        writeMessage(res.getString("choose.operation"));
        writeMessage("1 " + res.getString("operation.INFO") +
                "\n2 " + res.getString("operation.DEPOSIT") +
                "\n3 " + res.getString("operation.WITHDRAW") +
                "\n4 " + res.getString("operation.EXIT"));
        CashMachine.Operation operation;
        do {
            try {
                int i = Integer.parseInt(readString());
                if (i == 0) throw new IllegalArgumentException();

                operation = CashMachine.Operation.getAllowableOperationByOrdinal(i);
                break;
            } catch (NumberFormatException e) {
                writeMessage(res.getString("value.NaN"));
            } catch (IllegalArgumentException e) {
                writeMessage(res.getString("invalid.operation"));
            }
        } while (true);
        return operation;
    }
}
