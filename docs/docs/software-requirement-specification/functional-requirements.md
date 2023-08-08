---
title: 7. Functional Requirements (기능 요구사항)
sidebar_position: 8
---


# 7	Functional Requirements (기능요구사항)
2장에서 설명되었던 제품 주요 기능을 상세하게 분류하고, 설명한다.
각 기능을 구분 가능하도록 개별번호를 붙인다.
WBS의 각 기능항목은 작업량이 1~2일 정도로 산정 가능하도록 세분하여 작성한다.
아무렇게 개인이 가장 익숙한 방법으로 기술하라
Text, UML…무엇이든지..
일단 큰 제목부터 적어라.

Instruction to organizing the functional requirements

For anything but trivial systems the detailed requirements tend to be extensive. For this reason, it is recommended
that careful consideration be given to organizing these in a manner optimal for understanding.
There is no one optimal organization for all systems.

1. System mode
   Some systems behave quite differently depending on the mode of operation. For example, a control system
   may have different sets of functions depending on its mode: training, normal, or emergency. The choice depends on whether interfaces and performance are dependent on mode.

2. User class
   Some systems provide different sets of functions to different classes of users. For example, an elevator
   control system presents different capabilities to passengers, maintenance workers, and fire fighters.

3. Objects
   Objects are real-world entities that have a counterpart within the system. For example, in a patient monitoring
   system, objects include patients, sensors, nurses, rooms, physicians, medicines, etc. Associated with
   each object is a set of attributes (of that object) and functions (performed by that object). These functions are
   also called services, methods, or processes. When organizing this section by object, Note that sets of objects may share attributes and services. These are grouped together as classes.

4. Feature
   A feature is an externally desired service by the system that may require a sequence of inputs to effect the
   desired result. For example, in a telephone system, features include local call, call forwarding, and conference
   call. Each feature is generally described in a sequence of stimulus-response pairs.

5. Stimulus
   Some systems can be best organized by describing their functions in terms of stimuli. For example, the functions
   of an automatic aircraft landing system may be organized into sections for loss of power, wind shear,
   sudden change in roll, vertical velocity excessive, etc.

6. Response
   Some systems can be best organized by describing all the functions in support of the generation of a
   response. For example, the functions of a personnel system may be organized into sections corresponding to
   all functions associated with generating paychecks, all functions associated with generating a current list of
   employees, etc.

7. Functional hierarchy
   When none of the above organizational schemes prove helpful, the overall functionality can be organized
   into a hierarchy of functions organized by either common inputs, common outputs, or common internal data
   access. Data Flow diagrams and data dictionaries can be used to show the relationships between and among
   the functions and data.


## 7.1	대분류 기능1
### 7.1.1 	 ……
### 7.1.2 
### 7.1.3 	……
### 7.1.4 	……


## 7.2	 대분류 기능2
## 7.3	 대분류 기능3
…… 
