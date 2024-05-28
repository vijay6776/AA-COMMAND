package com.user.binding;

import com.user.repoandentity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEvent {
	
	private String eventName;
	private UserEntity userEntity;
}
