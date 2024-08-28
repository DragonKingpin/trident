package com.sauron.saurye.peripheral.Skill.Util;

import com.pinecone.framework.util.Randomium;
import com.sauron.saurye.system.PredatorArchWizardum;

import java.io.File;

public class FileHelper {
    public static String randomName( String szPath, int nLength, String szExt ) {
        Randomium random = new Randomium( System.nanoTime());

        while ( true ){
            String szFileName = random.nextString( nLength ) + "." + szExt ;
            String szFilePath = szPath + szFileName ;
            if( !( new File( szFilePath ) ).exists() ){
                return szFileName;
            }
        }
    }

    public static boolean erase (PredatorArchWizardum soul, String szRelatePath ) {
        String szReal = soul.system().getSystemPath() + szRelatePath;
        return ( new File( szReal ) ).delete();
    }
}
