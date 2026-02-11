# GEMINI.md - Spring Boot Project Guide

## 프로젝트 개요
이 문서는 Gemini AI가 이 Spring Boot 프로젝트를 효과적으로 이해하고 작업할 수 있도록 돕는 가이드입니다.

## 프로젝트 구조

### 기본 정보
- **프레임워크**: Spring Boot [버전]
- **빌드 도구**: Maven / Gradle
- **Java 버전**: [21]
- **패키지 구조**: [com.example.project]

### 디렉토리 구조
```
src/
├── main/
│   ├── java/
│   │   └── com/example/project/
│   │       ├── config/          # 설정 클래스
│   │       ├── controller/      # REST API 컨트롤러
│   │       ├── service/         # 비즈니스 로직
│   │       ├── repository/      # 데이터 접근 계층
│   │       ├── domain/          # 엔티티 및 도메인 모델
│   │       ├── dto/             # Data Transfer Objects
│   │       ├── exception/       # 예외 처리
│   │       └── util/            # 유틸리티 클래스
│   └── resources/
│       ├── application.yml      # 애플리케이션 설정
│       ├── application-dev.yml  # 개발 환경 설정
│       ├── application-prod.yml # 운영 환경 설정
│       └── static/              # 정적 리소스
└── test/
    └── java/                    # 테스트 코드
```

## 코딩 컨벤션

### 명명 규칙
- **Controller**: `*Controller` (예: UserController)
- **Service**: `*Service` + `*ServiceImpl` (예: UserService, UserServiceImpl)
- **Repository**: `*Repository` (예: UserRepository)
- **DTO**: `*Request`, `*Response`, `*Dto` (예: UserCreateRequest, UserResponse)
- **Entity**: 명사형 (예: User, Order)

### 레이어 아키텍처 원칙
1. **Controller**: HTTP 요청/응답 처리, 유효성 검증
2. **Service**: 비즈니스 로직, 트랜잭션 관리
3. **Repository**: 데이터베이스 접근
4. **Domain**: 엔티티 및 비즈니스 규칙

### 코드 스타일
```java
// Controller 예시
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }
}

// Service 예시
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    
    @Override
    @Transactional
    public UserResponse createUser(UserCreateRequest request) {
        // 비즈니스 로직
    }
}
```

## 주요 의존성

### Core Dependencies
- Spring Web
- Spring Data JPA
- Spring Security (사용 시)
- Lombok
- Validation

### Database
- [H2/MySQL/PostgreSQL]
- Flyway/Liquibase (마이그레이션)

### Testing
- JUnit 5
- Mockito
- Spring Boot Test

## 설정 관리

### application.yml 구조
```yaml
spring:
  profiles:
    active: dev
  datasource:
    url: ${DB_URL}
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}
  jpa:
    hibernate:
      ddl-auto: validate
    show-sql: false
    properties:
      hibernate:
        format_sql: true
```

### 환경별 설정
- **dev**: 로컬 개발 환경
- **test**: 테스트 환경
- **prod**: 운영 환경

## API 설계 가이드

### REST API 규칙
- **Base URL**: `/api/v1`
- **HTTP Methods**: 
  - GET: 조회
  - POST: 생성
  - PUT/PATCH: 수정
  - DELETE: 삭제

### 응답 형식
```json
{
  "success": true,
  "data": {},
  "message": "Success",
  "timestamp": "2024-01-01T00:00:00"
}
```

### 에러 응답
```json
{
  "success": false,
  "error": {
    "code": "USER_NOT_FOUND",
    "message": "사용자를 찾을 수 없습니다.",
    "details": []
  },
  "timestamp": "2024-01-01T00:00:00"
}
```

## 예외 처리

### Global Exception Handler
```java
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFound(EntityNotFoundException e) {
        // 예외 처리 로직
    }
}
```

### 커스텀 예외
- `BusinessException`: 비즈니스 로직 예외
- `EntityNotFoundException`: 엔티티 조회 실패
- `InvalidRequestException`: 잘못된 요청

## 테스트 전략

### 단위 테스트
```java
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    
    @InjectMocks
    private UserServiceImpl userService;
    
    @Test
    void createUser_Success() {
        // given, when, then
    }
}
```

### 통합 테스트
```java
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    void getUser_Success() throws Exception {
        // 통합 테스트 로직
    }
}
```

## 보안

### 인증/인가 (Spring Security 사용 시)
- JWT 토큰 기반 인증
- Role 기반 권한 관리
- CORS 설정

### 데이터 보호
- 민감 정보 암호화
- SQL Injection 방지 (Prepared Statement)
- XSS 방지

## 성능 최적화

### JPA 최적화
- N+1 문제 해결 (fetch join, @EntityGraph)
- 적절한 캐싱 전략
- 쿼리 튜닝

### 캐싱
- Spring Cache Abstraction
- Redis (사용 시)

## 로깅

### 로깅 레벨
- **ERROR**: 심각한 오류
- **WARN**: 경고
- **INFO**: 주요 비즈니스 로직
- **DEBUG**: 디버깅 정보

### 로깅 포맷
```java
log.info("User created: userId={}, username={}", user.getId(), user.getUsername());
```

## 배포

### 빌드
```bash
# Maven
./mvnw clean package

# Gradle
./gradlew clean build
```

### 실행
```bash
java -jar -Dspring.profiles.active=prod application.jar
```

## Gemini에게 요청할 때 유용한 정보

### 코드 생성 시
- 레이어별 책임 준수
- 트랜잭션 범위 명확히
- 예외 처리 포함
- 테스트 코드 함께 작성

### 리팩토링 시
- 기존 코드 스타일 유지
- 하위 호환성 고려
- 변경 사항 명확히 설명

### 버그 수정 시
- 재현 가능한 테스트 케이스 작성
- 근본 원인 분석
- 유사한 문제 점검

## 참고 문서
- [Spring Boot 공식 문서](https://spring.io/projects/spring-boot)
- [프로젝트 위키]
- [API 명세서]

## 업데이트 이력
- 2024-XX-XX: 초기 작성

---
