package com.sauron.saurye;


import com.pinecone.Pinecone;
import com.pinecone.framework.util.json.JSONObject;
import com.sauron.saurye.system.Redstone;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan( basePackages = "com.sauron.saurye.system" )
@ComponentScan( basePackages = "com.sauron.saurye.wizard" )
@ComponentScan( basePackages = "com.sauron.saurye.controller" )
@ComponentScan( basePackages = "com.sauron.saurye.service" )
@ComponentScan( basePackages = "com.sauron.saurye.Interceptor" )
@ComponentScan( basePackages = "com.sauron.saurye.config" )
//@ComponentScan( basePackages = "com.sauron.saurye.mapper" )
@MapperScan( basePackages = "com.sauron.saurye.mapper" )
public class SauryeBoot {
    public static Redstone system = null;

    public static void main( String[] args ) throws Exception {
        SauryeBoot.system = new Redstone( args, Pinecone.sys() );
        SauryeBoot.system.init( (Object...cfg )->{
            SauryeBoot.system.vitalize();


            return 0;
        }, (Object[]) args );
    }
}
