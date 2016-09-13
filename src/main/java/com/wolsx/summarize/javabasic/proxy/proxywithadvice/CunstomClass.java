package com.wolsx.summarize.javabasic.proxy.proxywithadvice;

/**
 * Created by hy on 6/29/16.
 */
public class CunstomClass {
    @Override
    public boolean equals(Object obj) {
        return (this.hashCode() == obj.hashCode());
    }
}
