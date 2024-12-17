create table students
(
    id    int auto_increment,
    name  varchar(64) null,
    email varchar(64) null,
    constraint students_pk
        primary key (id)
);

create table borrows
(
    student_id  int  not null,
    book_id     int  not null,
    borrow_date date null,
    return_date date null,
    constraint borrows_pk
        primary key (student_id, book_id),
    constraint borrows___fk_book
        foreign key (book_id) references books (id),
    constraint borrows___fk_student
        foreign key (student_id) references students (id)
);