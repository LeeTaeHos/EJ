------------- admin ----------------
drop table tbl_admin;

create table tbl_admin(
    id varchar2(20) not null,
    password varchar2(20) not null,
    name varchar2(20) not null,
    email varchar2(30) not null
);

insert into tbl_admin values('test', 'test', '홍길동', 'test');
select * from tbl_admin;

commit;
------------- category ----------------
drop table category;
create table category(
    cat_num number(5) primary key,
    code varchar2(10) not null,
    cat_name varchar2(20) not null
);

create sequence cat_seq;

drop table product;

create table product(
    pNum number(10) primary key,
    pName varchar2(50) not null,
    pCategory_fk varchar2(30) not null,
    pCompany varchar2(50),
    pImage varchar2(200),
    pQty number(5) default 0,
    price number(10) default 0,
    pSpec varchar2(20),
    pContent varchar2(300),
    pPoint number(8) default 0,
    pInputDate date
);

create sequence prod_seq;

------------- shopMember --------------------
drop table shopMember;

create table shopMember(
    id varchar2(20) not null,
    pw varchar2(200) not null,
    name varchar2(30) not null,
    tel varchar2(20) not null,
    email varchar2(50) not null,
    addr varchar2(100) not null,
    rdate timestamp not null
);

insert into shopMember values('test','1234','테스트아이디','1212','test@gmail.com','서울',sysdate);
commit;
