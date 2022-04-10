INSERT INTO users (user_id,password,username) VALUES ( 1,'$2a$12$GVWikMQClUBtvi30mRoOeuQRAJc.yq.j0qKXTzOrPxdMk8TLM2jSy','admin');
INSERT INTO roles (role_id,name) VALUES ( 1, 'ADMIN' );

INSERT INTO users_roles (user_id,role_id) VALUES ( 1, 1);