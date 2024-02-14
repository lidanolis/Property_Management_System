package model;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.systemAdmin;
import model.systemProperty;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-08-15T14:36:30")
@StaticMetamodel(systemOwner.class)
public class systemOwner_ { 

    public static volatile SingularAttribute<systemOwner, String> gmail;
    public static volatile SingularAttribute<systemOwner, systemAdmin> systemAdmin;
    public static volatile SingularAttribute<systemOwner, String> password;
    public static volatile SingularAttribute<systemOwner, String> address;
    public static volatile SingularAttribute<systemOwner, Character> gender;
    public static volatile SingularAttribute<systemOwner, Float> balance;
    public static volatile SingularAttribute<systemOwner, String> contact;
    public static volatile SingularAttribute<systemOwner, LocalDate> DOC;
    public static volatile SingularAttribute<systemOwner, String> id;
    public static volatile SingularAttribute<systemOwner, Integer> age;
    public static volatile ListAttribute<systemOwner, systemProperty> properties;
    public static volatile SingularAttribute<systemOwner, String> status;

}