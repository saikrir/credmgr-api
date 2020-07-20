CREATE DATABASE t_cmgr_db;
use t_cgmr_db;

create table users(
      username varchar(50) not null primary key,
      password varchar(50) not null,
      enabled boolean not null
      );

 create table authorities (
      username varchar(50) not null,
      authority varchar(50) not null,
      constraint fk_authorities_users foreign key(username) references users(username));
      create unique index ix_auth_username on authorities (username,authority);

create table system_credentials(
      id  BIGINT not null primary key AUTO_INCREMENT,
      sys_nm varchar(50) not null,
      sys_user_id varchar(50) not null,
      sys_password varchar(50) not null,
      additional_info varchar(100),
      app_user varchar(50) not null,
      last_updated DATETIME not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
      constraint fk_sys_credentials foreign key(app_user) references users(username)
);

commit