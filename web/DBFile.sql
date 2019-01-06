
CREATE   DATABASE  scrumprojectmgmt;

/*
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
*/

/*===========DONNEES DE TEST=====================*/
/*INSERT INTO person(idPers,address,email) values('00000000','Goma','admin@gmail.com');

INSERT INTO individual(idPers,firstName,lastName,middleName) values('00000000','admin','admin','admin');

INSERT INTO sysUser (idPers,pswd) values('00000000','1234');

INSERT INTO project(idProject,projectName,ownerID) values(1,'Test','00000000');

INSERT INTO scrumRole(scrole) values('Product owner'),('ScrumMaster'),('Developper'),('Analyst'),('Designer'),('Tester');

INSERT INTO projectRole(idPers,idProject,scrole) values('00000000',1,'Product owner');*/
/*==================================================*/

CREATE TABLE member_categorie
(
	category varchar(50) NOT NULL,
	CONSTRAINT pk_categ PRIMARY KEY(category)
);

CREATE TABLE project_member
(
	account varchar(20) NOT NULL,
	name varchar(250) NOT NULL,
	initials varchar(20),
	addres varchar(100),
	tel varchar(13) NOT NULL,
	email varchar(30) NOT NULL,
	category varchar(50) NOT NULL,
	CONSTRAINT pk_project_member PRIMARY KEY(account),
	CONSTRAINT fk_member_category FOREIGN KEY(category) REFERENCES member_categorie(category) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE user_account
(
	account varchar(20) NOT NULL,
	pswd varchar(12) NOT NULL,
	CONSTRAINT pk_member_account PRIMARY KEY(account),
	CONSTRAINT fk_member_account FOREIGN KEY(account) REFERENCES project_member(account) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE scrum_project
(
	id_project varchar(20) NOT NULL,
	project_name varchar(250) NOT NULL,
	CONSTRAINT pk_scrum_project PRIMARY KEY(id_project)
);

CREATE TABLE scrum_role
(
	scr_role varchar(30),
	CONSTRAINT pk_scr_role PRIMARY KEY(scr_role)
);

CREATE TABLE project_member_role
(
	account varchar(20) NOT NULL,
	id_project varchar(20) NOT NULL,
	scr_role varchar(30),
	CONSTRAINT pk_project_member_role PRIMARY KEY(account,id_project),
	CONSTRAINT fk_member_role FOREIGN KEY(account) REFERENCES project_member(account) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT fk_proj_role FOREIGN KEY(id_project) REFERENCES scrum_project(id_project) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT fk_proj_scr_role FOREIGN KEY(scr_role) REFERENCES scrum_role(scr_role) ON UPDATE CASCADE ON DELETE CASCADE
);

/*===============TEST DATA===================*/
INSERT INTO scrum_role(scr_role) values('Project owner'),('Product owner'),('ScrumMaster'),('Developper'),('Analyst'),('Designer'),('Tester');

INSERT INTO member_categorie(category) values('Administrator'),('Individual'),('Hospital'),('School'),('University'),('Shop'),('Bank'),('Government');

INSERT INTO project_member(account,name,initials,addres,tel,email,category) values
			('admin','Administrator','-','Goma','+243994401108','kamkyaj@gmail.com','Administrator'),
			('Jerome','Jerome Kambale Kyamuva','-','Goma','+243994401108','kamkyaj@gmail.com','Individual'),
			('dgrnk','Direction Générale des Recettes du Nord-Kivu','-','Goma','-','dgrnk@gmail.com','Government');

INSERT INTO user_account(account,pswd) values('admin','admin'),('Jerome','jerome'),('dgrnk','dgrnk');

INSERT INTO scrum_project(id_project,project_name) values('1','Test'),('2','Gestion Annuaire Informatique'),('3','KODInet');

INSERT INTO project_member_role(account,id_project,scr_role) values('Jerome','1','Project owner'),('Jerome','2','Project owner'),('dgrnk','3','Project owner');

/*=============PRODUCT BACKLOG==============*/
CREATE TABLE backlog_item_domain
(
	domain varchar(50),
	CONSTRAINT pk_bid PRIMARY KEY(domain)
);


CREATE TABLE product_backlog_item
(
	pbl_id uuid,
	id_project varchar(20),
	title varchar(100),
	type_of_user varchar(50),
	user_story text,
	story_goal text,
	item_priority int,
	estimation real,
	acceptance text,
	domain varchar(50),
	userid varchar(20),
	saving_date date,
	CONSTRAINT pk_pbl PRIMARY KEY(pbl_id),
	CONSTRAINT uk_pbl UNIQUE(id_project,type_of_user,user_story),
	CONSTRAINT fk_pd_backlog_pj FOREIGN KEY(id_project) REFERENCES scrum_project(id_project) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT fk_pd_backlog_dom FOREIGN KEY(domain) REFERENCES backlog_item_domain(domain) ON UPDATE CASCADE ON DELETE CASCADE
);

/*==========DOMAIN DATA===========*/
INSERT INTO backlog_item_domain VALUES('Functional requirement'),('Security'),('Design'),('User support'),('Settings');

/*=========scrum_project table modification==========*/
ALTER TABLE scrum_project ADD 
userid varchar(20);

ALTER TABLE scrum_project ADD
saving_date timestamptz default (now() AT TIME ZONE 'utc');

ALTER TABLE scrum_project ADD
CONSTRAINT uk_name UNIQUE(project_name,userid);

/*=======Sprint table=========*/
CREATE TABLE sprint
(
	pbl_id uuid NOT NULL,
	id_project character varying(20),
	start_date date,
	end_date date,
	goal text,
	userid character varying(20),
	saving_date timestamptz default (now() AT TIME ZONE 'utc'),
	CONSTRAINT pk_sprint PRIMARY KEY(pbl_id),
	CONSTRAINT fk_sp_pj FOREIGN KEY(id_project) REFERENCES scrum_project(id_project) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT uk_sp UNIQUE(id_project,start_date,end_date)
);

/*code for UUID insert*/
INSERT INTO public."testUUID"(
            id, value)
    SELECT (SELECT id FROM (SELECT uuid_in((md5((random())::text))::cstring) as id) t WHERE id NOT IN (SELECT id FROM public."testUUID")), 'value 2';




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
