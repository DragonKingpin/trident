package com.sauron.saurye.peripheral.Skill;

import com.pinecone.framework.util.json.JSONObject;
import com.pinecone.framework.util.mysql.MySQLExecutor;
import com.sauron.saurye.system.Saurye;

public abstract class SkillSoul implements Skill {
    protected Coach mCoach = null ;

    protected JSONObject mProperties;

    public SkillSoul( Coach coach ) {
        this.mCoach = coach;
    }

    @Override
    public Skill setProperty( String key, Object val ) {
        this.mProperties.put( key, val );
        return this;
    }

    @Override
    public JSONObject property() {
        return this.mProperties;
    }

    public Saurye host() {
        return this.mCoach.host();
    }

    protected MySQLExecutor mysql(){
        return this.host().mysql();
    }

    @Override
    public Coach getCoach() {
        return this.mCoach;
    }
}
