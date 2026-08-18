package com.ecomm.user.service;

import java.time.LocalDate;

import com.ecomm.user.dto.ProfileDto;
import com.ecomm.user.entity.Profile;

import jakarta.persistence.Column;

public interface ProfileService {
	
	public ProfileDto addProfile(Profile profile);
	
	public ProfileDto getProfileById(Integer profileId);
	
}
