/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package avimarkmodmedcond;

import com.tulskiy.keymaster.common.HotKey;
import com.tulskiy.keymaster.common.HotKeyListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.KeyStroke;
import org.yaml.snakeyaml.Yaml;

/**
 *
 * @author jbaudens
 */
public class MyHotKeyListener implements HotKeyListener {
    
    private MedicalConditionFrame medicalConditionFrame;
    
    public MyHotKeyListener(String yamlFile) {
        super();
        medicalConditionFrame = new MedicalConditionFrame("Medical Condition Test",yamlFile);
        FileInputStream fileInputStream = null;
        Yaml yaml = new Yaml();
        try {
            fileInputStream = new FileInputStream(medicalConditionFrame.getYamlfile());
            ArrayList listOfObjects = (ArrayList)yaml.load(fileInputStream);
            for(Object o : listOfObjects){
                if (o instanceof MyKeyStroke){
                    toggleVisibility = ((MyKeyStroke)o).getKeyStroke();
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MedicalConditionFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String getToggleVisibility() {
        return toggleVisibility;
    }

    
    private String toggleVisibility = "control alt O";
    
    /**
     *
     * @param hotKey
     */
    @Override
    public void onHotKey(HotKey hotKey) {        
        if (hotKey.keyStroke.equals(KeyStroke.getKeyStroke(toggleVisibility))) {
            medicalConditionFrame.setVisible(!medicalConditionFrame.isVisible());
        }
    }
}
