package me.solymi.component;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.security.SecureRandom;

public class UploadIdGenerator implements IdentifierGenerator {

    private final SecureRandom rnd = new SecureRandom();

    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
        return rnd.nextLong(0xFFFFFFFFL, 0xFFFFFFFFFL);
    }
}
