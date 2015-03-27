package org.morgade.grumbler.entity.adapter;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import org.morgade.grumbler.entity.Account;
import org.morgade.grumbler.entity.Status;

/**
 *
 */
public class StatusAdapter {

    public static final String KIND_STATUS = "status";
    public static final String KIND_TIMELINE = "timeline";
    public static final String FIELD_TEXT = "text";
    public static final String FIELD_TIMESTAMP = "timestamp";

    public static Status toStatus(Entity entity) {
        if (!entity.getKind().equals(KIND_STATUS)) {
            throw new RuntimeException("Invalid kind");
        }

        Key accountKey = entity.getKey().getParent();

        Status status = new Status(new Account(accountKey.getName()));
        status.setBody((String) entity.getProperty(FIELD_TEXT));
        status.setTimestamp((Date) entity.getProperty(FIELD_TIMESTAMP));
        return status;
    }

    public static List<Status> toStatus(Collection<Entity> entities) {
        List<Status> l = new ArrayList<>(entities.size());
        for (Entity entity : entities) {
            l.add(toStatus(entity));
        }
        return l;
    }

    public static Entity toEntity(Status status) {
        Key parentKey = AccountAdapter.keyOf(status.getSender());
        Entity entity = new Entity(KIND_STATUS, parentKey);
        entity.setProperty(FIELD_TEXT, status.getBody());
        entity.setProperty(FIELD_TIMESTAMP, status.getTimestamp());
        return entity;
    }

    public static Comparator<Status> timestampComparator() {
        return new Comparator<Status>() {
            @Override
            public int compare(Status s1, Status s2) {
                if (s1 == null || s2 == null) {
                    return s1 == null ? 1 : -1;
                } else if (s1.getTimestamp() == null || s2.getTimestamp() == null) {
                    return s1.getTimestamp() == null ? 1 : -1;
                } else {
                    return s1.getTimestamp().compareTo(s2.getTimestamp()) * -1;
                }
            }
        };
    }
}
