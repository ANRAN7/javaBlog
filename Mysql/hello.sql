-- 创建表
create database day01;
-- show character
show create database day01;
-- change character
alter database day01 default character set utf8;
show create database day01;


-- 创建table必须先使用数据库alter
use day01;
drop table student;
-- create table
create table student(
		sid INT,-- 学号整型
        sname varchar(20),-- 名字字符型
        smark int,-- 分数整型 
        sage int-- 年龄
);
-- 展示所有的表
show tables;


-- 删除数据库drop database day01; 
-- 删除表 drop table student;
-- 修改表

-- 添加字段
alter table student add column sgender varchar(2);
-- 删除字段
alter table student add column sbb varchar(2);
alter table student drop column sbb;
-- 修改字段数据类型alter
alter table student modify column smark varchar(2);
-- 修改字段名称
alter table student change column smark mark int;
-- 修改表名称alter table student rename to teacher;
-- 查看表的详细信息
desc student;

-- ***二增删改数据**--

-- 1.增加数据
INSERT INTO student VALUES(001,'peace',1,22,'男');
-- 注意不能少或多字段值
-- INSERT INTO student VALUES(2,'李四','女');错误
-- 插入部分字段
INSERT INTO student(sid,sname) VALUES(002,'rong');
-- 显示列和数据--
select * from student;

-- 2.修改数据--
-- 防止没有主键报错--
SET SQL_SAFE_UPDATES = 0;
-- 修改所有数据，建议少用--
UPDATE student set mark=10;
-- 带条件的修改（建议使用）--
update student set mark=2 where sid=2;
-- 修改多个字段，SET 字段名=值,字段名=值,....--
update student set mark=1,sage=23 where sid=1; 
-- 3. 删除数据--
-- 防止没有主键报错--
SET SQL_SAFE_UPDATES = 0;account
-- 删除所有数据(建议少用)--
-- delete from student;
-- 带条件的删除--
insert into student(sid,sname) values(003,'sisi');
delete from student where sid=003;
--  另一种方式
-- delete from: 可以全表删除      1)可以带条件删除  2）只能删除表的数据，不能删除表的约束     3)使用delete from删除的数据可以回滚（事务）
-- truncate table: 可以全表删除   1）不能带条件删除 2）即可以删除表的数据，也可以删除表的约束 3）使用truncate table删除的数据不能回滚
-- TRUNCATE TABLE student;
-- 测试如下； 
CREATE TABLE test(
	id INT PRIMARY KEY AUTO_INCREMENT, -- 自增长约束
	NAME VARCHAR(20)
);
DESC test;

-- 1.
DELETE FROM test;
-- 2
TRUNCATE TABLE test;

INSERT INTO test(NAME) VALUES('张三');
INSERT INTO test(NAME) VALUES('张三2');
INSERT INTO test(NAME) VALUES('张三3');

SELECT * FROM test;
-- 三.查询数据---
-- 三。一简单查询--
use day01;
-- 查询所有的列--
select * from student;
-- 查询指定的列 并指定别名as--
select sname,sid as id from student;
-- 查询时添加常量列--
-- 增加一个班级列，内容为：自动化班 名称为2014级 --
select sid,sname,'自动化班' as '2014级' from student;
-- 限制查询行数 --
select sid,sname from student limit 1;

INSERT INTO student VALUES(003,'peace1',20,22,'男');
INSERT INTO student VALUES(004,'peace2',20,22,'男');
INSERT INTO student VALUES(005,'peace3',20,22,'男');
-- 查询排序  Desc降序排序 --
select * from student order by sid Desc;

-- 三。二查询之过滤--
-- 检查单个值--
select * from student where sid=002;
select * from student where sid<>002;
-- 范围检查-- 
select * from student where sid between 002 and 004;
-- 空值检查-- 
select * from student where sgender is null;
-- 条件逻辑过滤-- 
select * from student where (sid=001 or sid=002) and mark>1;
-- In 和not--
select * from student where sid not in (001,002,003);

-- 用通配符进行过滤 like % _-- 
-- 选出名字中含有eac的词-- 
select * from student where sname like '%eac%';
-- 选出第一个字符任意，后面字符为eace的词-- 
select * from student where sname like '_eace';
-- 三。三查询之正则表达式-- 
-- 正则表达式，不同于like，使用的操作符是regexp，匹配的也是含有-- 
-- 基本字符匹配-- 
select sname from student where sname regexp 'ong';-- 此去如果用like不会返回结果，因为不加通配符时，like时完全匹配； 
-- 正则书写部分基本与其他正则语言一致--
select sname from student where sname regexp '[peac,ron]';
select * from student where sid regexp '[[:digit:]]';

-- 三。四查询之计算字段--
-- 拼接-- 
select concat(sname,'(',sid,')') as sname from student;
-- 执行算法计算-- 
select sid,sname,sid*mark as imark from student;
-- 文本处理函数-- 
select sname,upper(sname) from student;
-- 日期和时间处理函数-- student
-- select c_id,order_num from orders where date(order_date)='2015-11-4';
-- 数值处理函数-- 
select sid,sname from student where mod(sid,2)=0;
-- 三。五查询之汇总分组数据--
-- avg 平均函数 max, min, sum-- 
select avg(sid) as avg,
	   min(sid) as min,
       max(sid) as max,
       sum(sid) as sum
from student;
-- count函数-- 
select count(*) from student;
select count(sage) from student;
-- 聚集不同的值 --
select avg(distinct mark) from student;
select avg(mark) from student;
-- 数据分组 group by--
select mark,count(*) as num from student group by mark;
-- 过滤分组having num>0--  
select mark,count(*)as num from student where sid>1 group by mark having num>0;
-- 注意having有where的功能，但where不可以替代having，where用于过滤结果，having用于过滤分组结果--
-- select子句顺序--
-- select--from--where--group by--having--order by--limit--

