insert into role (id, name) values (1, 'ROLE_USER');
insert into role (id, name) values (2, 'ROLE_ADMIN');

insert into account(id,name,phone,email,password) values(1,'Dasha','+37522345671','dashaa@gmail.com','111');
insert into account(id,name,phone,email,password) values(2,'Anna','+37522345673','anyaa@gmail.com','222');

insert into account_to_role (role_id, account_id) values (1, 1);
insert into account_to_role (role_id, account_id) values (2, 1);

insert into travel_company (id, name,address,phone,email) values (1, 'name','aa','a','a');

insert into tour (id, name,country,exit_date,number_days,cost,rating,travel_company_id,type,image_url) values (1, 'tour','belarus','2000-02-03','2000-03-03','1',3,1,'BUS_TOUR','ddd');
