create sequence customer_seq start with 1 increment by 50;
create sequence train_seq start with 1 increment by 50;
create sequence ticket_seq start with 1 increment by 50;

create table customer (
    id bigint not null unique,
    name varchar(255) not null,
    email varchar(255) not null unique,
    balance int not null,
    primary key (id)
);
create table train (
    id bigint not null unique,
    train_name varchar(255) not null unique,
    primary key (id)
);
create table ticket (
    id bigint not null unique,
    customer_id bigint not null,
    train_id bigint not null,
    purchase_time date not null,
    validity date not null,
    primary key (id),
    foreign key (customer_id) references customer (id),
    foreign key (train_id) references train (id)
);