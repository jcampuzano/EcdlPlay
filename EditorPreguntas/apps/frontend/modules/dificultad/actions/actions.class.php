<?php

/**
 * dificultad actions.
 *
 * @package    EditorPreguntas
 * @subpackage dificultad
 * @author     Your name here
 * @version    SVN: $Id: actions.class.php 23810 2009-11-12 11:07:44Z Kris.Wallsmith $
 */
class dificultadActions extends sfActions {

    public function executeIndex(sfWebRequest $request) {
        $this->ecdl_dificultads = Doctrine_Core::getTable('EcdlDificultad')
                ->createQuery('a')
                ->execute();
    }

    public function executeNew(sfWebRequest $request) {
        $this->form = new EcdlDificultadForm();
    }

    public function executeEdit(sfWebRequest $request) {
        $this->forward404Unless($ecdl_dificultad = Doctrine_Core::getTable('EcdlDificultad')->find(array($request->getParameter('id'))), sprintf('Object ecdl_dificultad does not exist (%s).', $request->getParameter('id')));
        $this->dificultad = $this->getRoute()->getObject();
        $this->form = new EcdlDificultadForm($this->dificultad);
    }

    public function executeUpdate(sfWebRequest $request) {
        $this->forward404Unless($request->isMethod(sfRequest::POST) || $request->isMethod(sfRequest::PUT));
        $this->forward404Unless($ecdl_dificultad = Doctrine_Core::getTable('EcdlDificultad')->find(array($request->getParameter('id'))), sprintf('Object ecdl_dificultad does not exist (%s).', $request->getParameter('id')));
        $this->form = new EcdlDificultadForm($ecdl_dificultad);

        $this->dificultad = $this->getRoute()->getObject();
        $this->form = new EcdlDificultadForm($this->dificultad);
        $this->processForm($request, $this->form);
        $this->setTemplate('edit');
    }

    protected function processForm(sfWebRequest $request, sfForm $form) {
        $form->bind($request->getParameter($form->getName()), $request->getFiles($form->getName()));
        if ($form->isValid()) {
            $ecdl_dificultad = $form->save();

            // Redirect
            $this->redirect('dificultad_index');
        }
    }

}
 