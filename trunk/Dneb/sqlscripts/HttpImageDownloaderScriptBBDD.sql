delete from Survey;
insert into Survey values(1,'poss1_red');
insert into Survey values(2,'poss1_blue');
insert into Survey values(3,'poss2ukstu_blue');
insert into Survey values(4,'poss2ukstu_ir');
insert into Survey values(5,'poss2ukstu_red');
-- insert into Survey values(6,'quickv&r');
-- insert into Survey values(7,'phase2_gsc2');
-- insert into Survey values(8,'phase2_gsc1');

delete from FormatoFichero;
insert into FormatoFichero values(1,'fits','fits');
insert into FormatoFichero values(2,'jpeg','jpeg');

delete from tip_proc;
insert into tip_proc values(1,'Procesamiento busqueda dobles','Procesamiento busqueda dobles');
insert into tip_proc values(2,'Procesamiento calculo distancia','Procesamiento calculo distancia');

delete from tipo_parametro;
insert into tipo_parametro values(1,'Brillo','Brillo de busqueda de estrellas dobles');
insert into tipo_parametro values(2,'Umbral','Umbral de busqueda de estrellas dobles');

delete from INFOREL_IMAGEN_JT;
delete from INFO_REL; 
delete from TIP_INFO_REL;
insert into TIP_INFO_REL values(2,'CALC DIST','INFO RELEVANTE DEL CALCULO DE DISTANCIA USANDO EL WDSC');
insert into TIP_INFO_REL values(1,'CALC DOBLES','BUSQUEDA ESTRELLA DOBLE');