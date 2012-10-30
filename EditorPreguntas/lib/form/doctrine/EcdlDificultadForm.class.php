<?php

/**
 * EcdlDificultad form.
 *
 * @package    EditorPreguntas
 * @subpackage form
 * @author     Your name here
 * @version    SVN: $Id: sfDoctrineFormTemplate.php 23810 2009-11-12 11:07:44Z Kris.Wallsmith $
 */
class EcdlDificultadForm extends BaseEcdlDificultadForm {

    public function configure() {
        unset(
                // Remove Timestamps
                $this['created_at'], $this['updated_at']
        );
    }

}
 