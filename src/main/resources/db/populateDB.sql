DELETE FROM user_roles;
DELETE FROM users;
-- DELETE FROM goods;
-- DELETE FROM costs;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password, datetime)
VALUES ('guest', 'juest@rambler.ru', '', '2017-04-01 10:20');

INSERT INTO users (name, email, password, datetime)
VALUES ('User', 'user@yandex.ru', 'password', '2017-04-02 15:15');

INSERT INTO users (name, email, password, datetime)
VALUES ('Admin', 'admin@gmail.com', 'admin', '2017-04-03 23:00');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_USER', 100001),
  ('ROLE_ADMIN', 100002);

INSERT INTO goods (userid, name, description, url, itemnamefromsite, cost, datetime, costelementindexes, costelementtagsandclasses)
VALUES
  ('100000',
   'HDD Seagate 2Tb',
   'DNS',
   'http://www.dns-shop.ru/product/3a9c693168163330/2-tb-zestkij-disk-seagate-barracuda-st2000dm006/',
   '2 ТБ Жесткий диск Seagate BarraCuda ST2000DM006',
   '4 599',
   '2017-04-01 20:00:00',
   '[0,1,4,1,2,3,1,0,5,0,0,0,0,1,0,0]',
   '[{"tag":"html","clazz":""},{"tag":"body","clazz":""},{"tag":"div","clazz":"container"},{"tag":"main","clazz":""},{"tag":"div","clazz":""},{"tag":"div","clazz":"price-item ec-price-item"},{"tag":"div","clazz":"node-block"},{"tag":"div","clazz":"item-header"},{"tag":"div","clazz":"col-header col-order"},{"tag":"div","clazz":"clearfix"},{"tag":"div","clazz":"col-lg-12 hidden-xs hidden-sm price-block-wrap"},{"tag":"div","clazz":"price-block"},{"tag":"div","clazz":"item-order-price"},{"tag":"div","clazz":"price"},{"tag":"div","clazz":"price_g"},{"tag":"span","clazz":""}]'),

  ('100000',
   'HDD WD 2Tb',
   'DNS',
   'http://www.dns-shop.ru/product/72ed1bba84203361/2-tb-zestkij-disk-wd-blue-wd20ezrz/',
   '2 ТБ Жесткий диск WD Blue [WD20EZRZ]',
   '4 799',
   '2017-04-01 20:00:00',
   '[0,1,4,1,2,3,1,0,5,0,0,0,0,1,0,0]',
   '[{"tag":"html","clazz":""},{"tag":"body","clazz":""},{"tag":"div","clazz":"container"},{"tag":"main","clazz":""},{"tag":"div","clazz":""},{"tag":"div","clazz":"price-item ec-price-item"},{"tag":"div","clazz":"node-block"},{"tag":"div","clazz":"item-header"},{"tag":"div","clazz":"col-header col-order"},{"tag":"div","clazz":"clearfix"},{"tag":"div","clazz":"col-lg-12 hidden-xs hidden-sm price-block-wrap"},{"tag":"div","clazz":"price-block"},{"tag":"div","clazz":"item-order-price"},{"tag":"div","clazz":"price"},{"tag":"div","clazz":"price_g"},{"tag":"span","clazz":""}]'),

  ('100000',
   'HDD Toshiba 2Tb',
   'DNS',
   'http://www.dns-shop.ru/product/4953e0465bbd3120/2-tb-zestkij-disk-toshiba-dt01aca200/',
   '2 ТБ Жесткий диск Toshiba [DT01ACA200]',
   '4 350',
   '2017-04-01 20:00:00',
   '[0,1,4,1,2,3,1,0,5,0,0,0,0,1,0,0]',
   '[{"tag":"html","clazz":""},{"tag":"body","clazz":""},{"tag":"div","clazz":"container"},{"tag":"main","clazz":""},{"tag":"div","clazz":""},{"tag":"div","clazz":"price-item ec-price-item"},{"tag":"div","clazz":"node-block"},{"tag":"div","clazz":"item-header"},{"tag":"div","clazz":"col-header col-order"},{"tag":"div","clazz":"clearfix"},{"tag":"div","clazz":"col-lg-12 hidden-xs hidden-sm price-block-wrap"},{"tag":"div","clazz":"price-block"},{"tag":"div","clazz":"item-order-price"},{"tag":"div","clazz":"price"},{"tag":"div","clazz":"price_g"},{"tag":"span","clazz":""}]'),

  ('100000',
   'HDD Seagate 2Tb',
   'Технопоинт',
   'http://technopoint.ru/product/3a9c693168163330/2-tb-zestkij-disk-seagate-barracuda-st2000dm006-sale/',
   '2 ТБ Жесткий диск Seagate BarraCuda ST2000DM006',
   '4 650',
   '2017-04-01 20:00:00',
   '[0,1,2,1,2,3,1,0,5,0,0,0,0,1,0,0]',
   '[{"tag":"html","clazz":""},{"tag":"body","clazz":""},{"tag":"div","clazz":"container"},{"tag":"main","clazz":""},{"tag":"div","clazz":""},{"tag":"div","clazz":"price-item ec-price-item"},{"tag":"div","clazz":"node-block"},{"tag":"div","clazz":"item-header"},{"tag":"div","clazz":"col-header col-order"},{"tag":"div","clazz":"clearfix"},{"tag":"div","clazz":"col-lg-12 hidden-xs hidden-sm price-block-wrap"},{"tag":"div","clazz":"price-block"},{"tag":"div","clazz":"item-order-price"},{"tag":"div","clazz":"price"},{"tag":"div","clazz":"price_g"},{"tag":"span","clazz":""}]'),

  ('100000',
   'HDD WD 2Tb',
   'Технопоинт',
   'http://technopoint.ru/product/72ed1bba84203361/2-tb-zestkij-disk-wd-blue-wd20ezrz-sale/',
   '2 ТБ Жесткий диск WD Blue [WD20EZRZ]',
   '4 750',
   '2017-04-01 20:00:00',
   '[0,1,2,1,2,3,1,0,5,0,0,0,0,1,0,0]',
   '[{"tag":"html","clazz":""},{"tag":"body","clazz":""},{"tag":"div","clazz":"container"},{"tag":"main","clazz":""},{"tag":"div","clazz":""},{"tag":"div","clazz":"price-item ec-price-item"},{"tag":"div","clazz":"node-block"},{"tag":"div","clazz":"item-header"},{"tag":"div","clazz":"col-header col-order"},{"tag":"div","clazz":"clearfix"},{"tag":"div","clazz":"col-lg-12 hidden-xs hidden-sm price-block-wrap"},{"tag":"div","clazz":"price-block"},{"tag":"div","clazz":"item-order-price"},{"tag":"div","clazz":"price"},{"tag":"div","clazz":"price_g"},{"tag":"span","clazz":""}]'),

  ('100000',
   'HDD Toshiba 2Tb',
   'Технопоинт',
   'http://technopoint.ru/product/4953e0465bbd3120/2-tb-zestkij-disk-toshiba-dt01aca200-sale/',
   '2 ТБ Жесткий диск Toshiba [DT01ACA200]',
   '4 590',
   '2017-04-01 20:00:00',
   '[0,1,2,1,2,3,1,0,5,0,0,0,0,1,0,0]',
   '[{"tag":"html","clazz":""},{"tag":"body","clazz":""},{"tag":"div","clazz":"container"},{"tag":"main","clazz":""},{"tag":"div","clazz":""},{"tag":"div","clazz":"price-item ec-price-item"},{"tag":"div","clazz":"node-block"},{"tag":"div","clazz":"item-header"},{"tag":"div","clazz":"col-header col-order"},{"tag":"div","clazz":"clearfix"},{"tag":"div","clazz":"col-lg-12 hidden-xs hidden-sm price-block-wrap"},{"tag":"div","clazz":"price-block"},{"tag":"div","clazz":"item-order-price"},{"tag":"div","clazz":"price"},{"tag":"div","clazz":"price_g"},{"tag":"span","clazz":""}]'),

  ('100000',
   'HDD Seagate 2Tb',
   'Ситилинк',
   'https://www.citilink.ru/catalog/computers_and_notebooks/hdd/hdd_in/385559/',
   'Жесткий диск SEAGATE Barracuda ST2000DM006, 2Тб, HDD, SATA III, 3.5"',
   '4430',
   '2017-04-01 20:00:00',
   '[0,1,5,0,2,0,2,0,0,2,0,0,0]',
   '[{"tag":"html","clazz":"no-js no-retina"},{"tag":"body","clazz":""},{"tag":"div","clazz":"wide nav-menu-expanded"},{"tag":"div","clazz":""},{"tag":"div","clazz":"main_content"},{"tag":"div","clazz":"main_content_wrapper"},{"tag":"aside","clazz":"product-sidebar"},{"tag":"section","clazz":"product-sidebar__box block_data__gtm-js block_data__pageevents-js"},{"tag":"div","clazz":"product_actions product_data__gtm-js product_data__pageevents-js"},{"tag":"div","clazz":"product-sidebar__line"},{"tag":"div","clazz":"product-sidebar__line-box standart_price"},{"tag":"div","clazz":"price price_break"},{"tag":"ins","clazz":"num"}]'),

  ('100000',
   'HDD WD 2Tb',
   'Ситилинк',
   'https://www.citilink.ru/catalog/computers_and_notebooks/hdd/hdd_in/318866/',
   'Жесткий диск WD Blue WD20EZRZ, 2Тб, HDD, SATA III, 3.5"',
   '4550',
   '2017-04-01 20:00:00',
   '[0,1,5,0,2,0,2,0,0,2,0,0,0]',
   '[{"tag":"html","clazz":"no-js no-retina"},{"tag":"body","clazz":""},{"tag":"div","clazz":"wide nav-menu-expanded"},{"tag":"div","clazz":""},{"tag":"div","clazz":"main_content"},{"tag":"div","clazz":"main_content_wrapper"},{"tag":"aside","clazz":"product-sidebar"},{"tag":"section","clazz":"product-sidebar__box block_data__gtm-js block_data__pageevents-js"},{"tag":"div","clazz":"product_actions product_data__gtm-js product_data__pageevents-js"},{"tag":"div","clazz":"product-sidebar__line"},{"tag":"div","clazz":"product-sidebar__line-box standart_price"},{"tag":"div","clazz":"price price_break"},{"tag":"ins","clazz":"num"}]'),

  ('100000',
   'HDD Toshiba 2Tb',
   'Ситилинк',
   'https://www.citilink.ru/catalog/computers_and_notebooks/hdd/hdd_in/714907/',
   'Жесткий диск TOSHIBA DT01ACA200, 2Тб, HDD, SATA III, 3.5"',
   '4130',
   '2017-04-01 20:00:00',
   '[0,1,5,0,2,0,2,0,0,2,0,0,0]',
   '[{"tag":"html","clazz":"no-js no-retina"},{"tag":"body","clazz":""},{"tag":"div","clazz":"wide nav-menu-expanded"},{"tag":"div","clazz":""},{"tag":"div","clazz":"main_content"},{"tag":"div","clazz":"main_content_wrapper"},{"tag":"aside","clazz":"product-sidebar"},{"tag":"section","clazz":"product-sidebar__box block_data__gtm-js block_data__pageevents-js"},{"tag":"div","clazz":"product_actions product_data__gtm-js product_data__pageevents-js"},{"tag":"div","clazz":"product-sidebar__line"},{"tag":"div","clazz":"product-sidebar__line-box standart_price"},{"tag":"div","clazz":"price price_break"},{"tag":"ins","clazz":"num"}]'),

  ('100000', 'PS4Pro', 'mVideo',
   'http://www.mvideo.ru/products/igrovaya-konsol-playstation-4-pro-1tb-cuh-7008b-40065249',
   'Игровая консоль PlayStation 4 Pro 1TB (CUH-7008B)',
   '31990',
   '2017-04-01 20:00:00',
   '[0,1,0,0,2,0,1,0,1,1,7,0,1,0,1]',
   '[{"tag":"html","clazz":"no-js"},{"tag":"body","clazz":"productDetails no-inner-shaddow"},{"tag":"div","clazz":"wrapper"},{"tag":"div","clazz":"page-content"},{"tag":"div","clazz":"main-holder sel-main-holder"},{"tag":"div","clazz":"content-top-section sel-pdp-container"},{"tag":"div","clazz":"section"},{"tag":"div","clazz":"content-top-section-layout"},{"tag":"div","clazz":"content-top-secondary-section"},{"tag":"div","clazz":"pdp-summary-wrapper"},{"tag":"div","clazz":"content-frame product-details-summary-box"},{"tag":"div","clazz":"product-details-summary-price-holder"},{"tag":"div","clazz":"product-details-summary-prices"},{"tag":"div","clazz":"product-price pricing-product-detail"},{"tag":"strong","clazz":"product-price-current sel-product-tile-price"}]');

INSERT INTO costs (goodsid, datetime, cost) VALUES
 (100003, '2017-04-01 20:00:00', 4599),
 (100003, '2017-04-02 21:00:00', 4699),
 (100003, '2017-04-03 22:00:00', 4499),

 (100004, '2017-04-01 20:00:00', 4799),
 (100004, '2017-04-02 20:10:00', 4699),
 (100004, '2017-04-03 20:10:00', 4599),

 (100005, '2017-04-01 20:00:00', 4350),
 (100005, '2017-04-02 21:20:00', 4350),
 (100005, '2017-04-03 22:20:00', 4450),

 (100006, '2017-04-01 20:00:00', 4650),
 (100006, '2017-04-02 21:30:00', 4750),
 (100006, '2017-04-03 22:30:00', 4850),

 (100007, '2017-04-01 20:00:00', 4750),
 (100007, '2017-04-02 21:40:00', 4750),
 (100007, '2017-04-03 22:40:00', 4750),

 (100008, '2017-04-01 20:00:00', 4590),
 (100008, '2017-04-02 20:50:00', 4450),
 (100008, '2017-04-03 20:50:00', 4490),

 (100009, '2017-04-01 20:00:00', 4430),
 (100009, '2017-04-02 22:00:00', 4420),
 (100009, '2017-04-03 23:00:00', 4410),

 (100010, '2017-04-01 20:00:00', 4550),
 (100010, '2017-04-02 22:10:00', 4390),
 (100010, '2017-04-03 23:10:00', 4100),

 (100011, '2017-04-01 20:00:00', 4130),
 (100011, '2017-04-02 22:20:00', 4400),
 (100011, '2017-04-03 23:20:00', 4350),

 (100012, '2017-04-01 20:00:00', 31990),
 (100012, '2017-04-02 22:30:00', 34990),
 (100012, '2017-04-03 23:30:00', 31990);

