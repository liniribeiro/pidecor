package com.aliniribeiro.admin.api.common.security.jwt;

import com.aliniribeiro.admin.api.model.user.UserEntity;
import com.aliniribeiro.admin.api.model.user.enums.UserProfile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class JwtUserFactory {

	private JwtUserFactory() {

	}

	/**
	 * Converte e gera um Usuário com base nos dados do usuário
	 * 
	 * @param user
	 * @return
	 */
	public static JwtUser create(UserEntity user) {
		return new JwtUser(user.getId() , user.getEmail(), user.getPassword(),
				mapToGrantedAuthorities(user.getProfile()));
	}

	/**
	 * Converte o perfil do usuário para o formato utilizado no Spring Security
	 * 
	 * @return uma lista de GrantedAuthority
	 */
	private static List<GrantedAuthority> mapToGrantedAuthorities(UserProfile profile) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(profile.toString()));
		return authorities;
	}

}
