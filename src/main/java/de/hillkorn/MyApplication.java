package de.hillkorn;

import de.hillkorn.rest.Hello;
import io.undertow.Undertow;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.ServletException;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;

@ApplicationPath("/base")
public class MyApplication extends Application {

    public static void main(String[] args) throws ServletException {
        UndertowJaxrsServer server = new UndertowJaxrsServer();
        Undertow.Builder serverBuilder = Undertow.builder().addHttpListener(8080, "0.0.0.0");
        server.start(serverBuilder);

        server.deploy(MyApplication.class);
    }

    @Override
    public Set<Class<?>> getClasses() {
        HashSet<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(Hello.class);
        return classes;
    }
}
