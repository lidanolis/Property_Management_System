package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class systemCustomer implements Serializable {

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
    private String gmail;

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }
    
    public LocalDate getDOC() {
        return DOC;
    }

    public void setDOC(LocalDate DOC) {
        this.DOC = DOC;
    }
    
    @OneToMany
    private ArrayList<systemTransaction> pTransaction = new ArrayList<systemTransaction>();

    public ArrayList<systemTransaction> getpTransaction() {
        return pTransaction;
    }

    public void setpTransaction(ArrayList<systemTransaction> pTransaction) {
        this.pTransaction = pTransaction;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    

    public systemCustomer() {
    }

    public systemCustomer(String id, char gender, String contact, String address, int age, float balance, String password, LocalDate DOC, String gmail) {
        this.id = id;
        this.gender = gender;
        this.contact = contact;
        this.address = address;
        this.age = age;
        this.balance = balance;
        this.password = password;
        this.DOC = DOC;
        this.gmail = gmail;
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
        if (!(object instanceof systemCustomer)) {
            return false;
        }
        systemCustomer other = (systemCustomer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.systemCustomer[ id=" + id + " ]";
    }

}
