/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package avimarkmodmedcond;

import com.google.common.base.Joiner;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author jbaudens
 */
public class BodySystemUI implements ActionListener,ChangeListener{
    
    private BodySystem bodySystem;
    
    private JComboBox symmetryJComboBox;  
    private JRadioButton normalRadioButton;
    private JRadioButton abnormalRadioButton;
    private JRadioButton notExaminedRadioButton;
    private ButtonGroup bodySystemExaminationStateButtonGroup;
    private JLabel bodySystemNameJLabel;
    private JButton predefinedCommentsJButton;
    private JTextField notesJTextField;
    private JLabel bodySystemNameJLabel2;
    JList predefinedCommentsJList;
    JList predefinedCommentsJList2;
    JScrollPane listScrollPane1;
    JScrollPane listScrollPane2;


    public JButton getPredefinedCommentsJButton() {
        return predefinedCommentsJButton;
    }

    public void setPredefinedCommentsJButton(JButton predefinedCommentsJButton) {
        this.predefinedCommentsJButton = predefinedCommentsJButton;
    }

    public JButton getPredefinedCommentsJButton2() {
        return predefinedCommentsJButton2;
    }

    public void setPredefinedCommentsJButton2(JButton predefinedCommentsJButton2) {
        this.predefinedCommentsJButton2 = predefinedCommentsJButton2;
    }
    private JRadioButton normalRadioButton2;
    private JRadioButton abnormalRadioButton2;
    private JRadioButton notExaminedRadioButton2;
    private ButtonGroup bodySystemExaminationStateButtonGroup2;   
    private JLabel symmetryJLabel2; 
    private JButton predefinedCommentsJButton2;
    private JTextField notesJTextField2;
 
    public JLabel getBodySystemNameJLabel2() {
        return bodySystemNameJLabel2;
    }
    
    public BodySystem getBodySystem() {
        return bodySystem;
    }

    public JLabel getSymmetryJLabel2() {
        return symmetryJLabel2;
    }


    public JTextField getNotesJTextField2() {
        return notesJTextField2;
    }
    
    private String removePlural(String source){
        int lastValidIndex = source.length() - 1;
        if(source.charAt(lastValidIndex) == 's' || source.charAt(lastValidIndex) == 'S'){
            return source.substring(0, lastValidIndex);
        }
        return source;
    }

   @Override
   public void actionPerformed(ActionEvent e)
   {
        if (e.getSource() == symmetryJComboBox){
            if(symmetryJComboBox.getSelectedItem().equals("Both")){
                bodySystemNameJLabel.setText(bodySystem.getName());
                bodySystemNameJLabel2.setText("");
                symmetryJLabel2.setText("");
                setSymmetryRowEnabled(false);
            }
            else{
                if (symmetryJComboBox.getSelectedItem().equals("Right")) {
                    bodySystemNameJLabel.setText("Right " + removePlural(bodySystem.getName()));
                    bodySystemNameJLabel2.setText("Left " + removePlural(bodySystem.getName()));
                    symmetryJLabel2.setText("Left");
                
                }
                else if (symmetryJComboBox.getSelectedItem().equals("Left")) {
                    bodySystemNameJLabel.setText("Left " + removePlural(bodySystem.getName()));
                    bodySystemNameJLabel2.setText("Right " + removePlural(bodySystem.getName()));
                    symmetryJLabel2.setText("Right");
                }
                setSymmetryRowEnabled(true);
            }
        }
        else if (e.getSource() == predefinedCommentsJButton || 
                (e.getSource() == this.abnormalRadioButton && this.abnormalRadioButton.isSelected())){
            JOptionPane.showMessageDialog(predefinedCommentsJButton,
                    listScrollPane1, "Select your comments", JOptionPane.PLAIN_MESSAGE); 
        }
        else if (e.getSource() == predefinedCommentsJButton2 || 
                (e.getSource() == this.abnormalRadioButton2 && this.abnormalRadioButton2.isSelected())){
            JOptionPane.showMessageDialog(predefinedCommentsJButton,
                    listScrollPane2, "Select your comments", JOptionPane.PLAIN_MESSAGE);             
        }      
    }
    
    public void setSymmetryRowEnabled(boolean b){
        if (notExaminedRadioButton2.isSelected()){
            predefinedCommentsJButton2.setEnabled(false);
        }
        else{
            predefinedCommentsJButton2.setEnabled(b);
        }
        notesJTextField2.setEnabled(b);
        normalRadioButton2.setEnabled(b);
        abnormalRadioButton2.setEnabled(b);
        notExaminedRadioButton2.setEnabled(b);
    }
    
    @Override
    public void stateChanged(ChangeEvent e) {
        if(notExaminedRadioButton.isSelected()){
            predefinedCommentsJButton.setEnabled(false);
        }
        else{
            predefinedCommentsJButton.setEnabled(true);
        }
        
        if(notExaminedRadioButton2.isSelected()){
            predefinedCommentsJButton2.setEnabled(false);
        }
        else{
            predefinedCommentsJButton2.setEnabled(true);
        }
        
        if(e.getSource() == normalRadioButton && this.normalRadioButton.isSelected()){
            this.predefinedCommentsJList.clearSelection();
            this.predefinedCommentsJList.setSelectedIndex(0);
        }
        
        if(e.getSource() == normalRadioButton2 && this.normalRadioButton2.isSelected()){
            this.predefinedCommentsJList2.clearSelection();
            this.predefinedCommentsJList2.setSelectedIndex(0);
        }
    }
    
    /**
     *
     * @param bs
     */
    public BodySystemUI(BodySystem bs) {
        bodySystem=bs;
        set();

    }
    
    /**
     *
     */
    private void set(){
        predefinedCommentsJList = new JList(this.bodySystem.getListOfPredefinedComments().toArray());
        predefinedCommentsJList.setLayoutOrientation(JList.VERTICAL);
        predefinedCommentsJList.setSelectionModel(new DefaultListSelectionModel() {
        @Override
        public void setSelectionInterval(int index0, int index1) {
            if(super.isSelectedIndex(index0)) {
                super.removeSelectionInterval(index0, index1);
            }
            else {
                super.addSelectionInterval(index0, index1);
            }
        }
        });
        predefinedCommentsJList2 = new JList(this.bodySystem.getListOfPredefinedComments().toArray());
        predefinedCommentsJList2.setLayoutOrientation(JList.VERTICAL);
        predefinedCommentsJList2.setSelectionModel(new DefaultListSelectionModel() {
            @Override
            public void setSelectionInterval(int index0, int index1) {
                if(super.isSelectedIndex(index0)) {
                    super.removeSelectionInterval(index0, index1);
                }
                else {
                    super.addSelectionInterval(index0, index1);
                }
            }
            });
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double maxHeightofList = screenSize.height*0.60;

        listScrollPane1 = new JScrollPane(predefinedCommentsJList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        listScrollPane1.setSize(predefinedCommentsJList.getWidth(), (int)Math.round(maxHeightofList));
        listScrollPane1.setPreferredSize(new Dimension(predefinedCommentsJList.getWidth(), (int)Math.round(maxHeightofList)));
        listScrollPane2 = new JScrollPane(predefinedCommentsJList2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        listScrollPane2.setSize(predefinedCommentsJList.getWidth(), (int)Math.round(maxHeightofList));
        listScrollPane2.setPreferredSize(new Dimension(predefinedCommentsJList.getWidth(), (int)Math.round(maxHeightofList)));
        
        //Label
        bodySystemNameJLabel  = new JLabel(bodySystem.getName());
        
        //Label
        bodySystemNameJLabel2  = new JLabel("");
        
         //Body System Examination Status Radio Button Group
        normalRadioButton = new JRadioButton("N");
        abnormalRadioButton = new JRadioButton("AbN");
        notExaminedRadioButton = new JRadioButton("NE");
        bodySystemExaminationStateButtonGroup = new ButtonGroup();
        bodySystemExaminationStateButtonGroup.add(normalRadioButton);
        bodySystemExaminationStateButtonGroup.add(abnormalRadioButton);
        bodySystemExaminationStateButtonGroup.add(notExaminedRadioButton);
        bodySystemExaminationStateButtonGroup.setSelected(notExaminedRadioButton.getModel(), true);
        normalRadioButton.addChangeListener(this);
        abnormalRadioButton.addChangeListener(this);
        notExaminedRadioButton.addChangeListener(this);
        abnormalRadioButton.addActionListener(this);
        
        normalRadioButton2 = new JRadioButton("N");
        abnormalRadioButton2 = new JRadioButton("AbN");
        notExaminedRadioButton2 = new JRadioButton("NE");
        bodySystemExaminationStateButtonGroup2 = new ButtonGroup();
        bodySystemExaminationStateButtonGroup2.add(normalRadioButton2);
        bodySystemExaminationStateButtonGroup2.add(abnormalRadioButton2);
        bodySystemExaminationStateButtonGroup2.add(notExaminedRadioButton2);
        bodySystemExaminationStateButtonGroup2.setSelected(notExaminedRadioButton2.getModel(), true);
        normalRadioButton2.setEnabled(false);
        abnormalRadioButton2.setEnabled(false);
        notExaminedRadioButton2.setEnabled(false);
        normalRadioButton2.addChangeListener(this);
        abnormalRadioButton2.addChangeListener(this);
        notExaminedRadioButton2.addChangeListener(this);
        abnormalRadioButton2.addActionListener(this);
        
        //Symmetry Drop Down List       
        symmetryJComboBox = new JComboBox();
        symmetryJComboBox.addItem("Both");
        symmetryJComboBox.addItem("Left");
        symmetryJComboBox.addItem("Right");  
        if (!bodySystem.getIsSymmetrical()){
            symmetryJComboBox.setVisible(false);          
        }
        symmetryJComboBox.addActionListener(this);
               
        symmetryJLabel2 = new JLabel(); 
        if (!bodySystem.getIsSymmetrical()){
            symmetryJLabel2.setVisible(false);          
        }
       
        predefinedCommentsJButton = new JButton("Comments...");
        predefinedCommentsJButton2 = new JButton("Comments...");  
        predefinedCommentsJButton.addActionListener(this);
        predefinedCommentsJButton2.addActionListener(this);
        predefinedCommentsJButton.setEnabled(false);
        predefinedCommentsJButton2.setEnabled(false);
        //Notes
        notesJTextField  = new JTextField();
        notesJTextField2  = new JTextField();
    }

    public JRadioButton getNormalRadioButton2() {
        return normalRadioButton2;
    }

    public JRadioButton getAbnormalRadioButton2() {
        return abnormalRadioButton2;
    }

    public JRadioButton getNotExaminedRadioButton2() {
        return notExaminedRadioButton2;
    }

    public ButtonGroup getBodySystemExaminationStateButtonGroup2() {
        return bodySystemExaminationStateButtonGroup2;
    }
    
    /**
     *
     */
    public void reset(){
        bodySystemExaminationStateButtonGroup.setSelected(notExaminedRadioButton.getModel(), true);
          
        if (!bodySystem.getIsSymmetrical()){
            symmetryJComboBox.setEnabled(false);
            symmetryJComboBox.setVisible(false); 
        }
        symmetryJComboBox.setSelectedItem("Both");
        
        predefinedCommentsJList = new JList(this.bodySystem.getListOfPredefinedComments().toArray());
        predefinedCommentsJList2 = new JList(this.bodySystem.getListOfPredefinedComments().toArray());
        
        notesJTextField.setText("");
    }
    
    /*
     * N     General Appearance/Attitude - Bright an responsive
AbN  Right Ear - waxy exudate, inflammation
N      Left Ear - Clean, non-inflamed
AbN  Nose/Oral/MM - dry, hyperkeratotic nose, 3+ dental disease
N      Skin/haircoat - shiny coat, no excessive shedding
NE    Nodes/Thyroid 
N      Cardiovascular - normal sinus rhythm
N      Lungs - clear
N      Abdomen/ GI - pliant abdomen, no masses palpated
N      MS/Neuro - normal movement
NE    Urogenital/breasts
NE    Anal/rectal/prostate
     */
    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        if (this.isAbnormal()){result.append("Abn");}
        else if (this.isNormal()){result.append("N");}
        else if (this.isNotExamined()){result.append("NE");}
        result.append("\t");
        result.append(this.bodySystemNameJLabel.getText());
        
        ArrayList<String> allComments;
        if(!predefinedCommentsJList.isSelectionEmpty()){
            allComments = (ArrayList)predefinedCommentsJList.getSelectedValuesList();
        }
        else{
            allComments = new ArrayList<>();
        }
        if (!notesJTextField.getText().isEmpty()){
            allComments.add(notesJTextField.getText());
        }
        if (!allComments.isEmpty()){
            result.append(" - ");
            Joiner joiner = Joiner.on(", ").skipNulls();
            result.append(joiner.join(allComments));
        }
        
        if (this.isSymmetryActive()){
            result.append("\n");
            if (this.isAbnormal2()){result.append("Abn");}
            else if (this.isNormal2()){result.append("N");}
            else if (this.isNotExamined2()){result.append("NE");}
            result.append("\t");
            result.append(this.bodySystemNameJLabel2.getText());
            
            ArrayList<String> allComments2;
            if(!predefinedCommentsJList2.isSelectionEmpty()){
                allComments2 = (ArrayList)predefinedCommentsJList2.getSelectedValuesList();
            }
            else{
                allComments2 = new ArrayList<>();
            }
            if (!notesJTextField2.getText().isEmpty()){
                allComments2.add(notesJTextField2.getText());
            }
            if (!allComments2.isEmpty()){
                result.append(" - ");
                Joiner joiner = Joiner.on(", ").skipNulls();
                result.append(joiner.join(allComments2));
            }
        }
        result.append("\n");
        return result.toString();
    }
    
//        public String toString(int j){
//        StringBuilder result = new StringBuilder();
//        if (j==1){
//            ArrayList<String> listOfCommentsAndNotes = new ArrayList();
//            listOfCommentsAndNotes.addAll(predefinedCommentsJList.getSelectedValuesList());
//
//            if (!notesJTextField.getText().isEmpty()){
//                listOfCommentsAndNotes.add(notesJTextField.getText());
//            }
//
//            if (this.isAbnormal()){
//                result.append("\t").append(this.bodySystemNameJLabel.getText());
//
//
//                if (!listOfCommentsAndNotes.isEmpty()){
//                    Joiner joiner = Joiner.on(", ").skipNulls();
//                    result.append(" - ").append(joiner.join(listOfCommentsAndNotes));
//                }
//            }
//
//            if (this.isNormal() || this.isNotExamined()){
//                result.append(this.bodySystemNameJLabel.getText());
//
//                
//                if (!listOfCommentsAndNotes.isEmpty()){
//                    Joiner joiner = Joiner.on(", ").skipNulls();
//                    result.append(" (").append(joiner.join(listOfCommentsAndNotes)).append(")");
//                }
//            }
//        }
//        else if (j==2){
//            ArrayList<String> listOfCommentsAndNotes = new ArrayList();
//            listOfCommentsAndNotes.addAll(predefinedCommentsJList2.getSelectedValuesList());
//
//            if (!notesJTextField2.getText().isEmpty()){
//                listOfCommentsAndNotes.add(notesJTextField2.getText());
//            }
//
//            if (this.isAbnormal2()){
//                result.append("\t").append(this.bodySystemNameJLabel2.getText());
//
//                if (!listOfCommentsAndNotes.isEmpty()){
//                    Joiner joiner = Joiner.on(", ").skipNulls();
//                    result.append(" - ").append(joiner.join(listOfCommentsAndNotes));
//                }
//            }
//
//            if (this.isNormal2() || this.isNotExamined2()){
//                result.append(this.bodySystemNameJLabel2.getText());
//                
//                if (!listOfCommentsAndNotes.isEmpty()){
//                    Joiner joiner = Joiner.on(", ").skipNulls();
//                    result.append(" (").append(joiner.join(listOfCommentsAndNotes)).append(")");
//                }
//            }            
//        }
//
//        return result.toString();
//    }
    
    public boolean isSymmetryActive(){
        return this.symmetryJComboBox.getSelectedItem().equals("Left") || this.symmetryJComboBox.getSelectedItem().equals("Right");
    }
    
    /**
     *
     * @return
     */
    public boolean isExamined(){
        return !bodySystemExaminationStateButtonGroup.isSelected(notExaminedRadioButton.getModel());
    }
    /**
     *
     * @return
     */
    public boolean isNormal(){
        return bodySystemExaminationStateButtonGroup.isSelected(normalRadioButton.getModel());
    }
    /**
     *
     * @return
     */
    public boolean isAbnormal(){
        return bodySystemExaminationStateButtonGroup.isSelected(abnormalRadioButton.getModel());
    }
    /**
     *
     * @return
     */
    public boolean isNotExamined(){
        return bodySystemExaminationStateButtonGroup.isSelected(notExaminedRadioButton.getModel());
    }
    /**
     *
     * @return
     */
    public boolean isExamined2(){
        return !bodySystemExaminationStateButtonGroup2.isSelected(notExaminedRadioButton2.getModel());
    }
    /**
     *
     * @return
     */
    public boolean isNormal2(){
        return bodySystemExaminationStateButtonGroup2.isSelected(normalRadioButton2.getModel());
    }
    /**
     *
     * @return
     */
    public boolean isAbnormal2(){
        return bodySystemExaminationStateButtonGroup2.isSelected(abnormalRadioButton2.getModel());
    }
    /**
     *
     * @return
     */
    public boolean isNotExamined2(){
        return bodySystemExaminationStateButtonGroup2.isSelected(notExaminedRadioButton2.getModel());
    }    
    /**
     *
     * @return
     */
    public JComboBox getSymmetryJComboBox() {
        return symmetryJComboBox;
    }

    /**
     *
     * @return
     */
    public JRadioButton getNormalRadioButton() {
        return normalRadioButton;
    }

    /**
     *
     * @return
     */
    public JRadioButton getAbnormalRadioButton() {
        return abnormalRadioButton;
    }

    /**
     *
     * @return
     */
    public JRadioButton getNotExaminedRadioButton() {
        return notExaminedRadioButton;
    }

    /**
     *
     * @return
     */
    public ButtonGroup getBodySystemExaminationStateButtonGroup() {
        return bodySystemExaminationStateButtonGroup;
    }

    /**
     *
     * @return
     */
    public JLabel getBodySystemNameJLabel() {
        return bodySystemNameJLabel;
    }

    /**
     *
     * @return
     */
    public JTextField getNotesJTextField() {
        return notesJTextField;
    }

}
