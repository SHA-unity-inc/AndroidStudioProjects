package com.example.artemshloma5;

import java.util.List;
import java.util.Vector;

public class MegaClass {
    String name;
    int type, image;
    Vector<MicroClass> podClass;

    public MegaClass(String name, Vector<MicroClass> microClasses, int type, int image){
        this.name = name;
        podClass = microClasses;
        this.type = type;
        this.image = image;
    }
}
