<?php

/**
 * EcdlPregunta form base class.
 *
 * @method EcdlPregunta getObject() Returns the current form's model object
 *
 * @package    EditorPreguntas
 * @subpackage form
 * @author     Your name here
 * @version    SVN: $Id: sfDoctrineFormGeneratedTemplate.php 29553 2010-05-20 14:33:00Z Kris.Wallsmith $
 */
abstract class BaseEcdlPreguntaForm extends BaseFormDoctrine
{
  public function setup()
  {
    $this->setWidgets(array(
      'id'            => new sfWidgetFormInputHidden(),
      'modulo_id'     => new sfWidgetFormDoctrineChoice(array('model' => $this->getRelatedModelName('EcdlModulo'), 'add_empty' => false)),
      'imagen'        => new sfWidgetFormInputText(),
      'dificultad_id' => new sfWidgetFormDoctrineChoice(array('model' => $this->getRelatedModelName('EcdlDificultad'), 'add_empty' => false)),
      'texto'         => new sfWidgetFormInputText(),
      'created_at'    => new sfWidgetFormDateTime(),
      'updated_at'    => new sfWidgetFormDateTime(),
    ));

    $this->setValidators(array(
      'id'            => new sfValidatorChoice(array('choices' => array($this->getObject()->get('id')), 'empty_value' => $this->getObject()->get('id'), 'required' => false)),
      'modulo_id'     => new sfValidatorDoctrineChoice(array('model' => $this->getRelatedModelName('EcdlModulo'))),
      'imagen'        => new sfValidatorString(array('max_length' => 255)),
      'dificultad_id' => new sfValidatorDoctrineChoice(array('model' => $this->getRelatedModelName('EcdlDificultad'))),
      'texto'         => new sfValidatorPass(),
      'created_at'    => new sfValidatorDateTime(),
      'updated_at'    => new sfValidatorDateTime(),
    ));

    $this->widgetSchema->setNameFormat('ecdl_pregunta[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    $this->setupInheritance();

    parent::setup();
  }

  public function getModelName()
  {
    return 'EcdlPregunta';
  }

}
