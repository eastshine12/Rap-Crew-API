# Rap-Crew-API
>힙합 뮤지션들을 위한 크루원 구인 커뮤니티

<br>

## 제작 기간
2023년 2월 ~ 2023년 5월 <br>
개인 프로젝트
<br>
###### Front 구현 repository는 https://github.com/eastshine12/Rap-Crew 

<br>

## 사용 기술
  - Java 11
  - Spring Boot 2.7.8
  - Gradle
  - Spring Data JPA
  - MariaDB 10.6
  - Spring Security


<br>

## ERD 설계

<img src="./src/main/resources/static/rap-crew erd.png">

(원본 크게 보기)
https://www.erdcloud.com/d/9xGFvmZNu34GasP86


<br><br>

## 기능 구현 내용
1. Web Server에서 DB에 접근하기 위한 RestAPI 구현
```
@RequestMapping(path = "/api/user")
@RestController
public class UserApiController {

    private final UserService userService;

    @GetMapping("")
    public UserDto getUser(@RequestBody UserDto userDto) {

        User user = userService.findUser(userDto.getLoginId());
        // ...
        return userDto;

    }
}
```
<br>
   
2. Spring Data JPA를 활용하여 회원가입과 글 작성 API를 구현. 글 조회 시 발생하는 N+1 문제를 해결하기 위해 Join Fetch를 적용하여 조회 성능 개선

```
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLoginId(String loginId);

}
```

```
public List<Article> findArticle() {
    return em.createQuery(
            "select a from Article a" +
                    " join fetch a.user u", Article.class).getResultList();
}
```
<br>

3. Spring Security를 활용한 JWT 인증을 구현하여 사용자 인증과 권한을 부여

```
public class JwtTokenProvider {

    @Value("${spring.jwt.secret}")
    private String secretKey;

    @Value("${spring.jwt.expirationMs}")
    private int jwtExpirationMs;

    private final UserDetailsService userDetailsService;

    @PostConstruct
    protected void init() {
        log.info("[init] JwtTokenProvider secretKey 초기화");

        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String userId, List<String> userRoles) {
        log.info("[createToken] JwtTokenProvider token 생성");

        Claims claims = Jwts.claims().setSubject(userId);
        claims.put("auth", userRoles);

        Date now = new Date();
        Date validity = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

// ...

}
```
<br>
   
4. CORS를 적용하여 다른 도메인에서의 요청에 대해 제한적으로 접근하도록 설정
```
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // 적용할 URL 패턴을 지정.
                .allowedOrigins("http://localhost:8082") // 허용할 오리진을 지정.
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 HTTP 메서드를 지정.
                .maxAge(3600); // 캐시 유효 시간을 지정.
    }
}
```

