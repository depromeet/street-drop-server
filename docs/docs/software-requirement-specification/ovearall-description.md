---
title: 2. Overall Description (전체 설명)
sidebar_position: 3
---
# 2	Overall Description (전체 설명)
Street Drop은 위치 기반 음악 공유 소셜 서비스로서, 사용자들이 음악을 공유하고 다른 사용자들과 상호작용할 수 있는 플랫폼을 제공합니다. 

이 서비스는 대한민국 내에서 사용 가능하며, 추후에는 해외 서비스 제공을 확장할 수 있습니다. 

애플리케이션은 iOS 디바이스 중 아이폰만을 지원하며, 안드로이드 기기는 현재 지원하지 않습니다.

## 2.1	Product Perspective (제품 조망)
본 프로젝트 산출물과 회사의 기존 제품(또는 신규제품)과의 관계 및 연관성에 대해 기술한다.
큰 시스템중의 일부분이면 큰 시스템과의 인터페이스를 기술한다.
외부에서 본 전체적인 관점에서 보는 시스템을 인터페이스와 함께 간단한 다이어그램으로 그리는 것이 유용하다.
자세한 인터페이스는 4장의 External Interface Requirements 에서 기술한다.
시장에서의 경쟁자와의 비교를 간단히 기술한다. 연구를 위한 제품을 만든다면 관련된 연구를 언급하라.
안쪽에는 관심이 없다.
주위 환경을 본다 주위 환경에 관련된 모든 것을 빠짐 없이 그린다. (사람,데이터베이스..)
여기에 있는 것들을 4장 External interface 에서 자세히 설명하는 것이다.
Simple Stock 팀의 문서를 참조하라

외부 인터페이스
DB
사용자


Describe the context and origin of the product. For example, state whether this product is a follow-on member of a product family, a replacement for certain existing systems, or a new, self-contained product.

If the SRS defines a component of a larger system, relate the requirements of the larger system to the functionality of this software and identify interfaces between the two.

A simple diagram that shows the major components of the overall system, subsystem interconnections, and external interfaces can be helpful.

If you are building a real system, compare its similarity and differences to other systems in the marketplace.  
If you are doing a research-oriented project, state what related research compares to the system you are planning to build.

## 2.2	Overall System Configuration (전체 시스템 구성)
본 프로젝트 산출물의 전체 시스템 구성도를 묘사한다. 내부의 관점에서 주요 Component를 도출하고 연관관계를 그린다.
2.1과 동일하나 제품의 사이즈가 커진다.
제품 내부의 컴포넌트를 그린다.
컴포넌트와 외부간의 연결을 그린다.
컴포넌트간의 연결을 그린다.
컴포넌트는 3~7개정도가 적당
공통으로 사용하는 라이브러리
컴포넌트 : user handler / process manager / ….
만일 내부 컴포넌트간에 interface를 정의해야 할 것 같으면 내부 sub SRS를 만들면 된다.
SRS에서 중요한 것은 external interface이다.
여기서 컴포넌트를 잘 정의해 놓아야 좋다.

## 2.3	Overall Operation (전체 동작방식)
본 프로젝트 산출물의 전체 시스템 구성도를 기준으로 동작 원리 및 시나리오 등을 기술한다.
기본적인 시나리오에 대한 컴포넌트 흐름도 정도는 그려야 한다. (시나리오 몇 개)
시나리오를 설명할 때 컴포넌트가 적어도 한번은 등장해야 한다.
컴포넌트간의 인터페이스 가 명시되어야 하고

## 2.4	Product Functions (제품 주요 기능)
Street Drop은 다음과 같은 기능을 제공합니다.

1) 음악 공유: 사용자들은 자신이 좋아하는 음악을 애플리케이션에 업로드하고, 위치 정보를 기반으로 공유할 수 있습니다.

2) 위치 기반 서비스: 애플리케이션은 사용자의 현재 위치 정보를 활용하여 주변의 다른 사용자들이 공유한 음악을 확인하고, 주변 사용자들과 상호작용할 수 있는 서비스를 제공합니다.

3) 소셜 기능: 사용자들은 음악에 대한 평가와 댓글을 남기며, 다른 사용자들과 소통하고 커뮤니케이션할 수 있습니다.


## 2.5	User Classes and Characteristics (사용자 계층과 특징)
본 프로젝트 산출물을 사용할 계층과 각각의 특징에 대해 기술한다.

Identify the various user classes that you anticipate will use this product. User classes may be differentiated based on frequency of use, subset of product functions used, technical expertise, security or privilege levels, educational level, or experience.

Describe the pertinent characteristics of each user class. Certain requirements may pertain only to certain user classes. Distinguish the most important user classes for this product from those who are less important to satisfy.
사용자 계층은 아주 많다. 사용자, 설치기사..기술지원…

## 2.6	Assumptions and Dependencies (가정과 종속 관계)
본 프로젝트를 수행하기 위해서 필요하거나, 반드시 수행 또는 결정되어야 할 전제조건 또는 선행되어야 할 프로젝트에 대해 기술하며, 그 결과에 따라 본 프로젝트의 어떤 부분에 어떻게 영향을 미칠지를 기술한다. 또한, 통제불가능한 외부요소에 의해 영향을 받을 수 있는 경우, 그 요소에 대해 기술한다.
이것은 PM에게 가장 중요하다  Risk management 를 해야 하기 때문이다. 계속해서 관리해야 한다.
프로젝트가 딜레이 된다면, 여기에 있는 가정이 원인이 되어야 한다. 그렇지 않으면 잘 못 적힌 것이다.
천재지변은 안 적는다.

List any assumed factors (as opposed to known facts) that could affect the requirements stated in the SRS. These could include third-party or commercial components that you plan to use, issues around the development or operating environment, or constraints.

The project could be affected if these assumptions are incorrect, are not shared, or change. Also identify any dependencies the project has on external factors, such as software components that you intend to reuse from another project, unless they are already documented elsewhere (for example, in the vision and scope document or the project plan).

## 2.7	Apportioning of Requirements (단계별 요구사항)
Identify requirements that may be delayed until future versions of the system.  After you look at the project plan and hours available, you may realize that you just cannot get everything done.

This section divides the requirements into different sections for development and delivery.  Remember to check with the customer – they should prioritize the requirements and decide what does and does not get done.

This can also be useful if you are using an iterative life cycle model to specify which requirements will map to which interation.

이 프로젝트 안에서 단계적으로 하겠다고 가능하다.
3번 돌리겠다?  (알파,베파,알씨)를 3번 돌리는 것이 iterative 이다.

미래 제품에 대해 적어야 한다.
왜냐하면 아키텍처에 대한 대비, 확장성, 호환성 등을 준비해야 하니까..

## 2.8	Backward compatibility (하위 호환성)
신규 개발이 아닌 경우, 기존 산출물과의 호환성을 보장하기 위한 방법 및 Migration 방법을 기술한다.
None : 생각을 해봐야 하는데, 생각을 해봤더니 안할 꺼야…안하는게 좋아…
NA(None Applicable) : 해당사항 없음, 생각할 필요조차 없다.

벤처에서 새로운 제품을 만드는 경우, 호환성 : NA
MS 모바일 7.0에 6.5어플이 안돌아간다 : 호환성 : None

안드로이드를 무슨 버전으로 해야 할지에 대한 명시가 되어야 한다.
안드로이드의 스펙을 보아야 호환성에 대한 이야기를 할 수 있다.
외부 제품으로 나갔을 때 의미가 있음.

우리는 3.0을 개발하는 것을 기준으로 하면
4.0은 없다, 개발하지 않겠다..
혹은, 4.0에 대한 계획…을 대충 가상으로 적어라 ( 시간,일정에 대한 명시 해야 함. 1년뒤, 6개월뒤) 
