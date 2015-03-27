package org.morgade.grumbler.data;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.morgade.grumbler.entity.Account;
import org.morgade.grumbler.entity.Status;
import org.morgade.grumbler.entity.adapter.AccountAdapter;
import org.morgade.grumbler.entity.adapter.StatusAdapter;

/**
 *
 */
public class StatusDao extends DAO {
    
    public void create(Status status) {
        Key statusKey = datastore.put(StatusAdapter.toEntity(status));
        
        Query query = new Query(AccountAdapter.KIND_FOLLOWER, AccountAdapter.keyOf(status.getSender()))
                            .setKeysOnly();
        
        List<Entity> l = new LinkedList<>();
        for (Entity followerEntity : datastore.prepare(query).asIterable()) {
            Entity timelineEntity = new Entity(StatusAdapter.KIND_TIMELINE, 
                                               KeyFactory.keyToString(statusKey),
                                               KeyFactory.createKey(AccountAdapter.KIND_ACCOUNT, followerEntity.getKey().getName()) );
            timelineEntity.setProperty(StatusAdapter.FIELD_TIMESTAMP, status.getTimestamp());
            l.add(timelineEntity);
        }

        Entity timelineEntity = new Entity(StatusAdapter.KIND_TIMELINE, 
                                               KeyFactory.keyToString(statusKey),
                                               KeyFactory.createKey(AccountAdapter.KIND_ACCOUNT, AccountAdapter.keyOf(status.getSender()).getName()) );
        timelineEntity.setProperty(StatusAdapter.FIELD_TIMESTAMP, status.getTimestamp());
        l.add(timelineEntity);
        
        datastore.put(l);
    }
    
    public List<Status> getTimeline(Account account) {
        Query timelineQuery = new Query(StatusAdapter.KIND_TIMELINE, AccountAdapter.keyOf(account))
                                    .addSort(StatusAdapter.FIELD_TIMESTAMP, Query.SortDirection.DESCENDING);
        
        List<Key> l = new LinkedList<>();
        for (Entity timelineEntity : datastore.prepare(timelineQuery).asIterable()) {
            l.add(KeyFactory.stringToKey(timelineEntity.getKey().getName()));
        }
        
        List<Status> tl = StatusAdapter.toStatus(datastore.get(l).values());
        Collections.sort(tl, StatusAdapter.timestampComparator());
        return tl;
    }
    
    public List<Status> get(Account account) {
        Query query = new Query(StatusAdapter.KIND_STATUS, AccountAdapter.keyOf(account))
                            .addSort(StatusAdapter.FIELD_TIMESTAMP, Query.SortDirection.DESCENDING);
        
        List<Entity> result = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());
        
        return StatusAdapter.toStatus(result);
    }
    
}
