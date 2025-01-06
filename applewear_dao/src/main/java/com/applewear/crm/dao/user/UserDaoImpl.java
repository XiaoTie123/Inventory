package com.applewear.crm.dao.user;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.applewear.crm.dao.generic.GenericDaoImpl;
import com.applewear.crm.entity.user.User;

@Repository
@Transactional
public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao {

}
