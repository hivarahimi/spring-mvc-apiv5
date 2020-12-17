package com;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

public class Application {
    public static void main(String[] args) throws Exception {
        String baseDir =".";
        String webappDirLocation = "src/main/webapp/";
        String webxmlDirLocation = "src/main/webapp/WEB-INF/web.xml";
        Tomcat tomcat = new Tomcat();
         tomcat.setPort(8080);
         tomcat.setBaseDir(baseDir);
         tomcat.getHost().setAppBase(baseDir);
        tomcat.getHost().setDeployOnStartup(true);
         tomcat.getHost().setAutoDeploy(true);
         tomcat.enableNaming();
        StandardContext ctx = (StandardContext)
                 tomcat.addWebapp("/service", new File(webappDirLocation).getAbsolutePath());
        File additionWebInfClasses = new File("target/classes");
         WebResourceRoot resources = new StandardRoot(ctx);
         resources.addPreResources(new DirResourceSet(resources,
                 "/WEB-INF/classes",
                                 additionWebInfClasses.getAbsolutePath(), "/"));
         ctx.setResources(resources);
         ctx.setDefaultWebXml(new File(webxmlDirLocation).
                 getAbsolutePath());
         tomcat.start();
         tomcat.getServer().await();

    }
}
