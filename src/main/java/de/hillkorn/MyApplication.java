package de.hillkorn;

import io.undertow.Undertow;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;
import javax.servlet.ServletException;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;
import org.apache.deltaspike.cdise.api.CdiContainer;
import org.apache.deltaspike.cdise.api.CdiContainerLoader;
import org.apache.deltaspike.cdise.api.ContextControl;
import org.apache.deltaspike.cdise.servlet.CdiServletRequestListener;
import org.jboss.resteasy.plugins.server.servlet.ResteasyBootstrap;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.jboss.resteasy.spi.ResteasyDeployment;
import org.reflections.Reflections;

@ApplicationPath("/base")
@ApplicationScoped
public class MyApplication extends Application {

  private static final int PORT = 8080;

  public static void main(String[] args) throws ServletException {
    //HTTP Server
    UndertowJaxrsServer server = new UndertowJaxrsServer();
    Undertow.Builder serverBuilder = Undertow.builder().addHttpListener(PORT, "0.0.0.0");
    server.start(serverBuilder);
    CdiContainer cdiContainer = CdiContainerLoader.getCdiContainer();
    cdiContainer.boot();

    ContextControl contextControl = cdiContainer.getContextControl();
    contextControl.startContexts();
//        contextControl.startContext(ApplicationScoped.class);

    ResteasyDeployment deployment = new ResteasyDeployment();
//        deployment.setInjectorFactoryClass(CdiInjectorFactory.class.getName());
    deployment.setInjectorFactoryClass("org.jboss.resteasy.cdi.CdiInjectorFactory");
    deployment.setApplicationClass(MyApplication.class.getName());

    DeploymentInfo undertowDeployment = server.undertowDeployment(deployment, "/");

    undertowDeployment.setClassLoader(MyApplication.class.getClassLoader())
            .setContextPath("/")
            .setDeploymentName("Tryout")
            .addListener(Servlets.listener(CdiServletRequestListener.class))
            .addListener(Servlets.listener(ResteasyBootstrap.class));
    server.deploy(undertowDeployment);
  }

  private final Set<Class<?>> classes;

  public MyApplication() {
    System.out.println("Scan for Paths");
    Reflections reflections = new Reflections("de.hillkorn.rest");
    classes = reflections.getTypesAnnotatedWith(Path.class);
//      classes.add(TestService.class);
  }

  @Override
  public Set<Class<?>> getClasses() {
//        classes.add(TestService.class);
    return classes;
//        return null;
  }

  @Override
  public Set<Object> getSingletons() {
//      Set<Object> resources = new LinkedHashSet<Object>();
//      return resources;
    return null;
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
