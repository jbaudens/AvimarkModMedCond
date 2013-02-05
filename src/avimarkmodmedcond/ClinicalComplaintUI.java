/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package avimarkmodmedcond;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author jbaudens
 */
public class ClinicalComplaintUI implements ActionListener{
    ClinicalComplaint clinicalComplaint;

    public ClinicalComplaint getClinicalComplaint() {
        return clinicalComplaint;
    }
    
    private JLabel clinicalComplaintNameJLabel;
    private JButton askQuestionsJButton;
    private JPanel questionsJPanel;

    public JButton getAskQuestionsJButton() {
        return askQuestionsJButton;
    }

    
    public JLabel getClinicalComplaintNameJLabel() {
        return clinicalComplaintNameJLabel;
        
    }
    
    /**
     *
     * @param cc
     */
    public ClinicalComplaintUI(ClinicalComplaint cc) {
        clinicalComplaint=cc;
        set();
    }
    

    
    private void set(){
        clinicalComplaintNameJLabel = new JLabel(clinicalComplaint.getName());
        askQuestionsJButton = new JButton("Ask Questions");
        askQuestionsJButton.addActionListener(this);
        questionsJPanel = new JPanel();
        questionsJPanel.setLayout(new GridLayout(clinicalComplaint.getListOfQuestions().size(),1));
        for(Question q : clinicalComplaint.getListOfQuestions()){
            questionsJPanel.add(new QuestionJPanel(q));
        }
        
    }
    

    public void reset(){
        for(Component c1 : this.questionsJPanel.getComponents()){
            if (c1 instanceof JPanel){
                JPanel j = (JPanel) c1;
                for(Component c2 : j.getComponents()){
                    if (c2 instanceof JTextField){
                        ((JTextField) c2).setText("");
                    }
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.askQuestionsJButton){
            JOptionPane.showMessageDialog(this.askQuestionsJButton.getParent().getParent().getParent(),
                    this.questionsJPanel, "Answer the questions", JOptionPane.PLAIN_MESSAGE); 
        }
    }
    
    public String toString(String category){
        StringBuilder result = new StringBuilder();
        for(Component c1 : this.questionsJPanel.getComponents()){
            if (c1 instanceof QuestionJPanel){
                QuestionJPanel j = (QuestionJPanel) c1;
                if (j.question.getCategory().equals(category)){
                    for(Component c2 : j.getComponents()){
                        if (c2 instanceof JTextField){
                            if(!((JTextField) c2).getText().isEmpty()){
                                result.append("\t");
                                result.append(((JTextField) c2).getText());
                                result.append("\n");
                            }
                        }
                    }
                }
            }
        }
        
        return result.toString();
    }
    
       public String toString(){
        StringBuilder result = new StringBuilder();
        result.append(this.clinicalComplaintNameJLabel.getText());
        result.append("\n");
        boolean atLeastOneQuestionIsAnswered = false;
        for(Component c1 : this.questionsJPanel.getComponents()){
            if (c1 instanceof QuestionJPanel){
                QuestionJPanel j = (QuestionJPanel) c1;
                String category = j.question.getCategory();
                String answer = "";
                String question = "";
                for(Component c2 : j.getComponents()){
                    if (c2 instanceof JTextField){
                        if(!((JTextField) c2).getText().isEmpty()){
                            answer = ((JTextField) c2).getText();
                        }
                    }
                     if (c2 instanceof JLabel){
                        if(!((JLabel) c2).getText().isEmpty()){
                            question =((JLabel) c2).getText();
                        }
                    }
                }
                if(!answer.isEmpty()){
                    atLeastOneQuestionIsAnswered = true;
                    String temp = "   " + category + ": " + answer + "\n";
                    result.append(temp);
                }
                
            }
        }
        result.append("\n");
        if (atLeastOneQuestionIsAnswered){
            return result.toString();
        }
        else{
            return "";
        }
    }
}
