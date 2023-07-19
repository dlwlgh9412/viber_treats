alter table tb_product
    drop foreign key tb_product_ibfk_1;
alter table tb_brand
    modify id bigint unsigned auto_increment;
alter table tb_brand
    add column brand_name varchar(100) not null;
alter table tb_brand
    modify brand_image varchar(255);
alter table tb_product
    modify brand_id bigint unsigned;
alter table tb_product
    add constraint tb_product_ibfk_1 foreign key (brand_id) references tb_brand (id);