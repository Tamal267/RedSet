package com.example.RedSet.Lattice;

import java.util.Base64;

public class encodeDecode {
    public static String encode(String txt){
        return Base64.getEncoder().encodeToString(txt.getBytes());
    }
    public static String decode(String txt){
        byte[] decodedBytes = Base64.getDecoder().decode(txt);
        return new String(decodedBytes);
    }
}
