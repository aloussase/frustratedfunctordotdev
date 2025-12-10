create table book_tags
(
    id   serial primary key,
    name text not null
);

create table books
(
    id     serial primary key,
    author text not null,
    title  text not null,
    status text not null check
        ( status in ('reading', 'read', 'wishlist') )
);

create table books_tags
(
    book_id integer not null
        references books (id),
    tag_id  integer not null
        references book_tags (id)
);
