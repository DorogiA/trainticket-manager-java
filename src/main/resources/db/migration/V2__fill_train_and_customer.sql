insert into train (id, train_name) values (1, 'Almamellék');
insert into train (id, train_name) values (2, 'Balatonfenyves');
insert into train (id, train_name) values (3, 'Debrecen');
insert into train (id, train_name) values (4, 'Felsőtárkány');
insert into train (id, train_name) values (5, 'Gemenc');
insert into train (id, train_name) values (6, 'Kemence');
insert into train (id, train_name) values (7, 'Lillafüred');
insert into train (id, train_name) values (8, 'Szilvásvárad');

insert into customer (id, name, email, balance) values (1, 'Mr Potter', 'potter@hogwarts.com', 1);
insert into customer (id, name, email, balance) values (2, 'Mr Weasley', 'weasley@hogwarts.com', 1);
insert into customer (id, name, email, balance) values (3, 'Heermiiooooneeee', 'levioooosa@hogwarts.com', 1);
insert into customer (id, name, email, balance) values (4, 'Sirius', 'prisoner@azkaban.com', 1);

insert into ticket (id, customer_id, train_id, purchase_time, validity) values (1, 1, 3, '2024-5-2', '2024-5-2');

alter sequence customer_seq restart with 100;
alter sequence train_seq restart with 100;
alter sequence ticket_seq restart with 100;