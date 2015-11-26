package de.hillkorn.service;

import javax.enterprise.context.Dependent;

/**
 *
 * @author hillkorn
 */
@Dependent
public class SecondService {

    public String testMethod() {
        return "Works";
    }
}
