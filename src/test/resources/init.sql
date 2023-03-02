-- auto-generated definition
create table if not exists fruit (
    id           serial,
    name         varchar(50)
);

INSERT INTO public.fruit(name) VALUES ('Banana');
INSERT INTO public.fruit(name) VALUES ('Apple');
INSERT INTO public.fruit(name) VALUES ('Orange');
