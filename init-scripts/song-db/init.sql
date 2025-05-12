drop table if exists song_metadata;

create table song_metadata
(
    id       serial not null primary key,
    song_id  bigint,
    name     text,
    artist   text,
    album    text,
    duration text,
    year     text
);