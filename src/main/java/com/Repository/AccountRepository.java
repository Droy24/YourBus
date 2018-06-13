package com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Account;


@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>
{

}
