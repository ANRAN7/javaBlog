create database Restaurant default char set utf8;
use Restaurant;
-- 1建立餐桌表：
create table if not exists dinnerTable(#判断表是否存在进行创建 
	id int  auto_increment primary key comment '餐桌主键',#设置为主键，自动增长
    tableName varchar(20) not null  comment '餐桌名名',#设置不能为空
    tableStatus int default 0 comment '桌状态',#0:空闲； 1：预定。此处不用bool是便于扩展
    orderDate datetime comment '时间',
    index(id)#id为外键，建立外键关系的域必须为索引类型
)engine=innodb character set utf8 collate utf8_general_ci auto_increment=1;#设置引擎以及字符集
-- 2建立菜类别表：
create table if not exists foodType(#判断表是否存在进行创建 
	id int  auto_increment primary key comment '主键',#设置为主键，自动增长
    typeName varchar(20) not null  comment '名称',#设置不能为空
    index(id)#id为外键，建立外键关系的域必须为索引类型
)engine=innodb character set utf8 collate utf8_general_ci auto_increment=1;#设置引擎以及字符集
-- 3建立菜品表
create table if not exists food(
       id int auto_increment primary key comment '主键',
	   foodType_id int comment '菜系外键',
       foodName varchar(20) not null comment '名称',
       price double default 0 comment '价格',
       mprice double default 0 comment '会员价格',
	   remark varchar(200) comment '菜品简介',
       img varchar(100) comment '图片索引',
       index(id),#id为外键，建立外键关系的域必须为索引类型
	   foreign key(foodType_id) references foodType(id) on delete cascade on update cascade #建立外键，使用foodType(id)作为外键。
)engine=innodb character set utf8 collate utf8_general_ci auto_increment=1;#设置引擎以及字符集
-- 4建立订单表
create table if not exists orders(
	   id int auto_increment primary key comment '主键',
       table_id int comment '餐桌id外键',
       totalPrice double comment '总价',
       orderStatus int default 0 comment '订单状态',#0:未结账； 1：已结账。此处不用bool是便于扩展
	   orderDate DateTime comment '时间',
       index(id),
       foreign key(table_id) references dinnerTable(id) on delete cascade on update cascade #建立外键，使用dinnerTable(id)作为外键。
)engine=innodb character set utf8 collate utf8_general_ci auto_increment=1;#设置引擎以及字符集
-- 5建立订单详细表：
create table if not exists orderDetail(
	   id int auto_increment primary key comment '主键',
       orderId int comment '订单id外键',
       food_id int comment '菜品id外键',
	   foodCount int comment '菜品数量',
       foreign key(orderId) references orders(id) on delete cascade on update cascade ,#建立外键，使用orders(id)作为外键。
       foreign key(food_id) references food(id) on delete cascade on update cascade #建立外键，使用food(id)作为外键。
)engine=innodb character set utf8 collate utf8_general_ci auto_increment=1;#设置引擎以及字符集