package javaproject;
// Generated Feb 20, 2023, 7:40:20 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Usuarios generated by hbm2java
 */
public class Usuarios  implements java.io.Serializable {


     private long id;
     private String password;
     private Date lastLogin;
     private boolean isSuperuser;
     private String username;
     private String firstName;
     private String lastName;
     private String email;
     private boolean isStaff;
     private boolean isActive;
     private Date dateJoined;
     private Set estudianteses = new HashSet(0);

    public Usuarios() {
    }

	
    public Usuarios(long id, String password, boolean isSuperuser, String username, String firstName, String lastName, String email, boolean isStaff, boolean isActive, Date dateJoined) {
        this.id = id;
        this.password = password;
        this.isSuperuser = isSuperuser;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.isStaff = isStaff;
        this.isActive = isActive;
        this.dateJoined = dateJoined;
    }
    public Usuarios(long id, String password, Date lastLogin, boolean isSuperuser, String username, String firstName, String lastName, String email, boolean isStaff, boolean isActive, Date dateJoined, Set estudianteses) {
       this.id = id;
       this.password = password;
       this.lastLogin = lastLogin;
       this.isSuperuser = isSuperuser;
       this.username = username;
       this.firstName = firstName;
       this.lastName = lastName;
       this.email = email;
       this.isStaff = isStaff;
       this.isActive = isActive;
       this.dateJoined = dateJoined;
       this.estudianteses = estudianteses;
    }
   
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public Date getLastLogin() {
        return this.lastLogin;
    }
    
    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
    public boolean isIsSuperuser() {
        return this.isSuperuser;
    }
    
    public void setIsSuperuser(boolean isSuperuser) {
        this.isSuperuser = isSuperuser;
    }
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public boolean isIsStaff() {
        return this.isStaff;
    }
    
    public void setIsStaff(boolean isStaff) {
        this.isStaff = isStaff;
    }
    public boolean isIsActive() {
        return this.isActive;
    }
    
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    public Date getDateJoined() {
        return this.dateJoined;
    }
    
    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }
    public Set getEstudianteses() {
        return this.estudianteses;
    }
    
    public void setEstudianteses(Set estudianteses) {
        this.estudianteses = estudianteses;
    }




}


