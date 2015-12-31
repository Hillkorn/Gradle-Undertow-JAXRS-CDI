package de.hillkorn.errai;

import javax.annotation.PostConstruct;
import org.jboss.errai.ioc.client.api.EntryPoint;

@EntryPoint
public class Application {
    
    @PostConstruct
    public void init() {
//        Window.alert("Hello World");
    }
}
