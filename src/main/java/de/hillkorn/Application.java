package de.hillkorn;

/**
 *
 * @author hillkorn
 */
public class Application {
  public static void main(String[] args) {
    ServletInfo servletInfo = Servlets.servlet("YourServletName", YourServlet.class).setAsyncSupported(true)
        .setLoadOnStartup(1).addMapping("/*");
    ListenerInfo listenerInfo = Servlets.listener(CdiServletRequestListener.class);
    DeploymentInfo di = new DeploymentInfo()
        .addListener(listenerInfo)
        .setContextPath("/")
        .addServlet(servletInfo).setDeploymentName("CdiSEServlet")
        .setClassLoader(ClassLoader.getSystemClassLoader());
    DeploymentManager deploymentManager = Servlets.defaultContainer().addDeployment(di);
    deploymentManager.deploy();
    Undertow server = Undertow.builder()
        .addHttpListener(port, "localhost")
        .setHandler(deploymentManager.start())
        .build();
    server.start();
  }
}
