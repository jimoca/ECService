package com.demo.ecclient.model;


import lombok.Builder;

import java.io.Serializable;
import java.math.BigInteger;


@Builder
public class PictureMask  implements Serializable {
    private static final long serialVersionUID = -3751255153289772365L;
    private BigInteger[] pixels;
    private int width;
    private int height;

    public PictureMask(BigInteger[] pixels, int width, int height) {
        this.pixels = pixels;
        this.width = width;
        this.height = height;
    }

    public BigInteger[] getPixels() {
        return pixels;
    }

    public void setPixels(BigInteger[] pixels) {
        this.pixels = pixels;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
