package com.jedihkrz.server.services;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.jedihkrz.server.models.Account;
import com.jedihkrz.server.models.AccountList;

import javax.servlet.ServletContext;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

/**
 * Created by steven.donnelly on 5/8/17.
 */
public class AccountReader {
    private static final String TEST_PATH = "src/main/java/com/jedihkrz/server/accountInfo/Accounts.json";

    public List<Account> getAccountsFromJson() throws FileNotFoundException{
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader("/tmp/accounts/Accounts.json"));
        AccountList accountList = gson.fromJson(reader, AccountList.class);
        return accountList.getAccountList();
    }
}
