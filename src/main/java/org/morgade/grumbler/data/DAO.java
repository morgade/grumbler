package org.morgade.grumbler.data;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

/**
 *
 */
public class DAO {
    protected final DatastoreService datastore;

    public DAO() {
        datastore = DatastoreServiceFactory.getDatastoreService();
    }
    
    protected boolean exists(String kind, String id) {
        Query query = new Query(kind).setFilter(new Query.FilterPredicate(Entity.KEY_RESERVED_PROPERTY, Query.FilterOperator.EQUAL, KeyFactory.createKey(kind, id))).setKeysOnly();
        PreparedQuery pq = datastore.prepare(query);
        return pq.countEntities(FetchOptions.Builder.withDefaults()) > 0;
    }
}
