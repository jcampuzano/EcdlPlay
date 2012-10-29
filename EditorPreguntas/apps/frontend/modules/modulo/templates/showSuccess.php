<table>
  <tbody>
    <tr>
      <th>Id:</th>
      <td><?php echo $ecdl_modulo->getId() ?></td>
    </tr>
    <tr>
      <th>Nombre:</th>
      <td><?php echo $ecdl_modulo->getNombre() ?></td>
    </tr>
    <tr>
      <th>Descripcion:</th>
      <td><?php echo $ecdl_modulo->getDescripcion() ?></td>
    </tr>
    <tr>
      <th>Created at:</th>
      <td><?php echo $ecdl_modulo->getCreatedAt() ?></td>
    </tr>
    <tr>
      <th>Updated at:</th>
      <td><?php echo $ecdl_modulo->getUpdatedAt() ?></td>
    </tr>
  </tbody>
</table>

<hr />

<a href="<?php echo url_for('modulo/edit?id='.$ecdl_modulo->getId()) ?>">Edit</a>
&nbsp;
<a href="<?php echo url_for('modulo/index') ?>">List</a>
