package com.ec.ecservice.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;


@Data
@Builder
public class PictureModel implements Serializable {
    private BigInteger[] pixels;
    private int width;
    private int height;
}
