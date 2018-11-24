
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
