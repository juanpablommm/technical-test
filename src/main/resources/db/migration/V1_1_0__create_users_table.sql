-- enum for gender
CREATE TYPE public.gender AS ENUM (
	'FEMININE',
	'MALE');

-- Table
CREATE TABLE public.users (
	id serial NOT NULL,
	first_name varchar(500) NOT NULL,
	last_name varchar(500) NOT NULL,
	email varchar(50) NOT NULL,
	gender public.gender NOT NULL,
	password varchar(500) NOT NULL,
	CONSTRAINT email_unique UNIQUE (email),
	CONSTRAINT users_pk PRIMARY KEY (id)
);
