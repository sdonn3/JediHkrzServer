package com.jedihkrz.server.services;

import com.jedihkrz.server.models.Account;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.Pack200;

/**
 * Created by steven.donnelly on 5/17/17.
 */
public class UserMapper {
    private static UserMapper userMapper;
    private Map<String, Account> userMap = new HashMap<>();

    private UserMapper(){}

    public static UserMapper get(){
        if (userMapper == null){
            userMapper = new UserMapper();
        }
        return userMapper;
    }

    public void addUser(String userName, String faceId){
        if (!userMap.containsKey(userName)){
            AccountReader accountReader = new AccountReader();
            try{
                List<Account> accountList = accountReader.getAccountsFromJson();
                for (int i = 0; i < accountList.size(); i++){
                    if (accountList.get(i).getAccountHolder().equals(userName)){
                        Account currentAccount = accountList.get(i);
                        currentAccount.setFaceId(faceId);
                        userMap.put(userName, currentAccount);
                    }
                }
            } catch (Exception e){

            }
        }
    }

    public Account getUserAccount(String userName){
        if (userMap.containsKey(userName)){
            return userMap.get(userName);
        }
        else {
            return null;
        }
    }
}
