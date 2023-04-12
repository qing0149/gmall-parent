# E--business
-- 索引集合:

create index idx_spu_id on spu_image (spu_id);
create index idx_spu_id on spu_poster (spu_id);
create index idx_spu_id on spu_sale_attr (spu_id);
create index idx_spu_id_base_sale_attr_id on spu_sale_attr_value (spu_id, base_sale_attr_id);
create index idx_attr_id on base_attr_value (attr_id);
create index idx_trademark_id_category3_id on base_category_trademark (trademark_id,category3_id);
create index idx_sku_id_is_deleted on sku_image (sku_id, is_deleted);
create index idx_sku_id_is_deleted on sku_sale_attr_value (sku_id, is_deleted);
create index idx_sku_id_is_deleted on sku_attr_value (sku_id, is_deleted);
create index id_id_is_deleted on sku_info (id, is_deleted);
--创建视图
CREATE
OR REPLACE VIEW base_category_view AS SELECT
bc3.id,
bc1.id category1_id,
bc1.NAME category1_name,
bc2.id category2_id,
bc2.NAME category2_name,
bc3.id category3_id,
bc3.NAME category3_name
FROM
base_category1 bc1
INNER JOIN base_category2 bc2 ON bc2.category1_id = bc1.id
INNER JOIN base_category3 bc3 ON bc3.category2_id = bc2.id 

