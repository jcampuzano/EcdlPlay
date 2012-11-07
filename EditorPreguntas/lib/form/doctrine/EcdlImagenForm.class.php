<?php

/**
 * EcdlImagen form.
 *
 * @package    EditorPreguntas
 * @subpackage form
 * @author     Your name here
 * @version    SVN: $Id: sfDoctrineFormTemplate.php 23810 2009-11-12 11:07:44Z Kris.Wallsmith $
 */
class EcdlImagenForm extends BaseEcdlImagenForm {

    public function configure() {
        unset($this['created_at'], $this['updated_at']);

        $this->setValidator('filename', new sfValidatorFile(array(
                    'mime_types' => 'web_images',
                    'path' => sfConfig::get('sf_upload_dir') . '/preguntas',
                    'required' => false,
                )));

        $this->setWidget('filename', new sfWidgetFormInputFileEditable(array(
                    'file_src' => '/uploads/preguntas/' . $this->getObject()->filename,
                    'edit_mode' => !$this->isNew(),
                    'is_image' => true,
                    'with_delete' => true,
                    'delete_label' => 'Eliminar'
                )));
        
        $this->validatorSchema['filename_delete'] = new sfValidatorPass();
    }

}
