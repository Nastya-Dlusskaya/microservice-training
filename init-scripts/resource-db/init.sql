drop table if exists files;

create table files
(
    id      serial not null primary key,
    data    bytea
);