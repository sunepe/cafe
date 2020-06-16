insert into login (id, password) VALUES ('coffee', 'coffee');

insert into login (id, password) VALUES ('coffee1', '123');
commit;

CREATE table login (
id VARCHAR2(20) PRIMARY key,
password varchar2(20) );

drop table login;

SELECT
    *
FROM login;

commit;

select menu from coffeemenu where menucode like 'H%';

select * from coffee;


select sum(price) from coffee where payway like '현금';

select sum(price), SUBSTR(ordertime,1,13) from coffee where payway like '카드' group by SUBSTR(ordertime,1,13);
select sum(price), SUBSTR(ordertime,1,13) from coffee group by SUBSTR(ordertime,1,13);

select payway, price, SUBSTR(ordertime,1,13) from coffee; 

drop table coffee;
create table coffee (
payway varchar2(10),
menucode varchar2(50),
menu varchar2(50),
price number(13),
ordertime varchar2(50));

select * from coffee;

select payway, menu, price, ordertime from coffee where ordertime like '2020년 06월 04일%';

select menu , count(*)  from coffee group by menu order by count(*) desc;

CREATE TABLE coffeemenu(
menucode VARCHAR2(50)PRIMARY KEY,
menu VARCHAR2(50),
price NUMBER(13));

select * from coffeemenu;

drop table coffeemenu;

INSERT into coffeemenu(menucode, menu, price)
VALUES('IA1','ICE 아메리카노', 3200);
INSERT into coffeemenu(menucode,menu, price)
VALUES('HA1','아메리카노', 3200);
INSERT into coffeemenu(menucode,menu, price)
VALUES('IC1','ICE 카페라떼', 3700);
INSERT into coffeemenu(menucode,menu, price)
VALUES('HC1','카페라떼', 3700);
INSERT into coffeemenu(menucode,menu, price)
VALUES('IM1','ICE 카페모카', 3900);
INSERT into coffeemenu(menucode,menu, price)
VALUES('HM1','카페모카', 3900);
INSERT into coffeemenu(menucode,menu, price)
VALUES('IB1','ICE 바닐라라떼', 3900);
INSERT into coffeemenu(menucode,menu, price)
VALUES('HB1','바닐라라떼', 3900);
INSERT into coffeemenu(menucode,menu, price)
VALUES('IC2','ICE 카푸치노', 3700);
INSERT into coffeemenu(menucode,menu, price)
VALUES('HC2','카푸치노', 3700);
INSERT into coffeemenu(menucode,menu, price)
VALUES('BO1','오리진 쉐이크', 4300);
INSERT into coffeemenu(menucode,menu, price)
VALUES('BD1','딸기 쉐이크', 4800);
INSERT into coffeemenu(menucode,menu, price)
VALUES('BC1','초코쿠키 쉐이크', 4500);
INSERT into coffeemenu(menucode,menu, price)
VALUES('BC2','초코묻고더블 쉐이크', 4800);
INSERT into coffeemenu(menucode,menu, price)
VALUES('BC3','치즈가쿠키했대 쉐이크', 4800);
INSERT into coffeemenu(menucode,menu, price)
VALUES('BY1','요거트 플랫치노', 4200);
INSERT into coffeemenu(menucode,menu, price)
VALUES('BS1','딸기 플랫치노', 4200);
INSERT into coffeemenu(menucode,menu, price)
VALUES('BB1','블루베리요거트 플랫치노', 4200);
INSERT into coffeemenu(menucode,menu, price)
VALUES('BH1','꿀복숭아 플랫치노', 3500);