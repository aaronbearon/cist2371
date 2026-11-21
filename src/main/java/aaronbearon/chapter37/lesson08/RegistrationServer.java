package aaronbearon.chapter37.lesson08;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;

/**
 * Run target for the Chapter 37 in-class StudentRegistration servlets: boots an
 * embedded Tomcat that serves index.jsp and registers the servlets programmatically.
 */
public class RegistrationServer {
    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        int port = args.length > 0 ? Integer.parseInt(args[0]) : 8080;
        tomcat.setPort(port);
        tomcat.setBaseDir(Files.createTempDirectory("chapter37lesson08-tomcat").toString());

        File webappDir = resolveWebappDir();
        Context context = tomcat.addWebapp("", webappDir.getAbsolutePath());

        Tomcat.addServlet(context, "helloServlet", new HelloServlet());
        context.addServletMappingDecoded("/hello-servlet", "helloServlet");
        Tomcat.addServlet(context, "registrationServlet", new RegistrationServlet());
        context.addServletMappingDecoded("/register", "registrationServlet");
        Tomcat.addServlet(context, "confirmationServlet", new ConfirmationServlet());
        context.addServletMappingDecoded("/confirmation", "confirmationServlet");

        tomcat.getConnector();
        tomcat.start();

        System.out.println("Open http://localhost:" + port + "/register");

        tomcat.getServer().await();
    }

    private static File resolveWebappDir() throws URISyntaxException {
        URL webappUrl = RegistrationServer.class.getResource("WEB-INF");
        if (webappUrl == null) {
            throw new IllegalStateException("WEB-INF not found on the classpath; did the project build?");
        }
        return new File(webappUrl.toURI()).getParentFile();
    }
}
