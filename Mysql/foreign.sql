use day01;
#MySQL创建关联表可以理解为是两个表之间有个外键关系，但这两个表必须满足三个条件
#1.两个表必须是InnoDB数据引擎
#2.使用在外键关系的域必须为索引型(Index)
#3.使用在外键关系的域必须与数据类型相似
#创建关联表 
create table if not exists goods(#判断表是否存在进行创建 
	goods_id int not null auto_increment primary key comment '商品编号',#设置为主键，自动增长
    goods_name varchar(20) not null  comment '商品名',#设置不能为空
    gooods_price float not null comment '商品价格',
    goods_maker varchar(20) default null comment '生产商',
    index(goods_id)#建立外键关系的域必须为索引类型
)engine=innodb character set utf8 collate utf8_general_ci auto_increment=1;#设置引擎以及字符集

create table if not exists detil(
	customer_id int not null primary key comment '客户id',#设置为主键 
    goods int not null comment '商品id',
    count int not null comment '数量',
    index(goods),#经测试此去的索引可以省去。
    foreign key(goods) references goods(goods_id) on delete cascade on update cascade#建立外键，使用goods(goods_id)作为外键。
    #on delete(update) cascade 意思为当goods表有相关记录删除(修改)时，detil想要的记录也会被删去（修改）。
)engine=innodb character set utf8 collate utf8_general_ci;#设置引擎以及字符集

#向goods表添加数据 
insert into goods values(null,'饼干1','2.3','三无产品');
insert into goods values(null,'饼干2','2.3','三无产品');
insert into goods values(null,'饼干3','2.3','三无产品');
insert into goods values(null,'饼干4','2.3','三无产品');
insert into goods values(null,'饼干5','2.3','三无产品');
insert into goods values(null,'饼干6','2.3','三无产品');
insert into goods values(null,'饼干7','2.3','三无产品');
insert into goods values(null,'饼干8','2.3','三无产品');
#向detil表添加数据
#必须在goods中有goods_id的编号，能正确插入detil；
insert into detil values(001,1,2);
insert into detil values(002,2,2);
insert into detil values(003,3,2);
insert into detil values(004,4,2);
insert into detil values(005,1,2);
insert into detil values(006,1,2);
insert into detil values(007,1,2);
insert into detil values(008,1,2);
#insert into detil values(008,30,2);会报错。
select * from goods;#显示表
select * from detil;#显示表
##删除goods中的一行，detil对应的一行也会被删去。 
delete from goods where goods_id=2;
select * from detil;


