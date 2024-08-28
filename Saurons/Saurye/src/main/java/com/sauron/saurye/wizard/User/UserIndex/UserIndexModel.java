package com.sauron.saurye.wizard.User.UserIndex;

import com.pinecone.framework.util.json.JSONArraytron;
import com.pinecone.summer.ArchConnection;
import com.pinecone.summer.prototype.Pagesion;
import com.sauron.saurye.system.prototype.JasperModifier;

import java.sql.SQLException;

@JasperModifier
public class UserIndexModel extends UserIndex implements Pagesion {
    public UserIndexModel( ArchConnection connection ){
        super(connection);
    }

    @Override
    public void beforeGenieInvoke() throws Exception {
        super.beforeGenieInvoke();

        this.mPageData.put( "userBaseInfo", this.mysql().fetch( String.format(
                "SELECT tUser.*, " +
                        "tProfile.`u_gender`,tProfile.`u_occupation`,tProfile.`u_school`, tProfile.`u_focus_band`, tProfile.`u_sign_introduce`, tProfile.`u_birthday`, tProfile.`u_introduce` " +
                        "FROM (" +
                        "( SELECT tUser.`username`, tUser.`avatar`, tUser.`nickname`  FROM %s AS tUser WHERE tUser.`username` = '%s' ) AS tUser " +
                        "LEFT JOIN %s AS tProfile ON tProfile.`username` = tUser.`username` )",
                this.alchemist().user().profile().tabUsersNS(), this.currentUser().username() ,
                this.alchemist().user().profile().tabProfileNS()
        ) ).optJSONObject(0) );
    }

    @Override
    public void defaultGenie() throws Exception {
        super.defaultGenie();
        this.profile();
    }

    public void profile() throws SQLException {


    }

    public void infoMaintain() throws SQLException {
        this.mPageData.put( "accountData", this.currentUser().user().getUserCache() );

        String szEquipName = "SingleImgUploader";
        this.appendDefaultAttribute( szEquipName, this.equipmentPeddler().purchase( szEquipName ).mount(
                new JSONArraytron("[{'at':'avatarField', 'name':'avatar', 'src': '" + this.currentUser().user().getAvatar() + "' }]")
        ) );
    }
}