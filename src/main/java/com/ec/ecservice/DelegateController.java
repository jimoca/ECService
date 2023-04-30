package com.ec.ecservice;


import com.ec.ecservice.exception.BusinessException;
import com.ec.ecservice.model.DelegateModel;
import com.ec.ecservice.model.PictureModel;
import com.ec.ecservice.model.TestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import security.paillier.PaillierPublicKey;

@RestController
@RequestMapping(value = "/api")
public class DelegateController {

    @Autowired
    private DelegateService delegateService;

    @PostMapping("/hello")
    public ResponseEntity<TestModel> hello(@RequestBody TestModel testModel) {
        return delegateService.hello(testModel)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new BusinessException("Something wrong with hello!"));
    }

//    @PostMapping("/delegate")
//    public ResponseEntity<PictureModel> delegate(@RequestBody DelegateModel delegateModel) {
//        return delegateService.delegate(delegateModel)
//                .map(ResponseEntity::ok)
//                .orElseThrow(() -> new BusinessException("Something wrong with delegate!"));
//    }

    @PostMapping("/delegate")
    public ResponseEntity<byte[]> delegateFile(@RequestBody DelegateModel delegateModel) {
        return delegateService.delegateFile(delegateModel)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new BusinessException("Something wrong with delegate!"));
    }


}