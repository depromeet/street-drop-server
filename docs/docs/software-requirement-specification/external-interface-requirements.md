---
title: 4. External Interface Requirements (외부 인터페이스 요구사항)(작성중)
sidebar_position: 5
---

# 4	External Interface Requirements (외부 인터페이스 요구사항)
이 제품과 연결되어 있는 모든 종류의 인터페이스를 기술한다. ( 추후 작성한다 )

[[ If the external interfaces are complicated, the separate IRS can replaces this chapter. ]]

If the product is independent and totally self-contained, it should be so stated here.  If the SRS defines a product that is a component of a larger system, as frequently occurs, then this subsection relates the requirements of the larger system to functionality of the software and identifies interfaces between that system and the software.

A block diagram showing the major components of the larger system, interconnections, and external interfaces can be helpful.  This is not a design or architecture picture.  It is more to provide context, especially if your system will interact with external actors.  The system you are building should be shown as a black box.  Let the design document present the internals.

This contains a detailed description of all inputs into and outputs from the software system.

It contains both content and format as follows:

•	Name of item
•	Description of purpose
•	Source of input or destination of output
•	Valid range, accuracy and/or tolerance
•	Units of measure
•	Timing
•	Relationships to other inputs/outputs
•	Screen formats/organization
•	Window formats/organization
•	Data formats
•	Command formats
•	End messages

If the external interfaces are complicated , you may write a separate ‘Interface Requirement Specification’ document for all or any of the following interfaces.

## 4.1	System Interfaces (시스템 인터페이스)
List each system interface and identify the functionality of the software to accomplish the system requirement and the interface description to match the system. These are external systems that you have to interact with. For instance, if you are building a business application that interfaces with the existing employee payroll system, what is the API to that system that designers will need to use?

[[ Means existing systems that company currently uses. Software systems(e.g. DB) to be installed with this SRS will be explained in Software Interface section ]]

## 4.2	User Interface (사용자 인터페이스)
This is a description of how the system will interact with its users.
Describe the logical characteristics of each interface between the software product and the users. This may include sample screen images, any GUI standards or product family style guides that are to be followed, screen layout constraints, standard buttons and functions (e.g., help) that will appear on every screen, keyboard shortcuts, error message display standards, and so on.

Is there a GUI, a command line or some other type of interface?  Are there special interface requirements?  If you are designing for the general student population for instance, what is the impact of ADA (American with Disabilities Act) on your interface?

Details of the user interface design should be documented in a separate user interface specification.


## 4.3	Hardware Interface (하드웨어 인터페이스)
### 4.3.1 Client Hardware Interface (클라이언트 하드웨어 인터페이스)
- 아래와 같은 ios 디바이스의 개발 기준 해상도를 지원해야합니다.
  

| 기기 모델                           | 화면 크기 (인치) | 실제 해상도   | 개발 기준 해상도 |
|------------------------------------|-------------------|-----------------|------------------------|
| iPhone 6, 6s, 7, 8, SE2, SE3       | 4.7               | 750 x 1334       | 375 x 667              |
| iPhone 12 mini, 13 mini            | 5.4               | 1080 x 2340      | 375 x 812              |
| iPhone 6+, 6s+, 7+, 8+             | 5.5               | 1242 x 2208      | 414 x 736              |
| iPhone X, XS, 11 Pro              | 5.8               | 1125 x 2436      | 375 x 812              |
| iPhone Xr, 11                     | 6.1               | 828 x 1792       | 414 x 896              |
| iPhone 12, 12 Pro, 13, 13 Pro, 14 | 6.1               | 1170 x 2532      | 390 x 844              |
| iPhone 14 Pro                     | 6.1               | 1179 x 2556      | 393 x 852              |
| iPhone Xs Max, 11 Pro Max         | 6.5               | 1242 x 2688      | 414 x 896              |
| iPhone 12 Pro Max, 13 Pro Max, 14 Plus | 6.7         | 1284 x 2778      | 428 x 926              |
| iPhone 14 Pro Max                 | 6.7               | 1290 x 2796      | 430 x 932              |

### 4.3.2 Server Hardware Interface (서버 하드웨어 인터페이스)
이 시스템에는 하드웨어 인터페이스 요구 사항이 없습니다.

### 4.3.3 Admin Web Hardware Interface (서버 하드웨어 인터페이스)
- Admin 웹페이지의 경우, 데스크톱이나 노트북등에서의 화면사이즈를 지원합니다.
- 모바일 화면사이즈의 경우는 지원하지 않습니다.

## 4.4	Software Interface (소프트웨어 인터페이스)
Describe the connections between this product and other specific software components (name and version), including databases, operating systems, tools, libraries, and integrated commercial components. Identify the data items or messages coming into the system and going out and describe the purpose of each.

Describe the services needed and the nature of communications.
Refer to documents that describe detailed application programming interface protocols.
Identify data that will be shared across software components. If the data sharing mechanism must be implemented in a specific way (for example, use of a global data area in a multitasking operating system), specify this as an implementation constraint.

[[ This is one of the examples where a senior engineer can write better SRS than a requiremnt specialist ]]

Specify the use of other required software products and interfaces with other application systems.  For each required software product, include:
(1)	Name
(2)	Mnemonic (약식코드)
(3)	Specification number
(4)	Version number
(5)	Source
For each interface, provide:
(1)	Discussion of the purpose of the interfacing software as related to this software product
(2)	Definition of the interface in terms of message content and format

Here we document the APIs, versions of software that we do not have to write, but that our system has to use.  For instance if your  customer uses SQL Server 7 and you are required to use that, then you need to specify i.e.
‘Microsoft SQL Server 7’.  The system must use SQL Server as its database component.  Communication with the DB is through ODBC connections.  The system must provide SQL data table definintions to be provided to the company DBA for setup. A key point to remember is that you do NOT want to specify software here that you think would be good to use.  This is only for customer-specified systems that you have to interact with.  Choosing SQL Server 7 as a DB without a customer requirement is a Design choice, not a requirement. This is a subtle but important point to writing good requirements and not over-constraining the design.


## 4.5	Communication Interface (통신 인터페이스)

### 4.5.1 클라이언트 - 서버 통신
**통신 프로토콜**: 제품은 클라이언트와 API 서버 간의 통신에 HTTPS 프로토콜을 사용합니다. 특히 RESTful API를 구현하여 자원을 표현하고 관리합니다.
- Referecne : https://datatracker.ietf.org/doc/html/rfc2818

**메시지 포맷**: API 요청 및 응답은 JSON (JavaScript Object Notation) 형식으로 구성됩니다. 요청 및 응답 메시지는 가독성과 확장성을 고려하여 명확하게 정의되어야 합니다.

**통신 보안**: 데이터의 기밀성과 무결성을 보장하기 위해 TLS (Transport Layer Security)를 사용하여 통신을 암호화합니다.


### 4.5.2 서버 - 서버 통신
**통신 프로토콜**: 서버와 서버 통신간에는 Http 프로토콜을 사용하거나 Message Queue를 사용하여 비동기 통신을 할 수 있습니다. 구현 난이도에 따라서, 알파버전에서는 Https 프로토콜을 사용하여 통신을 하고, 베타버전에서는 Message Queue를 사용하여 통신을 할 수 있습니다.
