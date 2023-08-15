---
title: 3. Environment (환경)
sidebar_position: 4
---


# 3	Environment (환경)
## 3.1	Operating Environment (운영 환경)

### 3.1.1 	Hardware Environment (하드웨어 환경)
지원하는 IOS 디바이스 기종은 아래와 같습니다.

Mac, iPhone, iPod touch



### 3.1.2 	Software Environment (소프트웨어 환경)

#### 3.1.2.1 OS Environment (운영체제 환경)

#### IOS 

| 디바이스         | 최소 요구 사항            |
|-----------------|--------------------------|
| Mac             | macOS 11.0 이상, Apple M1 칩 이상 탑재 |
| iPhone          | iOS 14.0 이상 필요        |
| iPod touch      | iOS 14.0 이상 필요        |


#### Server
Window, Linux, Mac OS 등 다양한 운영체제에서 사용 가능하지만, 서비스 배포를 위해 **Linux 환경을 권장**합니다. 
클라우드 서비스를 이용하여 배포할 경우, AWS, GCP, Azure 등 다양한 클라우드 서비스에서 사용 가능합니다.
개발 과정에서 클라우드 환경을 고려하여 개발하였습니다.

배포를 위한 최소 사양은 다음과 같습니다.

| Server               | CPU      | RAM     | Storage |
|----------------------|---------|---------|---------|
| API Server           | 1 Core  | 1GB     | 10GB    |
| Search Server        | 1 Core  | 1GB     | 10GB    |
| Notification Server  | 1 Core  | 1GB     | 10GB    |
| Database Server      | 1 Core  | 1GB     | 10GB    |
| Batch Server         | 1 Core  | 1GB     | 10GB    |


## 3.2	Product Installation and Configuration (제품 설치 및 설정)
App Store를 통해 배포되었습니다. 아래 링크를 통해서 앱스토어에서 제품을 다운로드 받을 수 있습니다.

https://apps.apple.com/kr/app/%EC%8A%A4%ED%8A%B8%EB%A6%BF%EB%93%9C%EB%9E%8D-street-drop/id6450315928

## 3.3	Distribution Environment (배포 환경)
### 3.3.1 	Master Configuration (마스터 구성)
본 프로젝트의 산출물 마스터를 어떤 형태로 구성할 것인지를 기술한다. 외적인 구성 형태 및 마스터 내부 구성 형태를 미리 고려한다.

예를 들어, 외적인 구성은 CD로 구성할지, 두 개의 CD로 구성할지 등을 결정한다.
예를 들어, Windows의 경우 마스터의 내부 구성은 일반적으로 다음과 같이 구성 할 수 있다(제품마다 다름).
•	Disk 1 (압축파일 폴더)
•	Autorun.inf (CD 자동 실행)
•	install.exe (설치)
•	remove.ini (제거 정보)
•	Acrobat (Acrobat reader 프로그램 폴더)
•	Manual (매뉴얼 폴더)

스크립트도 포함되어야 하고, apk 파일,
### 3.3.2 	Distribution Method (배포 방법
#### IOS

| 디바이스         | 최소 요구 사항            |
|-----------------|--------------------------|
| Mac             | macOS 11.0 이상, Apple M1 칩 이상 탑재 |
| iPhone          | iOS 14.0 이상 필요        |
| iPod touch      | iOS 14.0 이상 필요        |


#### Server
Window, Linux, Mac OS 등 다양한 운영체제에서 사용 가능하지만, 서비스 배포를 위해 **Linux 환경을 권장**합니다.
클라우드 서비스를 이용하여 배포할 경우, AWS, GCP, Azure 등 다양한 클라우드 서비스에서 사용 가능합니다.
개발 과정에서 클라우드 환경을 고려하여 개발하였습니다.

배포를 위한 최소 사양은 다음과 같습니다.
본 프로젝트의 산출물 마스터를 어떤 방법으로 배포할 것인지를 기술한다.
예를 들어, CD로 전달한다든지, 소프트웨어를 웹에서 다운로드 받게 한다든지 등의 배포 방법이 있다.
간단하게 적는다
iTunes AppStore를 통해 배포한다. 배포 전에 iTunes Connect를 통해 Application 등록 과정 절차를 따른다.

### 3.3.3 	Patch/Update Method (패치와 업데이트 방법)
배포 이후, 제품 패치와 데이터나 구성 파일 업데이트등의 업데이트 방법 및 환경을 기술한다.
패치는 통째로 해야 한다는 정보를 제공해 준다
## 3.4	Development Environment (개발 환경)
본 프로젝트의 산출물을 개발하기 위한 하드웨어 환경 정보와 소프트웨어 환경 정보를 기술한다.

### 3.4.1 	Hardware Environment (하드웨어 환경)
### 3.4.2 	Software Environment (소프트웨어 환경)
환경에 대한 버전도 적는 것이 좋다.

## 3.5	Test Environment (테스트 환경)
본 프로젝트의 산출물을 설치하고 테스트하기 위한 하드웨어 환경 정보와 소프트웨어 환경 정보(OS 및 사전에 설치되어야 할 소프트웨어 등)를 기술한다.

### 3.5.1 	Hardware Environment (하드웨어 환경)
### 3.5.2 	Software Environment (소프트웨어 환경)

## 3.6	 Configuration Management (형상관리)
### 3.6.1 	Location of Outputs (산출물 위치)
형상관리 서버상의 본 프로젝트의 소스 위치 및 문서 위치를 명시한다.
구글을 사용한다
#### 3.6.1.1.	 Location of Source Code (소스코드 위치)
모든 소스코드는 Github를 통해서 관리됩니다.

**IOS**



**Server**
- 모노레포 구조로 관리됩니다.
- 소스 폴더 구조는 다음과 같습니다.
```
.
├── README.md
├── api
```

#### 3.6.1.2.	 Location of Documents (문서 위치)
- 모든 문서는 현재 웹페이지와 내부적으로 사용되는 문서는 모두 Notion을 통해서 관리됩니다.

### 3.6.2 	Build Environment (빌드 환경)
빌드머신 등의 빌드 환경을 어떻게 구축/운영할지 Build/Release 팀과 협의하여 기술한다.
빌드 기계, 빌드 디렉토리, 특수하게 요구되는 빌드방법 등을 기술한다.
빌드 환경은 자동화 되어야 한다. 스크립트를 만들어서 데일리 빌드도 하고..
빌드 스크립트가 어디 있고..머..그런 정보.  SVN에 build 디렉토리 안에….
빌드 서버의 사양, OS, compiler 등을 명시


빌드와 배포의 경우 




## 3.7	Bugtrack System (버그트래킹)
Notion으로 Bug Tracking을 진행합니다. 제 품 버전과 해당 버전에서 발생한 문제, 발생 시간 등을 기록하고, 크리티컬한 문제에 대해서 즉각적으로 대응할 수 있도록, 
버그 레벨을 분류하고, 버그 레벨에 따라 대응 시간을 정합니다. 추가적으로 버그 담당자를 지정하여, 해당 버그에 대한 해결에 대한 진행사항과 해결 여부를 확인할 수 있도록 합니다.

**버그 레벨**
- Critical: 1일 이내
- High: 1주 이내
- Medium: 2주 이내
- Low: 1달 이내
- Very Low: 1달 이상



## 3.8	Other Environment (기타 환경)
