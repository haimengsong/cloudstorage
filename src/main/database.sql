drop database cloudstorage;
create database cloudstorage charset utf8;
use cloudstorage;

create table users(
    id int primary key auto_increment,
    username varchar(50) not null unique,
    password varchar(50) not null,
    email varchar(50) not null unique,
    gender int(1) not null,
    portrait varchar(50) default 'portrait',
    joindate char(30) not null
);

create table myfile(
    id int primary key auto_increment,
    user_id int not null,
    parent_id int,
    name varchar(100) not null,
    size long not null,
    type char(20) not null,
    path varchar(500) not null,
    createdate char(30),
    password varchar(20),
    islock int(1) default 0,
    isshare int(1) default 0,
    sharedownload int default 0,
    location varchar(220),
    shareurl varchar(100),
    md5 char(32),
    description varchar(200),
    index(parent_id),
    foreign key (user_id) references users(id),
    foreign key (parent_id) references myfile(id) on DELETE CASCADE
);

create table mydiskinfo(
    id int primary key auto_increment,
    user_id int not null unique,
    totalsize long not null,
    usedsize long,
    filenumber int,
    sharenumber int,
    foreign key (user_id) references users(id)
);

create table message(
    id int primary key auto_increment,
    user_id int not null,
    email varchar(50) not null,
    username varchar(30) not null,
    title varchar(30) not null,
    content text not null,
    foreign key (user_id) references users(id)
);