<?php

/**
 * BaseEcdlModulo
 * 
 * This class has been auto-generated by the Doctrine ORM Framework
 * 
 * @property integer $id
 * @property string $nombre
 * @property text $descripcion
 * @property Doctrine_Collection $Modulo
 * 
 * @method integer             getId()          Returns the current record's "id" value
 * @method string              getNombre()      Returns the current record's "nombre" value
 * @method text                getDescripcion() Returns the current record's "descripcion" value
 * @method Doctrine_Collection getModulo()      Returns the current record's "Modulo" collection
 * @method EcdlModulo          setId()          Sets the current record's "id" value
 * @method EcdlModulo          setNombre()      Sets the current record's "nombre" value
 * @method EcdlModulo          setDescripcion() Sets the current record's "descripcion" value
 * @method EcdlModulo          setModulo()      Sets the current record's "Modulo" collection
 * 
 * @package    EditorPreguntas
 * @subpackage model
 * @author     Your name here
 * @version    SVN: $Id: Builder.php 7490 2010-03-29 19:53:27Z jwage $
 */
abstract class BaseEcdlModulo extends sfDoctrineRecord
{
    public function setTableDefinition()
    {
        $this->setTableName('ecdl_modulo');
        $this->hasColumn('id', 'integer', null, array(
             'type' => 'integer',
             'primary' => true,
             'autoincrement' => true,
             'unsigned' => true,
             ));
        $this->hasColumn('nombre', 'string', 255, array(
             'type' => 'string',
             'notnull' => true,
             'unique' => true,
             'length' => 255,
             ));
        $this->hasColumn('descripcion', 'text', null, array(
             'type' => 'text',
             'notnull' => true,
             'unique' => false,
             ));
    }

    public function setUp()
    {
        parent::setUp();
        $this->hasMany('EcdlPregunta as Modulo', array(
             'local' => 'id',
             'foreign' => 'modulo_id'));

        $timestampable0 = new Doctrine_Template_Timestampable();
        $this->actAs($timestampable0);
    }
}