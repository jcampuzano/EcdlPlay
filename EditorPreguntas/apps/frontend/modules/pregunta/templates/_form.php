<form action="<?php echo url_for('pregunta/' . ($form->getObject()->isNew() ? 'create' : 'update') . (!$form->getObject()->isNew() ? '?id=' . $form->getObject()->getId() : '')) ?>" method="post" <?php $form->isMultipart() and print 'enctype="multipart/form-data" ' ?>>


    <label for="<?php echo $form['modulo_id']->renderId() ?>">Modulo:</label>
    <?php echo $form['modulo_id']->render() ?>

    <label for="<?php echo $form['dificultad_id']->renderId() ?>">Dificultad:</label>
    <?php echo $form['dificultad_id']->render() ?>

    <label for="<?php echo $form['texto']->renderId() ?>">Texto:</label>
    <?php echo $form['texto']->render() ?>

    <label for="<?php echo $form['imagen']->renderId() ?>">Imagen:</label>
    <div class="divImagen">
        <?php echo $form['imagen']['filename']->render(array('width' => 300)) ?>
    </div>

    <?php foreach ($form['answers'] as $k => $answer): ?>
        <section>
            <h3>Respuesta <?php echo $k; ?></h3>

            <label>Texto:</label>
            <?php echo $answer['texto']->render() ?> <?php echo $answer['texto']->renderError() ?>

            <div style="clear:both;"></div>

            <label>Correcta:</label>
            <?php echo $answer['correcta']->render() ?> <?php echo $answer['correcta']->renderError() ?>
        </section>
    <?php endforeach; ?>

    <?php echo $form->renderHiddenFields(false) ?>
    <?php echo $form['imagen']->renderHiddenFields(false) ?>
    <a href="<?php echo url_for('pregunta_index') ?>">Volver</a>

    <input type="submit" value="Guardar" />
</form>