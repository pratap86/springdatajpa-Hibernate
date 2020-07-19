package com.pratap.springdata.utils;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class CustomRandomIDGenerator implements IdentifierGenerator {


	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		Random random = new SecureRandom();
		long id = 0;
		id = random.nextInt(100000);
		return id;
	}

}
