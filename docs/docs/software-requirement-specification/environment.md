---
title: 3. Environment (환경)
sidebar_position: 4
---


# 3	Environment (환경)
샘플을 보고 해도 된다.
## 3.1	Operating Environment (운영 환경)
본 프로젝트의 산출물을 설치하고 운영하기 위한 하드웨어 환경 정보와 소프트웨어 환경 정보(OS 및 사전에 설치되어야 할 소프트웨어 등)를 기술한다.

### 3.1.1 	Hardware Environment (하드웨어 환경)
안드로이드는 하드웨어 환경이 여러 가지이다.
CPU는 생략해도 된다.
버전 정보와 제품 이름 정도…
### 3.1.2 	Software Environment (소프트웨어 환경)
안드로이드는 여러 가지이다. 리눅스, 윈도우…DB….
제품이 사용되어질 환경
OS 버전, 제품 버전. 등이 많아 진다.

#### 3.1.2.1 OS Environment (운영체제 환경)
본 프로젝트의 산출물이 지원하는 OS를 확인하기 위해 전사적으로 최신 OS 목록을 항상 가지고 있어야 한다.

지원하는 플랫폼에 따라 내용을 확인하고, 필요에 따라
(1) 아래 표와 같이 복사하고, 지원여부란을 만들어 체크하거나,
(2) 지원하는 플랫폼 리스트만 기술하시기 바랍니다.

1) Windows 플랫폼

[아래표는 샘플입니다. 반드시, 최신 OS Coverage Sheet를 받아서 사용하시기 바랍니다.]


2) Unix/Linux 플랫폼
   최신 OS Coverage Sheet를 참조하여 작성하시기 바랍니다.

3.1.2.2 OS외 software 환경

1) Web Browser

- MS Internet Explorer 버전 (필수 지원)
- Mozilla Firefox 버전 (필수 지원)

2) 기타1

3) 기타2


## 3.2	Product Installation and Configuration (제품 설치 및 설정)
본 프로젝트의 산출물의 설치 과정에서의 요구사항을 기술한다. 또한 제품을 실행하는데 필요한 기본 설정 요소 및 방법에 대한 요구사항을 기술한다.

High Level로 기술하면 된다. (간단하게)
제품을 어디에 올려서 어디서 다운로드 받아서 사용한다..등..

InstallShield와 같은 상용 설치툴 혹은  자체 개발한 설치툴이 있으면 기술한다.

6장의 Site Adaptation Requirements와 중복되는 부분은 명시하지 않는다.


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
### 3.3.2 	Distribution Method (배포 방법)
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
#### 3.6.1.2.	 Location of Documents (문서 위치)
### 3.6.2 	Build Environment (빌드 환경)
빌드머신 등의 빌드 환경을 어떻게 구축/운영할지 Build/Release 팀과 협의하여 기술한다.
빌드 기계, 빌드 디렉토리, 특수하게 요구되는 빌드방법 등을 기술한다.
빌드 환경은 자동화 되어야 한다. 스크립트를 만들어서 데일리 빌드도 하고..
빌드 스크립트가 어디 있고..머..그런 정보.  SVN에 build 디렉토리 안에….
빌드 서버의 사양, OS, compiler 등을 명시


## 3.7	Bugtrack System (버그트래킹)
이 제품의 유지보수를 위해 사용할 버그트래킹 시스템과 버그트래킹에서 사용될 제품이름을 명시한다.
구글에서 한다
버그트래킹은 언제 시작하는가? SRS를 처음 적고 이것에 오류가 있을 때, 버그트래킹을 사용한다.
보통 빌드.릴리즈 부서가 담당하는 경우가 많다.
어느 정도 stable 한 버전이 나와야 의미있는 버그가 나온다. 알파버전?...
## 3.8	Other Environment (기타 환경)
