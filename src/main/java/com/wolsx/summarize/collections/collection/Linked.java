package com.wolsx.summarize.collections.collection;

/**
 * Created by hy
 * Date: 16-4-23.
 */
public class Linked {

    Entry entry;
    Linked(){
        //只有一个头节点
        Object data = null;
        entry = new Entry(null, null);
    }

    static class Entry{
        Object data;
        Entry next;
        Entry(){}
        Entry(Object data,Entry next)
        {
            this.data = data;
            this.next = next;
        }
    }
}
