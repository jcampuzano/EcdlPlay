<h1>Ecdl preguntas List</h1>

<table>
  <thead>
    <tr>
      <th>Id</th>
      <th>Modulo</th>
      <th>Imagen</th>
      <th>Dificultad</th>
      <th>Texto</th>
      <th>Created at</th>
      <th>Updated at</th>
    </tr>
  </thead>
  <tbody>
    <?php foreach ($ecdl_preguntas as $ecdl_pregunta): ?>
    <tr>
      <td><a href="<?php echo url_for('preguntaTest/edit?id='.$ecdl_pregunta->getId()) ?>"><?php echo $ecdl_pregunta->getId() ?></a></td>
      <td><?php echo $ecdl_pregunta->getModuloId() ?></td>
      <td><?php echo $ecdl_pregunta->getImagenId() ?></td>
      <td><?php echo $ecdl_pregunta->getDificultadId() ?></td>
      <td><?php echo $ecdl_pregunta->getTexto() ?></td>
      <td><?php echo $ecdl_pregunta->getCreatedAt() ?></td>
      <td><?php echo $ecdl_pregunta->getUpdatedAt() ?></td>
    </tr>
    <?php endforeach; ?>
  </tbody>
</table>

  <a href="<?php echo url_for('preguntaTest/new') ?>">New</a>
