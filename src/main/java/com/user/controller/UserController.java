package com.user.controller;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.binding.UserEvent;
import com.user.repoandentity.UserEntity;
import com.user.repoandentity.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private UserRepository userRepository;
	
	private KafkaTemplate<String,UserEvent> kafkaTemplate;

	
	
	public UserController(UserRepository userRepository, KafkaTemplate<String, UserEvent> kafkaTemplate) {
		super();
		this.userRepository = userRepository;
		this.kafkaTemplate = kafkaTemplate;
	}



	@PostMapping("/create")
	public UserEntity createUser(@RequestBody UserEntity entity) {
		 UserEntity userEntity = userRepository.save(entity);
		 UserEvent userEvent =new UserEvent("userAdd",userEntity);
		kafkaTemplate.send("User-topic",userEvent);
		return userEntity;
	}
	

}
