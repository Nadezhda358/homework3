create table if not exists hotels(
id int not null auto_increment primary key,
name varchar(60),
location varchar(200),
rooms_count int(11),
stars_count int(11)
);