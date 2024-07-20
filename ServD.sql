/*
ServD
Fecha de creación: 05/07/2023
Fecha de finalización 07/07/2023
*/

drop database if exists DBServD;
create database DBServD;
use DBServD;


create table Proveedor(
	codigoProveedor int not null auto_increment,
    nombreProveedor varchar(100) not null,
    direccionProveedor varchar(100) not null,
    telefonoProveedor varchar(8) not null,
    primary key PK_codigoProveedor(codigoProveedor)
);
create table Equipo(
	codigoEquipo int not null auto_increment,
    nombreEquipo varchar(100) not null,
    descripcionEquipo varchar(100) not null,
    cantidad int not null,
    primary key PK_codigoEquipo(codigoEquipo)
);

Create table Proveedor_has_Equipo(
	codigoProveedor_has_Equipo int not null auto_increment, 
    descripcionProveedor varchar(100) not null, 
    codigoProveedor int not null, 
    codigoEquipo int not null, 
    primary key PK_codigoProveedor_has_Equipo(codigoProveedor_has_Equipo), 
    constraint FK_Proveedor_has_Equipo_Proveedor foreign key(codigoProveedor)
		references Proveedor(codigoProveedor), 
	constraint FK_Proveedor_has_Equipo_Equipo foreign key(codigoEquipo)
		references Equipo(codigoEquipo)
);

create table TipoServicio(
	codigoTipoServicio int not null auto_increment,
    tipoServicio varchar(50) not null,
    descripcion varchar(100) not null,
    precioTipoServicio decimal(10,2) not null,
    primary key PK_codigoTipo_servicio(codigoTipoServicio)
);
create table Servicio(
	codigoServicio int not null auto_increment,
    lugarServicio varchar(100) not null,
    numeroServicio varchar(8)not null,
    horaServicio time not null,
    fechaServicio Date not null,
    codigoTipoServicio int not null,
    primary key PK_codigoServicio(codigoServicio),
    constraint FK_Servicio_TipoServicio_ foreign key(codigoTipoServicio)
		references TipoServicio(codigoTipoServicio)
);

create table MedioTransporte(
	codigoTransporte int not null auto_increment,
    placa varchar(8) not null,
    tipoVehiculo varchar(100) not null,
    marca varchar(50) not null,
	primary key PK_codigoTransporte(codigoTransporte)
);
create table Empresa(
	codigoEmpresa int not null auto_increment,
    nombreEmpresa varchar(100) not null,
    telefonoEmpresa varchar(8) not null,
    direccionEmpresa varchar(100) not null,
    estadoEmpresa varchar(1) not null,
    primary key PK_codigoEmpresa(codigoEmpresa)
);
create table Compra(
	codigoCompra int not null auto_increment,
    costoCompra decimal(10,2) not null,
    descripcionCompra varchar(100) not null,
    fechaCompra date not null,
    codigoEmpresa int not null,
    primary key PK_codigoCompra(codigoCompra),
	constraint FK_Compra_Empresa foreign key(codigoEmpresa)
		references Empresa(codigoEmpresa)
);

Create table Servicio_has_Compra(
	Servicio_codigoCompra int not null auto_increment, 
	descripcionDetalle varchar(100) not null, 
    codigoServicio int not null, 
    codigoCompra int not null, 
    primary key PK_Servicio_codigoCompra(Servicio_codigoCompra), 
    constraint FK_Servicio_has_Compra_Servicio foreign key(codigoServicio)
		references Servicio(codigoServicio), 
	constraint FK_Servicio_has_Compra_Compra foreign key(codigoCompra)
		references Compra(codigoCompra)
);

create table TipoEmpleado(
	codigoTipoEmpleado int not null auto_increment,
    descripcionTipoEmpleado varchar(100) not null,
    categoria varchar(50) not null,
    sueldo decimal(10,2) not null,
    primary key PK_codigoTipoEmpleado(codigoTipoEmpleado)
);
create table Empleado(
	codigoEmpleado int not null auto_increment, 
	usuario varchar(15) not null,
    DPIEmpleado varchar(15) not null,
    nombresEmpleado varchar(100) not null,
    apellidosEmpleado varchar(100) not null,
    telefonoContacto varchar(8) not null,
    codigoTipoEmpleado int not null,
    codigoTransporte int not null, 
    codigoEquipo int not null,
    foto longblob,
    primary key PK_codigoEmpleado(codigoEmpleado),
    constraint FK_Empleado_TipoEmpleado foreign key(codigoTipoEmpleado)
		references tipoEmpleado(codigoTipoEmpleado),
	constraint FK_Empleado_Transporte foreign key(codigoTransporte)
		references medioTransporte(codigoTransporte),
	constraint FK_Empleado_Equipo foreign key(codigoEquipo)
		references equipo(codigoEquipo)
);

create table Empleados_has_Servicios(
	codigoEmpleados_has_servicios int not null auto_increment,
    costoServicio decimal(10,2) not null,
    codigoEmpleado int not null,
    codigoServicio int not null,
    primary key PK_codigoEmpleados_has_servicios(codigoEmpleados_has_servicios),
    constraint FK_empleado_has_servicio_Empleado foreign key(codigoEmpleado) 
		references Empleado(codigoEmpleado),
	constraint FK_servicio_has_servicio_Servicio foreign key(codigoServicio)
		references Servicio(codigoServicio)
    
);
create table Equipo_has_Empleado(
	Equipo_codigoEmpleado int not null auto_increment,
    codigoEquipo int not null,
    codigoEmpleado int not null,
    cantidadEquipo int not null,
    primary key PK_equipoEmpleado_Equipo(Equipo_codigoEmpleado),
	constraint FK_Equipo_has_Empleado_Equipo foreign key(codigoEquipo)
		references Equipo(codigoEquipo),
	constraint FK_Equipo_has_Empleado_Empleado foreign key(codigoEmpleado)
		references Empleado(codigoEmpleado)
);
show tables;

-- -------------------------------- Agregar Datos ---------------------------------------
-- --Proveedor-- --
insert into Proveedor(nombreProveedor, direccionProveedor, telefonoProveedor) values ('Compu Bodegas', 'Zona 10', '67543289');
insert into Proveedor(nombreProveedor, direccionProveedor, telefonoProveedor) values ('Vision Consultores S.A.', 'Zona 8 de mixco', '87654323');
-- --Equipo-- --
insert into Equipo(nombreEquipo, descripcionEquipo, cantidad) values ('Herramientas', 'Diferentes herramientas para trabajo', 2000);
insert into Equipo(nombreEquipo, descripcionEquipo, cantidad) values ('Computadora', 'PC de muchos recursos', 300);
insert into Equipo(nombreEquipo, descripcionEquipo, cantidad) values ('Memoria Ram', '16 Ram', 4000);
insert into Equipo(nombreEquipo, descripcionEquipo, cantidad) values ('Disco Duro', '512GB', 1000);
insert into Equipo(nombreEquipo, descripcionEquipo, cantidad) values ('Mouse', 'Inalambrico HP', 180);

-- --Proveedor_Has_Equipo-- --
insert into Proveedor_has_Equipo(descripcionProveedor, codigoProveedor, codigoEquipo) values ('Distribuidora de Computadoras y sus partes', 1, 1);
insert into Proveedor_has_Equipo(descripcionProveedor, codigoProveedor, codigoEquipo) values ('Distribuidora de diferentes herramientas y utensilios', 2, 2);
insert into Proveedor_has_Equipo(descripcionProveedor, codigoProveedor, codigoEquipo) values ('Distribuidora de mantenimiento de computadoras', 1, 1);
insert into Proveedor_has_Equipo(descripcionProveedor, codigoProveedor, codigoEquipo) values ('Distribuidora de Microsoft ', 2, 2);
insert into Proveedor_has_Equipo(descripcionProveedor, codigoProveedor, codigoEquipo) values ('Distribuidora de Sotfware ', 1, 2);



-- --Tipo Servicio-- --
insert into tipoServicio(tipoServicio, descripcion, precioTipoServicio) values ('Reparacion', 'Reparacion de algun electrodomestico o Computadora', 350);
insert into tipoServicio(tipoServicio, descripcion, precioTipoServicio) values ('Mantenimiento', 'mantenimiento de hardware', 250);
insert into tipoServicio(tipoServicio, descripcion, precioTipoServicio) values ('Limpieza', 'Limpieza de algun electrodomestico o Computadora', 250);
insert into tipoServicio(tipoServicio, descripcion, precioTipoServicio) values ('Programacion', 'Programación de software', 500);
insert into tipoServicio(tipoServicio, descripcion, precioTipoServicio) values ('Técnico', 'servicio técnico', 400);
insert into tipoServicio(tipoServicio, descripcion, precioTipoServicio) values ('Reparación', 'reparación de electrodomesticos', 350);
-- --Servicio-- --
insert into Servicio(lugarServicio, numeroServicio, horaServicio, fechaServicio, codigoTipoServicio)
	values('kinal', '1234567', '15:00:00', '2023-04-04', 1);
insert into Servicio(lugarServicio, numeroServicio, horaServicio, fechaServicio, codigoTipoServicio) values ('Burguer King cc Miraflores', 2, '14:00:00', '2023-08-01', 2);
-- --Medio Transporte-- --
insert into MedioTransporte(placa, tipoVehiculo, marca) values ('021EEE', 'Hilux', 'Toyota');
insert into MedioTransporte(placa, tipoVehiculo, marca) values('001AAA','Sedán', 'Honda');
-- --Empresa-- --
insert into Empresa(nombreEmpresa, telefonoEmpresa, direccionEmpresa, estadoEmpresa) values('Guatex', '23456789', 'Carretera al Salvador', '1');
insert into Empresa(nombreEmpresa, telefonoEmpresa, direccionEmpresa, estadoEmpresa) values('Seur', '45628797', 'colonia landivar', '1');
insert into Empresa(nombreEmpresa, telefonoEmpresa, direccionEmpresa, estadoEmpresa) values('Nacex', '11884447', 'Ciudad de guatemala', '1');
insert into Empresa(nombreEmpresa, telefonoEmpresa, direccionEmpresa, estadoEmpresa) values('Fedex', '24112100', 'Atanasio Tzu', '1');
insert into Empresa(nombreEmpresa, telefonoEmpresa, direccionEmpresa, estadoEmpresa) values('DHL', '67190276', 'Carretera al Pacifico', '1');
-- --Compra-- --
insert into Compra(costoCompra, descripcionCompra, fechaCompra, codigoEmpresa) values(600, 'Servicio de mantenimiento', 
	'2023-09-02', 2);
insert into Compra(costoCompra, descripcionCompra, fechaCompra, codigoEmpresa) values(650, 'Servicio de limpieza', 
	'2023-10-04', 1);
insert into Compra(costoCompra, descripcionCompra, fechaCompra, codigoEmpresa) values(2000, 'Servicio de programacion', 
	'2023-10-04', 2);
insert into Compra(costoCompra, descripcionCompra, fechaCompra, codigoEmpresa) values(200, 'Servicio de técnico', 
	'2023-10-04', 1);
insert into Compra(costoCompra, descripcionCompra, fechaCompra, codigoEmpresa) values(80, 'Servicio de reparación', 
	'2023-10-04', 2);
    
-- --Servicio_has_Compra-- --
insert into Servicio_has_Compra(descripcionDetalle, codigoServicio, codigoCompra) values('Servicio para Burguer King', 2, 2);
insert into Servicio_has_Compra(descripcionDetalle, codigoServicio, codigoCompra) values('Servicio para Kinal', 1, 1);
insert into Servicio_has_Compra(descripcionDetalle, codigoServicio, codigoCompra) values('Servicio para Pizza Hut', 1, 2);
insert into Servicio_has_Compra(descripcionDetalle, codigoServicio, codigoCompra) values('Servicio para Portales', 2, 1);
insert into Servicio_has_Compra(descripcionDetalle, codigoServicio, codigoCompra) values('Servicio para Metronorte', 2, 1);

-- --Tipo Empleado-- --
insert into TipoEmpleado(descripcionTipoEmpleado, categoria, sueldo) values ('Programador', 'Programador', 7750.65);
insert into TipoEmpleado(descripcionTipoEmpleado, categoria, sueldo) values ('Conductor', 'Conductor', 3750.65);
-- --Empleado-- --
insert into Empleado(usuario, DPIEmpleado, nombresEmpleado, apellidosEmpleado, telefonoContacto, codigoTipoEmpleado, codigoTransporte, codigoEquipo) values
	('parmas', '123', 'Pedro', 'Armas', '12345678', 1,1,1);
insert into Empleado(usuario, DPIEmpleado, nombresEmpleado, apellidosEmpleado, telefonoContacto, codigoTipoEmpleado, codigoTransporte, codigoEquipo) 
	values ('caltan', '123', 'Carlos', 'Altan', '87654321', 2,2,2);
insert into Empleado(usuario, DPIEmpleado, nombresEmpleado, apellidosEmpleado, telefonoContacto, codigoTipoEmpleado, codigoTransporte, codigoEquipo) 
	values ('dabad', '111', 'Sebastian', 'Abad', '3366552', 2,2,2);
insert into Empleado(usuario, DPIEmpleado, nombresEmpleado, apellidosEmpleado, telefonoContacto, codigoTipoEmpleado, codigoTransporte, codigoEquipo) 
	values ('dako', '222', 'David', 'Balcarcel', '11580566', 2,2,2);
insert into Empleado(usuario, DPIEmpleado, nombresEmpleado, apellidosEmpleado, telefonoContacto, codigoTipoEmpleado, codigoTransporte, codigoEquipo) 
	values ('armas', '455', 'Carlos', 'Armas', '11220300', 2,2,2);
insert into Empleado(usuario, DPIEmpleado, nombresEmpleado, apellidosEmpleado, telefonoContacto, codigoTipoEmpleado, codigoTransporte, codigoEquipo) 
	values ('pbermudez', '450545', 'Pablo', 'Bermudez', '11225712', 2,2,2);
insert into Empleado(usuario, DPIEmpleado, nombresEmpleado, apellidosEmpleado, telefonoContacto, codigoTipoEmpleado, codigoTransporte, codigoEquipo) 
	values ('boteo', '4054065', 'Juan', 'Boteo', '11225560', 2,2,2);
insert into Empleado(usuario, DPIEmpleado, nombresEmpleado, apellidosEmpleado, telefonoContacto, codigoTipoEmpleado, codigoTransporte, codigoEquipo) 
	values ('carlitos', '05452', 'Carlos', 'Cabrera', '11525500', 2,2,2);
insert into Empleado(usuario, DPIEmpleado, nombresEmpleado, apellidosEmpleado, telefonoContacto, codigoTipoEmpleado, codigoTransporte, codigoEquipo) 
	values ('omarcito', '45054', 'Omar', 'Castillo', '11552800', 2,2,2);
insert into Empleado(usuario, DPIEmpleado, nombresEmpleado, apellidosEmpleado, telefonoContacto, codigoTipoEmpleado, codigoTransporte, codigoEquipo) 
	values ('richi', '77885', 'Ricardo', 'Colindres', '22556500', 2,2,2);
insert into Empleado(usuario, DPIEmpleado, nombresEmpleado, apellidosEmpleado, telefonoContacto, codigoTipoEmpleado, codigoTransporte, codigoEquipo) 
	values ('coy', '332523', 'Edwin', 'Coy', '11290600', 2,2,2);
insert into Empleado(usuario, DPIEmpleado, nombresEmpleado, apellidosEmpleado, telefonoContacto, codigoTipoEmpleado, codigoTransporte, codigoEquipo) 
	values ('delacruz', '31233', 'Carlos', 'De la Cruz', '11225540', 2,2,2);
insert into Empleado(usuario, DPIEmpleado, nombresEmpleado, apellidosEmpleado, telefonoContacto, codigoTipoEmpleado, codigoTransporte, codigoEquipo) 
	values ('rodrigo', '54205', 'Rodrigo', 'Diaz', '11224240', 2,2,2);
-- --Empleados_has_Servicios-- --
insert into Empleados_has_Servicios(costoServicio, codigoEmpleado, codigoServicio) values (350, 1, 1);
insert into Empleados_has_Servicios(costoServicio, codigoEmpleado, codigoServicio) values (250, 2, 2);
insert into Empleados_has_Servicios(costoServicio, codigoEmpleado, codigoServicio) values (440, 2, 1);
insert into Empleados_has_Servicios(costoServicio, codigoEmpleado, codigoServicio) values (290, 1, 2);
insert into Empleados_has_Servicios(costoServicio, codigoEmpleado, codigoServicio) values (333, 1, 1);

select * from Empleado;
select * from Servicio;
select * from Empleados_has_Servicios;



-- --Equipo_has_Empleado-- --
insert into Equipo_has_Empleado(codigoEquipo, codigoEmpleado, cantidadEquipo) values (1, 1, 3);
insert into Equipo_has_Empleado(codigoEquipo, codigoEmpleado, cantidadEquipo) values (2, 2, 3);
insert into Equipo_has_Empleado(codigoEquipo, codigoEmpleado, cantidadEquipo) values (3, 1, 2);
insert into Equipo_has_Empleado(codigoEquipo, codigoEmpleado, cantidadEquipo) values (4, 3, 1);
insert into Equipo_has_Empleado(codigoEquipo, codigoEmpleado, cantidadEquipo) values (5, 4, 1);

select *from Empleado;
-- select * from Empresa

