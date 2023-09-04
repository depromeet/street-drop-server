---
title: 3. 환경
sidebar_position: 4
---


# 3	환경
## 3.1	운영 환경

### Client
ios 14.0 이상을 지원하는 모든 iphone에서 사용 가능합니다.


### Server
Window, Linux, Mac OS 등 다양한 운영체제에서 사용 가능하지만, 서비스 배포를 위해 **Linux 환경을 권장**합니다. 
클라우드 서비스를 이용하여 배포할 경우, AWS, GCP, Azure 등 다양한 클라우드 서비스에서 사용 가능합니다.
개발 과정에서 클라우드 환경을 고려하여 개발하였습니다. 각 서버 배포를 위한  최소 사양은  AWS EC2 t2.micro 인스턴스 기준으로 CPU 1 Core, RAM 1GB, Storage 10GB 입니다.

### Admin Web
Web Browser를 통해서 사용 가능합니다. Chrome, Safari, Firefox, Edge 등을 지원합니다.

## 3.2	제품 설치 및 설정
App Store를 통해 배포되었습니다. 아래 링크를 통해서 앱스토어에서 제품을 다운로드 받을 수 있습니다.

https://apps.apple.com/kr/app/%EC%8A%A4%ED%8A%B8%EB%A6%BF%EB%93%9C%EB%9E%8D-street-drop/id6450315928

## 3.3	배포 환경

### 3.3.1.1 IOS 배포 방법
- iOS 애플리케이션은 App Store와 Test Flight를 통해 배포됩니다.
- Test Flight를 통해서, 앱스토어에 배포되기 전에 내부 테스트를 진행합니다.
- 내부 테스트를 통과한 앱은 앱스토어에 배포합니다.

### 3.3.1.2 서버 배포 방법
**1. 프로파일(Profile) 선택**: 우선 배포환경에 따라 알맞은 프로필을 선택하고 환경 변수를 설정합니다.
- 우선적으로 해당 프로필에 알맞는 환경 변수를 설정합니다.
- 프로파일은 다음과 같이 3가지로 구분됩니다.
  - dev: 개발 환경을 위한 프로파일로, 주로 로컬 개발 머신에서 사용됩니다.
  - prod: 운영 환경을 위한 프로파일로, 실제 운영 서버에서 사용됩니다.
  - local: 로컬 환경을 위한 프로파일로, 로컬 개발 머신에서 사용됩니다.


**2. 빌드 및 패키징**: 빌드를 통해서 JAR 파일을 생성합니다.
- Gradle 7.6.1 빌드 도구를 사용하여 빌드 및 패키징됩니다.
  - `streetdrop-api` 를 빌드하기 위해서는 `backend` 디렉토리로 이동하여 다음 명령어를 실행합니다.
      ```bash
      $ ./gradlew streetdrop-api:build
      ```
  - 해당 빌드 프로세스를 통해서 JAR 파일이 생성됩니다.

**3. 프로파일 선택 및 배포** : 빌드된 JAR 파일을 배포 서버에 배포합니다.
- 배포 서버는 스프링 부트 내장 톰캣 서버를 사용합니다.
- 배포를 위해서 환경에 따른 적합한 프로파일은 선택합니다.
  - 예를 들어 `prod` 프로파일을 선택하여 배포하려면 다음과 같이 실행합니다.
    ```bash
    $ java -jar -Dspring.profiles.active=prod streetdrop-api/build/libs/streetdrop-api-0.0.1-SNAPSHOT.jar
    ```


### 3.3.2 	패치와 업데이트 방법

#### IOS 패치
1. 버그가 발생한 경우, 버그 트래킹 시스템에 등록합니다.
2. 버그 트래킹 시스템에 등록된 버그는 버그 레벨에 따라서 즉각적으로 대응합니다.
3. 즉각 대응시에는 패치 버전을 0.0.1 증가시키고 앱 스토어에 배포를 진행합니다.
4. API 응답 형식이 변경되는 경우 마이너 버전이 변경되므로, 마이너 버전으로 패치를 진행합니다.

#### Server 패치
1. 버그가 발생한 경우, 버그 트래킹 시스템과 서버 Gihtub Issue에 등록합니다.
2. 심각한 문제일 경우, Hotfix 브랜치를 생성하여 즉각적으로 대응합니다.
3. 즉각 대응시에는 패치 버전을 0.0.1 증가시키고 배포를 진행합니다.
4. API 응답 형식이 변경되는 경우 IOS도 재배포가 필요하므로, HotFix에서는 API 응답 형식을 변경하지 않습니다.

## 3.4	개발 환경
### 3.4.1 IOS 개발 환경

- 하드웨어 환경으로는 ios 빌드가 가능한 Mac 컴퓨터와 테스트를 위한 iOS 디바이스가 필요합니다.
- Swift와 UIKit을 사용하여 IOS 애플리케이션을 개발합니다.

### 3.4.2 서버 개발 환경

- 하드웨어 요구사항의 경우 아래 소프트웨어 요구사항을 만족시킬 수 있는 디바이스면 가능합니다.
- 운영 체제: JVM(Java Virtual Machine)을 실행할 수 있는 운영 체제 모두 사용할 수 있습니다.
- 웹 서버: Nginx를 활용하고 있습니다.
- 백엔드 프레임워크: 백엔드 프레임 워크로는 Spring Boot를 활용하고 있습니다. Spring MVC, Spring Data JPA, Spring Security 등을 사용합니다.
- 데이터베이스 시스템: 서비스 데이터 저장을 위해서는 MySQL 8.0.33 데이터베이스 시스템을 사용하며, 푸시 데이터 저장을 위해서는 MongoDB 5.0.2 데이터베이스 시스템을 사용합니다.


## 3.5	테스트 환경
### 3.5.1 IOS 테스트 환경
- 다양한 화면 사이즈에서 테스트 하기 위해 시뮬레이터와 실제 디바이스를 사용합니다
  - 실제 디바이스 :  iphone 8, iphone 11, iphone 12, iphone 12 pro max, iphone 12 mini, iphone 14 pro

### 3.5.2 서버 테스트 환경
- 유닛 테스트는 아래와 같은 테스트 환경에서 자동화된 테스트를 진행합니다.
  - 운영 체제 : Ubuntu Latest
  - 컴파일러 : JDK 19
  - 빌드 도구 : Gradle 7.6.1
  - 테스트 도구 : JUnit 5
  - 테스트 커버리지 도구 : JaCoCo
  - 테스트 커버리지 보고서 도구 : Codecov
  - 자동화 도구 : Github Actions

- 테스트 서버는 실제 서버와 동일한 환경에서 운영됩니다. 스테이징 서버는 비용문제로 인해서 구축하지 않습니다.
  - 운영 체제: JVM(Java Virtual Machine)을 실행할 수 있는 운영 체제 모두 사용할 수 있습니다.
  - 웹 서버: Nginx를 활용하고 있습니다.
  - 백엔드 프레임워크: 백엔드 프레임 워크로는 Spring Boot를 활용하고 있습니다. Spring MVC, Spring Data JPA, Spring Security 등을 사용합니다.
  - 데이터베이스 시스템: 서비스 데이터 저장을 위해서는 MySQL 8.0.33 데이터베이스 시스템을 사용하며, 푸시 데이터 저장을 위해서는 MongoDB 5.0.2 데이터베이스 시스템을 사용합니다.



## 3.6	 형상관리
### 3.6.1 	산출물 위치

#### 3.6.1.1.	 소스코드 위치
모든 소스코드는 기본적으로 Github를 통해서 관리됩니다.

**IOS**
- Github : https://github.com/depromeet/street-drop-iOS
- 소스 폴더 구조는 다음과 같습니다.
```
└── StreetDrop
    ├── NetworkManagerTest
    ├── StreetDrop
    │   ├── Application
    │   ├── Data
    │   ├── Network
    │   ├── Presentation
    │   ├── Resource
    │   └── Util
    └── StreetDrop.xcodeproj
```

**Server**
- Github : https://github.com/depromeet/street-drop-server
- 기본적으로 모노레포 구조로 관리됩니다. backend 폴더내에 프로젝트가 멀티 모듈 구조로 관리됩니다.
- 소스 폴더 구조는 다음과 같습니다.

```
├── .github
├── backend
│   ├── streetdrop-admin  # 관리자 웹 어플리케이션
│   │   ├── streetdrop-admin-server  # 관리자 웹 API 서버
│   │   ├── streetdrop-admin-web # 관리자 웹 프론트엔드
│   │   └── streetdrop-admin-web-server # 관리자 웹 프론트엔드 정적 배포용 서버
│   ├── streetdrop-api  # API 서버
│   ├── streetdrop-batch  # 배치 서버
│   ├── streetdrop-common  # 공통 모듈
│   ├── streetdrop-domain  # 도메인 모듈
│   ├── streetdrop-notification  # 푸시 알림 서버
│   └── streetdrop-search  # 검색 서버
├── docs # 문서관리용 폴더
└── infra # 인프라 관리용 폴더

```


#### 3.6.1.2.	 문서 위치
- 모든 문서는 현재 웹페이지와 내부적으로 사용되는 문서는 기본적으로 Notion을 통해서 관리됩니다.
- 외부 공개가 가능한 문서는 현재 웹페이지 https://docs.street-drop.com/docs 에서 확인할 수 있습니다.
### 3.6.2 빌드 환경
#### 3.6.2.1. 서버 빌드 환경
- **빌드 환경 구축 및 운영**
    - 빌드 환경은 Gradle 7.6.1 버전을 사용합니다.
    - 전체 모듈의 빌드 스크립트는 `backend` 디렉토리 하위의 `build.gradle` 파일에 정의되어 있습니다.
    - 각 모듈별 빌드 스크립트는 각 모듈 하위의 `build.gradle` 파일에 정의되어 있습니다.
    - 모듈 별로 빌드하기 위해서는 `backend` 디렉토리에서 `./gradlew :{모듈명}:build` 명령어를 사용합니다.
    - 예를 들어, streetdrop-api 모듈을 빌드하기 위해서는 `backend` 디렉토리에서 `./gradlew streetdrop-api:build` 명령어를 사용합니다.

- **빌드 서버 사양**
  - 운영 체제: 리눅스 운영 체제를 사용합니다.
  - 컴파일러: 스프링(Spring) 애플리케이션의 컴파일을 지원하는 JDK(Java Development Kit) 19가 설치되어 있어야 합니다.
  - 빌드 도구: Gradle 7.6.1 버전이 설치되어 있어야 합니다.

- **빌드 환경 자동화(추후)**
  - 빌드 및 배포 환경 자동화에 앞서서, 테스트 서버, 프로덕션 서버 환경을 분리하여 빌드 및 배포 환경을 구성합니다.
  - 각 모듈별로 빌드 및 배포가 다르게 구성되어야 합니다.
  - 빌드 스크립트는 자동화 하며, 이를 소스 코드 버전 관리 시스템(Git)을 통해서 관라힙니다.
  - Daily Build 및 Continuous Integration(CI) 도구를 통해 자동화된 빌드와 테스트를 수행합니다.
    

## 3.7	버그트래킹
- Notion으로 Bug Tracking을 진행합니다. 
- 제품 버전과 해당 버전에서 발생한 문제, 발생 시간 등을 기록하고, 크리티컬한 문제에 대해서 즉각적으로 대응할 수 있도록, 버그 레벨을 분류하고, 버그 레벨에 따라 대응 시간을 정합니다. 
- 추가적으로 버그 담당자를 지정하여, 해당 버그에 대한 해결에 대한 진행사항과 해결 여부를 확인할 수 있도록 합니다.
- 버그 레벨 및 대응 시간은 다음과 같습니다.

### 버그 레벨 및 대응 시간
- Critical: 1일 이내
- High: 1주 이내
- Medium: 2주 이내
- Low: 1달 이내
- Very Low: 1달 이상
