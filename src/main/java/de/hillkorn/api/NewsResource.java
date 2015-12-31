package de.hillkorn.api;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

@Path("/news")
@Produces(MediaType.APPLICATION_JSON)
public interface NewsResource {

    @GET
    void getNews(@Suspended final AsyncResponse asyncResponse) throws InterruptedException;

    @POST
    void createNews(@Suspended AsyncResponse asyncResponse);

}
