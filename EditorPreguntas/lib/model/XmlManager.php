<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of XmlManager
 *
 * @author julio
 */
class XmlManager {

    public static function getXml() {
        $xml = new DOMDocument("1.0", "UTF-8");
        $xml->formatOutput = true;

        $root = $xml->createElement('ecdlplay');
        $xml->appendChild($root);

        $modulos = EcdlModuloTable::getInstance()->createQuery('a')->execute();
       
        foreach ($modulos as $modulo) {
            $preguntas = EcdlPreguntaTable::getInstance()->getPreguntasXML($modulo->getId());

            //if ($preguntas && count($preguntas) > 0) {
                $mod = $xml->createElement('modulo');

                $nombre_mod = $xml->createElement('nombre', $modulo->getNombre());
                $mod->appendChild($nombre_mod);

                $desc_mod = $xml->createElement('descripcion', $modulo->getDescripcion());
                $mod->appendChild($desc_mod);

                $preg = $xml->createElement('preguntas');
                XmlManager::appendPreguntas($xml, $preg, $preguntas);
                $mod->appendChild($preg);

                $root->appendChild($mod);
            //}
        }

        return $xml->saveXML();
    }

    

    private static function appendPreguntas(DOMDocument $xml, DOMElement $mod, $preguntas) {
        foreach ($preguntas as $pregunta) {
            $preg = $xml->createElement('pregunta');

            $preg_texto = $xml->createElement('texto', $pregunta->getTexto());
            $preg->appendChild($preg_texto);
            
            $preg_dificultad = $xml->createElement('dificultad', $pregunta->getEcdlDificultad()->getValor());
            $preg->appendChild($preg_dificultad);

            if ($pregunta->getImagen() !== null) {
                XmlManager::appendImagen($xml, $preg, $pregunta);
            }

            $preg_resp = $xml->createElement('respuestas');

            foreach ($pregunta->getRespuestas() as $respuesta) {
                $resp = $xml->createElement('respuesta', $respuesta->getTexto());

                if ($respuesta->getCorrecta()) {
                    $correcta = $xml->createAttribute('correcto');
                    $correcta->value = '1';

                    $resp->appendChild($correcta);
                }

                $preg_resp->appendChild($resp);
            }

            $preg->appendChild($preg_resp);

            $mod->appendChild($preg);
        }
    }

    private static function appendImagen(DOMDocument $xml, DOMElement $preg, EcdlPregunta $pregunta) {
        $ruta = 'http://' . $_SERVER['HTTP_HOST'] . '/uploads/preguntas/' . $pregunta->getImagen();

        $fichero = fopen($ruta, 'rb', TRUE);

        if ($fichero) {

            $buffer = stream_get_contents($fichero);

            fclose($fichero);

            $imagen = $xml->createElement('imagen', base64_encode($buffer));

            $preg->appendChild($imagen);
        }
    }

}

?>
