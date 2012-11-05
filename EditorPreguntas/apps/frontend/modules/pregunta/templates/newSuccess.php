<?php slot('title', 'Nueva Pregunta') ?>
<form action="<?php echo url_for('preguntaTest/'.($form->getObject()->isNew() ? 'create' : 'update').(!$form->getObject()->isNew() ? '?id='.$form->getObject()->getId() : '')) ?>" method="post" <?php $form->isMultipart() and print 'enctype="multipart/form-data" ' ?>>
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
    </div>
    
</form>