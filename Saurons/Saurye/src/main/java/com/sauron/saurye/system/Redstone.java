package com.sauron.saurye.system;

import com.pinecone.framework.system.CascadeSystem;
import com.pinecone.framework.system.functions.Executor;
import com.pinecone.summer.ArchServiceSystem;
import com.pinecone.summer.spring.Springron;
import com.sauron.radium.Radium;
import com.sauron.saurye.SauryeBoot;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Redstone extends Radium {
    public static final ConcurrentMap<String, Object > GlobalConfigs = new ConcurrentHashMap<>();
    public static Redstone GlobalSauryeSystem;

    private ArchServiceSystem mServletSystem = null             ;
    private Springron         mSpringron;

    public Redstone( String[] args, CascadeSystem parent ) {
        this( args, null, parent );
    }

    public Redstone( String[] args, String szName, CascadeSystem parent ){
        super( args, szName, parent );
        Redstone.GlobalSauryeSystem = this;
        Redstone.GlobalConfigs.put( "Saurye.SauryeSystem" , this );
    }

    @Override
    protected void traceSubsystemWelcomeInfo() {
        this.pout().print( "----------------------SauronEyes Subsystem---------------------\n" );
        this.pout().print( "\u001B[31m\uD83D\uDE08 Sauron`s Eyes (God View) Subsystem \uD83D\uDE08 \u001B[0m\n" );
        this.pout().print( "\u001B[32mShadow is hungry, desiring for blood.\u001B[0m\n" );

        super.traceSubsystemWelcomeInfo();
    }

    public ArchServiceSystem getServletSystem() {
        return this.mServletSystem;
    }

    @Override
    public void vitalize () throws Exception {
        super.vitalize();

        this.mSpringron     = new RedSpringService( "Saurye", this );
        this.mServletSystem = new Saurye(this.getPrimaryConfigsPath().toString() + "/saurye/", "config.json5" );
        this.mServletSystem.init();
        Redstone.GlobalConfigs.put( "Saurye.ServletSystem", this.mServletSystem );
        this.mSpringron.setPrimarySources( SauryeBoot.class );

        this.mSpringron.setInitializer(new Executor() {
            @Override
            public void execute() throws Exception {
                Redstone.this.mSpringron.getSpringApplication().addInitializers(new ApplicationContextInitializer<ConfigurableApplicationContext>() {
                    @Override
                    public void initialize( ConfigurableApplicationContext applicationContext ) {
                        GenericApplicationContext genericApplicationContext = (GenericApplicationContext) applicationContext;
                        genericApplicationContext.registerBean("saurye", Saurye.class, () -> (Saurye) Redstone.this.mServletSystem);
                        genericApplicationContext.registerBean("sauryeSystem", Saurye.class, () -> (Saurye) Redstone.this.mServletSystem);
                    }
                });
            }
        });
        this.mSpringron.execute();



        this.getTaskManager().add( this.mSpringron );
        this.getTaskManager().syncWaitingTerminated();
    }

    public Springron getSpringron() {
        return this.mSpringron;
    }
}
