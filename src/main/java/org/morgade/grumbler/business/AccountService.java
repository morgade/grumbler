package org.morgade.grumbler.business;

import java.util.List;
import org.morgade.grumbler.data.AccountDao;
import org.morgade.grumbler.data.FollowDao;
import org.morgade.grumbler.entity.Account;

/**
 *
 */
public class AccountService {
    private AuthenticationService authenticationService = new AuthenticationService();
    private AccountDao accountDao = new AccountDao();
    private FollowDao followDao = new FollowDao();
    
    public void create(Account usuario) {
        accountDao.create(usuario);
    }
    
    public Account get(String id) {
        return accountDao.get(id);
    }
    
    public List<Account> latest() {
        return accountDao.getAll(new Account(authenticationService.getCurrentAccountId()));
    }
    
    public List<Account> getFollowers() {
        return getFollowers(new Account(authenticationService.getCurrentAccountId()));
    }
    
    public List<Account> getFollowers(Account account) {
        return followDao.getFollowers(account);
    }
    
    public List<Account> getFollowings(Account account) {
        return followDao.getFollowings(account);
    }
    
    public void follow(Account following) {
        followDao.follow(new Account(authenticationService.getCurrentAccountId()), following);
    }
    
    public void unfollow(Account following) {
        followDao.unfollow(new Account(authenticationService.getCurrentAccountId()), following);
    }
}
