<?php

/**
 * modulo actions.
 *
 * @package    EditorPreguntas
 * @subpackage modulo
 * @author     Your name here
 * @version    SVN: $Id: actions.class.php 23810 2009-11-12 11:07:44Z Kris.Wallsmith $
 */
class moduloActions extends sfActions
{
  public function executeIndex(sfWebRequest $request)
  {
    $this->ecdl_modulos = Doctrine_Core::getTable('EcdlModulo')
      ->createQuery('a')
      ->execute();
  }

  public function executeShow(sfWebRequest $request)
  {
    $this->ecdl_modulo = Doctrine_Core::getTable('EcdlModulo')->find(array($request->getParameter('id')));
    $this->forward404Unless($this->ecdl_modulo);
  }

  public function executeNew(sfWebRequest $request)
  {
    $this->form = new EcdlModuloForm();
  }

  public function executeCreate(sfWebRequest $request)
  {
    $this->forward404Unless($request->isMethod(sfRequest::POST));

    $this->form = new EcdlModuloForm();

    $this->processForm($request, $this->form);

    $this->setTemplate('new');
  }

  public function executeEdit(sfWebRequest $request)
  {
    $this->forward404Unless($ecdl_modulo = Doctrine_Core::getTable('EcdlModulo')->find(array($request->getParameter('id'))), sprintf('Object ecdl_modulo does not exist (%s).', $request->getParameter('id')));
    $this->form = new EcdlModuloForm($ecdl_modulo);
  }

  public function executeUpdate(sfWebRequest $request)
  {
    $this->forward404Unless($request->isMethod(sfRequest::POST) || $request->isMethod(sfRequest::PUT));
    $this->forward404Unless($ecdl_modulo = Doctrine_Core::getTable('EcdlModulo')->find(array($request->getParameter('id'))), sprintf('Object ecdl_modulo does not exist (%s).', $request->getParameter('id')));
    $this->form = new EcdlModuloForm($ecdl_modulo);

    $this->processForm($request, $this->form);

    $this->setTemplate('edit');
  }

  public function executeDelete(sfWebRequest $request)
  {
    $request->checkCSRFProtection();

    $this->forward404Unless($ecdl_modulo = Doctrine_Core::getTable('EcdlModulo')->find(array($request->getParameter('id'))), sprintf('Object ecdl_modulo does not exist (%s).', $request->getParameter('id')));
    $ecdl_modulo->delete();

    $this->redirect('modulo/index');
  }

  protected function processForm(sfWebRequest $request, sfForm $form)
  {
    $form->bind($request->getParameter($form->getName()), $request->getFiles($form->getName()));
    if ($form->isValid())
    {
      $ecdl_modulo = $form->save();

      $this->redirect('modulo/edit?id='.$ecdl_modulo->getId());
    }
  }
}
