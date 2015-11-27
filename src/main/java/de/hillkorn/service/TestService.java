package de.hillkorn.service;

import de.hillkorn.dto.Simple;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class TestService {

  @Inject
  SecondService secondService;

  public TestService() {
    System.out.println("Created Service 1" + this);
  }

  public Simple getTest() {
//    return new Simple("Test Service");
    return new Simple(secondService.testMethod());
  }
}
