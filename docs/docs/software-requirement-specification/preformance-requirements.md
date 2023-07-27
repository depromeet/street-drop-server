---
title: 5. Performance Requirements (성능 요구사항)
sidebar_position: 6
---


# 5	Performance requirements (성능 요구사항)
프로젝트 목표 제품의 성능 측면의 요구사항을 기술한다. 즉, 성능 목표(가능한 수치화하여)를 도출한다.
아래 항목은 프로젝트 별로 다른 성능 지표를 도출한 경우, 이를 적용하여 수정및 추가 할 수 있다.
각자 알아서 적는다. 일반적인 성능에 대한 기술
누락된 것만 없도록 신경 써서 적는다.

[[ Performance requirements is one of the Non-Functional requirements. This section shows requirements that is applied to all Funtional Requirements. If there are differnet requirements for each function, they have to be specified in Ch7 Functional Requirements section. ]]

If there are performance requirements for the product under various circumstances, state them here and explain their rationale, to help the developers understand the intent and make suitable design choices.
Specify the timing relationships for real time systems.
Make such requirements as specific as possible. You may need to state performance requirements for individual functional requirements or features here or “Functional Requirements” section.

Specify both the static and the dynamic numerical requirements placed on the software or on human interaction with the software, as a whole.  
Static numerical requirements may include:
(a)  The number of terminals to be supported
(b)  The number of simultaneous users to be supported
(c)  Amount and type of information to be handled
Static numerical requirements are sometimes identified under a separate section entitled capacity.

Dynamic numerical requirements may include, for example, the numbers of transactions and tasks and the amount of data to be processed within certain time periods for both normal and peak workload conditions.
All of these requirements should be stated in measurable terms.
For example,
95% of the transactions shall be processed in less than 1 second
rather than,
An operator shall not have to wait for the transaction to complete.
(Note: Numerical limits applied to one specific function are normally specified as part of the processing subparagraph description of that function.)

## 5.1	Throughput (작업처리량)
일정한 시간내에 수행한 작업량을 의미한다.
통로를 통해서 데이터가 왔다 갔다 할 때는 분명히 존재한다. 고려해보아라 ..러한 환경에서는 …러한 throughput을 가진다.
예를 들어, 아이폰에서 구글에 있는 데이터를 sync하는데 1분 걸린다. 적어도 None 은 아니다. 다른 곳에 적어 놓은 곳이 있다면, ---를 보아라 라고 적는다.
## 5.2	Concurrent Session (동시 세션)
동시 처리수를 의미한다. 적어도 None 은 아니다. 다른 곳에 적어 놓은 곳이 있다면, ---를 보아라 라고 적는다.
혼자서도 여러가지의 서비스를 한다든지, 쓰레드를 여러 개 쓴다든지… 고려해 보아라

## 5.3	Response Time (대응시간)
처리 시간을 의미한다. 적어도 None 은 아니다. 다른 곳에 적어 놓은 곳이 있다면, ---를 보아라 라고 적는다.

## 5.4	Performance Dependency (성능 종속 관계)
하나 이상의 성능이 서로 종속적일 경우 연관관계를 기술한다.
## 5.5	Other Performance Requirements (기타 성능 요구사항)
메모리, 디스크 공간 요구사항, DB 최대 row수와 같은 기타 성능 관련 요구사항들을 기술한다.

### 5.5.1 	Database 요구 사항
DB의 최고 Size, limitation 등은 여기에 적고, DB의 내용, Schema 는 6.4에 적는 것이 좋다.
SecretNote의 각 개인 정보별 항목에 대한 최대 개수는 다음과 같다.

- 신용카드정보 : 200개
- 계좌 정보 : 200개
- 인터넷 정보 : 500개
- 가족 정보 : 200명

### 5.5.2 	메모리(RAM) 요구 사항
SecretNote의 안정적인 동작을 위해서는, 512Kbyte의 메모리가 확보되어야 한다.

### 5.5.3 	Flash 메모리 요구 사항
SecretNote의 데이터 저장과, resource저장등을 위하여, 1Mbyte의 flash 메모리 저장공간을 요구한다.
