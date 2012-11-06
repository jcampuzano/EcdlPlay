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
        $this->widgetSchema["imagen_id"] = new sfWidgetFormInputFile();

        // Create subforms Array
        $answers = new sfForm();
        // Iterate 3 answers
        for ($i = 0; $i < 3; $i++) {
            // Create Subform and append content if proceed
            if (isset($this->answers) && isset($this->answers[$i])) {
                $answer = new EcdlRespuestaForm($this->answers[$i]);
            } else {
                $answer = new EcdlRespuestaForm();
            }
            // Embed Form
            $answers->embedForm($i + 1, $answer);
        }

        // Embed all answers
        $this->embedForm('answers', $answers);
    }

    public function isUniqueAnswerCorrect() {
        // Initialize flag
        $unique_answers = 0;

        // Iterate forms
        foreach ($this->values['answers'] as $answer) {
            // Check Correct
            if ($answer['correcta'] === true){
                $unique_answers++;
            }
        }

        //echo 'unique='.$unique_answers;

        return $unique_answers == 1;
    }

}
