package com.ec.ecservice.utils;

import com.demo.ecclient.model.PictureBase;
import com.demo.ecclient.model.PictureMask;
import com.ec.ecservice.exception.BusinessException;
import security.misc.HomomorphicException;
import security.paillier.PaillierCipher;
import security.paillier.PaillierPublicKey;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaskWithPaillier {
    public static BigInteger[] apply(PictureBase image, PictureMask mask, PaillierPublicKey pk) {
        System.out.println("Start");
        int img_width = image.getWidth();
        int img_height = image.getHeight();
        int mask_width = mask.getWidth();
        int mask_height = mask.getHeight();

        BigInteger[] big_img_pixels = image.getPixels();
        BigInteger[] big_mask_pixels = mask.getPixels();

        int img_start_row = img_height / 2 - mask_height / 2;
        int img_start_col = img_width / 2 - mask_width / 2;

        Map<BigInteger, BigInteger> multiplyMap = new HashMap<>();

        Map<List<BigInteger>, BigInteger> addMap = new HashMap<>();

        System.out.println("Operating...");

        try {
            // from first row of mask
            for (int i = 0; i < mask_height; i++) {
                int img_index = (img_width * img_start_row) + img_start_col;
                if (img_start_row >= img_height || img_index >= big_img_pixels.length) {
                    break;
                }
                // from first col of first row of mask
                for (int j = i * mask_width; j < i * mask_width + mask_width; j++) {
                    // if map doesn't have the add result.
                    if(!addMap.containsKey(List.of(big_img_pixels[img_index], big_mask_pixels[j]))) {
                        // if map doesn't have the multiply result.
                        if (!multiplyMap.containsKey(big_mask_pixels[j])) {
                            // cal and put it into multiply map.
                            BigInteger resOfMultiply = PaillierCipher.multiply(big_mask_pixels[j], 16777216, pk);
                            multiplyMap.put(big_mask_pixels[j], resOfMultiply);
                        }
                        // cal and put it into add map.
                        BigInteger resOfAdd = PaillierCipher.add(big_img_pixels[img_index], multiplyMap.get(big_mask_pixels[j]), pk);
                        addMap.put(List.of(big_img_pixels[img_index], big_mask_pixels[j]), resOfAdd);
                    }

                    big_img_pixels[img_index] = addMap.get(List.of(big_img_pixels[img_index], big_mask_pixels[j]));
                    img_index++;
                }
                img_start_row++;
            }
        } catch (HomomorphicException he) {
            throw new BusinessException(he.getMessage());
        }


        System.out.println("Finished");

        return big_img_pixels;
    }

}
