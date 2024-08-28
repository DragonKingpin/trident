package com.sauron.saurye.wizard.Public.undefined;

import com.pinecone.summer.ArchConnection;
import com.pinecone.summer.prototype.Wizard;
import com.sauron.saurye.system.PredatorGenieBottle;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Scope( "prototype" )
public class undefined extends PredatorGenieBottle implements Wizard {
    public undefined( ArchConnection connection ){
        super(connection);
    }

    public undefined(){
        super();
    }

    @GetMapping( "/fuck" )
    @ResponseBody
    public String test( HttpServletRequest request, HttpServletResponse response ) {
        //this.afterGetArrived( request, response );
        return this.$_GET().toJSONString();
    }

    public String prototypeName(){
        return this.getClass().getSimpleName();
    }
}
