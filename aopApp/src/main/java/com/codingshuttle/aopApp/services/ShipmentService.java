package com.codingshuttle.aopApp.services;

public interface ShipmentService {

    public String orderPackage(Long orderId);

    public String trackPackage(Long orderId);
}
