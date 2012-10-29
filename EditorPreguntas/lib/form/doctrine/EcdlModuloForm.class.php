<?php

/**
 * EcdlModulo form.
 *
 * @package    EditorPreguntas
 * @subpackage form
 * @author     Your name here
 * @version    SVN: $Id: sfDoctrineFormTemplate.php 23810 2009-11-12 11:07:44Z Kris.Wallsmith $
 */
class EcdlModuloForm extends BaseEcdlModuloForm {

    public function configure() {
        unset(
                // Remove Timestamps
                $this['created_at'], $this['updated_at']
        );
        
        $this->widgetSchema['descripcion'] = new sfWidgetFormTextarea(array(), array('rows' => 6, 'cols' => 50));
        
        $this->validatorSchema['descripcion'] = new sfValidatorString(
                        array('min_length' => 5, 'max_length' => 255),
                        array(
                            'required' => 'El campo no puede ser vacío',
                            'min_length' => '"%value%" es demasiado corto (%min_length% caracteres mínimo).',
                            'max_length' => 'El texto es demasiado largo (%max_length% caracteres máximo).'
                        )
        );
        
        $this->validatorSchema['descripcion'] = new sfValidatorString(
                        array('min_length' => 5, 'max_length' => 145),
                        array(
                            'required' => 'El campo no puede ser vacío',
                            'min_length' => '"%value%" es demasiado corto (%min_length% caracteres mínimo).',
                            'max_length' => 'El texto es demasiado largo (%max_length% caracteres máximo).'
                        )
        );
    }

}
