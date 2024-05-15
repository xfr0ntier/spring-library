CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE public.tbl_patron (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),

    name TEXT NOT NULL,
    phone TEXT NOT NULL,
    email TEXT,
    address TEXT,

    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP
);

CREATE TABLE public.tbl_book (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),

    title TEXT NOT NULL,
    author TEXT NOT NULL,
    pub_year INTEGER,
    isbn TEXT,
    edition TEXT,
    language TEXT,

    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP
);

CREATE TABLE public.tbl_borrow_record (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),

    book_id UUID NOT NULL REFERENCES public.tbl_book(id),
    patron_id UUID NOT NULL REFERENCES public.tbl_patron(id),

    borrowed_at TIMESTAMP NOT NULL DEFAULT NOW(),
    returned_at TIMESTAMP,

    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP
);
