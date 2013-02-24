<?php

/**
 * xml actions.
 *
 * @package    EditorPreguntas
 * @subpackage xml
 * @author     Your name here
 * @version    SVN: $Id: actions.class.php 23810 2009-11-12 11:07:44Z Kris.Wallsmith $
 */
class xmlActions extends sfActions
{
 /**
  * Executes index action
  *
  * @param sfRequest $request A request object
  */
  public function executeIndex(sfWebRequest $request)
  {
    set_time_limit(0);
    $xml = XmlManager::getXml();
    $this->getResponse()->setContentType('text/xml');
    header('Content-Disposition: attachment; filename='.basename('datos.xml'));
    $this->getResponse()->setContent($xml);
    
    return sfView::NONE;
  }
}
