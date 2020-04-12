create table account
(
    id       bigint(6) auto_increment
        primary key,
    phone    varchar(20)  not null,
    name     varchar(45)  not null,
    email    varchar(60)  not null,
    password varchar(100) not null,
    constraint user_email_uindex
        unique (email),
    constraint user_phone_uindex
        unique (phone)
);

create table account_to_role
(
    role_id    bigint not null,
    account_id bigint not null,
    primary key (account_id, role_id)
);


create table role
(
    id   bigint auto_increment
        primary key,
    name enum ('ROLE_ADMIN', 'ROLE_USER','ROLE_COMPANY') null
);

create table travel_company
(
    id      bigint(6) auto_increment,
    name    varchar(45) not null,
    address varchar(45) not null,
    phone   varchar(45) not null,
    email   varchar(45) null,
    constraint address_UNIQUE
        unique (address),
    constraint email_UNIQUE
        unique (email),
    constraint name_UNIQUE
        unique (name),
    constraint phone_UNIQUE
        unique (phone)
);

alter table travel_company
    add primary key (id);

create table tour
(
    id                bigint(6)                                       not null primary key,
    name              varchar(255)                                    not null,
    country           varchar(100)                                    not null,
    exit_date         date                                            not null,
    number_days       varchar(10)                                     not null,
    description       mediumtext                                      null,
    cost              varchar(10)                                     not null,
    travel_company_id bigint(6)                                       not null,
    type              enum ('BUS_TOUR', 'AIR_TOUR','LAST_OFFER_TOUR') not null,
    image_url         varchar(100)                                    null,
    rating            int                                             null,
    constraint id_UNIQUE
        unique (id),
    constraint name_UNIQUE_tour
        unique (name),
    constraint tour_travel_company__fk
        foreign key (travel_company_id) references travel_company (id)

);

create index fk_tour_travel_company_idx
    on tour (travel_company_id);

create table comments
(
    id         bigint(6) auto_increment,
    message    varchar(255) not null,
    rating     int          not null,
    time       date         null,
    account_id bigint(6)    not null,
    tour_id    bigint(6)    not null,

    constraint comments_tour__fk
        foreign key (tour_id) references tour (id),
    constraint comments_user__fk
        foreign key (account_id) references account (id)
);

create index fk_comments_client1_idx
    on comments (account_id);

create index fk_comments_tour1_idx
    on comments (tour_id);

alter table comments
    add primary key (id);

create table reservation
(
    id            bigint(6) auto_increment,
    tour_id       bigint(6)   not null,
    account_id    bigint(6)   not null,
    number_person int         not null,
    phone         varchar(45) not null,
    username      varchar(45) not null,
    constraint id_UNIQUE_reservation
        unique (id),
    constraint fk_tour_has_client_tour1
        foreign key (tour_id) references tour (id),
    constraint reservation_user__fk
        foreign key (account_id) references account (id)
);

create index fk_tour_has_client_client1_idx
    on reservation (account_id);

create index fk_tour_has_client_tour1_idx
    on reservation (tour_id);

alter table reservation
    add primary key (id);

create table tour_description
(
    id          bigint auto_increment
        primary key,
    description mediumtext not null,
    day_number  int        not null,
    id_tour     bigint     null,
    constraint tour_description_tour__fk
        foreign key (id_tour) references tour (id)
);
