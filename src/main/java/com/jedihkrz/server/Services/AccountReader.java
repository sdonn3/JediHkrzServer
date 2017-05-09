package com.jedihkrz.server.Services;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.jedihkrz.server.models.Account;
import com.jedihkrz.server.models.AccountList;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by steven.donnelly on 5/8/17.
 */
public class AccountReader {
    public List<Account> getAccountsFromJson() throws FileNotFoundException{
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader("src/main/java/com/jedihkrz/server/accountInfo/Accounts.json"));
        AccountList accountList = gson.fromJson(reader, AccountList.class);
        return accountList.getAccountList();
    }
}
