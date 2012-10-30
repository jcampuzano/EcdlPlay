<?php slot('title', 'Edición de los módulos del ECDL') ?>
<div class="editar">
    <h2>Ecdl modulos List</h2>

    <table>
        <thead>
            <tr>
                <th>Nombre</th>
                <th>Descripcion</th>
            </tr>
        </thead>
        <tbody>
            <?php foreach ($ecdl_modulos as $ecdl_modulo): ?>
                <tr>
                    <td><a href="<?php echo url_for('modulo_edit', $ecdl_modulo) ?>"><?php echo $ecdl_modulo->getNombre() ?></a></td>
                    <td><?php echo $ecdl_modulo->getDescripcion() ?></td>
                </tr>
            <?php endforeach; ?>
        </tbody>
    </table>
</div>