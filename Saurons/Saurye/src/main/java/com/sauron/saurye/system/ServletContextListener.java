package com.sauron.saurye.system;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;

@Component
public class ServletContextListener implements ApplicationListener<ContextRefreshedEvent> {
    private final ServletContext servletContext;

    public ServletContextListener(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    public void onApplicationEvent( ContextRefreshedEvent event ) {
        Saurye              saurye = (Saurye        ) Redstone.GlobalConfigs.get( "Saurye.ServletSystem" );
        Redstone      sauryeSystem = (Redstone) Redstone.GlobalConfigs.get( "Saurye.SauryeSystem"  );
        saurye.setLegacyServlet(
                this.servletContext, sauryeSystem.getRuntimeContextPath()
        );
    }
}