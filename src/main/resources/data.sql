insert into users
  (full_name,email,password)
values('Douglas Morato', 'dmoratos@gmail.com', '123');
insert into users
  (id, full_name, email, password, created_date)
values
  (6001, 'Bruno', 'bruno@gmail.com', '123456', now());

insert into users
  (id, full_name, email, password, created_date)
values
  (6002, 'Fernando', 'fernando@email.com', '123456', now());

insert into users
  (id, full_name, email, password, created_date)
values
  (6003, 'Marcela', 'marcela@email.com', '123456', now());

insert into users
  (id, full_name, email, password, created_date)
values
  (6004, 'Geovanna', 'geovanna@email.com', '123456', now());

insert into users
  (id, full_name, email, password, created_date)
values
  (6005, 'Alisson', 'alisson@email.com', '123456', now());

insert into event_logs
  (id,level,event_log,description,origin,event_date,quantity)
values
  (6001, 'error', 'teste de event log 1', 'descricao do evento log 1', 'origin 1', '2020-06-11', 1);

insert into event_logs
  (id,level,event_log,description,origin,event_date,quantity)
values
  (6002, 'warning', 'teste de event log 2', 'descricao do evento log 2', 'origin 2', '2020-06-11', 2);

insert into event_logs
  (id,level,event_log,description,origin,event_date,quantity)
values
  (6003, 'info', 'teste de event log 3', 'descricao do evento log 3', 'origin 3', '2020-06-11', 1);

insert into event_logs
  (id,level,event_log,description,origin,event_date,quantity)
values
  (6004, 'error', 'teste de event log 4', 'descricao do evento log 4', 'origin 4', '2020-06-11', 1);