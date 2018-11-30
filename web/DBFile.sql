
CREATE   DATABASE  scrumprojectmgmt;

CREATE TABLE person(
    idPers varchar(20) NOT NULL,
    address varchar(50) NOT NULL,
    email varchar(50),
    CONSTRAINT chekMail CHECK(email like '%@%'),
    CONSTRAINT PK_Person PRIMARY KEY(idPers)
);

CREATE TABLE individual
(
    idPers varchar(20) NOT NULL,
    firstName varchar(30) NOT NULL,
    lastName varchar(30) NOT NULL,
    middleName varchar(30) NOT NULL,
    CONSTRAINT PK_indivdual PRIMARY KEY(idPers),
    CONSTRAINT FK_indiv_pers FOREIGN KEY (idPers) REFERENCES person(idPers) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE companyType
(
    CompType varchar(20) NOT NULL,
    CONSTRAINT PK_compType PRIMARY KEY(CompType)
);

CREATE TABLE company
(
    idPers varchar(20) NOT NULL,
    company_name varchar(100) NOT NULL,
    initials varchar(15) NOT NULL,
    regNumber varchar(20),
    CompType varchar(20) NOT NULL,
    CONSTRAINT PK_company PRIMARY KEY(idPers),
    CONSTRAINT FK_company_pers FOREIGN KEY (idPers) REFERENCES person(idPers) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_company_type FOREIGN KEY (CompType) REFERENCES companyType(CompType) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE telephone
(
    idPers varchar(20),
    tel_number varchar(15) NOT NULL,
    CONSTRAINT PK_telephone PRIMARY KEY(tel_number),
    CONSTRAINT FK_tel_pers FOREIGN KEY (idPers) REFERENCES person(idPers) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE  TABLE project
(
    idProject int NOT NULL,
    projectName varchar(100) NOT NULL,
    ownerID varchar(20) NOT NULL,
    constraint pk_project  primary key(idProject),
    constraint fk_project_person  foreign  key(ownerID)  references person(idPers)on delete cascade on update cascade
);

create table sysUser
(
	idPers varchar(20) NOT NULL,
	pswd varchar(20) NOT NULL,
	CONSTRAINT pk_sys_user PRIMARY KEY(idPers),
	CONSTRAINT fk_user_indiv FOREIGN KEY(idPers) REFERENCES individual(idPers) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE scrumRole
(
	scrole varchar(30),
	CONSTRAINT pk_scrum_role PRIMARY KEY(scrole)
);

CREATE TABLE projectRole
(
	idPers varchar(20) NOT NULL,
	idProject int NOT NULL,
	scrole varchar(30),
	CONSTRAINT pk_project_role PRIMARY KEY(idPers,idProject,scrole),
	CONSTRAINT fk_project_role_user FOREIGN KEY(idPers) REFERENCES sysUser(idPers) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk_project_role FOREIGN KEY(idProject) REFERENCES project(idProject) ON DELETE NO ACTION ON UPDATE NO ACTION,
	CONSTRAINT fk_project_role_scrum FOREIGN KEY(scrole) REFERENCES scrumRole(scrole) ON DELETE CASCADE ON UPDATE CASCADE
);

/*===========DONNEES DE TEST=====================*/
INSERT INTO person(idPers,address,email) values('00000000','Goma','admin@gmail.com');

INSERT INTO individual(idPers,firstName,lastName,middleName) values('00000000','admin','admin','admin');

INSERT INTO sysUser (idPers,pswd) values('00000000','1234');

INSERT INTO project(idProject,projectName,ownerID) values(1,'Test','00000000');

INSERT INTO scrumRole(scrole) values('Product owner'),('ScrumMaster'),('Developper'),('Analyst'),('Designer'),('Tester');

INSERT INTO projectRole(idPers,idProject,scrole) values('00000000',1,'Product owner');
/*==================================================*/


/*
CREATE TABLE sprint
(
prod_id int not null,
sp_number int not null,
st_date smalldatetime,
end_date smalldatetime,
goal ntext,
plan_decision ntext,
constraint pk_sprint primary key(prod_id,sp_number),
constraint uk_sprint unique (prod_id,st_date,end_date),
constraint fk_sprint_proj foreign key(prod_id) references software_product(prod_id)on delete cascade on update cascade 
);

CREATE TABLE task
(
prod_id int not null,
sp_number int not null,
task_code int not null,
task_name varchar(500),
duration float,
task_state varchar(25) not null,
constraint pk_task primary key(prod_id,sp_number,task_code),
constraint uk_task unique(prod_id,sp_number,task_name),
constraint fk_task_sprint foreign key(prod_id,sp_number) references sprint(prod_id,sp_number)on delete cascade on update cascade
)

CREATE TABLE predecessor_task
(
prod_id int not null,
sp_number int not null,
task_code int not null,
prod_id_pred int not null,
sp_number_pred int not null,
task_code_pred int not null,
constraint pk_pred_task primary key(prod_id,sp_number,task_code,prod_id_pred,sp_number_pred,task_code_pred),
constraint fk_pred_task foreign key(prod_id,sp_number,task_code) references task(prod_id,sp_number,task_code)on delete cascade on update cascade,
constraint fk_predecessor_task foreign key(prod_id_pred,sp_number_pred,task_code_pred) references task(prod_id,sp_number,task_
code)
)

CREATE TABLE task_assignment
(
prod_id int not null,
sp_number int not null,
task_code int not null,
pers_id varchar(50),
constraint pk_task_assign primary key(prod_id,sp_number,task_code,pers_id),
constraint fk_task_assign foreign key(prod_id,sp_number,task_code) references task(prod_id,sp_number,task_code)on delete cascade on update cascade,
constraint fk_task_assign_person foreign key(pers_id) references physical_person(id)
)
*/
