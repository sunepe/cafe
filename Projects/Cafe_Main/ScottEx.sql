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


select sum(price) from coffee where payway like '����';

select sum(price), SUBSTR(ordertime,1,13) from coffee where payway like 'ī��' group by SUBSTR(ordertime,1,13);
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

select payway, menu, price, ordertime from coffee where ordertime like '2020�� 06�� 04��%';

select menu , count(*)  from coffee group by menu order by count(*) desc;

CREATE TABLE coffeemenu(
menucode VARCHAR2(50)PRIMARY KEY,
menu VARCHAR2(50),
price NUMBER(13));

select * from coffeemenu;

drop table coffeemenu;

INSERT into coffeemenu(menucode, menu, price)
VALUES('IA1','ICE �Ƹ޸�ī��', 3200);
INSERT into coffeemenu(menucode,menu, price)
VALUES('HA1','�Ƹ޸�ī��', 3200);
INSERT into coffeemenu(menucode,menu, price)
VALUES('IC1','ICE ī���', 3700);
INSERT into coffeemenu(menucode,menu, price)
VALUES('HC1','ī���', 3700);
INSERT into coffeemenu(menucode,menu, price)
VALUES('IM1','ICE ī���ī', 3900);
INSERT into coffeemenu(menucode,menu, price)
VALUES('HM1','ī���ī', 3900);
INSERT into coffeemenu(menucode,menu, price)
VALUES('IB1','ICE �ٴҶ��', 3900);
INSERT into coffeemenu(menucode,menu, price)
VALUES('HB1','�ٴҶ��', 3900);
INSERT into coffeemenu(menucode,menu, price)
VALUES('IC2','ICE īǪġ��', 3700);
INSERT into coffeemenu(menucode,menu, price)
VALUES('HC2','īǪġ��', 3700);
INSERT into coffeemenu(menucode,menu, price)
VALUES('BO1','������ ����ũ', 4300);
INSERT into coffeemenu(menucode,menu, price)
VALUES('BD1','���� ����ũ', 4800);
INSERT into coffeemenu(menucode,menu, price)
VALUES('BC1','������Ű ����ũ', 4500);
INSERT into coffeemenu(menucode,menu, price)
VALUES('BC2','���ڹ������ ����ũ', 4800);
INSERT into coffeemenu(menucode,menu, price)
VALUES('BC3','ġ���Ű�ߴ� ����ũ', 4800);
INSERT into coffeemenu(menucode,menu, price)
VALUES('BY1','���Ʈ �÷�ġ��', 4200);
INSERT into coffeemenu(menucode,menu, price)
VALUES('BS1','���� �÷�ġ��', 4200);
INSERT into coffeemenu(menucode,menu, price)
VALUES('BB1','��纣�����Ʈ �÷�ġ��', 4200);
INSERT into coffeemenu(menucode,menu, price)
VALUES('BH1','�ܺ����� �÷�ġ��', 3500);