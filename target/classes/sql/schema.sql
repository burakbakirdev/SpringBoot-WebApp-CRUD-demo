create table product
(
   id identity primary key,
   productname varchar (60),
   brand varchar (50),
   description varchar (250),
   unitprice double,
   totalprice double,
   stock int,
   recorddate date,
   category varchar (30)
);
