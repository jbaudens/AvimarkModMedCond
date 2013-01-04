/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package avimarkmodmedcond;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javax.swing.*;
import org.yaml.snakeyaml.Yaml;

/**
 *
 * @author jbaudens
 */
public class MedicalConditionFrame extends JFrame{
    private static MedicalConditionFrame instance = null;
    
    private JPanel contentPane;
    
    private JFileChooser fc = new JFileChooser();;
    
    private Preferences prefs = Preferences.userRoot().node(this.getClass().getName());
    
    private String yamlfile;
    
    private BodySystems bodySystems = new BodySystems();
    
    private ClinicalComplaints clinicalComplaints = new ClinicalComplaints();
    
    private BodySystemsJPanel bodySystemsJPanel;
    private ClinicalComplaintsJPanel clinicalComplaintsJPanel;

    public String getYamlfile() {
        return yamlfile;
    }    
    
    private void loadBodySystems(){
        FileInputStream fileInputStream = null;
        Yaml yaml = new Yaml();
        try {
            fileInputStream = new FileInputStream(yamlfile);
            ArrayList listOfObjects = (ArrayList)yaml.load(fileInputStream);

            for(Object o : listOfObjects){
                if (o instanceof BodySystems){
                    bodySystems = (BodySystems) o;
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MedicalConditionFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadClinicalComplaints(){
        FileInputStream fileInputStream = null;
        Yaml yaml = new Yaml();
        try {
            fileInputStream = new FileInputStream(yamlfile);
            ArrayList listOfObjects = (ArrayList)yaml.load(fileInputStream);

            for(Object o : listOfObjects){
                if (o instanceof ClinicalComplaints){
                    clinicalComplaints = (ClinicalComplaints) o;
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MedicalConditionFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void resetUI(){
        this.contentPane.removeAll();
        this.contentPane.revalidate();
        this.contentPane.repaint();
    } 
    
    private void init(){
        JTabbedPane tabbedPane = new JTabbedPane();
        
        clinicalComplaintsJPanel = new ClinicalComplaintsJPanel(clinicalComplaints);
        tabbedPane.addTab("Clinical Complaints", clinicalComplaintsJPanel);
        
        bodySystemsJPanel = new BodySystemsJPanel(bodySystems);
        tabbedPane.addTab("Body Systems", bodySystemsJPanel);
        
        contentPane.add(tabbedPane);
    }
       
    
    /**
     *
     * @param title
     * @throws HeadlessException
     */
    protected MedicalConditionFrame(String title,String yamlFile) throws HeadlessException {
        super(title);
        
        contentPane = (JPanel)this.getContentPane();
        this.yamlfile = yamlFile;
        this.loadBodySystems();
        this.loadClinicalComplaints();
        this.init();
        

        
        /*Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double x = screenSize.width*0.05;
        double y = screenSize.height*0.05;
        double width = screenSize.width*0.9;
        double height = screenSize.height*0.9;*/
        if (bodySystemsJPanel.getBounds().height > clinicalComplaintsJPanel.getBounds().height){
            setBounds(bodySystemsJPanel.getBounds());
        }
        else{
            setBounds(clinicalComplaintsJPanel.getBounds());
        }
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
    } 
}
