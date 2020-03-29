insert into role (id, name) values (1, 'USER');
insert into role (id, name) values (2, 'ADMIN');

insert into account(id,name,phone,email,password) values(1,'Dasha','+37522345671','dashaa@gmail.com','111');
insert into account(id,name,phone,email,password) values(2,'Anya','+37522345673','anyaa@gmail.com','222');

insert into type_tour (id, name) values (1, 'tour');

insert into travel_company (id, name,address,phone,email) values (1, 'name','aa','a','a');

insert into tour (id, name,country,exit_date,number_days,description,cost,travel_company_id,type_tour_id,image_url) values (1, 'tour','belarus','2000-02-03','2000-03-03','tour','1',1,1,'ddd');
