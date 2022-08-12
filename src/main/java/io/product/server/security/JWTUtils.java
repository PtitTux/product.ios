package io.product.server.security;

import io.jsonwebtoken.*;
import io.product.server.entities.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class JWTUtils
{
	@Value("${product.io.jwtSecret}")
	private String jwtSecret;

	@Value("${product.io.jwtExpirationMs}")
	private int jwtExpirationMs;

	public String generateToken(UserEntity userEntity)
	{
		return Jwts.builder()
		           .setIssuer("product.io")
		           .setSubject((userEntity.getEmail()))
		           .setIssuedAt(new Date())
		           .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
		           .signWith(SignatureAlgorithm.HS512, jwtSecret)
		           .compact();
	}

	public Optional<String> validateToken(String token)
	{
		try
		{
			Claims jwt = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
			return Optional.of(jwt.getSubject());
		}
		catch (ExpiredJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e)
		{
			return Optional.empty();
		}
	}
}
