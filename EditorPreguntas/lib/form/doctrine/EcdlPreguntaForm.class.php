<?php

/**
 * EcdlPregunta form.
 *
 * @package    EditorPreguntas
 * @subpackage form
 * @author     Your name here
 * @version    SVN: $Id: sfDoctrineFormTemplate.php 23810 2009-11-12 11:07:44Z Kris.Wallsmith $
 */
class EcdlPreguntaForm extends BaseEcdlPreguntaForm {

    public function configure() {
        unset(
                $this["created_at"], $this["updated_at"]
        );

        $this->widgetSchema["texto"] = new sfWidgetFormTextarea(array(), array('rows' => 6, 'cols' => 50));
        
        $this->setValidator('imagen', new sfValidatorFile(array(
                    'mime_types' => 'web_images',
                    'path' => sfConfig::get('sf_upload_dir') . '/preguntas',
                    'required' => false,
                )));

        $edit_mode = !$this->isNew();
        
        $src = $this->getObject()->getImagen();
        if (!isset($src))
        {
            $edit_mode = false;
        }
        
        $this->setWidget('imagen', new sfWidgetFormInputFileEditable(array(
                    'file_src' => '/uploads/preguntas/' . $this->getObject()->getImagen(),
                    'edit_mode' => $edit_mode, //!$this->isNew(),
                    'is_image' => true,
                    'with_delete' => true,
                    'delete_label' => 'Eliminar'
                )));

        // Create subforms Array
        $answers = new sfForm();
        $respuestas = $this->getObject()->getRespuestas();
        if (null !== $respuestas && $respuestas->count() > 0) {
            foreach ($respuestas->getData() as $key => $value) {
               $answerFrm = new EcdlRespuestaForm($value);
               $answers->embedForm($key + 1, $answerFrm);
            }
        } else {
            // Iterate 3 answers
            for ($i = 0; $i < 3; $i++) {
                // Create Subform and append content if proceed

                $answer = new EcdlRespuesta();
                $answer->EcdlPregunta = $this->getObject();
                $answerFrm = new EcdlRespuestaForm($answer);
                // Embed Form
                $answers->embedForm($i + 1, $answerFrm);
            }
        }


        // Embed all answers
        $this->embedForm('answers', $answers);
                
        $this->validatorSchema['imagen_delete'] = new sfValidatorPass();
    }

    public function isUniqueAnswerCorrect() {
        // Initialize flag
        $unique_answers = 0;

        // Iterate forms
        foreach ($this->values['answers'] as $answer) {
            // Check Correct
            if ($answer['correcta'] === true) {
                $unique_answers++;
            }
        }

        //echo 'unique='.$unique_answers;

        return $unique_answers == 1;
    }

}
