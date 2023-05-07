package com.demo.ecclient.model;

import lombok.Data;
import security.paillier.PaillierPublicKey;

import java.io.Serializable;

@Data
public class DelegateModel implements Serializable  {
    private static final long serialVersionUID = -3751255153289772365L;
    private PictureBase base;
    private PictureMask mask;
    private PaillierPublicKey publicKey;
}
