<div class="editar">
    <h2>Lista de dificultades disponibles</h2>

    <table>
        <thead>
            <tr>
                <th>Nombre</th>
                <th>Valor</th>
            </tr>
        </thead>
        <tbody>
            <?php foreach ($ecdl_dificultads as $ecdl_dificultad): ?>
                <tr>
                    <td><a href="<?php echo url_for('dificultad_edit', $ecdl_dificultad) ?>"><?php echo $ecdl_dificultad->getNombre() ?></a></td>
                    <td><?php echo $ecdl_dificultad->getValor() ?></td>
                </tr>
            <?php endforeach; ?>
        </tbody>
    </table>
</div>