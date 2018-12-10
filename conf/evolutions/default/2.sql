# Insert DATA

# --- !Ups
--CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

INSERT INTO lan(id, alias, name) VALUES ('6b6ec0cf-ec62-4d56-b5e9-8d077fb2cd39', 'one', 'One Lan');
INSERT INTO lan(id, alias, name) VALUES ('41bb98ca-0852-4688-9700-3627c23aac9d', 'two', 'Two Lan');

INSERT INTO article(id, lanid, timestamp, header, body) VALUES ('f91c59c1-237e-4614-a267-47c55968cee0', '6b6ec0cf-ec62-4d56-b5e9-8d077fb2cd39', CURRENT_TIMESTAMP, 'Number One', 'Lorem ipsum');
INSERT INTO article(id, lanid, timestamp, header, body) VALUES ('75444ab0-9059-444f-aa39-a00d2a7adb94', '6b6ec0cf-ec62-4d56-b5e9-8d077fb2cd39', CURRENT_TIMESTAMP, 'Number Two', 'Lorem ipsum');
INSERT INTO article(id, lanid, timestamp, header, body) VALUES ('b560ed35-a861-4693-95b2-07e3313dbbbc', '6b6ec0cf-ec62-4d56-b5e9-8d077fb2cd39', CURRENT_TIMESTAMP, 'Number Three', 'Lorem ipsum');
INSERT INTO article(id, lanid, timestamp, header, body) VALUES ('cffd7b90-7293-45a6-9b47-12b974012021', '41bb98ca-0852-4688-9700-3627c23aac9d', CURRENT_TIMESTAMP, 'Number Four', 'Lorem ipsum');
INSERT INTO article(id, lanid, timestamp, header, body) VALUES ('f607bfa4-5c3e-4c15-b8fe-fb1983ace78b', '41bb98ca-0852-4688-9700-3627c23aac9d', CURRENT_TIMESTAMP, 'Number Five', 'Lorem ipsum');

--INSERT INTO lanarticle(articleid, lanid) VALUES ('f91c59c1-237e-4614-a267-47c55968cee0', '6b6ec0cf-ec62-4d56-b5e9-8d077fb2cd39');
--INSERT INTO lanarticle(articleid, lanid) VALUES ('75444ab0-9059-444f-aa39-a00d2a7adb94', '6b6ec0cf-ec62-4d56-b5e9-8d077fb2cd39');
--INSERT INTO lanarticle(articleid, lanid) VALUES ('b560ed35-a861-4693-95b2-07e3313dbbbc', '6b6ec0cf-ec62-4d56-b5e9-8d077fb2cd39');
--INSERT INTO lanarticle(articleid, lanid) VALUES ('cffd7b90-7293-45a6-9b47-12b974012021', '41bb98ca-0852-4688-9700-3627c23aac9d');
--INSERT INTO lanarticle(articleid, lanid) VALUES ('f607bfa4-5c3e-4c15-b8fe-fb1983ace78b', '41bb98ca-0852-4688-9700-3627c23aac9d');

# --- !Downs