-- create database
create database if not exists api;

-- switch database
use api;

-- user table
create table if not exists user
(
    id          varchar(64)                        not null primary key,
    username    varchar(256)                       not null,
    nickname    varchar(256)                       null,
    avatar      varchar(2048)                      null,
    role        tinyint  default 0                 not null comment 'role: - 0 user - 1 admin',
    password    varchar(512)                       not null,
    access_key  varchar(512)                       not null,
    secret_key  varchar(512)                       not null,
    create_time datetime default CURRENT_TIMESTAMP not null,
    update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    deleted     tinyint  default 0                 not null
) comment 'user';

-- interface info table
create table if not exists interface_info
(
    id              varchar(64)                        not null primary key,
    name            varchar(256)                       not null,
    description     varchar(256)                       null,
    url             varchar(512)                       not null,
    request_params  text                               null,
    request_header  text                               null,
    response_header text                               null,
    status          int      default 0                 not null comment 'status: - 0 close - 1 open',
    method          varchar(256)                       not null,
    creator_id      varchar(64)                        not null comment 'creator id',
    create_time     datetime default CURRENT_TIMESTAMP not null,
    update_time     datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    deleted         tinyint  default 0                 not null
) comment 'interface info';

-- interface user relation table
create table if not exists user_interface_info
(
    id           varchar(64)                        not null primary key,
    user_id      varchar(64)                        not null,
    interface_id varchar(64)                        not null,
    left_num     int      default 0                 not null comment 'remain call',
    status       int      default 0                 not null comment 'status: - 0 working - 1 ban',
    create_time  datetime default CURRENT_TIMESTAMP not null,
    update_time  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    deleted      tinyint  default 0                 not null
) comment 'user interface relation';
