<?php use_stylesheets_for_form($form) ?>
<?php use_javascripts_for_form($form) ?>

<form action="<?php echo url_for('preguntaTest/'.($form->getObject()->isNew() ? 'create' : 'update').(!$form->getObject()->isNew() ? '?id='.$form->getObject()->getId() : '')) ?>" method="post" <?php $form->isMultipart() and print 'enctype="multipart/form-data" ' ?>>
<?php if (!$form->getObject()->isNew()): ?>
<input type="hidden" name="sf_method" value="put" />
<?php endif; ?>
  <table>
    <tfoot>
      <tr>
        <td colspan="2">
          <?php echo $form->renderHiddenFields(false) ?>
          &nbsp;<a href="<?php echo url_for('preguntaTest/index') ?>">Back to list</a>
          <?php if (!$form->getObject()->isNew()): ?>
            &nbsp;<?php echo link_to('Delete', 'preguntaTest/delete?id='.$form->getObject()->getId(), array('method' => 'delete', 'confirm' => 'Are you sure?')) ?>
          <?php endif; ?>
          <input type="submit" value="Save" />
        </td>
      </tr>
    </tfoot>
    <tbody>
      <?php echo $form->renderGlobalErrors() ?>
      <tr>
        <th><?php echo $form['modulo_id']->renderLabel() ?></th>
        <td>
          <?php echo $form['modulo_id']->renderError() ?>
          <?php echo $form['modulo_id'] ?>
        </td>
      </tr>
      <tr>
        <th><?php echo $form['imagen_id']->renderLabel() ?></th>
        <td>
          <?php echo $form['imagen_id']->renderError() ?>
          <?php echo $form['imagen_id'] ?>
        </td>
      </tr>
      <tr>
        <th><?php echo $form['dificultad_id']->renderLabel() ?></th>
        <td>
          <?php echo $form['dificultad_id']->renderError() ?>
          <?php echo $form['dificultad_id'] ?>
        </td>
      </tr>
      <tr>
        <th><?php echo $form['texto']->renderLabel() ?></th>
        <td>
          <?php echo $form['texto']->renderError() ?>
          <?php echo $form['texto'] ?>
        </td>
      </tr>      
    </tbody>
  </table>
</form>
