create table orders
(
    id             bigserial not null unique primary key,
    courier_id     bigint    not null,
    customer_id    bigint    not null,
    shop_id        bigint    not null,
    order_status   varchar(255),
    generated_date timestamp(6)
);

create table order_items
(
    id         bigserial      not null unique primary key,
    price      numeric(38, 2) not null,
    quantity   integer        not null,
    shop_item  varchar(255)   not null,
    image_link varchar(255)   not null,
    order_id   bigint,
    constraint conKeyOrders foreign key (order_id) references orders
);

create sequence hibernate_sequence;
