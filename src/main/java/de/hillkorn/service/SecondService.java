package de.hillkorn.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author hillkorn
 */
@Stateless
public class SecondService {

  public SecondService() {
    System.out.println("Created Service 2 " + this);
  }

  public String testMethod() {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException ex) {
      Logger.getLogger(SecondService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return "Works";
    }
}
