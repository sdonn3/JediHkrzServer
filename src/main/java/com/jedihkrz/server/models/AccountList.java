package com.jedihkrz.server.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by steven.donnelly on 5/8/17.
 */
public class AccountList {
    @SerializedName("accounts")
    private List<Account> accountList;

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }
}
