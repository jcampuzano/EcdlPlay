<h1>Ecdl modulos List</h1>

<table>
  <thead>
    <tr>
      <th>Id</th>
      <th>Nombre</th>
      <th>Descripcion</th>
      <th>Created at</th>
      <th>Updated at</th>
    </tr>
  </thead>
  <tbody>
    <?php foreach ($ecdl_modulos as $ecdl_modulo): ?>
    <tr>
      <td><a href="<?php echo url_for('modulo/show?id='.$ecdl_modulo->getId()) ?>"><?php echo $ecdl_modulo->getId() ?></a></td>
      <td><?php echo $ecdl_modulo->getNombre() ?></td>
      <td><?php echo $ecdl_modulo->getDescripcion() ?></td>
      <td><?php echo $ecdl_modulo->getCreatedAt() ?></td>
      <td><?php echo $ecdl_modulo->getUpdatedAt() ?></td>
    </tr>
    <?php endforeach; ?>
  </tbody>
</table>

  <a href="<?php echo url_for('modulo/new') ?>">New</a>
