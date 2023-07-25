package com.udacity.jwdnd.course1.cloudstorage.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.services.impl.IAuthenticationFacade;

@Service
public class AuthenticationFacadeService implements IAuthenticationFacade {

	@Override
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

}
