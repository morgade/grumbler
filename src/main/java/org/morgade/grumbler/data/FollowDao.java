package org.morgade.grumbler.data;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import org.morgade.grumbler.entity.Account;
import org.morgade.grumbler.entity.adapter.AccountAdapter;

/**
 *
 */
public class FollowDao extends DAO {
    
    public List<Account> getFollowings(Account account) {
        Collection<Entity> entities = _list(AccountAdapter.KIND_FOLLOWING, account);
        return AccountAdapter.toAccount(entities);
    }
    
    public List<Account> getFollowers(Account account) {
        Collection<Entity> entities = _list(AccountAdapter.KIND_FOLLOWER, account);
        return AccountAdapter.toAccount(entities);
    }
    
    protected Collection<Entity> _list(String kind, Account parent) {
        Query query = new Query(kind, AccountAdapter.keyOf(parent)).setKeysOnly();
        List<Key> keys = new LinkedList<>();
        for (Entity entity : datastore.prepare(query).asIterable()) {
            keys.add(KeyFactory.createKey(AccountAdapter.KIND_ACCOUNT, entity.getKey().getName()));
        }
        return datastore.get(keys).values();
    }
    
    public void follow(Account follower, Account following) {
        if (follower.getId().equals(following.getId())) {
            throw new RuntimeException("Can't self follow");
        }
        Entity followingEntity = new Entity(AccountAdapter.KIND_FOLLOWING, following.getId(), AccountAdapter.keyOf(follower));
        Entity followerEntity = new Entity(AccountAdapter.KIND_FOLLOWER, follower.getId(), AccountAdapter.keyOf(following));
        datastore.put(Arrays.asList(followerEntity, followingEntity));
    }
    
    public void unfollow(Account follower, Account following) {
        Key followerKey = KeyFactory.createKey(AccountAdapter.KIND_ACCOUNT, follower.getId())
                                    .getChild(AccountAdapter.KIND_FOLLOWING, following.getId());
        Key followingKey = KeyFactory.createKey(AccountAdapter.KIND_ACCOUNT, following.getId())
                                    .getChild(AccountAdapter.KIND_FOLLOWER, follower.getId());
        datastore.delete(Arrays.asList(followerKey, followingKey));
    }
}
