<?php slot('title', 'ECDL - Editar preguntas') ?>
<div class="editar">
    <h2>Lista de Preguntas</h2>

    <table>
        <tbody>
            <tr>
                <th>&nbsp;</th>
                <th>Texto</th>
                <th>Modulo</th>
                <th>Dificultad</th>            
            </tr>    
            <?php foreach ($preguntas as $p) : ?>
                <tr>
                    <td>
                        <a href="<?php echo url_for('pregunta_edit', $p)?>">
                            <img src="/images/edit.png" alt="Editar"/></a> 
                        | 
                        <a href="<?php echo url_for('pregunta_show', $p) ?>">
                            <img src="/images/delete.png" alt="Borrar"/></a>
                    </td>
                    <td><?php echo $p->getTexto() ?></td>
                    <td><?php echo $p->getEcdlModulo()->getNombre() ?></td>
                    <td><?php echo $p->getEcdlDificultad()->getNombre() ?></td>
                </tr>

            <?php endforeach; ?>
        </tbody>
    </table>


    <?php if ($pager->haveToPaginate()): ?>
        <div class="pagination">
            <a href="<?php echo url_for('pregunta_index') ?>?page=1">
                <img src="/images/first.png" alt="First page" title="First page" />
            </a>

            <a href="<?php echo url_for('pregunta_index') ?>?page=<?php echo $pager->getPreviousPage() ?>">
                <img src="/images/previous.png" alt="Previous page" title="Previous page" />
            </a>

            <?php foreach ($pager->getLinks() as $page): ?>
                <?php if ($page == $pager->getPage()): ?>
                    <?php echo $page ?>
                <?php else: ?>
                    <a href="<?php echo url_for('pregunta_index') ?>?page=<?php echo $page ?>"><?php echo $page ?></a>
                <?php endif; ?>
            <?php endforeach; ?>

            <a href="<?php echo url_for('pregunta_index') ?>?page=<?php echo $pager->getNextPage() ?>">
                <img src="/images/next.png" alt="Next page" title="Next page" />
            </a>

            <a href="<?php echo url_for('pregunta_index') ?>?page=<?php echo $pager->getLastPage() ?>">
                <img src="/images/last.png" alt="Last page" title="Last page" />
            </a>
        </div>
    <?php endif; ?>


    <a href="<?php echo url_for('pregunta_new') ?>">Nueva</a>
</div>