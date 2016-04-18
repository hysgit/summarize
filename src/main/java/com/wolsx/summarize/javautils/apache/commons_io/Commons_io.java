package com.wolsx.summarize.javautils.apache.commons_io;

import org.apache.commons.io.FileSystemUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by hy
 * Date: 16-4-18.
 */
public class Commons_io {


    @Test
    public void testFreeSpace() throws IOException {
        System.out.println(FileSystemUtils.freeSpaceKb("/")/1024/1024);


    }
}
