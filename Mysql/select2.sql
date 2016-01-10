use day01;
##使用子查询
-- 多表查询规则：1）确定查询哪些表   2）确定哪些哪些字段   3）表与表之间连接条件 (规律：连接条件数量是表数量-1)
-- 建表步骤
-- 1检查detil的所有goods编号
-- 2检查具有前一步骤列出的编号的所有goods商品 
select * from goods where goods_id  in (select goods from detil);

-- 做为计算字段使用子查询 
-- 1从goods检索商品列表 
-- 2对于检索的每个商品id，统计其出现在detil的次数： 
select *,(select count(*) from detil where detil.goods=goods_id) as id_count #必须写全表名，防止混淆 
	 from goods;
##联接表： 
-- 使用连接 
-- 1 列出goods_name，goods_price客户够买的数量
select detil.customer_id as id,goods.goods_name as g_name,goods.gooods_price as price,detil.count as count,(goods.gooods_price * detil.count) as expensive
	from goods,detil
    where goods_id=detil.goods order by id;#这种通过测量相等的为内部联接；  
-- 2.使用内部方式写 
select detil.customer_id as id,goods.goods_name as g_name,goods.gooods_price as price,detil.count as count,(goods.gooods_price * detil.count) as expensive
	 from goods inner join detil
     on goods_id=detil.goods 
     order by id;
-- 3.可以联接多个表，使用and进行条件判断。 

-- 自联接 
-- 1.先找出有问题的饼干4对应的商品id
-- 2.通过商品id找到对应id生产的所有商品； 
select p1.goods_name,p1.goods_maker from goods as p1,goods as p2
       where p1.goods_id=p2.goods_id and p2.goods_name='饼干4'; 
-- 外部连接： 将没有关联的数据页查询出来；left查询出左边表（goods）所有的，right将右边表的所有查询； 
select detil.customer_id as id,goods.goods_name as g_name,goods.gooods_price as price,detil.count as count,(goods.gooods_price * detil.count) as expensive
	 from goods left outer join detil
     on goods_id=detil.goods 
     order by id;
##组合查询 union 跟where的多个or条件一样； 
select goods_id,goods_name from goods where goods_id>2
union
select goods_id,goods_name from goods where goods_name in ('饼干1','饼干3')
order by goods_id;

