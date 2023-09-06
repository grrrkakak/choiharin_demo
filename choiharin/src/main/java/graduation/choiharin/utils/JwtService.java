/**
 * jwt 다른 방식으로 구현함(config-auth)
 * 거기서 막힐 시 이거 수정해서 사용
 * **/

/*package graduation.choiharin.utils;

import graduation.choiharin.config.*;
import graduation.choiharin.config.secret.*;
import org.springframework.security.oauth2.jose.jws.*;
import org.springframework.stereotype.*;
import org.springframework.web.context.request.*;

import javax.servlet.http.*;
import java.util.*;

import static graduation.choiharin.config.BaseResponseStatus.EMPTY_JWT;
import static graduation.choiharin.config.BaseResponseStatus.INVALID_JWT;

@Service
public class JwtService {

    *//*
    JWT 생성
    @param userIdx
    @return String
     *//*
    public String createJwt(int userIdx){
        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam("type","jwt")
                .claim("userIdx",userIdx)
                .setIssuedAt(now)
                .setExpiration(new Date(System.currentTimeMillis()+1*(1000*60*60*24*365)))
                .signWith(SignatureAlgorithm.HS256, Secret.JWT_SECRET_KEY)
                .compact();
    }

    *//*
    Header에서 X-ACCESS-TOKEN 으로 JWT 추출
    @return String
     *//*
    public String getJwt(){
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        return request.getHeader("X-ACCESS-TOKEN");
    }

    *//*
    JWT에서 userIdx 추출
    @return int
    @throws BaseException
     *//*
    public int getUserIdx() throws BaseException {
        //1. JWT 추출
        String accessToken = getJwt();
        if(accessToken == null || accessToken.length() == 0){
            throw new BaseException(EMPTY_JWT);
        }

        // 2. JWT parsing
        Jws<Claims> claims;
        try{
            claims = Jwts.parser()
                    .setSigningKey(Secret.JWT_SECRET_KEY)
                    .parseClaimsJws(accessToken);
        } catch (Exception ignored) {
            throw new BaseException(INVALID_JWT);
        }

        // 3. userIdx 추출
        return claims.getBody().get("userIdx",Integer.class);  // jwt 에서 userIdx를 추출합니다.
    }

}*/
