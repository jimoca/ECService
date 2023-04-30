package com.ec.ecservice.model;

import lombok.Data;

@Data
public class DelegateModel {
    private PictureModel base;
    private PictureModel mask;
    private PublicKey publicKey;
}
