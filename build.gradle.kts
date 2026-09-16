plugins {
	java
	// 17 버전과 가장 잘 맞는 안정적인 최신 3.x 버전으로 변경합니다.
	id("org.springframework.boot") version "3.4.3"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		// PC에 설치된 Java 17을 그대로 사용합니다.
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
    // 1. 네이버 클라우드 메이븐 미러 (가장 안정적이고 빠름)
    maven(url = "https://naver.com")
    
    // 2. 카카오 넥서스 정식 미러 주소
    maven(url = "https://kakao.com")
    
    // 3. 기존 기본 저장소 (위의 국내 미러에 없는 파일이 있을 경우를 대비한 백업)
    mavenCentral()
    gradlePluginPortal() // 그래들 플러그인 포털 추가
    google()
}


dependencies {


	// 0. 
	    // 1. JPA (@Entity 에러 해결)
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // 2. Lombok (@Getter, @Setter 에러 해결)
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // 3. MariaDB 드라이버
    //runtimeOnly("org.mariadb.jdbc:mariadb-java-client")

	// 1. 기본 및 웹 기능 (React와 API 통신을 위해 필수)
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web")

	// 2. 데이터베이스 연결 (MariaDB & JPA)
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.mariadb.jdbc:mariadb-java-client")

	// 3. 테스트 관련 도구
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

// 소스 수정 시 자동 서버 재시작을 도와주는 도구
	developmentOnly("org.springframework.boot:spring-boot-devtools")


    // 🆕 유효성 검증(Validation) 라이브러리 추가
    implementation("org.springframework.boot:spring-boot-starter-validation")




}

tasks.withType<Test> {
	useJUnitPlatform()
}