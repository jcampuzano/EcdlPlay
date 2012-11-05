<?php

/**
 * preguntaTest actions.
 *
 * @package    EditorPreguntas
 * @subpackage preguntaTest
 * @author     Your name here
 * @version    SVN: $Id: actions.class.php 23810 2009-11-12 11:07:44Z Kris.Wallsmith $
 */
class preguntaTestActions extends sfActions
{
  public function executeIndex(sfWebRequest $request)
  {
    $this->ecdl_preguntas = Doctrine_Core::getTable('EcdlPregunta')
      ->createQuery('a')
      ->execute();
  }

  public function executeNew(sfWebRequest $request)
  {
    $this->form = new EcdlPreguntaForm();
  }

  public function executeCreate(sfWebRequest $request)
  {
    $this->forward404Unless($request->isMethod(sfRequest::POST));

    $this->form = new EcdlPreguntaForm();

    $this->processForm($request, $this->form);

    $this->setTemplate('new');
  }

  public function executeEdit(sfWebRequest $request)
  {
    $this->forward404Unless($ecdl_pregunta = Doctrine_Core::getTable('EcdlPregunta')->find(array($request->getParameter('id'))), sprintf('Object ecdl_pregunta does not exist (%s).', $request->getParameter('id')));
    $this->form = new EcdlPreguntaForm($ecdl_pregunta);
  }

  public function executeUpdate(sfWebRequest $request)
  {
    $this->forward404Unless($request->isMethod(sfRequest::POST) || $request->isMethod(sfRequest::PUT));
    $this->forward404Unless($ecdl_pregunta = Doctrine_Core::getTable('EcdlPregunta')->find(array($request->getParameter('id'))), sprintf('Object ecdl_pregunta does not exist (%s).', $request->getParameter('id')));
    $this->form = new EcdlPreguntaForm($ecdl_pregunta);

    $this->processForm($request, $this->form);

    $this->setTemplate('edit');
  }

  public function executeDelete(sfWebRequest $request)
  {
    $request->checkCSRFProtection();

    $this->forward404Unless($ecdl_pregunta = Doctrine_Core::getTable('EcdlPregunta')->find(array($request->getParameter('id'))), sprintf('Object ecdl_pregunta does not exist (%s).', $request->getParameter('id')));
    $ecdl_pregunta->delete();

    $this->redirect('preguntaTest/index');
  }

  protected function processForm(sfWebRequest $request, sfForm $form)
  {
    $form->bind($request->getParameter($form->getName()), $request->getFiles($form->getName()));
    if ($form->isValid())
    {
      $ecdl_pregunta = $form->save();

      $this->redirect('preguntaTest/edit?id='.$ecdl_pregunta->getId());
    }
  }
}
