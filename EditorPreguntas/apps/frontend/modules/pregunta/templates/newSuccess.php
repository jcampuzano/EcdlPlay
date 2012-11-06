<?php slot('title', 'Nueva Pregunta') ?>
<form action="<?php echo url_for('pregunta/' . ($form->getObject()->isNew() ? 'create' : 'update') . (!$form->getObject()->isNew() ? '?id=' . $form->getObject()->getId() : '')) ?>" method="post" <?php $form->isMultipart() and print 'enctype="multipart/form-data" ' ?>>
    <div class="editar">
        <h2>Nueva Pregunta</h2>

        <label for="<?php echo $form['modulo_id']->renderId() ?>">Modulo:</label>
        <?php echo $form['modulo_id'] ?>

        <label for="<?php echo $form['dificultad_id']->renderId() ?>">Dificultad:</label>
        <?php echo $form['dificultad_id'] ?>

        <label for="<?php echo $form['texto']->renderId() ?>">Texto:</label>
        <?php echo $form['texto'] ?>

        <label for="<?php echo $form['imagen_id']->renderId() ?>">Imagen:</label>
        <?php echo $form['imagen_id'] ?>


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
          <a href="<?php echo url_for('pregunta_index') ?>">Volver</a>
          
          <input type="submit" value="Guardar" />
    </div>

</form>