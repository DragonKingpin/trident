package com.sauron.saurye.peripheral.Skill.Plug;

import com.sauron.saurye.system.Saurye;
import com.hankcs.hanlp.corpus.io.IIOAdapter;

import java.io.*;

public class HanLPFileIoAdapter implements IIOAdapter {
    private static HanLPFileIoAdapter prototype;

    private Saurye mHost;

    public static void asPrototype( Saurye host ){
        HanLPFileIoAdapter.prototype = new HanLPFileIoAdapter( host );
    }

    public HanLPFileIoAdapter( Saurye host ){
        this.mHost = host;
    }

    public HanLPFileIoAdapter(){
        this.mHost = HanLPFileIoAdapter.prototype.mHost;
    }

    @Override
    public InputStream open( String path ) throws IOException {
        return new FileInputStream(this.mHost.getResourcesPath() + path );
    }

    @Override
    public OutputStream create( String path ) throws IOException {
        return new FileOutputStream(this.mHost.getResourcesPath() + path );
    }


}