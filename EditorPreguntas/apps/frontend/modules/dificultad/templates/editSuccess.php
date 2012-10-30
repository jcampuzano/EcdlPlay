<?php slot('title', 'Editar dificultad') ?>

<form action="<?php echo url_for('dificultad_update', $dificultad) ?>" method="post">
    <div class="editar">
        <h2>Editar dificultad del ECDL</h2>

        <label for="<?php echo $form['nombre']->renderId() ?>">Nombre:</label>
        <?php echo $form['nombre']->render() ?><?php echo $form['nombre']->renderError() ?>

        <label for="<?php echo $form['valor']->renderId() ?>">Valor:</label>
        <?php echo $form['valor']->render() ?><?php echo $form['valor']->renderError() ?>

        <input type="submit" value="Guardar"/>

        <?php echo $form['_csrf_token'] ?>
    </div>
</form>
 