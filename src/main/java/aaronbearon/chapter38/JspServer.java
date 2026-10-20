package aaronbearon.chapter38;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Run target for the Chapter 38 JSP lab: boots an embedded Tomcat.
 */
public class JspServer {
    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.setBaseDir(System.getProperty("java.io.tmpdir") + "/chapter38jsp-tomcat");

        File webappDir = resolveWebappDir();
        Context context = tomcat.addWebapp("", webappDir.getAbsolutePath());

        tomcat.getConnector();
        tomcat.start();

        System.out.println("Serving " + context.getDocBase());
        System.out.println("Open http://localhost:8080/ComputeTax.jsp");

        tomcat.getServer().await();
    }

    private static File resolveWebappDir() throws URISyntaxException {
        URL webappUrl = JspServer.class.getResource("WEB-INF");
        if (webappUrl == null) {
            throw new IllegalStateException("WEB-INF not found on the classpath; did the project build?");
        }
        return new File(webappUrl.toURI()).getParentFile();
    }
}
