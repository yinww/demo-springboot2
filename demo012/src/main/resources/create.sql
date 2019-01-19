create table province
(
   id                   int not null auto_increment,
   name                 varchar(64),
   code                 varchar(32),
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

create table city
(
   id                   int not null auto_increment,
   province_id          int,
   name                 varchar(64),
   code                 varchar(32),
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;
