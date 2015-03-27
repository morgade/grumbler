package org.morgade.grumbler.entity.adapter;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.morgade.grumbler.entity.Account;

/**
 *
 */
public class AccountAdapter {
    public static final String KIND_ACCOUNT = "account";
    public static final String KIND_FOLLOWING = "following";
    public static final String KIND_FOLLOWER = "follower";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_EMAIL = "email";

    public static List<Account> toAccount(Collection<Entity> entities) {
        List<Account> accounts = new ArrayList<>(entities.size());
        for (Entity entity : entities) {
            accounts.add(toAccount(entity));
        }
        return accounts;
    }
    
    public static Account toAccount(Entity entity) {
        if (!entity.getKind().equals(KIND_ACCOUNT)) {
            throw new RuntimeException("Invalid kind");
        }
        Account account = new Account(entity.getKey().getName());
        account.setName((String) entity.getProperty(FIELD_NAME));
        account.setEmail((String) entity.getProperty(FIELD_EMAIL));
        return account;
    }
    
    public static Entity toEntity(Account account) {
        Entity entity = new Entity(KIND_ACCOUNT, account.getId());
        entity.setProperty(FIELD_NAME, account.getName());
        entity.setProperty(FIELD_EMAIL, account.getEmail());
        return entity;
    }
    
    public static Key keyOf(Account account) {
        return KeyFactory.createKey(KIND_ACCOUNT, account.getId());
    }
}
