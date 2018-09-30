package com.aliniribeiro.admin.api.common.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil implements Serializable {

    static final String CLAIM_KEY_USERNAME = "sub";
    static final String CLAIM_KEY_ROLE = "role";
    static final String CLAIM_KEY_CREATED = "created";


    /**
     * JWT é um formato de token seguro que garante a integridade dos dados trafegado. Assim a aplicação
     */
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    /**
     * Obtém o username contido no token (email).
     *
     * @param token Token que será verificado.
     * @return username do token.
     */

    public String getUsernameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * Busca a data de expiração do token.
     *
     * @param token Token que será verificado.
     * @return Data de expiração do token.
     */
    private Date getExpirationDateFromToken(String token) {
        Date date;
        try {
            Claims claims = getClaimsFromToken(token);
            date = claims.getExpiration();
        } catch (Exception e) {
            date = null;
        }
        return date;
    }

    /**
     * Realiza o parse do token JWT para extrair as informações contidas em seu
     * corpo.
     *
     * @param token Token que será verificado.
     * @return Claims com os dados do token
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * Verifica se o token está expirado
     *
     * @param token Token que será verificado
     * @return True se o token está expirado.
     */
    private boolean isTokenExpired(String token) {
        Date expirationDate = this.getExpirationDateFromToken(token);
        if (expirationDate == null) {
            return false;
        }
        return expirationDate.before(new Date());
    }

    /**
     * Gera um token de acordo com os parâmetros enviados.
     *
     * @param claims Objeto com os atributos do token
     * @return
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims).setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    /**
     * Gera uma data de expiração que será utilizada ao gerar o Token.
     *
     * @return Data gerada.
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }


    /**
     * Retorna um novo token JWT com base nos dados do usuário
     *
     * @param userDetails
     * @return
     */
    public String getToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        userDetails.getAuthorities().forEach(autorithy -> claims.put(CLAIM_KEY_ROLE, autorithy.getAuthority()));
        claims.put(CLAIM_KEY_CREATED, new Date());

        return generateToken(claims);
    }

    private boolean canTokenRefreshed(String token) {
        return !isTokenExpired(token);
    }

    /**
     *  Cria um novo token
     * @param token
     * @return
     */
    public String refreshToken(String token) {
        String refreshToken;
        try {
            Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshToken = generateToken(claims);
        } catch (Exception e) {
            refreshToken = null;
        }
        return refreshToken;
    }

    public Boolean isTokenValid(String token, UserDetails userDetails){
        JwtUser user = (JwtUser) userDetails;
        final String username = getUsernameFromToken(token);
        return username.equals(user.getUsername()) && !isTokenExpired(token);
    }
}
