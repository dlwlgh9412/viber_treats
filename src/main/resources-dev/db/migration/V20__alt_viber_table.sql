alter table tb_viber_keyboard
    add column bg_color varchar(10);
alter table tb_viber_keyboard
    add column default_height boolean default false;
alter table tb_viber_keyboard
    add column custom_default_height int;
alter table tb_viber_keyboard
    add column height_scale int;
alter table tb_viber_keyboard
    add column buttons_group_columns int;
alter table tb_viber_keyboard
    add column buttons_group_rows int;
alter table tb_viber_keyboard
    add column input_field_state varchar(10);

alter table tb_viber_keyboard
    add check ( 1 <= buttons_group_columns and buttons_group_columns <= 6 );
alter table tb_viber_keyboard
    add check ( 1 <= buttons_group_rows and buttons_group_rows <= 7 );
alter table tb_viber_keyboard
    add check ( 20 <= height_scale and height_scale <= 100 );
alter table tb_viber_keyboard
add check ( 40 <= custom_default_height and custom_default_height <= 70 );