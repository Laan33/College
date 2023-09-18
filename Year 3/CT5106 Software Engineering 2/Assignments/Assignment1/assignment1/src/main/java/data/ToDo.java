/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

/**
 *
 * @author catha
 */
public class ToDo {
    
    private String taskSubject;
    private String taskDetails;
    
    public ToDo(String taskSubject, String taskDetails) { //task constructor 
        this.taskSubject = taskSubject;
        this.taskDetails = taskDetails;
    }
    
    public String getTaskSubject() {
        return taskSubject;
    }
    
    public String getTaskDetails() {
        return taskDetails;
    }
    
    public void setTaskSubject(String taskSubject) {
        this.taskSubject = taskSubject;
    }
    
    public void setTaskDetails(String taskDetails) {
        this.taskDetails = taskDetails;
    }
    
    
    
}
