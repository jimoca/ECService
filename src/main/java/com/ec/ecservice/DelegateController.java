package com.ec.ecservice;


import com.ec.ecservice.exception.BusinessException;
import com.ec.ecservice.model.TestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/delegate")
    public ResponseEntity<byte[]> delegateFile(@RequestBody byte[] delegateModel) {
        return delegateService.delegateFile(delegateModel)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new BusinessException("Something wrong with delegate!"));
    }


}
