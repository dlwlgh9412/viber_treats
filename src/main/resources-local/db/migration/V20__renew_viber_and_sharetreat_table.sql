create table tb_brand
(
    id          varchar(100) primary key,
    brand_image varchar(255) not null
);

create table tb_product
(
    id            bigint unsigned auto_increment primary key,
    brand_id      varchar(100),
    product_name  varchar(100) not null,
    product_image varchar(255) not null,
    amount        int unsigned not null,
    foreign key (brand_id) references tb_brand (id)
);

create table tb_viber_keyboard
(
    id            bigint unsigned auto_increment primary key,
    description   varchar(50) not null,
    metadata_type varchar(20) not null,
    created_at    timestamp   not null
);

create table tb_viber_keyboard_button
(
    id             bigint unsigned auto_increment primary key,
    keyboard_id    bigint unsigned not null,
    button_columns tinyint unsigned default 6,
    check ( 1 <= button_columns and button_columns <= 6 ),
    button_rows    tinyint unsigned default 1,
    check ( 1 <= button_rows and button_rows <= 2),
    text           varchar(255),
    text_h_align   varchar(10),
    text_v_align   varchar(10),
    action_type    varchar(10),
    action_body    varchar(10)     not null,
    bg_color       varchar(20),
    image          varchar(255),
    foreign key (keyboard_id) references tb_viber_keyboard (id)
);

create table tb_viber_rich_media
(
    id                    bigint unsigned auto_increment primary key,
    description           varchar(100) not null,
    metadata_type         varchar(20)  not null,
    buttons_group_columns tinyint unsigned default 6,
    check ( 1 <= buttons_group_columns and buttons_group_columns <= 6),
    buttons_group_rows    tinyint unsigned default 7,
    check ( 1 <= buttons_group_rows and buttons_group_rows <= 7 ),
    created_at            timestamp    not null
);

create table tb_viber_rich_media_button
(
    id             bigint unsigned auto_increment primary key,
    rich_media_id  bigint unsigned not null,
    button_columns tinyint unsigned default 6,
    check ( 1 <= button_columns and button_columns <= 6 ),
    button_rows    tinyint unsigned default 7,
    check ( 1 <= button_rows and button_rows <= 7),
    action_type    varchar(20),
    action_body    varchar(20)     not null,
    image          varchar(255),
    text           varchar(255),
    text_size      varchar(10),
    text_v_align   varchar(10),
    text_h_align   varchar(10),
    foreign key (rich_media_id) references tb_viber_rich_media (id)
);

# create table text_html_tags
# (
#     id        bigint unsigned auto_increment primary key,
#     tag_name  varchar(50)  not null,
#     property  varchar(50)  not null,
#     tag_value varchar(255) not null
# );
#
# create table text_products_html_tags
# (
#
# )