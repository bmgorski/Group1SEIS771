SET SQL_SAFE_UPDATES = 0;

drop table if EXISTS user cascade;
drop table if EXISTS AUTHORITY cascade;
drop table if EXISTS ANIMAL_CARE cascade;
drop table if EXISTS customer cascade;
drop table if EXISTS employee cascade;
drop table if EXISTS facility cascade;
drop table if EXISTS submission_application cascade;
drop table if EXISTS veterinarian cascade;
drop table if EXISTS animal cascade;

create table USER
(
   ID              		bigint not null AUTO_INCREMENT,
   FIRST_NAME           varchar(50),
   LAST_NAME            varchar(50),
   EMAIL                varchar(50),
   STREET               varchar(50),
   CITY                 varchar(50),
   STATE                varchar(50),
   ZIP                  int,
   PHONE                varchar(12),
   PASSWORD             varchar(128),
   primary key (ID)
);

create table AUTHORITY
(
   ID              		bigint not null AUTO_INCREMENT,
   EMAIL                varchar(50),
   AUTHORITY            varchar(123),
   primary key (ID)
);

-- ALTER TABLE ANIMAL DROP FOREIGN KEY FK_CONTAINS;
create table ANIMAL
(
   ID            		bigint not null AUTO_INCREMENT,
   FACILITY_ID          bigint,
   NAME                 varchar(50),
   ANIMAL_TYPE          varchar(50),
   BIRTHDATE            date,
   DEATH_DATE           date,
   NOTES                text,
   WEIGHT               int,
   BREED                varchar(50),
   ANIMAL_SIZE          varchar(50),
   primary key (ID)
);

-- ALTER TABLE CUSTOMER DROP FOREIGN KEY FK_INHERITANCE_2;
create table CUSTOMER
(
   ID              	bigint not null AUTO_INCREMENT,
   USER_ID          bigint not null,
   primary key (ID)
);

-- ALTER TABLE EMPLOYEE DROP FOREIGN KEY FK_INHERITANCE_1;
create table EMPLOYEE
(
   ID              		bigint not null AUTO_INCREMENT,
   USER_ID          	bigint not null,
   ROLE                 varchar(50),
   primary key (ID)
);

create table FACILITY
(
   ID          			bigint not null AUTO_INCREMENT,
   BUILDING             int,
   FACILITY_SIZE        varchar(50),
   CAPACITY			    int,
   ANIMAL_TYPE		    varchar(50),
   primary key (ID)
);

-- ALTER TABLE SUBMISSION_APPLICATION DROP FOREIGN KEY FK_REVIEWED_BY;
-- ALTER TABLE SUBMISSION_APPLICATION DROP FOREIGN KEY FK_SUBJECT;
-- ALTER TABLE SUBMISSION_APPLICATION DROP FOREIGN KEY FK_SUBMITS;
-- 

create table SUBMISSION_APPLICATION
(
   ID          				    bigint not null AUTO_INCREMENT,
   CUSTOMER_ID          	    bigint not null,
   EMPLOYEE_ID          	    bigint,
   ANIMAL_ID            	    bigint not null,
   DATE_SUBMITTED               date,
   REPORTED_CONDITION      	    varchar(50),
   REQUESTS		          	    varchar(100),
   APPLICATION_STATUS           varchar(50),
   REJECTION_NOTES              text,
   primary key (ID)
);

-- ALTER TABLE VETERINARIAN DROP FOREIGN KEY FK_INHERITANCE_3;
-- 
create table VETERINARIAN
(
   ID      				bigint not null AUTO_INCREMENT,
   USER_ID              bigint not null,
   ORGANIZATION         varchar(50),
   primary key (ID)
);

CREATE TABLE `seis771`.`animal_care`
(
	ID 				BIGINT NOT NULL AUTO_INCREMENT,
	ANIMAL_ID 		bigint not null,
	CARE_DATE 		DATE NULL,
	CARE_NOTES 		TEXT NULL,
  	PRIMARY KEY (ID),
    FOREIGN KEY (ANIMAL_ID) REFERENCES animal(ID)
);

-- alter table ANIMAL add constraint FK_CONTAINS foreign key (FACILITY_ID)
--      references FACILITY (ID) on delete restrict on update restrict;
--
-- alter table CUSTOMER add constraint FK_INHERITANCE_2 foreign key (USER_ID)
--      references USER (ID) on delete restrict on update restrict;
--
-- alter table EMPLOYEE add constraint FK_INHERITANCE_1 foreign key (USER_ID)
--      references USER (ID) on delete restrict on update restrict;
--
-- alter table SUBMISSION_APPLICATION add constraint FK_REVIEWED_BY foreign key (EMPLOYEE_ID)
--      references EMPLOYEE (ID) on delete restrict on update restrict;
--
-- alter table SUBMISSION_APPLICATION add constraint FK_SUBJECT foreign key (ANIMAL_ID)
--      references ANIMAL (ID) on delete restrict on update restrict;
--
-- alter table SUBMISSION_APPLICATION add constraint FK_SUBMITS foreign key (CUSTOMER_ID)
--      references CUSTOMER (ID) on delete restrict on update restrict;
--
-- alter table VETERINARIAN add constraint FK_INHERITANCE_3 foreign key (USER_ID)
--      references USER (ID) on delete restrict on update restrict;
-- 

-- master user
INSERT INTO USER (FIRST_NAME, LAST_NAME, EMAIL, STREET, CITY, STATE, ZIP, PHONE) 
VALUES ('Master', 'User', 'master@user.com', '100 Main St.', 'St. Paul', 'Minnesota', 55105, '651-555-5555');

-- employee
INSERT INTO USER (FIRST_NAME, LAST_NAME, EMAIL, STREET, CITY, STATE, ZIP, PHONE, PASSWORD) 
VALUES ('Ernie', 'Emerson', 'ernieemerson@gmail.com', '1613 Summit Avenue', 'St. Paul', 'Minnesota', 55105, '651-555-1029', 'iamernie');

-- veterinarian
INSERT INTO USER (FIRST_NAME, LAST_NAME, EMAIL, STREET, CITY, STATE, ZIP, PHONE, PASSWORD) 
VALUES ('Victor', 'Valens', 'victorvalens@gmail.com', '99 Washington Avenue', 'Minneapolis', 'Minnesota', 55455, '612-891-5728', 'valenspw');

-- customer
INSERT INTO USER (FIRST_NAME, LAST_NAME, EMAIL, STREET, CITY, STATE, ZIP, PHONE, PASSWORD) 
VALUES ('John', 'Doe', 'johndoe@gmail.com', '428 Elm Street', 'St. Paul', 'Minnesota', 55105, '651-444-7890', 'fishtank');

-- customer
INSERT INTO USER (FIRST_NAME, LAST_NAME, EMAIL, STREET, CITY, STATE, ZIP, PHONE, PASSWORD) 
VALUES ('Bob', 'Smith', 'bobsmith@gmail.com', '123 Main Street', 'Minneapolis', 'Minnesota', 55414, '612-222-3232', 'mypassword');

INSERT INTO EMPLOYEE (USER_ID)
SELECT ID FROM USER WHERE FIRST_NAME = 'Ernie' AND LAST_NAME = 'Emerson';
UPDATE EMPLOYEE SET ROLE = 'Caretaker' WHERE USER_ID = (
	SELECT ID FROM USER WHERE USER.FIRST_NAME  = 'Ernie' AND USER.LAST_NAME = 'Emerson');

INSERT INTO VETERINARIAN (USER_ID)
SELECT ID FROM USER WHERE FIRST_NAME = 'Victor' AND LAST_NAME = 'Valens';
UPDATE VETERINARIAN SET ORGANIZATION = 'Veterinarians-R-Us' WHERE USER_ID = (
	SELECT ID FROM USER WHERE USER.FIRST_NAME = 'Victor' AND USER.LAST_NAME = 'Valens');

INSERT INTO CUSTOMER (USER_ID)
SELECT ID FROM USER WHERE FIRST_NAME = 'John' AND LAST_NAME = 'Doe';

INSERT INTO CUSTOMER (USER_ID)
SELECT ID FROM USER WHERE FIRST_NAME = 'Bob' AND LAST_NAME = 'Smith';
   
INSERT INTO FACILITY (BUILDING, FACILITY_SIZE, CAPACITY, ANIMAL_TYPE)
VALUES (1, 'Medium', 1, 'Dog');
INSERT INTO FACILITY (BUILDING, FACILITY_SIZE, CAPACITY, ANIMAL_TYPE)
VALUES (2, 'Medium', 2, 'Cat');
INSERT INTO FACILITY (BUILDING, FACILITY_SIZE, CAPACITY, ANIMAL_TYPE)
VALUES (2, 'Medium', 2, 'Cat');
INSERT INTO FACILITY (BUILDING, FACILITY_SIZE, CAPACITY, ANIMAL_TYPE)
VALUES (1, 'Small', 2, 'Bird');
INSERT INTO FACILITY (BUILDING, FACILITY_SIZE, CAPACITY, ANIMAL_TYPE)
VALUES (3, 'Large', 1, 'Dog');
INSERT INTO FACILITY (BUILDING, FACILITY_SIZE, CAPACITY, ANIMAL_TYPE)
VALUES (3, 'Small', 1, 'Dog');
INSERT INTO FACILITY (BUILDING, FACILITY_SIZE, CAPACITY, ANIMAL_TYPE)
VALUES (1, 'Small', 1, 'Dog');

INSERT INTO ANIMAL (FACILITY_ID, NAME, ANIMAL_TYPE, NOTES, WEIGHT, BREED, ANIMAL_SIZE) 
VALUES (1, 'Fluffy', 'Dog', 'Active, enjoys walks', 48, 'Welsh Pembroke Corgie', 'Medium');

INSERT INTO SUBMISSION_APPLICATION (CUSTOMER_ID, EMPLOYEE_ID, ANIMAL_ID)
SELECT C.ID, E.ID, A.ID FROM CUSTOMER C join EMPLOYEE E join ANIMAL A WHERE 
C.USER_ID = (SELECT ID FROM USER WHERE FIRST_NAME = 'Bob' AND LAST_NAME = 'Smith') AND
E.USER_ID = (SELECT ID FROM USER WHERE FIRST_NAME = 'Ernie' AND LAST_NAME = 'Emerson') AND
A.ID = (SELECT ID FROM ANIMAL A2 WHERE A2.NAME = 'Fluffy');
UPDATE SUBMISSION_APPLICATION SET REPORTED_CONDITION = 'Healthy' WHERE ANIMAL_ID = (SELECT ID FROM ANIMAL WHERE NAME = 'Fluffy');
UPDATE SUBMISSION_APPLICATION SET REQUESTS = 'Adopted into home with other dogs' WHERE ANIMAL_ID = (SELECT ID FROM ANIMAL WHERE NAME = 'Fluffy');
UPDATE SUBMISSION_APPLICATION SET APPLICATION_STATUS = 'pending' WHERE ANIMAL_ID = (SELECT ID FROM ANIMAL WHERE NAME = 'Fluffy');
UPDATE SUBMISSION_APPLICATION SET DATE_SUBMITTED = '2015-04-22' WHERE ANIMAL_ID = (SELECT ID FROM ANIMAL WHERE NAME = 'Fluffy');
INSERT INTO AUTHORITY (EMAIL, AUTHORITY)
VALUES ('master@user.com', 'AUTHENTICATED_USER_AUTHORITY');

INSERT INTO AUTHORITY (EMAIL, AUTHORITY)
VALUES ('master@user.com', 'customer');

INSERT INTO AUTHORITY (EMAIL, AUTHORITY)
VALUES ('master@user.com', 'employee');

INSERT INTO AUTHORITY (EMAIL, AUTHORITY)
VALUES ('master@user.com', 'veterinarian');

INSERT INTO ANIMAL_CARE (ID, ANIMAL_ID, CARE_DATE, CARE_NOTES)
VALUES ('1', '1', '2015-05-09', 'sample care notes');
INSERT INTO ANIMAL_CARE (ID, ANIMAL_ID, CARE_DATE, CARE_NOTES)
VALUES ('2', '1', '2015-05-10', 'sample care notes2');


