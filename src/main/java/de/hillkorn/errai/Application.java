package de.hillkorn.errai;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.jboss.errai.enterprise.client.jaxrs.api.RestClient;
import org.jboss.errai.ioc.client.api.EntryPoint;
import org.jboss.errai.ui.nav.client.local.Navigation;

@EntryPoint
public class Application {

    @Inject
    Navigation navigation;

    @PostConstruct
    public void init() {
        RestClient.setJacksonMarshallingActive(true);
        RestClient.setApplicationRoot("/api");
    }
}
