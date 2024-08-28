package com.sauron.saurye.wizard.Admin.AdminUserManage;

import com.pinecone.summer.ArchConnection;
import com.pinecone.summer.prototype.Wizard;
import com.sauron.saurye.system.PredatorGenieBottle;

public class AdminUserManage extends PredatorGenieBottle implements Wizard {
    public AdminUserManage( ArchConnection connection ){
        super(connection);
    }

    public String prototypeName(){
        return this.getClass().getSuperclass().getSimpleName();
    }

}