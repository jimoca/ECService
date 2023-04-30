package com.ec.ecservice.model;


import lombok.Data;

import java.math.BigInteger;


@Data
public class PublicKey {
    public final int keysize;
    protected final BigInteger n;
    protected final BigInteger modulus;
    protected final BigInteger g;

    public PublicKey(int keysize, BigInteger n, BigInteger modulus, BigInteger g) {
        this.keysize = keysize;
        this.n = n;
        this.modulus = modulus;
        this.g = g;
    }
}
