insert into orders (id, courier_id, customer_id, shop_id, order_status, generated_date)
values (1, 1, 1, 1, 'CUSTOMER_CREATED', CURRENT_DATE);

insert into order_items (id, price, quantity, shop_item, image_link, order_id)
values (1, 200, 4, 'shop item info here', 'somelink', 1);
insert into order_items (id, price, quantity, shop_item, image_link, order_id)
values (2, 100, 2, 'shop item info here 2', 'somelink 2', 1);
insert into order_items (id, price, quantity, shop_item, image_link, order_id)
values (3, 150, 9, 'shop item info here 3', 'somelink 3', 1);

