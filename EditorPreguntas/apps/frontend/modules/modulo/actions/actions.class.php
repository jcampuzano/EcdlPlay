<?php

/**
 * modulo actions.
 *
 * @package    EditorPreguntas
 * @subpackage modulo
 * @author     Your name here
 * @version    SVN: $Id: actions.class.php 23810 2009-11-12 11:07:44Z Kris.Wallsmith $
 */
class moduloActions extends sfActions {

    public function executeIndex(sfWebRequest $request) {
        $this->ecdl_modulos = Doctrine_Core::getTable('EcdlModulo')
                ->createQuery('a')
                ->execute();
    }

    public function executeEdit(sfWebRequest $request) {
        $this->forward404Unless($ecdl_modulo = Doctrine_Core::getTable('EcdlModulo')->find(array($request->getParameter('id'))), sprintf('Object ecdl_modulo does not exist (%s).', $request->getParameter('id')));
        $this->modulo = $this->getRoute()->getObject();
        $this->form = new EcdlModuloForm($this->modulo);
    }

    public function executeUpdate(sfWebRequest $request) {
        $this->forward404Unless($request->isMethod(sfRequest::POST) || $request->isMethod(sfRequest::PUT));
        $this->forward404Unless($ecdl_modulo = Doctrine_Core::getTable('EcdlModulo')->find(array($request->getParameter('id'))), sprintf('Object ecdl_modulo does not exist (%s).', $request->getParameter('id')));
        
        $this->modulo = $this->getRoute()->getObject();
        $this->form = new EcdlModuloForm($this->modulo);
        $this->processForm($request, $this->form);
        $this->setTemplate('edit');
    }

    protected function processForm(sfWebRequest $request, sfForm $form) {
        // Bind
        $form->bind(
                $request->getParameter($form->getName()), $request->getFiles($form->getName())
        );

        if ($form->isValid()) {
            $modulo = $form->save();

            // Redirect
            $this->redirect('modulo_index');
        }
    }

}
