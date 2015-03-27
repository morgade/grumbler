package org.morgade.grumbler.rest.resource;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.morgade.grumbler.business.StatusService;
import org.morgade.grumbler.entity.Status;

/**
 *
 */
@Path("/status")
public class StatusResource {
    private StatusService statusService = new StatusService();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public void create(Status status) {
        statusService.create(status);
    }

}
