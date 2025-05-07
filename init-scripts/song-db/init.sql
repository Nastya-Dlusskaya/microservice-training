drop table if exists song;

create table song_metadata
(
    id       bigint not null primary key,
    name     text,
    artist   text,
    album    text,
    duration text,
    year     text
);