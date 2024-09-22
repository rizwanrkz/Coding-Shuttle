package com.codingshuttle.rizwan.week1.introductionToSpringBoot;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class Apple {

    void eatApple() {
        System.out.println("I am eating apple");
    }

    @PostConstruct
    void callThisBeforeAppleIsUsed() {
        System.out.println("Creating the aplle before use");
    }

    @PreDestroy
    void callThisBeforeDestroy() {
        System.out.println("Destroying the Apple bean");
    }


}
