create table orders
(
    id             bigserial    not null primary key,
    courier_id     bigint,
    customer_id    bigint       not null,
    shop_id        bigint       not null,
    order_status   varchar(255) not null,
    generated_date timestamp(6) not null
);

create table order_items
(
    id           bigserial not null primary key,
    price        integer   not null,
    quantity     integer   not null,
    shop_item_id bigint    not null,
    order_id     bigint    not null,
    constraint FKbioxgbv59vetrxe0ejfubep1w foreign key (order_id) references orders (id)
);

create sequence hibernate_sequence;