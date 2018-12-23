package Server.Entity;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@FilterDefs({
        @FilterDef(name = "id", parameters = @ParamDef(name = "id", type = "integer")),
        @FilterDef(name = "name", parameters = @ParamDef(name = "name", type = "string")),
        @FilterDef(name = "lastName", parameters = @ParamDef(name = "lastName", type = "string")),
        @FilterDef(name = "fiscalCode", parameters = {@ParamDef(name = "fiscalCode", type = "string")}),
        @FilterDef(name = "telephone", parameters = @ParamDef(name = "telephone", type = "string"))
})
@Filters({
        @Filter(name = "id", condition = "id = :id"),
        @Filter(name = "name", condition = "name like '%' || :name || '%'"),
        @Filter(name = "lastName", condition = "lastName like '%' || :lastName || '%'"),
        @Filter(name = "fiscalCode", condition = "fiscalCode like '%' || :fiscalCode || '%'"),
        @Filter(name = "telephone", condition = "telephone like '%' || :telephone || '%'")
})
public abstract class Person extends AbstractEntity {

    @Id
    @GeneratedValue
    @PrimaryKeyJoinColumn
    private Integer id;

    @Column(nullable = false, length = 16)
    private String name;

    @Column(nullable = false, length = 16)
    private String lastName;

    @Column(unique = true, length = 16)
    private String fiscalCode;

    @Column(unique = true, length = 10)
    private String telephone;

    public Person() {
        this("", "", "", "");
    }

    public Person(String name, String lastName, String fiscalCode, String telephone) {
        this.name = name;
        this.lastName = lastName;
        this.telephone = telephone;
        this.fiscalCode = fiscalCode;
    }

    public Person(String name, String lastName, String fiscalCode) {
        this(name, lastName, fiscalCode, "");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    protected void beforeSave() {
        super.beforeSave();

        String correctName = this.getName();
        if(!validateString(correctName, "^[\\p{L} .'-]+$")) throw new IllegalArgumentException("Violated constraints on name field (No symbols allowed, no numbers allowed, max length = 32)");
        this.setName(nameCorrector(correctName));

        String correctSurname = this.getLastName();
        if(!validateString(correctSurname, "^[\\p{L} .'-]+$")) throw new IllegalArgumentException("Violated constraints on surname field (No symbols allowed, no numbers allowed, max length = 32)");
        this.setLastName(nameCorrector(correctSurname));

        String correctFiscalCode = this.getFiscalCode();
        if(!validateString(correctFiscalCode, "^[a-zA-Z0-9]*$")) throw new IllegalArgumentException("Violated constraints on fiscal code field (Only numbers and letters are allowed, no spaces)");
        if(this.getFiscalCode().length() != 16) throw new IllegalArgumentException("Violated constraints on fiscal code field (This field has to be filled with a sequence of 16 letters or numbers in any combination)");
        this.setFiscalCode(correctFiscalCode.toUpperCase());
    }
}
