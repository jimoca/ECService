package com.ec.ecservice.utils;

import com.ec.ecservice.exception.BusinessException;
import com.ec.ecservice.model.PictureModel;
import security.misc.HomomorphicException;
import security.paillier.PaillierCipher;
import security.paillier.PaillierPublicKey;

import java.math.BigInteger;

public class MaskWithPaillier {
    public static BigInteger[] apply(PictureModel image, PictureModel mask, PaillierPublicKey pk) {
        System.out.println("apply");
        int img_width = image.getWidth();
        int img_height = image.getHeight();
        int mask_width = mask.getWidth();
        int mask_height = mask.getHeight();

        BigInteger[] big_img_pixels = image.getPixels();
        BigInteger[] big_mask_pixels = mask.getPixels();

        int img_start_row = img_height/2 - mask_height/2;
        int img_start_col = img_width/2 - mask_width/2;

        System.out.println("operating");

        try {
            for (int i = 0; i < mask_height; i++) {
                int img_index = (img_width * img_start_row) + img_start_col;
                if(img_start_row >= img_height || img_index >= big_img_pixels.length) {
                    break;
                }
                for (int j = i * mask_width; j < i * mask_width + mask_width; j++) {
                    big_img_pixels[img_index] = PaillierCipher.add(big_img_pixels[img_index], PaillierCipher.multiply(big_mask_pixels[j], 16777216, pk), pk);
                    img_index++;
                }
                img_start_row++;
            }
        } catch (HomomorphicException he) {
            throw new BusinessException(he.getMessage());
        }


        System.out.println("finished");

        return big_img_pixels;
    }

}
