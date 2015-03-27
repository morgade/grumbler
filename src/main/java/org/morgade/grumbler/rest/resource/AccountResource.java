package org.morgade.grumbler.rest.resource;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.morgade.grumbler.business.AccountService;
import org.morgade.grumbler.business.StatusService;
import org.morgade.grumbler.entity.Account;
import org.morgade.grumbler.entity.Status;

/**
 *
 */
@Path("/account")
public class AccountResource {
    private AccountService accountService = new AccountService();
    private StatusService statusService = new StatusService();
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Account get(@PathParam("id") String id) {
        Account usu = accountService.get(id);
        return usu;
    }
    
    @GET
    @Path("/{id}/status")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Status> statuses(@PathParam("id") String id) {
        return statusService.get(new Account(id));
    }
    
    @GET
    @Path("/{id}/followers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Account> getFollowers(@PathParam("id") String id) {
        return accountService.getFollowers(new Account(id));
    }
    
    @GET
    @Path("/{id}/following")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Account> getFollowings(@PathParam("id") String id) {
        return accountService.getFollowings(new Account(id));
    }
    
    @GET
    @Path("/{id}/timeline")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Status> timeline(@PathParam("id") String id) {
        return statusService.getTimeline(new Account(id));
    }
    
    @POST
    @Path("/follow")
    @Produces(MediaType.APPLICATION_JSON)
    public void follow(String id) {
        accountService.follow(new Account(id));
    }
    
    @POST
    @Path("/unfollow")
    @Produces(MediaType.APPLICATION_JSON)
    public void unfollow(String id) {
        accountService.unfollow(new Account(id));
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Account> latest() {
        return accountService.latest();
    }
}
