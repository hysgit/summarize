package com.wolsx.summarize.other.usejava;

/**
 * Created by hy on 3/16/17.
 */
public class Test {
    public static void main(String[] args) {
        byte[] b = new byte[1];
        b[0] = (byte) 128;
        if((b[0] & (byte)0x80) == (byte)0x80){
            System.out.println(128 + (b[0] & (byte)0x7f));
        }
        else{
            System.out.println(b[0]);
        }
        System.out.println(b[0]);

    }

    public static int getIntFromByte(byte b){

        if((b & (byte)0x80) == (byte)0x80){
            return 128 + (b & (byte)0x7f);
        }
        else{
            return (int)b;
        }
    }

    public static int getIntFrom2Byte(byte b1, byte b2){
        return (getIntFromByte(b1) << 8) + getIntFromByte(b2);
    }
}
