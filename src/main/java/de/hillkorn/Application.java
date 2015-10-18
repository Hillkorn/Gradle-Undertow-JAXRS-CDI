package de.hillkorn;

import io.undertow.Undertow;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import io.undertow.servlet.api.ListenerInfo;
import io.undertow.servlet.api.ServletInfo;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import org.apache.deltaspike.cdise.servlet.CdiServletRequestListener;

/**
 * @author hillkorn
 */
public class Application {

  public static void main(String[] args) throws ServletException {
    ServletInfo servletInfo = Servlets.servlet("WebTryout", HttpServlet.class).setAsyncSupported(true)
        .setLoadOnStartup(1).addMapping("/*");
    ListenerInfo listenerInfo = Servlets.listener(CdiServletRequestListener.class);
    DeploymentInfo di = new DeploymentInfo()
        .addListener(listenerInfo)
        .setContextPath("/")
        .addServlet(servletInfo).setDeploymentName("WebTryoutServlet")
        .setClassLoader(ClassLoader.getSystemClassLoader());
    DeploymentManager deploymentManager = Servlets.defaultContainer().addDeployment(di);
    deploymentManager.deploy();
    Undertow server = Undertow.builder()
        .addHttpListener(8080, "localhost")
        .setHandler(deploymentManager.start())
        .build();
    server.start();
  }
}
