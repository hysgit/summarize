package com.wolsx.summarize.javabasic.testgc;

/**
 * Created by hy on 8/27/16.
 */
public class GCobject {

    private Integer integer;

    public GCobject(Integer integer) {
        this.integer = integer;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("垃圾回收: "+this.getClass().getName()+" : "+integer);
        super.finalize();
    }
}
