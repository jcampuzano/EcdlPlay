<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <?php include_http_metas() ?>
        <?php include_metas() ?>
        <title><?php include_slot('title', 'ECDL - Editor de Preguntas') ?></title>
        <link rel="shortcut icon" href="/favicon.ico" />
        <?php include_stylesheets() ?>
        <?php include_javascripts() ?>
    </head>
    <body>
        <div id="wrapper">
            <header>
                <h1><a href="<?php echo url_for("homepage") ?>">ECDL Play</a></h1>
                <h2>Editor de Preguntas</h2>
            </header>

            <nav>
                <div class="menu">
                    <ul>
                        <li><a href="<?php echo url_for("homepage") ?>">Home</a></li>
                        <li><a href="<?php echo url_for("about") ?>">About</a></li>
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
                        <li><a href="<?php echo url_for("modulo_index") ?>">Editar módulos</a></li>
                        <li><a href="<?php echo url_for("dificultad_index") ?>">Editar dificultad</a></li>
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
