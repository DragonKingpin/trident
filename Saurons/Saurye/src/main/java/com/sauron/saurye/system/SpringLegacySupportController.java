package com.sauron.saurye.system;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.apache.catalina.Context;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.pinecone.summer.Connectiom;

// For support legacy java-web
@Controller
public class SpringLegacySupportController {
    private Saurye         mSystem           = null             ;
    private Redstone mSauryeSystem     = null             ;

    private ServletContext mServletContext                      ;

    @PostConstruct
    public void init() throws ServletException, IOException {
        this.mSystem        = (Saurye        ) Redstone.GlobalConfigs.get( "Saurye.ServletSystem" );
        this.mSauryeSystem  = (Redstone) Redstone.GlobalConfigs.get( "Saurye.SauryeSystem"  );
    }

    @Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext( Context context ) {
                context.setDocBase( SpringLegacySupportController.this.mSauryeSystem.getRuntimeContextPath() + "/Saurons/Saurye/web/" );
            }
        };
        return tomcat;
    }

    @GetMapping("/")
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        this.mSystem.handleByDispatcher().handleGet( new Connectiom( request, response, null ) );
    }

    @PostMapping("/")
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        this.mSystem.handleByDispatcher().handlePost( new Connectiom( request, response, null  ) );
    }

}
