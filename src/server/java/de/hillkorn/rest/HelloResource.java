package de.hillkorn.rest;

import de.hillkorn.api.dto.Simple;
import de.hillkorn.service.TestService;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ManagedBean
@Path("/hello")
public class HelloResource {

  @Inject
  TestService testService;

  public HelloResource() {
    System.out.println("Create Controller");
  }

  @PostConstruct
  public void init() {
    System.out.println("Init Controller");
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Simple get() {
    return testService.getTest();
  }

  @GET
  @Path("/async")
  @Produces(MediaType.APPLICATION_JSON)
  public void get(@Suspended AsyncResponse response) {
    response.resume(Response.ok(new Simple("ASYNC")).build());
  }
}
