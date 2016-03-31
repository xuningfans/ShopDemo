drop database if exists mystore ;
create database if not exists mystore default character set utf8;
use mystore;

/*============================*/
/* Table: 用户表结构 		      */
/*============================*/
drop table if exists user;
create table if not exists user
(
   /* 用户编号,自动增长 */
   user_id                  int not null auto_increment,
   /* 用户名称 */
   user_name                varchar(40),
   /* 用户密码 */
   user_password            varchar(40),
   /* 用户类型  */
   user_type                tinyint,
   primary key (user_id)
)ENGINE=InnoDB;

/*============================*/
/* Table: 商品表结构 		      */
/*============================*/
drop table if exists product;
create table if not exists product
(
   /* 商品编号,自动增长  */
   product_id               int not null auto_increment,
   /* 商品标题  */
   product_title            varchar(100),
   /* 商品图片  */
   product_image            varchar(255),
   /* 商品详情  */
   product_detail           varchar(255),
   /* 商品价格  */
   product_price            decimal(8,2),
   /* 商品摘要  */
   product_summary          varchar(255),
   /* 商品状态  */
   is_sell             boolean,
   primary key (product_id)
)ENGINE=InnoDB;

/*============================*/
/* Table: 订单表结构 	     	  */
/*============================*/
drop table if exists orders;
create table if not exists orders
(
   /* 订单编号,自动增长  */
   orders_id                int not null auto_increment,
   /* 用户编号 */
   user_id                  int,
   /* 商品编号  */
   product_id               int,
   /* 商品价格  */
   orders_price             decimal(8,2),
   /* 订单状态  */
   state                    tinyint,
   primary key (orders_id)
)ENGINE=InnoDB;

insert into user(user_name,user_password,user_type)
values('seller','relles',1),('buyer','reyub',0);
select * from user;
