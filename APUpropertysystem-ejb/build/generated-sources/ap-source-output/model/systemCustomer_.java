package model;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.systemTransaction;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-08-15T14:36:30")
@StaticMetamodel(systemCustomer.class)
public class systemCustomer_ { 

    public static volatile SingularAttribute<systemCustomer, String> gmail;
    public static volatile SingularAttribute<systemCustomer, String> password;
    public static volatile SingularAttribute<systemCustomer, String> address;
    public static volatile SingularAttribute<systemCustomer, Character> gender;
    public static volatile SingularAttribute<systemCustomer, Float> balance;
    public static volatile SingularAttribute<systemCustomer, String> contact;
    public static volatile SingularAttribute<systemCustomer, LocalDate> DOC;
    public static volatile SingularAttribute<systemCustomer, String> id;
    public static volatile ListAttribute<systemCustomer, systemTransaction> pTransaction;
    public static volatile SingularAttribute<systemCustomer, Integer> age;

}