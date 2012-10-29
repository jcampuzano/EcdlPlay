<?php

/**
 * EcdlRespuesta form base class.
 *
 * @method EcdlRespuesta getObject() Returns the current form's model object
 *
 * @package    EditorPreguntas
 * @subpackage form
 * @author     Your name here
 * @version    SVN: $Id: sfDoctrineFormGeneratedTemplate.php 29553 2010-05-20 14:33:00Z Kris.Wallsmith $
 */
abstract class BaseEcdlRespuestaForm extends BaseFormDoctrine
{
  public function setup()
  {
    $this->setWidgets(array(
      'id'          => new sfWidgetFormInputHidden(),
      'pregunta_id' => new sfWidgetFormDoctrineChoice(array('model' => $this->getRelatedModelName('EcdlPregunta'), 'add_empty' => false)),
      'texto'       => new sfWidgetFormInputText(),
      'correcta'    => new sfWidgetFormInputCheckbox(),
      'created_at'  => new sfWidgetFormDateTime(),
      'updated_at'  => new sfWidgetFormDateTime(),
    ));

    $this->setValidators(array(
      'id'          => new sfValidatorChoice(array('choices' => array($this->getObject()->get('id')), 'empty_value' => $this->getObject()->get('id'), 'required' => false)),
      'pregunta_id' => new sfValidatorDoctrineChoice(array('model' => $this->getRelatedModelName('EcdlPregunta'))),
      'texto'       => new sfValidatorPass(),
      'correcta'    => new sfValidatorBoolean(),
      'created_at'  => new sfValidatorDateTime(),
      'updated_at'  => new sfValidatorDateTime(),
    ));

    $this->widgetSchema->setNameFormat('ecdl_respuesta[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    $this->setupInheritance();

    parent::setup();
  }

  public function getModelName()
  {
    return 'EcdlRespuesta';
  }

}
