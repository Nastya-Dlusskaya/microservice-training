drop table if exists resource;

create table files
(
    id      bigint not null primary key,
    payload bytea
);