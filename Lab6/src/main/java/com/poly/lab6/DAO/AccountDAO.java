package com.poly.lab6.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import com.poly.lab6.entity.Account;

public interface AccountDAO extends JpaRepository<Account, String> {}
