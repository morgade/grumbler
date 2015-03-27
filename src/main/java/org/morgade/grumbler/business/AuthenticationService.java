package org.morgade.grumbler.business;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import org.morgade.grumbler.data.AccountDao;
import org.morgade.grumbler.entity.Account;

/**
 *
 */
public class AuthenticationService {
    private AccountDao accountDao = new AccountDao();
    private UserService userService = UserServiceFactory.getUserService();    
    
    public String getLoginUrl(String returnURI) {
        return userService.createLoginURL(returnURI);
    }
    
    public String getLogoutUrl(String returnURI) {
        return userService.createLogoutURL(returnURI);
    }
    
    public String getCurrentAccountId() {
        if (userService.isUserLoggedIn()) {
            return userService.getCurrentUser().getNickname();
        } else {
            return null;
        }
    }
    
    public Account registerAccount() {
        if (userService.isUserLoggedIn()) {
            User user = userService.getCurrentUser();
            Account account = accountDao.get(getCurrentAccountId());
            if (account==null) {
                account = new Account(getCurrentAccountId());
                account.setEmail(user.getEmail());
                account.setName(user.getNickname());
                accountDao.create(account);
            }
            return account;
        }
        return null;
    }
    
}
