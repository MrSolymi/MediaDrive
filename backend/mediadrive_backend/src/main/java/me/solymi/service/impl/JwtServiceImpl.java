package me.solymi.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import me.solymi.service.JwtService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        userDetails.getAuthorities().forEach(authority -> claims.put(authority.getAuthority(), authority));


        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+150000))
                .signWith(getKey())
                .compact();
    }

    @Override
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claimsResolver.apply(claims);
    }

    private SecretKey getKey(){
        byte[] keyBytes = Decoders.BASE64.decode("16c3f8f043d261a1419d4207d8f54480aea3f5d2cbf3046d724ddb4657762d6ee6b1a48a5a5bca19284ab5f755ae08d95a2c4bdc751a1d8ab594a1650b30e1a588eafb8926309e222550c64c55b09f868aff7bb6de70580d3c5466bfbbb476a6bba1f6a4ef7157ac42d66b062ec67bb64d5a932fda5cccd4194391c599bc48a28ff62d6beb790f828e589b9ab5f27e4ae5106ec5c75e43882f56d751fca94f40dc83bda6d3261b4ff479549826e85953eaec95e90d4beafa551d2842947fac539b9125d0325ab29ad38ea2fa18db75492832813fa0d90df5f23a45771387dd9e06a08ccfb1a846b412fe8c1b82610909f344479fbac459b061d0ec62323dc367");

        return Keys.hmacShaKeyFor(keyBytes);
    }

    private boolean isTokenExpired(String token) {
        Date d = extractClaim(token, Claims::getExpiration);
        return d.before(new Date());
    }
}
