drop table if exists Customers;
drop table if exists Orders;
drop table if exists Order_items;

create table Customers
(
    id        bigserial    not null unique primary key,
    email     varchar(255) not null unique,
    full_name varchar(255) not null,
    phone     varchar(255) not null unique
);

create table Orders
(
    id             bigserial not null unique primary key,
    courier_id     bigint    not null,
    customer_id    bigint    not null,
    shop_id        bigint    not null,
    order_status   varchar(255),
    generated_date timestamp(6)
);

create table Order_items
(
    id        bigserial      not null unique primary key,
    price     numeric(38, 2) not null,
    quantity  integer        not null,
    shop_item varchar(255)   not null,
    order_id  bigint,
    constraint FKbioxgbv59vetrxe0ejfubep1w foreign key (order_id) references orders
);

-- alter table if exists customers add constraint UK_rfbvkrffamfql7cjmen8v976v unique (email)
-- alter table if exists customers add constraint UK_m3iom37efaxd5eucmxjqqcbe9 unique (phone)
-- alter table if exists order_items add constraint FKbioxgbv59vetrxe0ejfubep1w foreign key (order_id) references orders