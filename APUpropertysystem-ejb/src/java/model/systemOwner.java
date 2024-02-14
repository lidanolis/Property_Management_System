package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class systemOwner implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private char gender;
    private String contact;
    private String address;
    private int age;
    private float balance;
    private String password;
    private LocalDate DOC;
    private String status;

    private String gmail;

    @OneToOne
    private systemAdmin systemAdmin;

    public systemAdmin getSystemAdmin() {
        return systemAdmin;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }


    public void setSystemAdmin(systemAdmin systemAdmin) {
        this.systemAdmin = systemAdmin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDOC() {
        return DOC;
    }

    public void setDOC(LocalDate DOC) {
        this.DOC = DOC;
    }

    @OneToMany
    private ArrayList<systemProperty> properties = new ArrayList<systemProperty>();

    public systemOwner() {
    }

    public systemOwner(String id, char gender, String contact, String address, int age, float balance, String password, LocalDate DOC, String status, String gmail) {
        this.id = id;
        this.gender = gender;
        this.contact = contact;
        this.address = address;
        this.age = age;
        this.balance = balance;
        this.password = password;
        this.DOC = DOC;
        this.status = status;
        this.gmail = gmail;
    }


    public ArrayList<systemProperty> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<systemProperty> properties) {
        this.properties = properties;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof systemOwner)) {
            return false;
        }
        systemOwner other = (systemOwner) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.systemOwner[ id=" + id + " ]";
    }

}
