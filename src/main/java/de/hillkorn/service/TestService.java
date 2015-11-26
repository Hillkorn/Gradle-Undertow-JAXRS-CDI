package de.hillkorn.service;

import de.hillkorn.dto.Simple;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
@Stateless
public class TestService {

    @Inject
    SecondService secondService;

    public Simple getTest() {
        return new Simple("Test Service");
//        return new Simple(secondService.testMethod());
    }
}
