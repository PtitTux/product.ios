package io.product.server.security.jwt;

import io.product.server.entities.UserEntity;
import io.product.server.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JWTUserDetailsServiceImpl implements UserDetailsService
{
	private final UserRepository userRepository;

	public JWTUserDetailsServiceImpl(UserRepository userRepository)
	{
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
	{
		UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));

		return JWTUserDetailsImpl.build(user);
	}
}
