package com.jedihkrz.server.services;

import com.jedihkrz.server.models.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by steven.donnelly on 5/17/17.
 */
public class UserMapper {

    static private final Logger log = LoggerFactory.getLogger(UserMapper.class);

    private static UserMapper userMapper;
    private static Map<String, Account> userMap = new HashMap<>();

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
                System.out.println("I have " + accountList.size() + " accounts");
                for (int i = 0; i < accountList.size(); i++){
                    System.out.println("looking at account " + accountList.get(i).getName());
                    if (accountList.get(i).getAccountHolder().equals(userName)){
                        Account currentAccount = accountList.get(i);
                        currentAccount.setFaceId(faceId);
                        userMap.put(userName, currentAccount);
                        System.out.println("placed " + userName + " - " + currentAccount + " to map");
                    }
                }
            } catch (Exception e){
                log.warn("User registration failed. " + e.getMessage());
            }
        }
    }

    public void clear(){
        userMap.clear();
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
