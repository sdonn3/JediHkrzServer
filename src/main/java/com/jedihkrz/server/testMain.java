package com.jedihkrz.server;

import com.jedihkrz.server.Services.AccountReader;

import java.io.FileNotFoundException;

/**
 * Created by steven.donnelly on 5/8/17.
 */
public class testMain {
    public static void main (String[] args){
        AccountReader accountReader = new AccountReader();
        try {
            System.out.println(accountReader.getAccountsFromJson());
        }
        catch (FileNotFoundException e){
            System.out.println("Could not read file");
        }

    }
}
