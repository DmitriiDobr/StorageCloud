create table users
(
    id serial NOT NULL ,
    username varchar(255) not null,
    password varchar(255) not null,
    role varchar(255) not null,
    active bool,
    primary key (id)

);

create table if not exists storage_files
(
    id       integer      not null
        primary key,
    content  bytea        not null,
    filename varchar(255) not null
        constraint uk_aivjqvtf0lr6uv36uwca4skj4
            unique,
    time     timestamp(6),
    users_id integer
        constraint fk1hugel43r0yrbwcg82him2rux
            references users
);



create table if not exists users_user_files
(
    users_id      integer not null
        constraint fk8nq6ljbrfjl8w08aaawv58f4d
            references users,
    user_files_id integer not null
        constraint uk_jkvbrno9i2lr8uikdv4k4iw9v
            unique
        constraint fk8ifqr7e6kroddk6b5fxstf6g2
            references storage_files
);

insert into users (username, password, role, active)
values ('user@one.user', '$2a$10$h7QmnlSEXLF2eQS4lIlRX.FXGZ0U8tpcLYi8IPkgLG/sqQiDA/HlG','ADMIN',True); -- pass: user_one


insert into users (username, password, role, active)
values ('user@two.user', '$2a$10$kNOJuKpknuUJPnOinunYDOEyJq2v3qXvyiaCMoS2CKOobT8Mfi8Da','ADMIN',TRUE); -- pass: user_two