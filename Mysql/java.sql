create table if not exists contact(
				sid int primary key auto_increment,
                sname varchar(20) not null,
                sgender varchar(4) default '女',
                sage int,
                sphone varchar(20),
                semail varchar(40),
                sqq varchar(20)
);
use day01;
create table if not exists admin(
				sid int primary key auto_increment,
                username varchar(10) not null,
                pwd varchar(6) default '123456'
);
create table if not exists ddept(
				did int primary key auto_increment,
                deptName varchar(10) not null,
                index(did)
)engine=innodb character set utf8 collate utf8_general_ci auto_increment=1;#设置引擎以及字符集
create table if not exists employ(
				eid int ,
                empName varchar(10) not null,
                index(eid),
                foreign key(eid) references ddept(did) on delete cascade on update cascade#建立外键，
)engine=innodb character set utf8 collate utf8_general_ci auto_increment=1;#设置引擎以及字符集
create table if not exists account(
				id int primary key auto_increment,
                accountName varchar(10) not null,
                money int 
);
insert into account values(null,'peace',10000);
-- 测试大数据类型
 CREATE TABLE longtxt(
      id INT PRIMARY KEY AUTO_INCREMENT,
      content LONGTEXT,
      img LONGBLOB
 );
 select * from longtxt;