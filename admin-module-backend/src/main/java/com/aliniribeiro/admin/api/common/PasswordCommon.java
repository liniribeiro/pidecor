package com.aliniribeiro.admin.api.common;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordCommon {

	/**
	 * Método responsável por gerar um hash da senha utilizandono BCrypt
	 * 
	 * @param password senha que será encriptografada
	 * @return hash com a senha encriptografada
	 */
	public static String generateBcrypt(String password) {
		if (password == null) {
			return password;
		}

		BCryptPasswordEncoder bCEncoder = new BCryptPasswordEncoder();
		return bCEncoder.encode(password);
	}
	
	/**
	 * Método que valida a senha informada com a senha encriptografada.
	 * @param password senha para ser validada
	 * @param encodedPassword senha encriptografada.
	 * @return 
	 */
	public static boolean validatePassword(String password, String encodedPassword) {
		BCryptPasswordEncoder bCEncoder = new BCryptPasswordEncoder();
		return bCEncoder.matches(password, encodedPassword);
	}
}
