package com.ec.ecservice;


import com.demo.ecclient.model.PictureBase;
import com.ec.ecservice.exception.BusinessException;
import com.demo.ecclient.model.DelegateModel;
import com.ec.ecservice.model.TestModel;
import com.ec.ecservice.utils.MaskWithPaillier;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;

import java.util.Optional;

@Service
public class DelegateService {

    Optional<TestModel> hello(TestModel testModel) {
        return Optional.of(testModel);
    }
    Optional<String> ping() {
        return Optional.of("pong");
    }


    Optional<byte[]> delegateFile(byte[] serializedData) {
        DelegateModel delegateModel = deserialize(serializedData);
        int baseHeight = delegateModel.getBase().getHeight();
        int baseWidth = delegateModel.getBase().getWidth();
        return Optional.of(MaskWithPaillier.apply(delegateModel.getBase(), delegateModel.getMask(), delegateModel.getPublicKey()))
                .map(it -> PictureBase.builder()
                        .pixels(it)
                        .height(baseHeight)
                        .width(baseWidth).build())
                .map(SerializationUtils::serialize);
    }


    DelegateModel deserialize(byte[] serializedData) {
        return Optional.of(serializedData)
                .map(it -> (DelegateModel) SerializationUtils.deserialize(it))
                .orElseThrow(() -> new BusinessException("Error"));
    }

}
