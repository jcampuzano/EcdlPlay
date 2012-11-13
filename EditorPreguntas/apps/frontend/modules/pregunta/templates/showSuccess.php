<?php slot('title', 'ECDL - Eliminar pregunta') ?>
<form action="<?php echo url_for('pregunta/delete?id=' . $form->getId()) ?>">
    <div class="editar">
        <h2>Eliminar Pregunta</h2>

        <label>Modulo:</label>
        <span>
            <?php echo $form->getEcdlModulo()->getNombre() ?>
        </span>

        <label>Dificultad:</label>
        <span>
            <?php echo $form->getEcdlDificultad()->getNombre() ?>
        </span>

        <label>Texto:</label>
        <span>
            <?php echo $form->getTexto() ?>
        </span>

        <label>Imagen:</label>
        <?php if ($form->getImagen() !== null) : ?>
            <img src="/uploads/preguntas/<?php echo $form->getImagen() ?>" width="300"/>
        <?php else : ?>
            <span>
                Sin Imagen...
            </span>            
        <?php endif ?>

        <?php foreach ($form['Respuestas'] as $k => $answer): ?>
            <section>
                <h3>Respuesta <?php echo $k + 1; ?></h3>

                <label>Texto:</label>
                <span>
                    <?php echo $answer->getTexto() ?>
                </span>
                
                <div style="clear:both;"></div>

                <label>Correcta:</label>
                <input type="checkbox" <?php echo $answer->getCorrecta() ? "checked=\"checked\"" : "" ?> disabled="disabled"/>

            </section>
        <?php endforeach; ?>

        <a href="<?php echo url_for('pregunta_index') ?>">Volver</a>

        <input type="submit" value="Borrar"/>
    </div>
</form>