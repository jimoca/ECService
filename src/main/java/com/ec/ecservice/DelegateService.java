package com.ec.ecservice;


import com.demo.ecclient.model.PictureBase;
import com.ec.ecservice.model.DelegateModel;
import com.ec.ecservice.model.PictureModel;
import com.ec.ecservice.model.TestModel;
import com.ec.ecservice.utils.MaskWithPaillier;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;
import security.paillier.PaillierPublicKey;

import java.util.Optional;

@Service
public class DelegateService {

    Optional<TestModel> hello(TestModel testModel) {
        return Optional.of(testModel);
    }



    Optional<byte[]> delegateFile(DelegateModel delegateModel) {
        PaillierPublicKey ppk = new PaillierPublicKey(delegateModel.getPublicKey().getKeysize(), delegateModel.getPublicKey().getN(), delegateModel.getPublicKey().getModulus(), delegateModel.getPublicKey().getG());
        int baseHeight = delegateModel.getBase().getHeight();
        int baseWidth = delegateModel.getBase().getWidth();
        return Optional.of(MaskWithPaillier.apply(delegateModel.getBase(), delegateModel.getMask(), ppk))
                .map(it -> PictureBase.builder()
                        .pixels(it)
                        .height(baseHeight)
                        .width(baseWidth).build())
                .map(SerializationUtils::serialize);
    }

}
