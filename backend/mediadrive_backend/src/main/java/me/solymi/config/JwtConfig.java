package me.solymi.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    private static final String SECRET = "16c3f8f043d261a1419d4207d8f54480aea3f5d2cbf3046d724ddb4657762d6ee6b1a48a5a5bca19284ab5f755ae08d95a2c4bdc751a1d8ab594a1650b30e1a588eafb8926309e222550c64c55b09f868aff7bb6de70580d3c5466bfbbb476a6bba1f6a4ef7157ac42d66b062ec67bb64d5a932fda5cccd4194391c599bc48a28ff62d6beb790f828e589b9ab5f27e4ae5106ec5c75e43882f56d751fca94f40dc83bda6d3261b4ff479549826e85953eaec95e90d4beafa551d2842947fac539b9125d0325ab29ad38ea2fa18db75492832813fa0d90df5f23a45771387dd9e06a08ccfb1a846b412fe8c1b82610909f344479fbac459b061d0ec62323dc367";

    @Bean
    public Algorithm jwtAlgorithm() {
        return Algorithm.HMAC256(SECRET);
    }

    @Bean
    public JWTVerifier jwtVerifier(final Algorithm jwtAlgorithm) {
        return JWT.require(jwtAlgorithm)
                .acceptLeeway(10)
                .withIssuer("solymi.tech")
                .build();
    }

}
