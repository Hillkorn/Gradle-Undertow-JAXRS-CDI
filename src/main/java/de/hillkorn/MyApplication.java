package de.hillkorn;

import de.hillkorn.rest.Hello;
import io.undertow.Undertow;
import io.undertow.servlet.api.DeploymentInfo;
import java.util.HashSet;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;
import javax.servlet.ServletException;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.apache.deltaspike.cdise.api.CdiContainer;
import org.apache.deltaspike.cdise.api.CdiContainerLoader;
import org.apache.deltaspike.cdise.api.ContextControl;
import org.jboss.resteasy.cdi.CdiInjectorFactory;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.jboss.resteasy.spi.ResteasyDeployment;

@ApplicationPath("/base")
@ApplicationScoped
public class MyApplication extends Application {

    public static void main(String[] args) throws ServletException {
        CdiContainer cdiContainer = CdiContainerLoader.getCdiContainer();
        cdiContainer.boot();
//
        ContextControl contextControl = cdiContainer.getContextControl();
        contextControl.startContexts();
//        contextControl.startContext(ApplicationScoped.class);

        ResteasyDeployment deployment = new ResteasyDeployment();
        deployment.setInjectorFactoryClass(CdiInjectorFactory.class.getName());
        deployment.setApplicationClass(MyApplication.class.getName());

        UndertowJaxrsServer server = new UndertowJaxrsServer();
        Undertow.Builder serverBuilder = Undertow.builder().addHttpListener(8080, "0.0.0.0");
//        serverBuilder.
        server.start(serverBuilder);

        DeploymentInfo deploymentInfo = server.undertowDeployment(deployment)
            .setClassLoader(MyApplication.class.getClassLoader())
            .setContextPath("/base")
            .setDeploymentName("Tryout");
//        server.deploy(MyApplication.class);
        server.deploy(deploymentInfo);
//        cdiContainer.shutdown();
    }

    @Override
    public Set<Class<?>> getClasses() {
        HashSet<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(Hello.class);
        return classes;
    }

//    ServletInfo servletInfo = Servlets.servlet("YourServletName", YourServlet.class).setAsyncSupported(true)
//    .setLoadOnStartup(1).addMapping("/*");
//ListenerInfo listenerInfo = Servlets.listener(CdiServletRequestListener.class);
//DeploymentInfo di = new DeploymentInfo()
//        .addListener(listenerInfo)
//        .setContextPath("/")
//        .addServlet(servletInfo).setDeploymentName("CdiSEServlet")
//        .setClassLoader(ClassLoader.getSystemClassLoader());
//DeploymentManager deploymentManager = Servlets.defaultContainer().addDeployment(di);
//deploymentManager.deploy();
//Undertow server = Undertow.builder()
//        .addHttpListener(port, "localhost")
//        .setHandler(deploymentManager.start())
//        .build();
//server.start();
}
