---
title: 1. Introduction (개요)
sidebar_position: 2
---

# 1	Introduction (개요)
## 1.1	Purpose (목표)
•	Describe the purpose of the particular SRS and specify the intended audience for the SRS.


•	Identify the product whose software requirements are specified in this document, including the revision or release number.


•	핵심을 짧게 잘 적어야 한다.


•	SRS의 목적이다.


•	이 문서는 모바일 HTS를 개발하기 위한 SRS이다. ( 이렇게 하면 안된다 )


•	너무 뻔한 이야기를 적으면 안된다.


•	CEO가 보아서 가치가 있을 이야기를 적어라.


•	이 일은 인도사람에게 외주를 주어서 개발하려는 것이다.  (예)


•	이것은 3개월간 프로토타입을 만들기 위한 것이다. (예)


•	이 문서의 독자는 외부도 포함한다(예)


•	Goals : 제품의 목적은 생략하라


•	Audience는 너무 당연한 것 빼고 중요한, 특이한 부분만 적는다.
•
•
## 1.2	Product Scope (범위)
Provide a short description of the software being specified. Relate the software to corporate goals or business strategies.
This should be an executive-level summary. Do not enumerate the whole requirements list here.

•	Identify the software product(s) to be produced by name


•	Explain what the software product(s) will, and, if necessary, will not do (더중요)


•	Describe the application of the software being specified, including relevant benefits, objectives, and goals  


•	Be consistent with similar statements in higher-level specifications if they exist


•	If a separate vision and scope document is available, refer to it rather than duplicating its contents here.


•	비즈니스 전략 : 실제 회사를 가정해도 된다. 아니면 새로운 회사를 가정해도 된다. (중요, 빠져있음)


•	임원들을 위한 summary (excutive-level summary)


•	이름이 확정되어야 한다.


•	우리는 안드로이드를 지원할것이다 보다, 윈모바일이나 블랙베리나..를 지원하지 않는다가 더 중요하다.


•	Relevant benefits : 다른 제품과 연관해서 이득. 첫 제품인 경우는 없는 것이고….


•	Goals : SRS의 목적


•	Higher-level specification 은 없다.


•	Separate vision and scope document 는 기획팀 자료..이것은 어디어디를 봐라, 참조하라…


•	지원하지 않는 것에 대해서 명시하는 것이 중요하다 ( 이유는 간단히 할 수 있으면 하고 아니면 그냥 하지 마라)



## 1.3	Document Conventions (문서규칙)
본 문서를 읽는데 필요한 기본 규칙을 기술한다.

Describe any standards or typographical conventions that were followed when writing this SRS, such as fonts or highlighting that have special significance. For example, state whether priorities  for higher-level requirements are assumed to be inherited by detailed requirements, or whether every requirement statement is to have its own priority.
이 부분은 회사의 sample을 쫒아해도 된다.

## 1.4	Terms and Abbreviations (정의 및 약어)
본 문서에서 자주 사용되는 용어에 대한 기본 정의 및 약어를 정리한다.
정의를 해야 한다.
수 십개 정도…

## 1.5	Related Documents (관련문서)
제품기획서, Onepager, SRS, IRS 등 본 프로젝트의 SRS와 관련된 문서를 기술한다.


모든 문서는 소스코드 관리 시스템에서의 파일 위치를 명시한다.


모든 문서는 프로젝트안에 있어야 하고 링크를 걸어 볼 수 있어야 한다.


문서를 링크 걸어야 한다

## 1.6	Intended Audience and Reading Suggestions (대상 및 읽는 방법)
본 문서를 리뷰 할 대상(관련 부서/팀 포함)을 명시한다.

Describe the different types of reader that the document is intended for, such as developers, project managers, marketing staff, users, testers, and documentation writers.

Describe what the rest of this SRS contains and how it is organized. Suggest a sequence for reading the document, beginning with the overview sections and proceeding through the sections that are most pertinent to each reader type.
샘플중에 적당한 것을 사용하면 된다.
어떻게 어느 정도로 읽어야 하는 지도 명시하면 좋다. 좀 자세히 읽으라든지,
왜 읽어야 하는지? ( 이런건 시험에 나오기도 한다)
표로 만드는 것은 좋다. 단, 좀 더 자세히….


## 1.7	Project Output (프로젝트 산출물)
본 프로젝트 결과물의 형태 및 버전 등에 대해 기술한다.
산출물의 형태가 제품인지 라이브러리인지 툴인지 등을 구분하여 기술하며, 산출물명(가칭) 및 그 대표 버전을 기술한다.
3장의 배포 방법과 중복이 될 수 있다.
필연적으로 중복이 될 수는 있는데 (없을 수는 없는데), 그 이유는?   ?

### 1.7.1 	Output Format (산출물 형태)
안드로이드 : aa.마
구동할 수 있는 application 이다

### 1.7.2 	Output Name and Version (산출물명(가칭) 및 버전)
Diary_ver1.0.
파일 이름에 대해 버전을 바꾸어야 하는지 아니면 안드로이드 내부 버전 관리가 되는 지…모름.