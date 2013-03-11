/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.gui;

import ecdlplay.domain.GameEngine;
import java.awt.Graphics;
import java.awt.Image;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author julio
 */
public class CanvasBaseTest {
    
    public CanvasBaseTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of loadResources method, of class CanvasBase.
     */
    @Test
    public void testLoadResources() {
        System.out.println("loadResources");
        CanvasBase instance = null;
        instance.loadResources();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of paint method, of class CanvasBase.
     */
    @Test
    public void testPaint() {
        System.out.println("paint");
        Graphics g = null;
        CanvasBase instance = null;
        instance.paint(g);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isComponentsVisible method, of class CanvasBase.
     */
    @Test
    public void testIsComponentsVisible() {
        System.out.println("isComponentsVisible");
        CanvasBase instance = null;
        boolean expResult = false;
        boolean result = instance.isComponentsVisible();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setComponentsVisible method, of class CanvasBase.
     */
    @Test
    public void testSetComponentsVisible() {
        System.out.println("setComponentsVisible");
        boolean visible = false;
        CanvasBase instance = null;
        instance.setComponentsVisible(visible);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getComponent method, of class CanvasBase.
     */
    @Test
    public void testGetComponent_int() {
        System.out.println("getComponent");
        int id = 0;
        CanvasBase instance = null;
        Component expResult = null;
        Component result = instance.getComponent(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getComponent method, of class CanvasBase.
     */
    @Test
    public void testGetComponent_int_int() {
        System.out.println("getComponent");
        int x = 0;
        int y = 0;
        CanvasBase instance = null;
        Component expResult = null;
        Component result = instance.getComponent(x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addComponent method, of class CanvasBase.
     */
    @Test
    public void testAddComponent() {
        System.out.println("addComponent");
        Component component = null;
        CanvasBase instance = null;
        instance.addComponent(component);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mousePressed method, of class CanvasBase.
     */
    @Test
    public void testMousePressed() {
        System.out.println("mousePressed");
        int x = 0;
        int y = 0;
        CanvasBase instance = null;
        instance.mousePressed(x, y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mouseReleased method, of class CanvasBase.
     */
    @Test
    public void testMouseReleased() {
        System.out.println("mouseReleased");
        CanvasBase instance = null;
        instance.mouseReleased();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeAllComponents method, of class CanvasBase.
     */
    @Test
    public void testRemoveAllComponents() {
        System.out.println("removeAllComponents");
        CanvasBase instance = null;
        instance.removeAllComponents();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of paintBlack method, of class CanvasBase.
     */
    @Test
    public void testPaintBlack() {
        System.out.println("paintBlack");
        Graphics g = null;
        CanvasBase instance = null;
        instance.paintBlack(g);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of paintComponents method, of class CanvasBase.
     */
    @Test
    public void testPaintComponents() {
        System.out.println("paintComponents");
        Graphics g = null;
        CanvasBase instance = null;
        instance.paintComponents(g);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBackground method, of class CanvasBase.
     */
    @Test
    public void testGetBackground() {
        System.out.println("getBackground");
        CanvasBase instance = null;
        Image expResult = null;
        Image result = instance.getBackground();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBackground method, of class CanvasBase.
     */
    @Test
    public void testSetBackground() {
        System.out.println("setBackground");
        Image background = null;
        CanvasBase instance = null;
        instance.setBackground(background);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of paintBackground method, of class CanvasBase.
     */
    @Test
    public void testPaintBackground() {
        System.out.println("paintBackground");
        Graphics g = null;
        CanvasBase instance = null;
        instance.paintBackground(g);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resetClip method, of class CanvasBase.
     */
    @Test
    public void testResetClip() {
        System.out.println("resetClip");
        Graphics g = null;
        CanvasBase instance = null;
        instance.resetClip(g);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCanvas method, of class CanvasBase.
     */
    @Test
    public void testGetCanvas() {
        System.out.println("getCanvas");
        GameEngine ge = null;
        CanvasBase expResult = null;
        CanvasBase result = CanvasBase.getCanvas(ge);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class CanvasBaseImpl extends CanvasBase {

        public CanvasBaseImpl() {
            super(null);
        }

        public void loadResources() {
        }

        public void paint(Graphics g) {
        }
    }
}
