package aaronbearon.chapter37.lab08;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Run target for the Chapter 37 servlet lab: boots an embedded Tomcat and
 * registers {@link ComputeLoanServlet} programmatically (no web.xml needed).
 */
public class LoanServer {
    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        Path baseDir = Files.createTempDirectory("chapter37lab08-tomcat");
        tomcat.setBaseDir(baseDir.toString());

        // The servlet serves everything itself, so the docBase only needs to exist.
        Context context = tomcat.addContext("", baseDir.toString());
        Tomcat.addServlet(context, "computeLoan", new ComputeLoanServlet());
        context.addServletMappingDecoded("/", "computeLoan");
        context.addServletMappingDecoded("/calculatePayment", "computeLoan");

        tomcat.getConnector();
        tomcat.start();

        System.out.println("Open http://localhost:8080/");

        tomcat.getServer().await();
    }
}
