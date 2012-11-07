<?php

/**
 * EcdlRespuesta form.
 *
 * @package    EditorPreguntas
 * @subpackage form
 * @author     Your name here
 * @version    SVN: $Id: sfDoctrineFormTemplate.php 23810 2009-11-12 11:07:44Z Kris.Wallsmith $
 */
class EcdlRespuestaForm extends BaseEcdlRespuestaForm {

    
    public function configure() {
        unset(
                // Remove Timestamps
                $this['created_at'], $this['updated_at'],
                // Remove Id
                $this['pregunta_id']
        );

        // Set Widget
        $this->widgetSchema['texto'] = new sfWidgetFormTextarea(array(), array('rows' => 2, 'cols' => 50));
        // Set Text Validator
        $this->validatorSchema['texto'] = new sfValidatorString(
                        array('min_length' => 1, 'max_length' => 145),
                        array(
                            'required' => 'El campo no puede ser vacío',
                            'min_length' => '"%value%" es demasiado corto (%min_length% caracteres mínimo).',
                            'max_length' => '"%value%" es demasiado largo (%max_length% caracteres máximo).'
                        )
        );

        // Set Default
        $this->setDefault('correcto', false);
    }

}
