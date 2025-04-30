package com.codingShuttle.cachingApp.services;

import com.codingShuttle.cachingApp.entities.Employee;
import com.codingShuttle.cachingApp.entities.SalaryAccount;

public interface SalaryAccountService {
    void createAccount(Employee employee);

    SalaryAccount incrementBalance(Long accountId);
}
