<?php slot('title', 'Editar módulo') ?>
<form action="<?php echo url_for('modulo_update', $modulo) ?>" method="post">

    <div id="modulo">
        <h2>Editar módulo del ECDL</h2>

        <label for="<?php echo $form['nombre']->renderId() ?>">Nombre:</label>
        <?php echo $form['nombre']->render() ?><?php echo $form['nombre']->renderError() ?>

        <label for="<?php echo $form['descripcion']->renderId() ?>">Descripción:</label>
        <?php echo $form['descripcion']->render() ?><?php echo $form['descripcion']->renderError() ?>

        <input type="submit" value="Guardar"/>
        
    </div>    
</form>
