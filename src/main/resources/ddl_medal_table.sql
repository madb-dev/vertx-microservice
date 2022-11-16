-- public.medal_table definition

-- Drop table

-- DROP TABLE public.medal_table;

CREATE TABLE public.medal_table (
	id serial4 NOT NULL,
	state varchar(255) NULL,
	total int4 NULL,
	CONSTRAINT medal_table_pkey PRIMARY KEY (id)
);