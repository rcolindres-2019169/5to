/*
	Ricardo Adrián Colindres Franco
    2019169
    IN5AM
    Fecha de Creación: 28/03/2023
    Fechas de Modificación: 18/04/2023
							25/04/2023
                            09/05/2023
                            23/05/2023
                            25/05/2023
                            31/05/2023
*/

drop database if exists DBTonysKinal2023;
create database DBTonysKinal2023;

Use DBTonysKinal2023;

Create table Empresas(
	codigoEmpresa int auto_increment not null,
    nombreEmpresa varchar(150) not null,
    direccion varchar(150) not null,
    telefono varchar(8),
    primary key PK_codigoEmpresa  (codigoEmpresa)
    
);

Create table TipoEmpleado(
	codigoTipoEmpleado int not null auto_increment,
    descripcion varchar(50) not null,
    primary key PK_codigoTipoEmpleado (codigoTipoEmpleado)
);

Create table TipoPlato(
	codigoTipoPlato int not null auto_increment,
    descripcionTipo varchar(100) not null,
    primary key PK_codigoTipoPlato (codigoTipoPlato)
);

create table Productos(
	codigoProducto int not null auto_increment,
    nombreProducto varchar(150) not null,
    cantidad int not null,
    primary key PK_codigoProducto (codigoProducto)
);

Create table Empleados(
	codigoEmpleado int not null auto_increment,
    numeroEmpleado int not null,
    apellidosEmpleado varchar(150) not null,
    nombresEmpleado varchar(150) not null,
    direccionEmpleado varchar(150) not null,
    telefonoContacto varchar(8) not null,
    gradoCocinero varchar(50),
    codigoTipoEmpleado int not null,
    primary key PK_codigoEmpleado (codigoEmpleado),
    constraint FK_Empleados_TipoEmpleado foreign key
		(codigoTipoEmpleado) references TipoEmpleado(codigoTipoEmpleado)
);

Create table Servicios(
	codigoServicio int not null auto_increment,
    fechaServicio date not null,
    tipoServicio varchar(150) not null,
    horaServicio time not null,
    lugarServicio varchar(150) not null,
    telefonoContacto varchar(150) not null,
    codigoEmpresa int not null,
    primary key PK_codigoServicio (codigoServicio),
    constraint FK_Servicios_Empresas foreign key (codigoEmpresa)
		references Empresas(codigoEmpresa)
);

Create table Presupuestos(
	codigoPresupuesto int not null auto_increment,
    fechaSolicitud date not null,
    cantidadPresupuesto decimal(10,2) not null,
    codigoEmpresa int not null,
    primary key PK_codigoPresupuesto (codigoPresupuesto),
    constraint FK_Presupuesto_Empresas foreign key (codigoEmpresa)
		references Empresas(codigoEmpresa)
);

Create table Platos(
	codigoPlato int not null auto_increment,
    cantidad int not null,
    nombrePlato varchar(150) not null,
    descripcionPlato varchar(150) not null,
    precioPlato decimal (10,2) not null,
    codigoTipoPlato int not null,
    primary key PK_codigoPlato (codigoPlato),
	constraint FK_Platos_TipoPlato foreign key (codigoTipoPlato)
		references TipoPlato(codigoTipoPlato)
);

Create table Productos_has_Platos(
	Productos_codigoProducto int not null,
    codigoPlato int not null,
    codigoProducto int not null,
    primary key PK_Productos_codigoProducto (Productos_codigoProducto),
    constraint FK_Productos_has_Platos_Productos foreign key (codigoProducto) 
		references Productos(codigoProducto),
	constraint FK_Productos_has_Platos_Platos foreign key (codigoPlato)
		references Platos(codigoPlato)
);

Create table Servicios_has_Platos(
	Servicios_codigoServicio int not null,
    codigoPlato int not null,
    codigoServicio int not null,
    primary key PK_Servicios_codigoServicio (Servicios_codigoServicio),
    constraint FK_Servicios_has_Platos_Servicios foreign key(codigoServicio)
		references Servicios(codigoServicio),
	constraint FK_Servicios_has_Platos_Platos foreign key (codigoPlato)
		references Platos(codigoPlato)
);

Create table Servicios_has_Empleados(
	Servicios_codigoServicio int not null,
    codigoServicio int not null,
    codigoEmpleado int not null,
    fechaEvento date not null,
    horaEvento time not null,
    lugarEvento varchar(150) not null,
    primary key PK_Servicios_codigoServicio (Servicios_codigoServicio),
    constraint FK_Servicios_has_Empleados_Servicios foreign key(codigoServicio)
		references Servicios(codigoServicio),
	constraint FK_Servicios_has_Empleados_Empleados foreign key (codigoEmpleado)
		references Empleados(codigoEmpleado)
);

Create table Usuario(
	codigoUsuario int not null auto_increment,
    nombreUsuario varchar(100) not null,
    apellidoUsuario varchar(100) not null,
    usuarioLogin varchar(50) not null,
    contrasena varchar(50) not null,
    primary key PK_codigoUsuario(codigoUsuario)
);


Create table Login(
	usuarioMaster varchar(50) not null,
    passwordLogin varchar(50) not null,
    primary key PK_usuarioMaster(usuarioMaster)
);


insert into Empresas (nombreEmpresa, direccion, telefono) values ('Kinal', 'Zona 7 Guatemala, Guatemala', '22160000');
-- ------------------------------------- PROCEDIMIENTOS ALMACENADOS -----------------------------

-- ------------------------------------- AGREGAR ----------------------------------------------


DELIMITER //
	Create procedure sp_AgregarUsuario(in nombreUsuario varchar(100), in apellidoUsuario varchar(100),
					in usuarioLogin varchar(50), in contrasena varchar(50))
			Begin
				Insert into Usuario (nombreUsuario, apellidoUsuario, usuarioLogin, contrasena)
					values(nombreUsuario, apellidoUsuario, usuarioLogin, contrasena);
           End//
DELIMITER ;

call sp_AgregarUsuario('Ricardo', 'Colindres', 'rcolindres', '1234');
call sp_AgregarUsuario('Pedro', 'Armas', 'parmas', 'parmas');

DELIMITER //
	Create procedure sp_ListarUsuarios()
		Begin
			select 
				U.codigoUsuario, 
                U.nombreUsuario, 
                U.apellidoUsuario, 
                U.usuarioLogin, 
                U.contrasena 
			from Usuario U;
        End//
DELIMITER ;

call sp_ListarUsuarios();

DELIMITER //
	create procedure sp_AgregarEmpresa (in nombreEmpresa varchar(150), in direccion varchar(150), 
					in telefono varchar(8))
		begin
			insert into Empresas(nombreEmpresa, direccion, telefono)
				values (nombreEmpresa, direccion, telefono);
		End//
DELIMITER ;

call sp_AgregarEmpresa('McDonalds', 'Zona 10 Guatemala', '22170000');
call sp_AgregarEmpresa('Los Cebollines', 'Majadas, Zona 11, Guatemala', '24150000');
call sp_AgregarEmpresa('Pollo Campero', 'Zona 7 Mixco, Guatemala', '24117177');
call sp_AgregarEmpresa('La Estancia','Majadas Zona 11, Guatemala' ,'24745908');

 -- SELECT * FROM Empresas;

DELIMITER //
	Create procedure sp_AgregarTipoEmpleado (in descripcion varchar(50))
			begin
				insert into TipoEmpleado (descripcion)
					values (descripcion);
            end//
DELIMITER ;

call sp_AgregarTipoEmpleado('Cajero');
call sp_AgregarTipoEmpleado('Cocinero');
call sp_AgregarTipoEmpleado('Limpieza');
call sp_AgregarTipoEmpleado('Seguridad');
call sp_AgregarTipoEmpleado('Mesero');

DELIMITER // 
	Create procedure sp_AgregarTipoPlato (in descripcionTipo varchar(100))
			begin
				insert into TipoPlato (descripcionTipo)
					values (descripcionTipo);
            end//
DELIMITER ;

call sp_AgregarTipoPlato('Almuerzo');
call sp_AgregarTipoPlato('Desayuno');
call sp_AgregarTipoPlato('Cena');
call sp_AgregarTipoPlato('Bouffete');
call sp_AgregarTipoPlato('Refacción');


DELIMITER //
	create procedure sp_AgregarProducto (in nombreProducto varchar(150), 
					in cantidad int)
				begin
					insert into Productos (nombreProducto, cantidad)
						values (nombreProducto, cantidad);
                end//
DELIMITER ;

call sp_AgregarProducto('Huevos', 150);
call sp_AgregarProducto('Pastel', 100);
call sp_AgregarProducto('Pizas', 90);
call sp_AgregarProducto('Gaseosas', 50);
call sp_AgregarProducto('Bocadillos', 75);


DELIMITER //
	Create procedure sp_AgregarEmpleado (in numeroEmpleado int, in apellidosEmpleado varchar(150),
					in nombresEmpleado varchar(150), in direccionEmpleado varchar(150),
                    in telefonoContacto varchar(8), in gradoCocinero varchar(50), in codigoTipoEmpleado int)
				begin
					insert into Empleados (numeroEmpleado, apellidosEmpleado, nombresEmpleado,
                    direccionEmpleado, telefonoContacto, gradoCocinero, codigoTipoEmpleado)
						values (numeroEmpleado, apellidosEmpleado, nombresEmpleado,
                    direccionEmpleado, telefonoContacto, gradoCocinero, codigoTipoEmpleado);
                end//
DELIMITER ;

call sp_AgregarEmpleado(54458795, 'García López', 'Lucas Emanuel', 'Zona 7 Guatemala', '57487874', '', 1);
call sp_AgregarEmpleado(44458795, 'Díaz Matínez', 'Manuela Lucía', 'Zona 10 Guatemala', '45487874', '2 año', 2);
call sp_AgregarEmpleado(54458796, 'González Vásquez', 'Darío Roberto', 'Zona 5, Mixco', '43128652', '1 año' ,2);
call sp_AgregarEmpleado(44458796, 'Miranda Barrios', 'Sara María', 'Zona 18, Guatemala', '24151640', '', 3);
call sp_AgregarEmpleado(54466133, 'Barrios Marroquín', 'Mario Ernesto', 'Zona 15, Guatemala', '56787412', '',4 );



DELIMITER //
	Create procedure sp_AgregarServicio(in fechaServicio date, in tipoServicio varchar(150),
					in horaServicio time, in lugarServicio varchar(150), 
                    in telefonoContacto varchar(150), in codigoEmpresa int)
				begin
					insert into Servicios (fechaServicio, tipoServicio, horaServicio,
                    lugarServicio, telefonoContacto, codigoEmpresa)
						values
					(fechaServicio, tipoServicio, horaServicio,
                    lugarServicio, telefonoContacto, codigoEmpresa);
                end//

DELIMITER ;

call sp_AgregarServicio('2023-05-20', 'Cena', '20:00', 'Kinal', '22160000', 1);
call sp_AgregarServicio('2023-05-28', 'Desayuno', '20:00', 'McDonalds', '22187000', 2);
call sp_AgregarServicio('2023-06-01', 'Bouffete', '13:00', 'La Estancia', '21251000', 5);
call sp_AgregarServicio('2023-05-29', 'Almuerzo', '13:30','Parque los Olivos', '55147485', 3);
call sp_AgregarServicio('2023-05-26', 'Refacción', '10:00', 'Pollo Campero', '42777895',4);


DELIMITER //
	Create procedure sp_AgregarPresupuesto(in fechaSolicitud date, in cantidadPresupuesto decimal(10,2),
						in codigoEmpresa int)
					begin
						insert into Presupuestos (fechaSolicitud, cantidadPresupuesto, codigoEmpresa)
							values
						(fechaSolicitud, cantidadPresupuesto, codigoEmpresa);
					end//
DELIMITER ;

call sp_AgregarPresupuesto('2023-05-10', 7550.00,1);
call sp_AgregarPresupuesto('2023-05-12', 6641.00,1);
call sp_AgregarPresupuesto('2023-05-20', 5748.00, 2);
call sp_AgregarPresupuesto('2023-05-24', 7900.00, 3);
call sp_AgregarPresupuesto('2023-05-28', 10000.00, 5);


DELIMITER //
	Create procedure sp_AgregarPlato(in cantidad int, in nombrePlato varchar(150), 
						in descripcionPlato varchar(150), in precioPlato decimal(10,2),
                        in codigoTipoPlato int)
					begin
						insert into Platos(cantidad, nombrePlato, descripcionPlato,
							precioPlato, codigoTipoPlato)
						values
							(cantidad, nombrePlato, descripcionPlato,
							precioPlato, codigoTipoPlato);
                    end//
	
DELIMITER ;

call sp_AgregarPlato(150, 'DeliAlmuerzo','Hamburguesa, papas y bebida', 45.00,1);
call sp_AgregarPlato(110, 'Asado', 'Carne Asada y bebida', 59.00,4);
call sp_AgregarPlato(120, 'Desayuno Tradicional', 'Frijoles con huevo y café', 25.00, 2);
call sp_AgregarPlato(100, 'Pizza', 'Pizza de peperonni con gaseosa', 40.00, 1);
call sp_AgregarPlato(150, 'Cena chapín', 'Frijoles, plátanos fritos y pan', 29.00 ,3)

DELIMITER // 
	Create procedure sp_AgregarProductoPlato(in Productos_codigoProducto int, in codigoPlato int, in codigoProducto int)
					begin
						insert into Productos_has_Platos(Productos_codigoProducto, codigoPlato, codigoProducto)
					values
						(Productos_codigoProducto, codigoPlato, codigoProducto);
					end//
	
DELIMITER ;

call sp_AgregarProductoPlato(1,1,1);
call sp_AgregarProductoPlato(2,2,2);
call sp_AgregarProductoPlato(3,3,3);
call sp_AgregarProductoPlato(4,4,4);
call sp_AgregarProductoPlato(5,4,4);

DELIMITER //
	Create procedure sp_AgregarServicioPlato (in Servicios_codigoServicio int, in codigoPlato int, in codigoServicio int)
					begin
						insert into servicios_has_platos(Servicios_codigoServicio, codigoPlato, codigoServicio)
					values
						(Servicios_codigoServicio, codigoPlato, codigoServicio);
                    end//
DELIMITER ;	

call sp_AgregarServicioPlato(1, 1, 1);
call sp_AgregarServicioPlato(2,2,2);
call sp_AgregarServicioPlato(3,3,3);
call sp_AgregarServicioPlato(4,4,4);
call sp_AgregarServicioPlato(5,4,4);


DELIMITER //
	Create procedure sp_AgregarServicioEmpleado(in Servicios_codigoServicio int, in codigoServicio int , in codigoEmpleado int,
							in fechaEvento date, in horaEvento time, in lugarEvento varchar(150))
					begin
						insert into Servicios_has_Empleados (Servicios_codigoServicio, codigoServicio, codigoEmpleado,
								fechaEvento, horaEvento, lugarEvento)
					values
								(Servicios_codigoServicio, codigoServicio, codigoEmpleado,fechaEvento, horaEvento, lugarEvento);
                    end//
DELIMITER ;

call sp_AgregarServicioEmpleado(1,1,1,'2023-05-20', '20:00:00', 'KINAL');
call sp_AgregarServicioEmpleado(2,1,2,'2023-05-22', '21:15:00', 'McDonalds');
call sp_AgregarServicioEmpleado(3,1,2,'2023-05-24', '21:15:00', 'Los Cebollines');
call sp_AgregarServicioEmpleado(4,1,2,'2023-05-27', '21:15:00', 'Pollo Campero');
call sp_AgregarServicioEmpleado(5,1,2,'2023-06-30', '21:15:00', 'La Estancia');

-- ------------------------------------ EDITAR ---------------------------------------------------

DELIMITER //
	create procedure sp_EditarEmpresa (in codEmpresa int, in nombreEmpresa varchar(150),
					in direccion varchar(150), in telefono varchar(8))
		begin
			update Empresas E
				set
					E.nombreEmpresa = nombreEmpresa,
                    E.direccion = direccion,
                    E.telefono = telefono
				where codigoEmpresa = codEmpresa;
		End//
DELIMITER ;

-- call sp_EditarEmpresa(2,'Hola', 'Hola', '44444444');

DELIMITER // 
	Create procedure sp_EditarTipoEmpleado (in codTipoEmpleado int, in descripcion varchar(50))
		begin
			update TipoEmpleado T
				set
					T.descripcion = descripcion
				where codigoTipoEmpleado = codTipoEmpleado;
        end//
DELIMITER ;


DELIMITER //
	Create procedure sp_EditarTipoPlato (in codTipoPlato int, in descripcionTipo varchar(100))
		begin
			update TipoPlato T
				set
					T.descripcionTipo = descripcionTipo
                where codigoTipoPlato = codTipoPlato;
        end//
DELIMITER ;	


DELIMITER //
	Create procedure sp_EditarProducto (in codProducto int, in nombreProducto varchar(150),
						in cantidad int)
		begin
			update Productos P
				set
                 P.nombreProducto = nombreProducto,
                 P.cantidad = cantidad
                where codigoProducto = codProducto;
        end//
DELIMITER ;


DELIMITER //
	Create procedure sp_EditarEmpleado (in codEmpleado int, in numeroEmpleado int,
						in apellidosEmpleado varchar(150), in nombresEmpleado varchar(150),
                        in direccionEmpleado varchar(150), in telefonoContacto varchar(8),
                        in gradoCocinero varchar(50), in codigoTipoEmpleado int)
					begin
						update Empleados E
							set
							E.numeroEmpleado = numeroEmpleado, 
							E.apellidosEmpleado = apellidosEmpleado, 
							E.nombresEmpleado = nombresEmpleado, 
							E.direccionEmpleado = direccionEmpleado, 
							E.telefonoContacto = telefonoContacto, 
							E.gradoCocinero = gradoCocinero, 
							E.codigoTipoEmpleado = codigoTipoEmpleado
						where codigoEmpleado = codEmpleado;
					end//
DELIMITER ;


DELIMITER //
	Create procedure sp_EditarServicio (in codServicio int, in fechaServicio date,
						in tipoServicio varchar(150), in horaServicio time, 
                        in lugarServicio varchar(150), in telefonoContacto varchar(150),
                        in codigoEmpresa int)
					begin
						update Servicios S
							set 
                            S.fechaServicio = fechaServicio, 
                            S.tipoServicio = tipoServicio, 
                            S.horaServicio = horaServicio, 
                            S.lugarServicio = lugarServicio, 
                            S.telefonoContacto = telefonoContacto, 
                            S.codigoEmpresa = codigoEmpresa
						where codigoServicio = codServicio;
                    end//
					

DELIMITER ;

DELIMITER //
	Create procedure sp_EditarPresupuesto (in codPresupuesto int, in fechaSolicitud date, 
						in cantidadPresupuesto decimal (10,2), in codigoEmpresa int)
					begin
						update Presupuestos P
							set
                            P.fechaSolicitud = fechaSolicitud, 
                            P.cantidadPresupuesto = cantidadPresupuesto,  
                            P.codigoEmpresa = codigoEmpresa
						where codigoPresupuesto = codPresupuesto;
                    end//

DELIMITER ; 


DELIMITER //
	Create procedure sp_EditarPlato (in codPlato int, in cantidad int, in nombrePlato varchar(150),
										in descripcionPlato varchar(150), in precioPlato decimal(10,2),
                                        in codigoTipoPlato int)
									begin
										update Platos P
											set
                                            P.cantidad = cantidad,
                                            P.nombrePlato = nombrePlato, 
                                            P.descripcionPlato = descripcionPlato, 
                                            P.precioPlato = precioPlato, 
                                            P.codigoTipoPlato = codigoTipoPlato
										where codigoPlato = codPlato;
                                    end//

DELIMITER ;


DELIMITER //
	Create procedure sp_EditarProductoPlato(in Productos_codProducto int, in codigoPlato int,
											in codigoProducto int)
										begin
											update Productos_has_Platos P
												set
                                                P.codigoPlato = codigoPlato, 
                                                P.codigoProducto = codigoProducto
											where Productos_codigoProducto = Productos_codProducto;
                                        end//

DELIMITER ;


DELIMITER //
	Create procedure sp_EditarServicioPlato(in Servicios_codServicio int, 
											in codigoPlato int, in codigoServicio int )
										begin
											update Servicios_has_Platos S
												set
                                                S.codigoPlato = codigoPlato,
                                                S.codigoServicio = codigoServicio
											where Servicios_codigoServicio = Servicios_codServicio;
                                        end//
DELIMITER ;


DELIMITER //
	Create procedure sp_EditarServicioEmpleado(in Servicios_codServicio int, in codigoServicio int,
												in codigoEmpleado int, in fechaEvento date, 
                                                in horaEvento time, in lugarEvento varchar(150))
											begin
												update Servicios_has_Empleados S
													set
                                                    S.codigoServicio = codigoServicio, 
                                                    S.codigoEmpleado = codigoEmpleado, 
                                                    S.fechaEvento = fechaEvento, 
                                                    S.horaEvento = horaEvento, 
                                                    S.lugarEvento = lugarEvento
												where Servicios_codigoServicio = Servicios_codServicio;
                                            end//	

DELIMITER ;
-- ------------------------------------- ELIMINAR ------------------------------------------------

DELIMITER //
	Create procedure sp_EliminarEmpresa(in codEmpresa int)
		begin
			Delete from Empresas 
				where codigoEmpresa = codEmpresa;
        End//
DELIMITER ;

-- call sp_ElimiarEmpresa(3);

DELIMITER //
	Create procedure sp_EliminarTipoEmpleado (in codTipoEmpleado int)
		begin
			Delete from TipoEmpleado
				where codigoTipoEmpleado = codTipoEmpleado;
        End//
DELIMITER ;	


DELIMITER //
	Create procedure sp_EliminarTipoPlato (in codTipoPlato int)
		begin
			Delete from TipoPlato
				where codigoTipoPlato = codTipoPlato;
        End//
DELIMITER ;


DELIMITER //
	Create procedure sp_EliminarProducto (in codProducto int)
		begin
			Delete from Productos
				where codigoProducto = codProducto;
        End//
DELIMITER ;	


DELIMITER //
	Create procedure sp_EliminarEmpleado (in codEmpleado int)
		begin
			Delete from Empleados
				where codigoEmpleado = codEmpleado;
        End//
DELIMITER ;


DELIMITER //
	Create procedure sp_EliminarServicio(in codServicio int)
		begin
			Delete from Servicios
				where codigoServicio = codServicio;
        end//

DELIMITER ;


DELIMITER //
	Create procedure sp_EliminarPresupuesto (in codPresupuesto int)
		begin
			Delete from Presupuestos
				where codigoPresupuesto = codPresupuesto;
        end//
DELIMITER ;


DELIMITER //
	Create procedure sp_EliminarPlato (in codPlato int)
		begin
			Delete from Platos
				where codigoPlato = codPlato;
        end//
DELIMITER ;

DELIMITER //
	Create procedure sp_EliminarProductoPlato (in Productos_codProducto int)
		begin
			Delete from Productos_has_Platos
				where Productos_codigoProducto = Productos_codProducto;
        end//
DELIMITER ;


DELIMITER //
	Create procedure sp_EliminarServicioPlato (in Servicios_codServicio int)
		begin
			Delete from Servicios_has_Platos
				where Servicios_codigoServicio = Servicios_codServicio;
        end//

DELIMITER ; 


DELIMITER //
	Create procedure sp_EliminarServicioEmpleado(in Servicios_codServicio int)
		begin
			Delete from Servicios_has_Empleados
				where Servicios_codigoServicio = Servicios_codServicio;
        end//
DELIMITER ;

-- -------------------------------------- LISTAR ----------------------------------------------------

DELIMITER //
	Create procedure sp_ListarEmpresas ()
		begin
			select 
				E.codigoEmpresa,
				E.nombreEmpresa,
                E.direccion,
                E.telefono
			from Empresas E;
		End//
DELIMITER ;


DELIMITER //
	Create procedure sp_ListarTipoEmpleados()
		begin
			select 
				T.codigoTipoEmpleado,
				T.descripcion

            from TipoEmpleado T;
        end//
DELIMITER ; 

set 

DELIMITER //
	Create procedure sp_ListarTipoPlatos()
		begin
			select 
				T.codigoTipoPlato,
                T.descripcionTipo
            from TipoPlato T;
        end//
DELIMITER ;


DELIMITER //
	Create procedure sp_ListarProductos()
		begin
			select
				P.codigoProducto,
                P.nombreProducto,
                P.cantidad
            from Productos P;
        end//
DELIMITER ;

call sp_ListarEmpresas();

DELIMITER // 
	Create procedure sp_ListarEmpleados()
		begin
			select
            E.codigoEmpleado, 
            E.numeroEmpleado, 
            E.apellidosEmpleado, 
            E.nombresEmpleado, 
            E.direccionEmpleado, 
            E.telefonoContacto, 
            E.gradoCocinero, 
            E.codigoTipoEmpleado
            from Empleados E;
        end//
DELIMITER ; 


DELIMITER //
	Create procedure sp_ListarServicios()
		begin
			select 
            S.codigoServicio, 
            S.fechaServicio, 
            S.tipoServicio, 
            S.horaServicio, 
            S.lugarServicio, 
            S.telefonoContacto, 
            S.codigoEmpresa
            from Servicios S;
        end//
DELIMITER ;


DELIMITER //
	Create procedure sp_ListarPresupuestos()
		begin
			select 
            P.codigoPresupuesto, 
            P.fechaSolicitud, 
            P.cantidadPresupuesto,
            P.codigoEmpresa
            from Presupuestos P;
        end//
DELIMITER ;


DELIMITER //
	Create procedure sp_ListarPlatos()
		begin
			select
            P.codigoPlato, 
            P.cantidad, 
            P.nombrePlato, 
            P.descripcionPlato, 
            P.precioPlato, 
            P.codigoTipoPlato
            from Platos P;
        end//
DELIMITER ;

DELIMITER //
	Create procedure sp_ListarProductosPlatos()
		begin
			select
            P.Productos_codigoProducto, 
            P.codigoPlato, 
            P.codigoProducto
            from Productos_has_Platos P;
        end//
DELIMITER ;


DELIMITER //
	Create procedure sp_ListarServiciosPlatos()
		begin 
			select
            S.Servicios_codigoServicio, 
            S.codigoPlato, 
            S.codigoServicio
            from Servicios_has_Platos S;
        end//
DELIMITER ;


DELIMITER //
	Create procedure sp_ListarServiciosEmpleados()
		begin
			select
            S.Servicios_codigoServicio, 
            S.codigoServicio, 
            S.codigoEmpleado, 
            S.fechaEvento, 
            S.horaEvento, 
            S.lugarEvento
            from Servicios_has_empleados S;
        end//
DELIMITER ;

-- ------------------------------------------- BUSCAR ------------------------------------------------
DELIMITER //
	Create procedure sp_BuscarEmpresa (in codEmpresa int)
		begin
			select 
				E.codigoEmpresa,
				E.nombreEmpresa,
                E.direccion,
                E.telefono 
			from Empresas E
            where codigoEmpresa = codEmpresa;
		End//
DELIMITER ;


DELIMITER //
	Create procedure sp_BuscarTipoEmpleado (in codTipoEmpleado int)
		begin
			select 
				T.codigoTipoEmpleado,
				T.descripcion
            from TipoEmpleado T
            where codigoTipoEmpleado = codTipoEmpleado;
        end//
DELIMITER ;


DELIMITER // 
	Create procedure sp_BuscarTipoPlato (in codTipoPlato int)
		begin
			select
				T.codigoTipoPlato,
                T.descripcionTipo
            from TipoPlato T
            where codigoTipoPlato = codTipoPlato;
        end//
DELIMITER ;


DELIMITER //
	Create procedure sp_BuscarProducto (in codProducto int)
		begin
			select 
				P.codigoProducto,
                P.nombreProducto,
                P.cantidad
            from Productos P
            where codigoProducto = codProducto;
        end//
DELIMITER ;

-- call sp_BuscarEmpresa(1);

DELIMITER // 
	Create procedure sp_BuscarEmpleado(in codEmpleado int)
		begin
			select
            E.codigoEmpleado, 
            E.numeroEmpleado, 
            E.apellidosEmpleado, 
            E.nombresEmpleado, 
            E.direccionEmpleado, 
            E.telefonoContacto, 
            E.gradoCocinero, 
            E.codigoTipoEmpleado
            from Empleados E
            where codigoEmpleado = codEmpleado;
        end//
DELIMITER ; 


DELIMITER //
	Create procedure sp_BuscarServicio(in codServicio int)
		begin
			select 
            S.codigoServicio, 
            S.fechaServicio, 
            S.tipoServicio, 
            S.horaServicio, 
            S.lugarServicio, 
            S.telefonoContacto, 
            S.codigoEmpresa
            from Servicios S
            where codigoServicio = codServicio;
        end//
DELIMITER ;


DELIMITER //
	Create procedure sp_BuscarPresupuesto(in codPresupuesto int)
		begin
			select 
            P.codigoPresupuesto, 
            P.fechaSolicitud, 
            P.cantidadPresupuesto,
            P.codigoEmpresa
            from Presupuestos P
            where codigoPresupuesto = codPresupuesto;
        end//
DELIMITER ;


DELIMITER //
	Create procedure sp_BuscarPlato(in codPlato int)
		begin
			select
            P.codigoPlato, 
            P.cantidad, 
            P.nombrePlato, 
            P.descripcionPlato, 
            P.precioPlato, 
            P.codigoTipoPlato
            from Platos P
            where codigoPlato = codPlato;
        end//
DELIMITER ;

DELIMITER //
	Create procedure sp_BuscarProductoPlato(in Productos_codProducto int)
		begin
			select
            P.Productos_codigoProducto, 
            P.codigoPlato, 
            P.codigoProducto
            from Productos_has_Platos P 
            where Productos_codigoProducto = Productos_codProducto;
        end//
DELIMITER ;


DELIMITER //
	Create procedure sp_BuscarServicioPlato(in Servicios_codServicio int)
		begin 
			select
            S.Servicios_codigoServicio, 
            S.codigoPlato, 
            S.codigoServicio
            from Servicios_has_Platos S
            where Servicios_codigoServicio = Servicios_codServicio;
        end//
DELIMITER ;


DELIMITER //
	Create procedure sp_BuscarServicioEmpleado(in Servicios_codServicio int)
		begin
			select
            S.Servicios_codigoServicio, 
            S.codigoServicio, 
            S.codigoEmpleado, 
            S.fechaEvento, 
            S.horaEvento, 
            S.lugarEvento
            from Servicios_has_empleados S
            where Servicios_codigoServicio = Servicios_codServicio;
        end//
DELIMITER ;

DELIMITER //
	Create procedure sp_EmpresaServicio(in codEmpresa int)
		begin
			select E.nombreEmpresa, S.tipoServicio, S.lugarServicio, She.fechaEvento, She.horaEvento, Em.apellidosEmpleado, Em.nombresEmpleado, 
							T.descripcion, P.nombrePlato, P.descripcionPlato, Pr.nombreProducto, Pres.codigoPresupuesto, Pres.fechaSolicitud, Pres.cantidadPresupuesto
					from Empresas E INNER JOIN Servicios S on E.codigoEmpresa = S.codigoEmpresa
						INNER JOIN Servicios_has_Empleados She on S.codigoServicio = She.codigoServicio
							INNER JOIN Empleados Em on She.codigoEmpleado = Em.codigoEmpleado
								INNER JOIN TipoEmpleado T on Em.codigoTipoEmpleado = T.codigoTipoEmpleado
									INNER JOIN Servicios_has_Platos Sp on S.codigoServicio = Sp.codigoServicio
										INNER JOIN Platos P on Sp.codigoPlato = P.codigoPlato
											INNER JOIN Productos_has_Platos Pp on P.codigoPlato = Pp.codigoPlato
												INNER JOIN Productos Pr on Pp.codigoProducto = Pr.codigoProducto
														INNER JOIN Presupuestos Pres on E.codigoEmpresa = Pres.codigoEmpresa
					where E.codigoEmpresa = codEmpresa;
        end//
DELIMITER ;

set global time_zone = "-6:00";

call sp_EmpresaServicio(1);





select * from Empresas E Inner join Servicios S on
  E.codigoEmpresa = S.codigoEmpresa where E.codigoEmpresa = 1;
  
  -- $P{codEmpresa}


select * from Empresas E Inner join Servicios S on E.codigoEmpresa = S.codigoEmpresa
INNER JOIN Servicios_has_Empleados She on S.codigoServicio = She.codigoServicio
INNER JOIN Empleados Em on She.codigoEmpleado = Em.codigoEmpleado
INNER JOIN TipoEmpleado T on Em.codigoTipoEmpleado = T.codigoTipoEmpleado
INNER JOIN Servicios_has_Platos Sp on S.codigoServicio = Sp.codigoServicio
INNER JOIN Platos P on Sp.codigoPlato = P.codigoPlato
INNER JOIN Productos_has_Platos Pp on P.codigoPlato = Pp.codigoPlato
INNER JOIN Productos Pr on Pp.codigoProducto = Pr.codigoProducto
 where E.codigoEmpresa =1;