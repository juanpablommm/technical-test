-- public.refresh_token definition

-- Drop table

-- DROP TABLE public.refresh_token;

CREATE TABLE public.refresh_token (
	id serial4 NOT NULL,
	refresh_token text NOT NULL,
	expiry_time timestamp NOT NULL,
	id_user int4 NOT NULL,
	CONSTRAINT auth_pk PRIMARY KEY (id),
	CONSTRAINT auth_unique UNIQUE (refresh_token),
	CONSTRAINT auth_unique_1 UNIQUE (id_user)
);


-- public.refresh_token foreign keys

ALTER TABLE public.refresh_token ADD CONSTRAINT refresh_token_users_fk FOREIGN KEY (id_user) REFERENCES public.users(id);