use day01;
drop table peoples;
##一数据约束 
-- -- 
create table if not exists peoples(
		id int primary key   auto_increment comment '每人的id',#主键（非空+唯一） 自动增长 
        p_name varchar(20)  comment '人名',#不为空 
        stu_id varchar(20) unique comment '学号',#学号，unique唯一 
         address varchar(20) default '华工',#默认值 为华工 
        index(id)#索引 
)engine=innodb character set utf8 collate utf8_general_ci auto_increment=1;#设置引擎以及字符集
insert into peoples values(2,'peace1',02,'武大');
insert into peoples(id,p_name,stu_id) values(null,'peace',01);
select * from peoples;
-- 修改字段属性-- 
alter table peoples modify column p_name varchar(30) default 'peace';
-- 修改字段名称和属性-- 
alter table peoples change column stu_id student_id int;
-- ALTER TABLE record ADD PRIMARY KEY (id);
 -- 解决数据冗余高的问题：给冗余的字段放到一张独立表中
-- 独立设计一张部门表
CREATE TABLE dept(
	id INT PRIMARY KEY,
	deptName VARCHAR(20)
);
-- 添加员工表
CREATE TABLE employee(
	id INT PRIMARY KEY,
	empName VARCHAR(20),
	deptId INT,-- 把部门名称改为部门ID
	-- 声明一个外键约束
	CONSTRAINT emlyee_dept_fk FOREIGN KEY(deptId) REFERENCES dept(id) 
    ON UPDATE CASCADE ON DELETE CASCADE  -- ON CASCADE UPDATE ：级联修改
	--           外键名称                  外键               参考表(参考字段)
);

INSERT INTO dept(id,deptName) VALUES(1,'软件开发部');
INSERT INTO dept(id,deptName) VALUES(2,'应用维护部');
INSERT INTO dept(id,deptName) VALUES(3,'秘书部');

INSERT INTO employee VALUES(1,'张三',1);
INSERT INTO employee VALUES(2,'李四',1);
INSERT INTO employee VALUES(3,'王五',2);
INSERT INTO employee VALUES(4,'陈六',3);   
-- 1）当有了外键约束，添加数据的顺序： 先添加主表，再添加副表数据
-- 2）当有了外键约束，修改数据的顺序： 先修改副表，再修改主表数据
-- 3）当有了外键约束，删除数据的顺序： 先删除副表，再删除主表数据
-- ON CASCADE UPDATE ：级联修改
UPDATE dept SET id=4 WHERE id=3;
select * from employee;
#修改表 
-- 添加一个字段-- 
alter table employee add bossid int;
-- 修改表数据--
update employee set bossid=1 where id=1;
update employee set bossid=2 where id=2;
update employee set bossid=3 where id=3;
-- 删除一个字段-- 
alter table employee drop column bossid;
-- 删除表-- drop table employee;
-- 重命名表-- 
rename table employee to employee1;
-- 删除特定的行-- 
delete from employee where id=1;

       #存储过程（方法建立）-- 
DELIMITER $
CREATE PROCEDURE pro_test()
BEGIN
	-- 可以写多个sql语句;
	SELECT * FROM employee;
END $
-- 删除存储过程-- 
drop procedure pro_test;
-- 创建存储过程--  #-- 声明结束符为$ 
DELIMITER $ 
create procedure pro_test()
begin
	SELECT * FROM employee;
    INSERT INTO employee(id,deptId) VALUES(5,1);  
END $
-- 执行存储过程-- 
CALL pro_test();
drop procedure pro_test;
-- 创建带输入参数的函数 
delimiter $
create procedure pro_findById(in eid int) -- in:输入参数 
begin 
	select * from employee where id=eid;
end $
-- 调用带输入参数的方法-- 
call pro_findById(4);
drop procedure pro_findById;
-- 创建带有输出参数的函数
delimiter $
create procedure pro_testout(out str varchar(20),
							 out sid int
                            )
begin 
	select min(id) into sid from employee;
    select empName from employee where id=3 into str;
end $
-- 带输出参数的函数调用
-- 变量用@name表示
call pro_testout(@str,@sid);
select @str,@sid;#显示变量
drop create procedure pro_testout
--  全局变量（内置变量）：mysql数据库内置的变量 （所有连接都起作用）
        -- 查看所有全局变量： show variables
        -- 查看某个全局变量： select @@变量名
        -- 修改全局变量： set 变量名=新值
        -- character_set_client: mysql服务器的接收数据的编码
        -- character_set_results：mysql服务器输出数据的编码
        
--  会话变量： 只存在于当前客户端与数据库服务器端的一次连接当中。如果连接断开，那么会话变量全部丢失！
        -- 定义会话变量: set @变量=值
        -- 查看会话变量： select @变量
        
-- 局部变量： 在存储过程中使用的变量就叫局部变量。只要存储过程执行完毕，局部变量就丢失！！
        -- 定义局部变量： DECLARE i INT DEFAULT 1;
show variables
use day01;
-- 带有输入输出参数的存储过程
DELIMITER $
CREATE PROCEDURE pro_testInOut(INOUT n INT)  -- INOUT： 输入输出参数
BEGIN
   -- 查看变量
   SELECT n;
   SET n =500;-- 修改变量 
END $

-- 调用
SET @n=10;
CALL pro_testInOut(@n);
SELECT @n;#显示修改后的值
drop PROCEDURE pro_testInOut;
-- 带有条件判断的存储过程
-- 需求：输入一个整数，如果1，则返回“星期一”,如果2，返回“星期二”,如果3，返回“星期三”。其他数字，返回“错误输入”;
DELIMITER $
CREATE PROCEDURE pro_testIf(IN num INT,OUT str VARCHAR(20))
BEGIN
	IF num=1 THEN
		SET str='星期一';
	ELSEIF num=2 THEN
		SET str='星期二';
	ELSEIF num=3 THEN
		SET str='星期三';
	ELSE
		SET str='输入错误';
	END IF;
END $

CALL pro_testIf(4,@str);
 
SELECT @str;
drop procedure pro_testIf;
--  带有循环功能的存储过程
-- 需求： 输入一个整数，求和。例如，输入100，统计1-100的和
DELIMITER $
CREATE PROCEDURE pro_testWhile(IN num INT,OUT result INT)
BEGIN
	-- 定义局部变量
	DECLARE i INT DEFAULT 1;
	DECLARE vsum INT DEFAULT 0;
	WHILE i<=num DO
	      SET vsum = vsum+i;
	      SET i=i+1;
	END WHILE;
	SET result=vsum;
END $
CALL pro_testWhile(100,@result);
drop procedure pro_testWhile;
SELECT @result;
#触发器-- 
-- 当进行 update,insert,delete的前后触发一个事件；
-- 创建日志表
create table test_log(
	  id int primary key auto_increment,
      content varchar(20)
);
-- 需求： 当向员工表插入一条记录时，希望mysql自动同时往日志表插入数据
-- 创建触发器(添加)

create trigger tri_empAdd After insert on employee for each row
	 insert into test_log(content) values('员工插入了一条记录');
-- 插入数据；
insert into employee values(7,'peace3',1,3);

select * from test_log;

-- 创建触发器(修改) before
CREATE TRIGGER tri_empUpd before UPDATE ON employee FOR EACH ROW    -- 当往员工表修改一条记录时
     INSERT INTO test_log(content) VALUES('员工表修改了一条记录');
     
 -- 修改
 UPDATE employee SET empName='eric' WHERE id=7;
 
-- 创建触发器(删除)before
CREATE TRIGGER tri_empDel before DELETE ON employee FOR EACH ROW    -- 当往员工表删除一条记录时
     INSERT INTO test_log(content) VALUES('员工表删除了一条记录');
  
 -- 删除
 DELETE FROM employee WHERE id=7;
 
 SELECT * FROM employee;
 SELECT * FROM test_log;
 
-- ***********mysql权限问题****************
 -- mysql数据库权限问题：root ：拥有所有权限（可以干任何事情）
 -- 权限账户，只拥有部分权限（peace）例如，只能操作某个数据库的某张表
 -- 如何修改mysql的用户密码？
 -- password: md5加密函数(单向加密)
 SELECT PASSWORD('root'); -- *81F5E21E35407D884A6CD4A731AEBFB6AF209E1B
 --  mysql数据库，用户配置 : user表
USE mysql;
show tables;
-- 查看所有使用者
select user from user;
-- 创建用户账号
create user peace identified by '1234';
-- 授权 select,insert,其他一样--
grant select,insert on day01.student to peace;
-- 授权所有：grant all on *.* to peace;
-- 设置与更改用户名--
set password for peace = password('123456');
-- 撤销用户权限--
-- 命令：
revoke insert on day01.student from peace;
-- 展示权限--
show grants for peace;
-- 删除用户--
drop user peace;