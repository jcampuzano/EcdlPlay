<?php

/**
 * EcdlPregunta filter form base class.
 *
 * @package    EditorPreguntas
 * @subpackage filter
 * @author     Your name here
 * @version    SVN: $Id: sfDoctrineFormFilterGeneratedTemplate.php 29570 2010-05-21 14:49:47Z Kris.Wallsmith $
 */
abstract class BaseEcdlPreguntaFormFilter extends BaseFormFilterDoctrine
{
  public function setup()
  {
    $this->setWidgets(array(
      'modulo_id'     => new sfWidgetFormDoctrineChoice(array('model' => $this->getRelatedModelName('EcdlModulo'), 'add_empty' => true)),
      'imagen'        => new sfWidgetFormFilterInput(array('with_empty' => false)),
      'dificultad_id' => new sfWidgetFormDoctrineChoice(array('model' => $this->getRelatedModelName('EcdlDificultad'), 'add_empty' => true)),
      'texto'         => new sfWidgetFormFilterInput(array('with_empty' => false)),
      'created_at'    => new sfWidgetFormFilterDate(array('from_date' => new sfWidgetFormDate(), 'to_date' => new sfWidgetFormDate(), 'with_empty' => false)),
      'updated_at'    => new sfWidgetFormFilterDate(array('from_date' => new sfWidgetFormDate(), 'to_date' => new sfWidgetFormDate(), 'with_empty' => false)),
    ));

    $this->setValidators(array(
      'modulo_id'     => new sfValidatorDoctrineChoice(array('required' => false, 'model' => $this->getRelatedModelName('EcdlModulo'), 'column' => 'id')),
      'imagen'        => new sfValidatorPass(array('required' => false)),
      'dificultad_id' => new sfValidatorDoctrineChoice(array('required' => false, 'model' => $this->getRelatedModelName('EcdlDificultad'), 'column' => 'id')),
      'texto'         => new sfValidatorPass(array('required' => false)),
      'created_at'    => new sfValidatorDateRange(array('required' => false, 'from_date' => new sfValidatorDateTime(array('required' => false, 'datetime_output' => 'Y-m-d 00:00:00')), 'to_date' => new sfValidatorDateTime(array('required' => false, 'datetime_output' => 'Y-m-d 23:59:59')))),
      'updated_at'    => new sfValidatorDateRange(array('required' => false, 'from_date' => new sfValidatorDateTime(array('required' => false, 'datetime_output' => 'Y-m-d 00:00:00')), 'to_date' => new sfValidatorDateTime(array('required' => false, 'datetime_output' => 'Y-m-d 23:59:59')))),
    ));

    $this->widgetSchema->setNameFormat('ecdl_pregunta_filters[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    $this->setupInheritance();

    parent::setup();
  }

  public function getModelName()
  {
    return 'EcdlPregunta';
  }

  public function getFields()
  {
    return array(
      'id'            => 'Number',
      'modulo_id'     => 'ForeignKey',
      'imagen'        => 'Text',
      'dificultad_id' => 'ForeignKey',
      'texto'         => 'Text',
      'created_at'    => 'Date',
      'updated_at'    => 'Date',
    );
  }
}
