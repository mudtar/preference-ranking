/*
*******************************************************************************************
CIS 234A, Spring, 2016
Team Name: FuledByJava
Team Member:Burton Ian, Kristiansen Eric, Li Chunwei, Tan BeeYean
2016.04.10 Chunwei Li     Inital -  Create tables, Insert data for test, Display test data.
*******************************************************************************************
*/

USE "234a_FueledByJava";

GO

Print ' 
==============================================================
Team: FuledByJava
Member:Burton Ian, Kristiansen Eric, Li Chunwei, Tan BeeYean
Create tables for FueldByJava.
Insert data for test.
Display test data
==============================================================' + CHAR(10);

IF EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES 
    WHERE TABLE_NAME = N'FBJ_TEST_ITEM')
BEGIN
	PRINT 'DROP TABLE FBJ_TEST_ITEM'
	DROP TABLE FBJ_TEST_ITEM
END

IF EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES 
    WHERE TABLE_NAME = N'FBJ_RESULT')
BEGIN
	PRINT 'DROP TABLE FBJ_RESULT'
	DROP TABLE FBJ_RESULT
END


IF EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES 
    WHERE TABLE_NAME = N'FBJ_TEST')
BEGIN
	PRINT 'DROP TABLE FBJ_TEST'
	DROP TABLE FBJ_TEST
END

IF EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES 
    WHERE TABLE_NAME = N'FBJ_USER')
BEGIN
	PRINT 'DROP TABLE FBJ_USER'
	DROP TABLE FBJ_USER
END

IF EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES 
    WHERE TABLE_NAME = N'FBJ_ITEM')
BEGIN
	PRINT 'DROP TABLE FBJ_ITEM'
	DROP TABLE FBJ_ITEM
END

GO
Print 'Create User Table.' + CHAR(10);

CREATE TABLE FBJ_USER (
		 PK_UserID INT IDENTITY(1,1) NOT NULL PRIMARY KEY
		,Email VARCHAR(50) NOT NULL
		,Password VARCHAR(20) NOT NULL
		,Name VARCHAR(20) NOT NULL
		,IsAdmin BIT NOT NULL
		,Updt DATETIME Default GETDATE()
	);

GO
Print 'Create Test Table.' + CHAR(10);

CREATE TABLE FBJ_TEST (
		PK_TestID INT IDENTITY(1,1) NOT NULL PRIMARY KEY
		,FK_UserID INT NOT NULL REFERENCES FBJ_USER(PK_UserID)
		,Updt DATETIME Default GETDATE()
	);

GO
Print 'Create Item Table.' + CHAR(10);

CREATE TABLE FBJ_ITEM (
		PK_ItemID INT IDENTITY(1,1) NOT NULL PRIMARY KEY
		,Name VARCHAR(20) NOT NULL
		,Updt DATETIME Default GETDATE()
	);

GO
Print 'Create Test Item Line Table.' + CHAR(10);

CREATE TABLE FBJ_TEST_ITEM (
		PK_TestItemID INT IDENTITY(1,1) NOT NULL PRIMARY KEY
		,FK_TestID INT NOT NULL REFERENCES FBJ_TEST(PK_TestID)
		,FK_ItemID INT NOT NULL REFERENCES FBJ_ITEM(PK_ItemID)
		,Updt DATETIME Default GETDATE()
	);

GO
Print 'Create Result Table.' + CHAR(10);

CREATE TABLE FBJ_RESULT (
		PK_ResultID INT IDENTITY(1,1) NOT NULL PRIMARY KEY
		,Value INT NOT NULL
		,FK_TestID INT NOT NULL REFERENCES FBJ_TEST(PK_TestID)
		,FK_Item1ID INT NOT NULL REFERENCES FBJ_ITEM(PK_ItemID)
		,FK_Item2ID INT NOT NULL REFERENCES FBJ_ITEM(PK_ItemID)
		,Updt DATETIME Default GETDATE()
	);

GO
Print 'Insert test data to User Table.' + CHAR(10);

INSERT INTO FBJ_USER
	(Email
	,Password
	,Name
	,IsAdmin
	)
	VALUES('chunwei.li@pcc.edu', '123', 'David', 1)
			,('eric.kristiansen@pcc.edu', '123', 'Eric', 1)
			,('beeyean.tan@pcc.edu', '123', 'BeeYean', 1)
			,('ian.burton@pcc.edu', '123', 'Ian', 1);

GO
Print 'Insert test data to Item Table.' + CHAR(10);
	
INSERT INTO FBJ_ITEM
	(Name)
	VALUES('Dog')
			,('Cat')
			,('Bull');	

GO
Print 'Insert test data to Test Table.' + CHAR(10);

INSERT INTO FBJ_TEST
	(FK_UserID)
	VALUES(1),
		   (3),
		   (4),
		   (2);

GO
Print 'Insert test data to Result Table.' + CHAR(10);

INSERT INTO FBJ_RESULT
	(Value
		,FK_TestID
		,FK_Item1ID
		,FK_Item2ID)
	VALUES(1, 1, 1, 2)
			,(-1, 1, 2, 1)
			,(1, 1, 1, 3)
			,(-1, 1, 3, 1)
			,(-1, 1, 2, 3)
			,(1, 1, 3, 2)
			,(1, 2, 1, 2)
			,(-1, 2, 2, 1)
			,(-1, 2, 1, 3)
			,(1, 2, 3, 1)
			,(1, 2, 2, 3)
			,(-1, 2, 3, 2)
			,(1, 3, 1, 2)
			,(-1, 3, 2, 1)
			,(-1, 3, 1, 3)
			,(1, 3, 3, 1)
			,(-1, 3, 2, 3)
			,(1, 3, 3, 2)
			,(0, 4, 1, 2)
			,(0, 4, 2, 1)
			,(-1, 4, 1, 3)
			,(1, 4, 3, 1)
			,(-1, 4, 2, 3)
			,(1, 4, 3, 2);

GO
Print 'Insert test data to Test Item Line Table.' + CHAR(10);

INSERT INTO FBJ_TEST_ITEM
	(FK_TestID
		,FK_ItemID)
	VALUES(1, 1)
			,(1, 2)
			,(1, 3);

GO
Print 'Print test data in User Table.' + CHAR(10);

SELECT * FROM FBJ_USER;

GO
Print 'Print test data in Item Table.' + CHAR(10);

SELECT * FROM FBJ_ITEM;

GO
Print 'Print test data in Test Table.' + CHAR(10);

SELECT * FROM FBJ_TEST;

GO
Print 'Print test data in Test Item Line Table.' + CHAR(10);

SELECT * FROM FBJ_TEST_ITEM;

GO
Print 'Print test data in Result Table.' + CHAR(10);

SELECT * FROM FBJ_RESULT;