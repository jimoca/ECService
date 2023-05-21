package com.ec.ecservice;


import com.ec.ecservice.exception.BusinessException;
import com.ec.ecservice.model.TestModel;
import io.reactivex.rxjava3.disposables.Disposable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.math.BigInteger;

@RestController
@RequestMapping(value = "/api")
public class DelegateController {

    @Autowired
    private DelegateService delegateService;

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return delegateService.ping()
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new BusinessException("Something wrong with hello!"));
    }


    @GetMapping("/start")
    public DeferredResult<BigInteger> startDelegation() {
        DeferredResult<BigInteger> deferredResult = new DeferredResult<>();
        Disposable subscription = delegateService.createTask().subscribe(
                deferredResult::setResult,
                deferredResult::setErrorResult
        );

        deferredResult.onCompletion(subscription::dispose);
        return deferredResult;
    }

    @PostMapping("/delegate")
    public ResponseEntity<byte[]> delegateFile(@RequestBody byte[] delegateModel) {
        return delegateService.delegateFile(delegateModel)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new BusinessException("Something wrong with delegate!"));
    }


}
