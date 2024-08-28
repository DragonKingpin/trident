package com.sauron.saurye.peripheral.Equipment;

import com.pinecone.framework.util.json.JSONObject;
import com.sauron.saurye.system.PredatorArchWizardum;
import com.sauron.saurye.system.Saurye;

public abstract class EquipmentPeddlerSour implements EquipmentPeddler {
    protected PredatorArchWizardum mhParentImage;

    public EquipmentPeddlerSour ( PredatorArchWizardum hSoul ) {
        this.mhParentImage = hSoul;
    }

    public Saurye host(){
        return this.mhParentImage.system();
    }

    @Override
    public JSONObject nodeProperty(){
        return this.host().getPeripheralConfig().getJSONObject( this.childType().getSimpleName() );
    }
}
