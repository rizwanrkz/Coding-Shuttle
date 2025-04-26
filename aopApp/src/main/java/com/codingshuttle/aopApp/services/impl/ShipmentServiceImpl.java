package com.codingshuttle.aopApp.services.impl;

import com.codingshuttle.aopApp.services.ShipmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class ShipmentServiceImpl implements ShipmentService {
    @Override
    public String orderPackage(Long orderId) {
        try{
            log.info("Processing the order...");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error("Error has occured while processing the order", e);
        }
        return "Order has been processed successfully, orderId: "  + orderId;
    }

    @Override
    @Transactional
    public String trackPackage(Long orderId) {
        try {
            log.info("Tracking the order...");
            Thread.sleep(1000);
            throw new RuntimeException("Exception occured during trackPackage");
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}
