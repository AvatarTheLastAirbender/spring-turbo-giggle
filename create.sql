create table todo_messages (todo_id bigint not null auto_increment, due_date varchar(255) not null, message varchar(255), status varchar(255), repeated_task bit, primary key (todo_id)) engine=InnoDB;
create table users (id bigint not null auto_increment, email varchar(255), password varchar(255), role varchar(255), name varchar(255), primary key (id)) engine=InnoDB;
