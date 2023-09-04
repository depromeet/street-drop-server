---
title: 6. Non-Functional Requirements (기능 이외의 요구사항)(작성중)
sidebar_position: 7
---


# 6	Non-Functional Requirements (기능 이외의 요구사항)

## 6.1	Safety requirements (안전성 요구사항)
Specify those requirements that are concerned with possible loss, damage, or harm that could result from the use of the product.

Define any safeguards or actions that must be taken, as well as actions that must be prevented. Refer to any external policies or regulations that state safety issues that affect the product’s design or use.

Define any safety certifications that must be satisfied.

천제지변의 경우에 어떻게 할 것인지 명시한다.
무엇이 누락되었는지 모른다는 것이 Risk.
휴대폰을 잃어버렸을 경우, 물에 빠졌을 경우에 대한 대응  data backup 등.
## 6.2	Security Requirements (보안 요구사항)
Specify any requirements regarding security or privacy issues surrounding use of the product or protection of the data used or created by the product.
Specify the factors that would protect the software from accidental or malicious access, use, modification, destruction, or disclosure. Specific requirements in this area could include the need to:
•	Utilize certain cryptographic techniques
•	Keep specific log or history data sets
•	Assign certain functions to different modules
•	Restrict communications between some areas of the program
•	Check data integrity for critical variables

Define any user identity authentication requirements. Refer to any external policies or regulations containing security issues that affect the product. Define any security or privacy certifications that must be satisfied.

Authentication  로그인을 하면…
Confidentiality  내용을 다른 사람이 볼 수 있게 할 것이냐…아니냐..
Integration  내용을 누가 조작을 했을 때
Key 암호화 하는 부분에 대한 설명이 필요하다. 키를 어디에 놓을 것인가? 어떤 알고리즘을 사용할 것인가?
## 6.3	Software System Attributes (소프트웨어 시스템 특성)
There are a number of attributes of software that can serve as requirements.  It is important that required attributes are  specified so that their achievement can be objectively verified. Attributes may include availability, correctness, flexibility, interoperability, maintainability, portability, reliability, reusability, robustness, testability, and usability. The following items provide a partial list of examples.  These are also known as non-functional requirements or quality attributes.

These are characteristics the system must possess, but that pervade (or cross-cut) the design.  
These requirements have to be testable just like the functional requirements.  Its easy to start philosophizing here, but keep it specific.

### 6.3.1 	Availability (가용성)
Specify the factors required to guarantee a defined availability level for the entire system such as checkpoint, recovery, and restart.  This is somewhat related to reliability.  Some systems run only infrequently on-demand (like MS Word).  Some systems have to run 24/7 (like an e-commerce web site).  The required availability will greatly impact the design.  What are the requirements for system recovery from a failure?  “The system shall allow users to restart the application after failure with the loss of at most 12 characters of input”.   
지금은 별 신경 쓸 것 없을 것 같음.
유저가 사용할 때 켰다가 사용하지 않을 때 끈다.
Restart 할 때 cool boot? Hot boot?

### 6.3.2 	Maintainability (유지보수성)
Specify attributes of software that relate to the ease of maintenance of the software itself.  There may be some requirement for certain modularity, interfaces, complexity, etc.
이 제품이 유지보수가 중요한 지 아닌지..
주력 제품이라면 유지보수가 중요하게 된다.

### 6.3.3 	Portability (이식성)
Specify attributes of software that relate to the ease of porting the software to other host machines and/or operating systems.  This may include:

•	Percentage of components with host-dependent code
•	Percentage of code that is host dependent
•	Use of a proven portable language
•	Use of a particular compiler or language subset
•	Use of a particular operating system
•	Use of a particular CPU (Big Endian vs. Little Endian issue)
안드로이드를 하지만 추후 아이폰,심비안 으로 할 지 안할 지…고려..
플랫폼(OS)간의 이식성…
CPU?   Endian?   Platform? 다양한 가능성 검토할 것
### 6.3.4 	Reliability (신뢰성)
Specify the factors required to establish the required reliability of the software system at time of delivery.  If you have MTBF requirements, express them here.  This doesn’t refer to just having a program that does not crash. This has a specific engineering meaning.
수명주기가 얼마냐..일주일 동안 안죽느냐…배터리 고려(?)
### 6.3.5 	Remaining Attributes (나머지 특성)
Definitions of the quality characteristics not defined in the paragraphs above follow.
Once the relevant characteristics are selected, a subsection should be written for each, explaining the rationale for including this characteristic and how it will be tested and measured.

•	Correctness - extent to which program satisfies specifications, fulfills user’s mission objectives
•	Efficiency - amount of computing resources and code required to perform function
•	Flexibility - effort needed to modify operational program
•	Interoperability - effort needed to couple one system with another (e.g EAI, Web Services)
•	Reusability - extent to which it can be reused in another application
•	Testability - effort needed to test to ensure performs as intended
•	Usability - effort required to learn, operate, prepare input, and interpret output
필요하면 적는다…

## 6.4	Logical Database Requirements (데이터베이스 요구사항)
This section specifies the logical requirements for any information that is to be placed into a database.  This may include:

•	Types of information used by various functions
•	Frequency of use
•	Accessing capabilities
•	Data entities and their relationships
•	Integrity constraints (무결성)
•	Data retention requirements (보존 요구)

If the customer provided you with data models, those can be presented here.  ER diagrams (or static class diagrams) can be useful here to show complex data relationships.  Remember a diagram is worth a thousand words of confusing text.

RDB면 ERD로 표현하면 되는데
RDB가 아니면 데이터 구조를 설명해주어야 한다.
SQL를 사용한다…SD카드에 저장한다..등
## 6.5	Business Rules (비즈니스 규칙)
List any operating principles about the product, such as which individuals or roles can perform which functions under specific circumstances.
These are not functional requirements in themselves, but they may imply certain functional requirements to enforce the rules.

[[ Business applications like ERP and EIS have a lot of different roles to use the system. ]]

생략한다
## 6.6	Design and Implementation Constraints (설계와 구현 제한사항)
Specify design constraints that can be imposed by other standards, hardware limitations, etc.

### 6.6.1 	Standards Compliance (표준준수)
Specify the requirements derived from existing standards or regulations. They might include:
(1)  Report format
(2)  Data naming
(3)  Accounting procedures
(4)  Audit Tracing

For example, this could specify the requirement for software to trace processing activity.  Such traces are needed for some applications to meet minimum regulatory or financial standards.  An audit trace requirement may, for example, state that all changes to a payroll database must be recorded in a trace file with before and after values.

현재는 별 상관 없을 것 같음…
날짜 표시?,
### 6.6.2 	Other Constraints (기타 제한 사항)
Describe any items or issues that will limit the options available to the developers.
These might include:
1)	corporate or regulatory policies;
2)	hardware limitations (e.g. signal timing requirements, memory requirements);
3)	interfaces to other applications;
4)	specific technologies, tools, and databases to be used;
5)	parallel operations;
6)	higher-order language requirements;
7)	communications protocols;
8)	design conventions
9)	programming standards (for example, if the customer’s organization will be responsible for maintaining the delivered software).

설계와 구현상의 제약 조건이 있을 경우 기술한다. 예를 들어,
1)	사용해야 하거나 피해야 할 기술, 설계툴, 개발 툴, 개발 언어, DB 등이 있으면 기술한다.
2)	준수해야 할 개발 규칙(프로그래밍가이드, 에러코드, 빌드버전 등)이나, 표준(표준명 및 버전) 등이 있으면 기술한다.
3)	하드웨어나 운영환경상의 제약조건은 3장에서 명시한다.
      주욱 읽어보고 특별히 기술해야 할 부분이 있으면 기술한다
      모바일 같은 경우 많은 내용이 있지는 않다.
      6.7	Memory Constraints (메모리 제한 사항)
      Specify any applicable characteristics and limits on primary and secondary memory. Don’t just make up something here.  If all the customer’s machines have only 128K of RAM, then your target design has got to come in under 128K so there is an actual requirement.  
      You could also cite market research here for shrink-wrap type applications “Focus groups have determined that our target market has between 256-512M of RAM, therefore the design footprint should not exceed 256M.”  If there are no memory constraints, so state.

중요함
안드로이드 설치하는 프로그램도 크면 안되고..데이터를 적을 때 맘대로 적으면 안되고..(SD카드를 사용해야 함)

## 6.8	Operations (운영 요구사항)
Specify the normal and special operations required by the user such as:
(1)	The various modes of operations in the user organization
(2)	Periods of interactive operations and periods of unattended operations
(3)	Data processing support functions
(4)	Backup and recovery operations

(Note:  This is sometimes specified as part of the User Interfaces section.)  
If you separate this from the UI stuff earlier, then cover business process type stuff that would impact the design.  For instance, if the company brings all their systems down at midnight for data backup that might impact the design. These are all the work tasks that impact the design of an application, but which might not be located in software.

해당사항 없음
## 6.9	Site Adaptation Requirements (사이트 적용 요구사항)
In this section:
(1)	Define the requirements for any data or initialization sequences that are specific to a given site, mission, or operational mode
(2)	Specify the site or mission-related features that should be modified to adapt the software to a particular installation

If any modifications to the customer’s work area would be required by your system, then document that here.  For instance, “A 100Kw backup generator and 10000 BTU air conditioning system must be installed at the user site prior to software installation”.
This could also be software-specific like, “New data tables created for this system must be installed on the company’s existing DB server and populated prior to system activation.”  
Any equipment the customer would need to buy or any software setup that needs to be done so that your system will install and operate correctly should be documented here.

해당 사항 없음
## 6.10	Internationalization Requirements (다국어 지원 요구사항)
현재 1.X.X 버전에서는 한국어만 지원할 예정입니다.

## 6.11	Unicode Support (유니코드 지원)
현재 Street Drop은 유니코드를 지원합니다.

## 6.12	64bit Support (64비트 지원)
현재 Street Drop은 64비트를 지원합니다.

## 6.13	Certification (제품 인증)
해당 사항 없습니다.

## 6.14	Field Test (필드 테스트)
해당 사항 없습니다.
## 6.15	Other Requirements (기타 요구 사항)
해당 사항 없습니다.