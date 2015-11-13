package de.hillkorn.service;

import de.hillkorn.dto.Simple;

//@Dependent
public class TestService {

    public Simple getTest() {
        return new Simple("From SERVICE!");
    }
}
