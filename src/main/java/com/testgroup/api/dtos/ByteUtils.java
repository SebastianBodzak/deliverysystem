package com.testgroup.api.dtos;

import java.nio.ByteBuffer;

public class ByteUtils {

        public static long bytesToLong(byte[] bytes) {
            ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
            if(bytes.length != Long.BYTES){
                throw new IllegalArgumentException("cannot convert byte array to long, wrong array size");
            }
            buffer.put(bytes, 0, bytes.length);
            buffer.flip();//need flip
            return buffer.getLong();
        }

    public static int bytesToInt(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
        int bytesLeft = Integer.BYTES - bytes.length;
        buffer.put(new byte[bytesLeft]);
        buffer.put(bytes);

        buffer.flip();//need flip
        return buffer.getInt();
    }
}
