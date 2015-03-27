package org.morgade.grumbler.business;

import java.util.Date;
import java.util.List;
import org.morgade.grumbler.data.StatusDao;
import org.morgade.grumbler.entity.Account;
import org.morgade.grumbler.entity.Status;

/**
 *
 */
public class StatusService {
    private AuthenticationService authenticationService = new AuthenticationService();
    private StatusDao statusDao = new StatusDao();
    
    public void create(Status status) {
        String senderId = authenticationService.getCurrentAccountId();
        status.setSender(new Account(senderId));
        status.setTimestamp(new Date());
        statusDao.create(status);
    }
    
    public List<Status> getTimeline() {
        return getTimeline(new Account(authenticationService.getCurrentAccountId()));
    }
    
    public List<Status> getTimeline(Account account) {
        return statusDao.getTimeline(account);
    }
    
    public List<Status> get(Account account) {
        return statusDao.get(account);
    }
    
}
