---
title: 4. External Interface Requirements (외부 인터페이스 요구사항)
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
Specify the logical characteristics of each interface between the software product and the hardware components of the system.  This includes configuration characteristics. This may include the supported device types, the nature of the data and control interactions between the software and the hardware, and communication protocols to be used.
This is not a description of hardware requirements in the sense that “This program must run on a Mac with 64M of RAM”.  This section is for detailing the actual hardware devices your application will interact with and control.  For instance, if you are controlling X10 type home devices, what is the interface to those devices?

Designers should be able to look at this and know what hardware they need to worry about in the design.  Many business type applications will have no hardware interfaces.  If none, just state “The system has no hardware interface requirements”  
If you just delete sections that are not applicable, then readers do not know if:  
•	this does not apply or
•	you forgot to include the section in the first place.



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
Describe the requirements associated with any communications functions required by this product, including e-mail, web browser, network server communications protocols, electronic forms, and so on.
Define any pertinent message formatting.
Identify any communication standards that will be used, such as FTP or HTTP.

Specify any communication security or encryption issues, data transfer rates, and synchronization mechanisms.

If you are using a custom protocol to communicate between systems, then document that protocol here so designers know what to design.  If it is a standard protocol, you can reference an existing document or RFC.


## 4.6	Other Interface (기타 인터페이스)