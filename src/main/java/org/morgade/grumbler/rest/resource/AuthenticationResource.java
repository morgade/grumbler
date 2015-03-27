package org.morgade.grumbler.rest.resource;

import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.morgade.grumbler.business.AuthenticationService;
import org.morgade.grumbler.entity.Account;

/**
 *
 */
@Path("/auth")
public class AuthenticationResource {
    private AuthenticationService authenticationService = new AuthenticationService();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map get() {
        Map map = new HashMap(3);
        Account account = authenticationService.registerAccount();
        if (account==null) {
            map.put("loginUrl", authenticationService.getLoginUrl("/"));
        } else {
            map.put("account", account);
            map.put("logoutUrl", authenticationService.getLogoutUrl("/"));
        }
        return map;
    }

}
