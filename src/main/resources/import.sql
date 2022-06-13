insert into operadores(id, operador, valor_plan)
values (300, 'Tigo', 20000), (301, 'Tigo', 20000), (302, 'Uff Móvil ', 15000), (303, 'Uff Móvil ', 15000),
(304, 'Uff Móvil ', 15000), (305, 'Móvil éxito', 10000), (310, 'Claro', 25000),
(311, 'Claro', 25000),(312, 'Claro', 25000),(313, 'Claro', 25000),(314, 'Claro', 25000),(315, 'Movistar', 20000),
(316, 'Movistar', 20000),(317, 'Movistar', 20000),(318, 'Movistar', 20000), (319, 'Virgin', 18000),
(320, 'Claro', 25000), (321, 'Claro', 25000);
insert into turnos (horarios) values ('6 p.m. - 2 p.m.'), ('2 p.m. - 10 p.m.'), ('10 p.m. - 6 a.m.');

insert into empleados (cedula, celular, nombre, correo, estrato, sueldo, fecha_nacimiento, edad, turno_id, operador_id)
values ('1019123123', '302123123', 'Camilo Andres', 'camilo@gmail.com', 1, 10000, '2022-09-23', 19, 1, 1);

