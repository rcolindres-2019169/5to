<%-- 
    Document   : Home Page
    Created on : 21-ago-2023, 18:46:42
    Author     : Santiago
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <title>Home Page</title>
    </head>
    <body>
        <div>
            <style>

                *{
                    margin: 0;
                    padding: 0;
                    box-sizing: border-box;

                }

                .acerca-de{
                    background: #f2f2f2;

                }

                .miembros{
                    background: #f2f2f2;
                }


                body {
                    font-family: 'open sans';
                    font-size: 16px;
                }

                header {
                    width: 100%;
                    height: 650px;
                    background-image: url(./img/InicioHome3.jpg);
                    background-attachment: fixed;
                    background-position: center;
                    background-size: cover;
                    position: relative;
                    overflow: hidden;
                    color:  #32325a;
                    text-align: center;
                }

                header .textos {
                    margin-top: 152px;
                }

                header .titulo {
                    font-size: 85px;
                }

                header .subtitulo {
                    font-size: 42px;
                    font-weight: 300;
                    margin-bottom: 32px;
                }

                header .boton {
                    display: inline-block;
                    padding: 6px;
                    width: 128px;
                    border: 1px solid #32325a;
                    color: #32325a;
                    font-size: 19px;
                    text-align: center;
                    text-decoration: none;
                    border-radius: 16px;
                }

                .sesgoabajo {
                    z-index: 10;
                    position: absolute;
                    bottom: 0;
                    left: 0;
                    border-width: 0 0 35vh 100vw;
                    border-style: solid;
                    border-color: transparent transparent #f2f2f2;
                }

                .sesgoarriba {
                    z-index: 10;
                    position: absolute;
                    top: 0;
                    left: 0;
                    border-width: 35vh 100vw 0 0;
                    border-style: solid;
                    border-color:  transparent transparent transparent;
                }

                .contenedor {
                    width: 90%;
                    max-width: 1000px;
                    margin: auto;
                    overflow: hidden;
                    padding: 52px 0;
                }

                /* Main */

                .sobre-nosotros {
                    text-align: center;
                    font-size: 56px;
                    margin-bottom: 10px;
                    font-weight: 600;
                    color: #32325a;
                }

                .slogan {
                    font-size: 24px;
                    font-weight: 300;
                    text-align: center;
                    margin-bottom: 24px;
                    color: #32325a;
                }

                .parrafo1 {
                    margin-bottom: 13px;
                    font-weight: 300;
                    text-align: center;
                    line-height: 24px;
                    color: #32325a;
                }

                .parrafo {
                    margin-bottom: 13px;
                    font-weight: 300;
                    text-align: justify;
                    line-height: 24px;
                    color: #32325a;
                }


                .galeria {
                    width: 100%;
                    height: 500px;
                    overflow: hidden;
                    display: flex;
                    position: relative;
                    flex-wrap: wrap;
                }

                .imagenes {
                    width: 20%;
                    height: 500px;
                    overflow: hidden;
                    position: relative;
                }

                .imagenes img {
                    width: 100%;
                    height: 100%;
                    object-fit: cover;
                }

                .encima {
                    position: absolute;
                    top: 0;
                    left: 0;
                    width: 100%;
                    height: 100%;
                    background: #CBF5F4;
                }

                .encima h2 {
                    position: relative;
                    top: 45%;
                    text-align: center;
                    color: #32325a;
                }

                .encima div {
                    position: relative;
                    display: block;
                    top: 46%;
                    width: 40px;
                    height: 5px;
                    background: #32325a;
                    margin: auto;
                }

                .cards {
                    width: 100%;
                    margin: auto;
                    display: flex;
                    flex-wrap: wrap;
                    justify-content: space-around;
                }

                .card {
                    color: #32325a;
                    margin: 20px 0;
                    width: 30%;
                    height: 250px;
                    text-align: center;
                    padding: 20px;
                    box-shadow: 0px 0px 4px 0 rgba(101, 103, 103, 0.678);
                }

                .cards img {
                    margin: auto;
                    width: 140px;
                    height: 140px;
                    object-fit: cover;
                    border-radius: 100%;
                }

                footer{
                    background: #CBF5F4;
                    padding: 60px 0 30px 0;
                    margin: auto;
                    overflow: hidden;
                }

                .contenedor-footer{
                    display: flex;
                    width: 90%;
                    justify-content: space-evenly;
                    margin: auto;
                    padding-bottom: 50px;
                    border-bottom: 1px solid #32325a;
                }

                .content-foo{
                    text-align: center;
                }

                .content-foo h4{
                    color: #32325a;
                    border-bottom: 3px solid #32325a;
                    padding-bottom: 5px;
                    margin-bottom: 10px;
                }

                .content-foo p{
                    color: #32325a;
                }

                .titulo-final{
                    text-align: center;
                    font-size: 24px;
                    margin: 20px 0 0 0;
                    color: #32325a;
                }
                /**/
                .titulo-footer,
                .subtitulo-footer {
                    color: #32325a;
                    text-align: center;
                    font-weight: 400;
                    font-size: 56px;
                }

                .subtitulo-footer {
                    font-size: 16px;
                    margin-bottom: 64px;
                }

                form {
                    display: flex;
                    width: 100%;
                    justify-content: space-between;
                    flex-wrap: wrap;
                    margin: auto;
                    color: #32325a;
                }

                input[type="text"],
                input[type="email"] {
                    display: inline-block;
                    width: 48%;
                    padding: 13px;
                    border: none;
                    color: #32325a;
                    font-family: 'open sans';
                    background: #f2f2f2;
                    margin-bottom: 16px;
                    border-top: #32325a;
                }

                input[type="text"]:focus,
                input[type="email"]:focus,
                textarea:focus {
                    border-top: #32325a;
                    color: #32325a;
                }

                textarea {
                    display: block;
                    width: 100%;
                    max-width: 100%;
                    min-width: 100%;
                    max-height: 200px;
                    min-height: 200px;
                    background: #f2f2f2;
                    padding: 13px;
                    border: none;
                    color: #32325a;
                    font-family: 'open sans';
                    margin-bottom: 16px;
                }

                input[type="submit"] {
                    display: inline-block;
                    padding: 13px;
                    border: none;
                    color: #32325a;
                    background: #f2f2f2;
                    width: 96px;
                }
            </style>
            <header id="Inicio">      
                <div class="textos">
                    <h1 class="titulo">ServD</h1>
                    <h3 class="subtitulo">¡El Trabajo Bien Hecho!</h3>
                    <a href="#contacto" class="boton">Contacto</a>
                </div>
                <div class="sesgoabajo"></div>

            </header>


            <section class="acerca-de">
                <div class="contenedor">
                    <h2 class="sobre-nosotros">Misión</h2>
                    <h3 class="slogan">¡No hagas chapuces, nosotros lo hacemos por ti!</h3>
                    <p class="parrafo1"> En nuestra empresa, nos enorgullece comprometernos con una misión multifacética que aborda las necesidades esenciales y tecnológicas de nuestros clientes. Nos esforzamos por ofrecer soluciones integrales y excepcionales en las áreas de limpieza, servicios técnicos y programación, con el objetivo de mejorar la calidad de vida y el funcionamiento eficiente de empresas y hogares.
                        Nuestra misión se centra en brindar un servicio de limpieza impecable que transforma espacios en entornos saludables y agradables. A través de técnicas y productos avanzados, eliminamos las preocupaciones de limpieza de nuestros clientes, permitiéndoles dedicar su tiempo y energía a lo que realmente importa.
                        Además, nos apasiona proporcionar servicios técnicos confiables y eficientes que mantengan las instalaciones y equipos en óptimas condiciones. Nuestros expertos técnicos están dedicados a resolver problemas de manera rápida y efectiva, asegurando la continuidad de las operaciones y minimizando los tiempos de inactividad.</p>
                    <p class="parrafo1">En el ámbito de la programación, nuestra misión se extiende a la creación de soluciones tecnológicas personalizadas que potencien la productividad y la eficiencia. A través de la innovación y la colaboración con nuestros clientes, desarrollamos software que se adapta a sus necesidades específicas, impulsando su éxito en un mundo digital en constante evolución.
                        Estamos comprometidos con la satisfacción del cliente en cada paso del camino. Nuestra misión se materializa a través de la excelencia en la ejecución, la dedicación al detalle y la búsqueda constante de mejoras. Buscamos no solo ser un proveedor de servicios, sino un socio estratégico que permita a nuestros clientes concentrarse en sus objetivos mientras nosotros nos encargamos de las complejidades técnicas y de limpieza.
                        En resumen, nuestra misión es elevar los estándares en limpieza, servicios técnicos y programación al proporcionar soluciones holísticas que agreguen valor real a la vida de las personas y al funcionamiento de las organizaciones.</p>
                </div>
            </section>

            <section class="galeria">
                <div class="sesgoarriba1"></div>
                <div class="imagenes none">
                    <img src="./img/conserje2.jpg" alt="">
                </div>
                <div class="imagenes">
                    <img src="./img/programador1.jpg" alt="">
                </div>
                <div class="imagenes">
                    <img src="programacion.jpg" alt="">
                    <div class="encima">
                        <h2>ServD</h2>
                        <div></div>
                    </div>
                </div>
                <div class="imagenes">
                    <img src="./img/tecnico1.jpg" alt="">
                </div>
                <div class="imagenes none">
                    <img src="./img/tecnico2.jpg" alt="">
                </div>
                <div class="sesgoabajo1"></div>
            </section>

            <section class="acerca-de">
                <div class="contenedor">
                    <h2 class="sobre-nosotros">Visión</h2>
                    <h3 class="slogan"></h3>
                    <p class="parrafo1"> Nuestra visión es liderar la industria como una empresa integral de servicios, 
                        reconocida por la calidad excepcional de nuestras soluciones en limpieza, servicios técnicos y programación. Aspiramos a ser innovadores y vanguardistas en la adopción de tecnologías emergentes para mejorar y simplificar la vida de nuestros clientes. A medida que crecemos,
                        buscamos ser un empleador ejemplar que atraiga a profesionales altamente capacitados y apasionados por brindar soluciones que tengan un impacto positivo en la sociedad y el medio ambiente. </p>
                </div>
            </section>

            <section class="miembros">
                <div class="contenedor">
                    <h2 class="sobre-nosotros">Nuestro equipo</h2>
                    <h3 class="slogan">Conoce a nuestro equipo de trabajo</h3>
                    <div class="cards">
                        <div class="card">
                            <img src="./img/programar.jpg" alt="">
                            <h4>Equipo </h4>
                            <p>Los mejores programadores fullstack</p>
                        </div>
                        <div class="card">
                            <img src="./img/tecnico.jpg" alt="">
                            <h4>Equipo de trabajo</h4>
                            <p>Tecnicos certificados Industrial</p>
                        </div>
                        <div class="card">
                            <img src="./img/conserje.jpg" alt="">
                            <h4>Servicios</h4>
                            <p>Impulsando valores y el trabajo bien hecho</p>
                        </div>
                    </div>
                </div>
            </section>

            <section class="acerca-de">
                <div class="contenedor">
                    <h2 class="sobre-nosotros">Meta</h2>
                    <h3 class="slogan">¡No hagas chapuces, nosotros lo hacemos por ti!</h3>
                    <p class="parrafo1"> Nuestra meta es convertirnos en el proveedor preferido de soluciones integrales para empresas y hogares, 
                        ofreciendo servicios de limpieza impecables, servicios técnicos confiables y desarrollo de software personalizado. Nos esforzamos por establecer relaciones duraderas con nuestros clientes,
                        basadas en la confianza y la satisfacción, y expandir nuestra presencia a nivel nacional e internacional.  </p>
                    <p class="parrafo1"> A través de la innovación constante y el enfoque en la calidad, nos comprometemos a mejorar continuamente nuestros servicios y contribuir al crecimiento y éxito de nuestros clientes.
                        Esta misión, visión y meta combinan la importancia de brindar múltiples servicios de manera excepcional mientras se mantienen altos estándares de calidad y satisfacción del cliente.</p>
                </div>
            </section>

            <footer id="contacto">
                <div class="contenedor">
                    <h2 class="titulo-footer">¿Quieres Contactarte Con Nostros?</h2>
                    <h3 class="subtitulo-footer">Lo hice con formsubmit</h3>
                    <div class="footer">
                        <form action="https://formsubmit.co/servdgt@gmail.com" method="POST" />
                            <input color="#32325a;" type="text" name="name" id="name" placeholder="Nombre">
                            <input type="email" name="email" id="email" placeholder="Email">
                            <textarea name="coments" id="coments" cols="30" rows="10" placeholder="Ingrese su mensaje..."></textarea>
                            <input type="submit" value="Enviar" href="#Inicio">
                            <input type="hidden" name="_next" value="http://localhost:17744/">
                            
                            <input type="hidden" name="_captcha" value="false">
                        </form>
                    </div>
                </div>
            </footer>
            <footer>
                <div class="contenedor-footer">
                    <div class="content-foo">
                        <h4>Contacto</h4>
                        <p>12345678</p>
                    </div>
                    <div class="content-foo">
                        <h4>Gmail</h4>
                        <p>servdgt@gmail.com</p>
                    </div>
                    <div class="content-foo">
                        <h4>Lugar</h4>
                        <p>zona 10</p>
                    </div>
                </div>           
                <h2 class="titulo-final">&copy; ServDGT  | Guatemala </h2>   
            </footer>                

        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    </body>
</html>
