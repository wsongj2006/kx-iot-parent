package com.kx.iot.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.util.StringUtil;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class JwtTokenUtils {
    public static final String TOKEN_HEADER = "token";
    public static final String TOKEN_PREFIX = "Bearer ";

    private static final String SECRET = "kanxun2019iot";
    private static final String ISS = "kanxun";

    // 角色的key
    private static final String ROLE_CLAIMS = "rol";
    private static final String SESSIONID = "sid";
    private static final String CUSTOMERID = "customerId";
    private static final String SUB_COMPANY_ID = "subCompanyId";
    private static final String SECTION_ID = "sectionId";
    private static final String USER_ID = "userId";
    private static final String TYPE = "type";
    private static final String LINE = "lineId";

    // 过期时间为7天
    private static final long EXPIRATION = 604800L;

    // 选择了记住我之后的过期时间为7天
    private static final long EXPIRATION_REMEMBER = 604800L;

    // 创建token
    public static String createToken(String userId, String username, String role, String customerId, String subCompanyId,
                                     String sectionId, String sessionId, Integer type, Long lineId) {
        long expiration = EXPIRATION;
        HashMap<String, Object> map = new HashMap<>();
        map.put(USER_ID, userId);
        map.put(ROLE_CLAIMS, role);
        map.put(SESSIONID, sessionId);
        map.put(CUSTOMERID, customerId);
        map.put(SUB_COMPANY_ID, subCompanyId);
        map.put(SECTION_ID, sectionId);
        map.put(TYPE, type);
        map.put(LINE, lineId);
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .setClaims(map)
                .setIssuer(ISS)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();
    }

    // 从token中获取用户名
    public static String getUsername(String token) {
        return getTokenBody(token).getSubject();
    }

    // 获取用户角色
    public static List<String> getUserRole(String token) {
        String roles = (String) getTokenBody(token).get(ROLE_CLAIMS);
        List<String> roleList = Arrays.asList(roles.split(","))
                .stream()
                .filter(role -> StringUtil.isNotEmpty(role)).collect(Collectors.toList());
        return roleList;
    }

    public static Long getSessionId(String token) {
        String sid = (String) getTokenBody(token).get(SESSIONID);
        return Long.parseLong(sid);
    }

    public static Long getCustomerId(String token) {
        String customerId = (String) getTokenBody(token).get(CUSTOMERID);
        if (customerId == null) {
            return null;
        }
        return Long.parseLong(customerId);
    }

    public static Long getSubCompanyId(String token) {
        String subCompanyId = (String) getTokenBody(token).get(SUB_COMPANY_ID);
        if (subCompanyId == null) {
            return null;
        }
        return Long.parseLong(subCompanyId);
    }

    public static Long getSectionId(String token) {
        String sectionId = (String) getTokenBody(token).get(SECTION_ID);
        if (sectionId == null) {
            return null;
        }
        return Long.parseLong(sectionId);
    }

    public static Long getUserId(String token) {
        String userId = (String) getTokenBody(token).get(USER_ID);
        if (userId == null) {
            return null;
        }
        return Long.parseLong(userId);
    }

    public static Integer getType(String token) {
        String type = (String) getTokenBody(token).get(TYPE);
        if (StringUtils.isEmpty(type)) {
            return null;
        }
        return Integer.parseInt(type);
    }

    public static Long getLineId(String token) {
        String lineId = (String) getTokenBody(token).get(LINE);
        if (StringUtils.isEmpty(lineId)) {
            return null;
        }
        return Long.parseLong(lineId);
    }

    // 是否已过期
    public static boolean isExpiration(String token) {
        try {
            return getTokenBody(token).getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

    private static Claims getTokenBody(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    public static boolean isSupperAdmin(String token) {
        List<String> roleList = getUserRole(token);
        if (roleList.contains("SUPPER_ADMIN")) {
            return true;
        }
        return false;
    }
}
