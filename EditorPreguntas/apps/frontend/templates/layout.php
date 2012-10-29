<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <?php include_http_metas() ?>
        <?php include_metas() ?>
        <?php include_title() ?>
        <link rel="shortcut icon" href="/favicon.ico" />
        <?php include_stylesheets() ?>
        <?php include_javascripts() ?>
    </head>
    <body>
        <div id="wrapper">
            <header>
                <h1><a href="#">ECDL Play</a></h1>
                <h2>Editor de Preguntas</h2>
            </header>

            <nav>
                <div class="menu">
                    <ul>
                        <li><a href="#">Home</a></li>
                        <li><a href="#">About</a></li>
                    </ul>
                </div>
            </nav>

            <section id="main">
                <section id="content">
                    <?php echo $sf_content ?>
                </section>

                <aside id="sidebar"><!-- sidebar -->
                    <h3>Opciones</h3>
                    <ul>
                        <li><a href="#">Editar módulos</a></li>
                        <li><a href="#">Editar Preguntas</a></li>
                        <li><a href="#">Descargar XML</a></li>
                    </ul>
            </section>

            <footer>
                <section id="footer-area">
                    <aside class="footer-segment">
                        &copy; 2012
                    </aside>
                </section>
            </footer>
        </div>
    </body>

</html>
