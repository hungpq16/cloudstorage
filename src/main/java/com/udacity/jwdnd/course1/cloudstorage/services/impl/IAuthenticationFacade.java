package com.udacity.jwdnd.course1.cloudstorage.services.impl;

import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade {
	Authentication getAuthentication();
}
