package com.sauron.saurye.wizard.User.PersonalGlossary;

import com.pinecone.summer.ArchConnection;
import com.pinecone.summer.prototype.Wizard;
import com.sauron.saurye.elements.user.Pamphlet.Pamphlet;
import com.sauron.saurye.elements.user.Pamphlet.PamphletIncarnation;
import com.sauron.saurye.system.PredatorGenieBottle;

import java.io.IOException;
import java.sql.SQLException;

public class PersonalGlossary extends PredatorGenieBottle implements Wizard, PamphletIncarnation {
    protected String mszCurrentUser = "";

    public PersonalGlossary( ArchConnection connection ){
        super(connection);
    }

    public String prototypeName(){
        return this.getClass().getSuperclass().getSimpleName();
    }

    @Override
    public Pamphlet protoIncarnated() {
        return this.alchemist().user().pamphlet().glossary();
    }

    /**
     * Code by JiaYiYuan. Temp
     * */
    public boolean glossaryIsReciting( String szClassId )throws SQLException, IOException {
        String szCondition = String.format( " WHERE `classid` = '%s'",
                szClassId , this.mszCurrentUser
        );
        String szGlossaryState = this.mysql().fetch(
                "SELECT `g_state` FROM " + this.alchemist().user().pamphlet().tabPamphletsNS()+ szCondition
        ).getJSONObject(0).getString("g_state");
        return szGlossaryState.equals("reciting");
    }

}