/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package avimarkmodmedcond;

import javax.swing.JComboBox;

/**
 *
 * @author jbaudens
 */
public class CheckComboBox extends JComboBox {

    public CheckComboBox () {
        super();
    }
    
    public CheckComboBox(Object[] items){
        super(items);
    }
    /**
     * This method needs to be overriden so that the popuplist won't close
     * when user selects one opion
     */
    @Override
    public void setPopupVisible(boolean flag)
    {
    //Not code her prevents the populist from closing
    }
}
 

