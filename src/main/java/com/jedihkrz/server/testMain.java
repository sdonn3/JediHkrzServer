package com.jedihkrz.server;

import com.jedihkrz.server.services.AccountReader;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by steven.donnelly on 5/8/17.
 */
public class testMain {
    public static void main (String[] args){
        AccountReader accountReader = new AccountReader();
        try {
            List testList = accountReader.getAccountsFromJson();
            System.out.println("Done");
        }
        catch (FileNotFoundException e){
            System.out.println("Could not read file");
        }

    }
}
