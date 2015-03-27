package org.morgade.grumbler.data;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.morgade.grumbler.entity.Account;
import org.morgade.grumbler.entity.adapter.AccountAdapter;

/**
 *
 */
public class AccountDao extends DAO {
    
    public boolean exists(Account account) {
        return exists(AccountAdapter.KIND_ACCOUNT, account.getId());
    }
    
    public List<Account> getAll(Account base) {
        List<Entity> followingEntities = datastore.prepare(new Query(AccountAdapter.KIND_FOLLOWING, AccountAdapter.keyOf(base)).setKeysOnly()).asList(FetchOptions.Builder.withDefaults());
        Set<String> followingEntitiesIds = new HashSet();
        for (Entity followingEntity : followingEntities) {
            followingEntitiesIds.add(followingEntity.getKey().getName());
        }
        
        List<Entity> entities = datastore.prepare(new Query(AccountAdapter.KIND_ACCOUNT)).asList(FetchOptions.Builder.withDefaults());
        List<Account> l = new ArrayList<>(entities.size());
        for (Entity entity : entities) {
            Account account = AccountAdapter.toAccount(entity);
            account.setFollows(followingEntitiesIds.contains(account.getId()));
            l.add(account);
        }
        return l;
    }
    
    public Account get(String id) {
        Key k = KeyFactory.createKey(AccountAdapter.KIND_ACCOUNT, id);
        try {
            Entity entity = datastore.get(k);
            return AccountAdapter.toAccount(entity);
        } catch (EntityNotFoundException ex) {
            return null;
        }
    }
    
    public void create(Account account) {
        if (!exists(AccountAdapter.KIND_ACCOUNT, account.getId())) {
            datastore.put(AccountAdapter.toEntity(account));
        } else {
            throw new RuntimeException("Já existe");
        }
    }
    
}
