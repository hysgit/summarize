package com.wolsx.summarize.gethostname;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by hy on 9/18/16.
 */
public class GetHostName {
    public static void main(String[] args) throws UnknownHostException {

        final InetAddress localHost = InetAddress.getLocalHost();
        System.out.println("hostAddress: " + localHost.getHostAddress());
        System.out.println("hostName: " + localHost.getHostName());
    }
}
