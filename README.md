# Vibe Coding - Spring Boot Project

이 프로젝트는 Spring Boot와 PostgreSQL을 기반으로 한 사용자 관리 웹 애플리케이션입니다.

## 🚀 주요 기능
- **사용자 관리**: 사용자 목록 조회, 생성, 수정, 삭제(CRUD) 기능 제공
- **반응형 웹 UI**: Thymeleaf 템플릿 엔진을 활용한 웹 인터페이스

## 🛠 기술 스택
- **Framework**: Spring Boot 4.0.2 (Spring 7.0.3)
- **Language**: Java 21
- **Database**: PostgreSQL
- **ORM**: Spring Data JPA (Hibernate)
- **Template Engine**: Thymeleaf
- **Build Tool**: Maven
- **Lombok**: 보일러플레이트 코드 제거

## ⚙️ 환경 설정

### 1. 데이터베이스 설정 (PostgreSQL)
`doc/database.txt` 파일의 내용을 참고하여 데이터베이스와 사용자를 생성합니다.

```sql
-- PostgreSQL 접속 후 실행
CREATE USER vibe_user WITH ENCRYPTED PASSWORD 'your_password';
CREATE DATABASE vibe_db OWNER vibe_user;
```

### 2. 애플리케이션 설정
`src/main/resources` 폴더 아래의 프로파일별 설정을 확인하세요.
- `application-dev.yml`: PostgreSQL 기반 (k3s 환경 등)
- `application-local.yml`: MySQL 기반 (로컬 DB 환경)

환경 변수(`DB_PASSWORD`)를 사용하거나 실제 비밀번호로 수정할 수 있습니다.

## 🏃 실행 방법

### 로컬 환경에서 실행 (MySQL 사용)
MySQL이 로컬(3306 포트)에 실행 중이어야 하며, `vibe_db` 데이터베이스가 생성되어 있어야 합니다.

```bash
# 환경 변수 설정
export DB_PASSWORD=your_password
export SPRING_PROFILES_ACTIVE=local

# 애플리케이션 실행
./mvnw spring-boot:run
```

### 개발 환경에서 실행 (PostgreSQL 사용)
```bash
export DB_PASSWORD=your_password
export SPRING_PROFILES_ACTIVE=dev

./mvnw spring-boot:run
```

## 📂 프로젝트 구조
제공된 `GEMINI.md` 가이드에 따라 레이어드 아키텍처를 준수합니다.

```
src/main/java/com/club012/vibe/
├── controller/      # HTTP 요청 처리 (UserController)
├── service/         # 비즈니스 로직 (UserService, UserServiceImpl)
├── repository/      # DB 접근 (UserRepository)
├── domain/          # 엔티티 (User)
└── VibeCodingApplication.java # 메인 애플리케이션
```

## 📝 참고 사항
- **PostgreSQL 예약어 관련**: `User` 엔티티는 PostgreSQL의 예약어인 `user`와의 충돌을 피하기 위해 `@Table(name = "users")`를 사용하여 `users` 테이블로 매핑되어 있습니다.
- **REST 클라이언트**: `client.http` 파일을 사용하여 API 테스트를 수행할 수 있습니다.

---
© 2026 Vibe Coding Project.
