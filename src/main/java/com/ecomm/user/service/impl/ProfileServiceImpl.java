package com.ecomm.user.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomm.user.dto.ProfileDto;
import com.ecomm.user.entity.Profile;
import com.ecomm.user.repository.ProfileRepository;
import com.ecomm.user.service.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService {
	
	@Autowired
	private ProfileRepository prepo;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public ProfileDto addProfile(Profile profile) {
		profile=prepo.save(profile);
		return mapper.map(profile, ProfileDto.class);
	}

	@Override
	public ProfileDto getProfileById(Integer profileId) {
		// TODO Auto-generated method stub
		return null;
	}

}
