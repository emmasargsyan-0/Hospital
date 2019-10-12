CREATE DATABASE hospitalDB
GO
USE [hospitalDB]
GO
CREATE TABLE Doctor(
ID int primary key IDENTITY(1,1),
[Name] varchar(30),
LastName varchar(30),
PassportNumber varchar(20),
Specialization varchar(60) 
)
GO
CREATE TABLE Patient(
ID int primary key IDENTITY(1,1),
[Name] varchar(30),
LastName varchar(30),
PassportNumber varchar(20),
Illness varchar(60), 
DoctorID int foreign key references Doctor(ID) 
)
GO
USE MASTER
GO
CREATE LOGIN lg WITH PASSWORD=N'1234', DEFAULT_DATABASE = hospitalDB, DEFAULT_LANGUAGE = US_ENGLISH
GO
ALTER LOGIN lg ENABLE
GO
USE hospitalDB
GO
CREATE USER lg FOR LOGIN lg WITH DEFAULT_SCHEMA = [DBO]
GO
USE [hospitalDB]
GO
ALTER AUTHORIZATION ON SCHEMA::[db_owner] TO [lg]
GO
USE [hospitalDB]
GO
ALTER ROLE [db_owner] ADD MEMBER [lg]
GO
