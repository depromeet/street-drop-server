# Street Drop - 스트릿 드랍 <a href="https://apps.apple.com/kr/app/%EC%8A%A4%ED%8A%B8%EB%A6%BF%EB%93%9C%EB%9E%8D-street-drop/id6450315928"><img src="https://github.com/siyeonSon/ReadmeImage/blob/main/street-drop-server/app-logo.png" align="left" width="100"></a>

[![Hits](https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https%3A%2F%2Fgithub.com%2Fdepromeet%2Fstreet-drop-server&count_bg=%2328DBE6&title_bg=%232D3540&icon=&icon_color=%23E7E7E7&title=hits&edge_flat=false)](https://hits.seeyoufarm.com)
[![codecov](https://codecov.io/gh/depromeet/street-drop-server/branch/dev/graph/badge.svg?token=7EHWI73ZQU)](https://codecov.io/gh/depromeet/street-drop-server)
[![Github Action](https://github.com/depromeet/street-drop-server/actions/workflows/coverage.yml/badge.svg)](https://github.com/depromeet/street-drop-server/actions)


## 📚 Quick Link
- #### 🏠 [Project Introduction](https://docs.street-drop.com/docs/service-intro)
- #### 📚 [Server Documents](https://docs.street-drop.com/)
- #### 📱 [Application App Store Download](https://apps.apple.com/kr/app/%EC%8A%A4%ED%8A%B8%EB%A6%BF%EB%93%9C%EB%9E%8D-street-drop/id6450315928)
## 💁‍♂️ Introduction
![intro-main](https://github.com/siyeonSon/ReadmeImage/blob/main/street-drop-server/intro-main.jpeg?raw=true)

![intro-description](https://github.com/siyeonSon/ReadmeImage/blob/main/street-drop-server/intro-description.jpeg?raw=true)
![home](https://github.com/siyeonSon/ReadmeImage/blob/main/street-drop-server/home.jpeg?raw=true)
![let's-go](https://github.com/siyeonSon/ReadmeImage/blob/main/street-drop-server/lets-go.jpeg?raw=true)

## 💁‍♀️ Documents
- [Software Requirement Specification](https://docs.street-drop.com/docs/software-requirement-specification/Intrduction)
- [Software Design Description](https://docs.street-drop.com/docs/software-design-description/Intrduction)
- [Software Test Specification](https://docs.street-drop.com/docs/software-test-specification/Intrduction)
- [Software Line Test Coverage Report](https://app.codecov.io/gh/depromeet/street-drop-server)
- [Street Drop Dev Blog](https://docs.street-drop.com/blog)




## 🚎 Architecture
![server-architecture](https://github.com/seonghun-dev/ReadmeImage/blob/main/src/street-drop/architecture.jpg?raw=true)
- 비용 절감을 위해서 Test(Dev), Admin 서버는 홈서버를 통해서 운영하고 있으며, Prod 서버는 서비스의 안정성을 위해서 AWS EC2를 사용하여 운영하고 있습니다.
- 네트워크 IO가 주된 작업이고, 외부 API만 연동되고 데이터 베이스에 의존성이 없는 검색 서버는 별도로 분리하여 구성하였습니다.
- 유저 레벨 업데이트, 예약 푸시 발송등을 위하여, 배치서버, 알림 서버를 분리하여 구성하였습니다.

### 🗄️ Directory Structure and Multi Module

#### 📂 Directory Structure
```
├── .github
├── backend
│   ├── streetdrop-admin  # 관리자 웹 어플리케이션
│   │   ├── streetdrop-admin-server  # 관리자 웹 API 서버
│   │   ├── streetdrop-admin-web # 관리자 웹 프론트엔드
│   │   └── streetdrop-admin-web-server # 관리자 웹 프론트엔드 정적 배포용 서버
│   ├── streetdrop-api  # API 서버
│   ├── streetdrop-batch  # 배치 서버
│   ├── streetdrop-common  # 공통 모듈
│   ├── streetdrop-domain  # 도메인 모듈
│   ├── streetdrop-notification  # 알림 서버
│   └── streetdrop-search  # 검색 서버
├── docs # 문서관리용 폴더
└── infra # 인프라 관리용 폴더 - Grafana, Prometheus, Jenkins
```
- 어드민의 경우 프론트 엔드를 Spring Boot로 정적 배포할 경우, 빌드 시간이 오래걸려 Node.js로 정적파일을 배포하고 있습니다.

#### 🧩 Multi Module

![multi-module](https://github.com/seonghun-dev/ReadmeImage/blob/main/src/street-drop/multi-module.png?raw=true)

- 멀티 모듈을 적용하여 역할에 따라 모듈을 분리하였습니다.
- Common 모듈은 Validation등의 순수 자바 코드, Domain 모듈은 엔티티 정보를 담고 있는 모듈로 구성되어 있습니다.
- 각 API, 배치, 알림, 검색 서버는 Domain 모듈을 의존성으로 가지고 있으며, 모듈간의 의존성은 상위 모듈이 하위 모듈만을 의존하도록 구성하였습니다.

### 📈 Dependency and Quick Start
#### 📦 Dependency
- 기본적인 의존성입니다. 자세한 의존성은 각 모듈별 build.gradle 파일과 문서를 참고해주세요.
  - Java 19
  - Gradle 7.6.1
  - MySQL 8.0.33
  - Spring Boot 3.0.6

#### 🚀 Quick Start
- 모듈 별로 빌드하기 위해서는 backend 디렉토리에서 `./gradlew :{모듈명}:build` 명령어를 사용합니다.
- 예를 들어, streetdrop-api 모듈을 빌드하기 위해서는 backend 디렉토리에서 `./gradlew streetdrop-api:build` 명령어를 사용합니다.
- 프로파일은 `dev, prod, local`로 구성되어 있으며, 각 환경별로 Swagger 지원, API 테스트용 헤더등이 다르게 구성되어 있으므로 적절한 프로파일을 선택해서 사용해야 합니다.


### 🖥️ Tech Stack
#### Framework - <img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=for-the-social&logo=Spring Boot&logoColor=white">  <img src="https://img.shields.io/badge/Gradle-02303A?style=for-the-social&logo=Gradle&logoColor=white">

#### ORM - <img src="https://img.shields.io/badge/Spring Data JPA-6DB33F?style=for-the-social&logo=Databricks&logoColor=white">

#### Authorization - <img src="https://img.shields.io/badge/Spring Security-6DB33F?style=for-the-social&logo=springsecurity&logoColor=white">  <img src="https://img.shields.io/badge/JSON Web Tokens-000000?style=for-the-social&logo=JSON Web Tokens&logoColor=white">

#### Test - <img src="https://img.shields.io/badge/JUnit5-25A162?style=for-the-sociak&logo=junit5&logoColor=white"> <img src="https://img.shields.io/badge/CodeCov-F01F7A?style=for-the-sociak&logo=codecov&logoColor=white">

#### Database - <img src="https://img.shields.io/badge/MySQL-4479A1.svg?style=for-the-social&logo=MySQL&logoColor=white">  <img src="https://img.shields.io/badge/MongoDB-234ea94b.svg?logo=mongodb&logoColor=white&style=for-the-social">

#### AWS - <img src ="https://img.shields.io/badge/AWS EC2-FF9900?style=for-the-social&logo=amazonec2&logoColor=white">  <img src ="https://img.shields.io/badge/AWS S3-69A31?style=for-the-social&logo=amazons3&logoColor=white">  <img src="https://img.shields.io/badge/AWS RDS-527FFF?style=for-the-social&logo=amazonrds&logoColor=white">  <img src ="https://img.shields.io/badge/AWS Cloud Watch-FF4F8B?style=for-the-social&logo=amazoncloudwatch&logoColor=white">  <img src ="https://img.shields.io/badge/AWS Lambda-F9900?style=for-the-social&logo=awslambda&logoColor=white">

#### Monitoring - <img src="https://img.shields.io/badge/Prometheus-E6522C?style=for-the-social&logo=prometheus&logoColor=white">  <img src="https://img.shields.io/badge/Grafana-F46800?style=for-the-social&logo=grafana&logoColor=white">  <img src="https://img.shields.io/badge/Slack-4A154B?style=for-the-social&logo=slack&logoColor=white">

#### Admin Web Page - <img src="https://img.shields.io/badge/React-61DAFB?style=for-the-social&logo=react&logoColor=white">  <img src="https://img.shields.io/badge/Node.js-339933?style=for-the-social&logo=node.js&logoColor=white">

#### Other - <img src="https://img.shields.io/badge/ Swagger-6DB33F?style=for-the-social&logo=swagger&logoColor=white"> <img src="https://img.shields.io/badge/Firebase Cloud Messaging-FFCA28?style=for-the-social&logo=firebase&logoColor=white">

## 📈 DataBase Schema
### MySQL Schema

![ERD](https://github.com/siyeonSon/ReadmeImage/blob/main/street-drop-server/ERD.jpeg?raw=true)


## 👥 Contributors
### 👜 Repository
- [Street Drop IOS Repository](https://github.com/depromeet/street-drop-iOS)
- [Street Drop Server Repository](https://github.com/depromeet/street-drop-server)
- [Street Drop Design Behance](https://www.behance.net/gallery/175696753/Street-Drop-Location-based-music-community-services)

### 🧑‍💻 Server Engineers
|                                                                                                                                                                                                                                                YunYoung                                                                                                                                                                                                                                                 |                                                                                         Seonghun                                                                                          |                                                                                                                                                                                                                                                                  Siyeon                                                                                                                                                                                                                                                                  |
|:-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
| <img src="https://avatars.githubusercontent.com/u/17813930?v=4" width="100" height="100" style="border-radius: 50%;"><br/><a href="https://www.linkedin.com/in/young-yun-60aa94163" target="_blank"><img src="https://img.shields.io/badge/윤_영-%230077B5.svg?style=for-the-socail&logo=linkedin&logoColor=white"/></a><br/><a href="https://github.com/yunyoung1819" target="_blank"><img src="https://img.shields.io/badge/yunyoung1819-181717?style=for-the-social&logo=github&logoColor=white"/></a> | <img src="https://avatars.githubusercontent.com/u/80201773?v=4" width="100" height="100" style="border-radius: 50%;"><br/><a href="https://www.linkedin.com/in/seonghun" target="_blank"><img src="https://img.shields.io/badge/정_성_훈-%230077B5.svg?style=for-the-social&logo=linkedin&logoColor=white"/></a><br/><a href="https://github.com/seonghun-dev" target="_blank"><img src="https://img.shields.io/badge/seonghun_dev-181717?style=for-the-social&logo=github&logoColor=white"/></a> |<img src="https://avatars.githubusercontent.com/u/87802191?s=400&u=a046b207b40b564a97db82bd2264b81727b50b77&v=4" width="100" height="100" style="border-radius: 50%;"><br/><a href="https://www.linkedin.com/in/siyeonson" target="_blank"><img src="https://img.shields.io/badge/손_시_연-%230077B5.svg?style=for-the-social&logo=linkedin&logoColor=white"/></a><br/><a href="https://github.com/siyeonSon" target="_blank"><img src="https://img.shields.io/badge/siyeonSon-181717?style=for-the-social&logo=github&logoColor=white"/></a>|