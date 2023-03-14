package com.spring.adapters.controller;

import com.spring.adapters.vo.request.PurchaseRequestVO;
import com.spring.adapters.vo.response.PurchaseCreationResponseVO;
import com.spring.domain.service.PurchaseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("purchase")
@AllArgsConstructor
public class PurchaseController {

    private PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<PurchaseCreationResponseVO> create(@RequestBody PurchaseRequestVO request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new PurchaseCreationResponseVO(purchaseService.save(request).getId()));
    }
}
