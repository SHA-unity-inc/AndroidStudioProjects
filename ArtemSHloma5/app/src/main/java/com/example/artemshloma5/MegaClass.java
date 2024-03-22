package com.example.artemshloma5;

import java.util.List;
import java.util.Vector;

public class MegaClass {
    String name;
    int type;
    Vector<MicroClass> podClass;

    public MegaClass(String name, Vector<MicroClass> microClasses, int type){
        this.name = name;
        podClass = microClasses;
        this.type = type;
    }
}
