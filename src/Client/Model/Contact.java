package Client.Model;

import Client.Controller.AbstractTableController;
import Client.RemoteManager;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;

public class Contact extends AbstractRowModel {
    private TextField name = new TextField();
    private TextField lastName = new TextField();
    private TextField telephone = new TextField();
    private TextField fiscalCode = new TextField();

    public Contact(AbstractTableController tableController) throws Exception {
        this(tableController, new JSONObject());
    }

    public Contact(AbstractTableController tableController, JSONObject data) throws Exception {
        super(RemoteManager.getInstance().getCustomerContactService(), tableController, data);

        refreshModel();
        events();
    }

    public void events() {
        name.textProperty().addListener((obs, oldText, newText) -> {
            needToSave();
            data.put("name", newText);
        });
        lastName.textProperty().addListener((obs, oldText, newText) -> {
            needToSave();
            data.put("lastName", newText);
        });
        telephone.textProperty().addListener((obs, oldText, newText) -> {
            needToSave();
            data.put("telephone", newText);
        });
        fiscalCode.textProperty().addListener((obs, oldText, newText) -> {
            needToSave();
            data.put("fiscalCode", newText);
        });
    }

    @Override
    public void refreshModel() {
        setName((String) data.get("name"));
        setLastName((String) data.get("lastName"));
        setTelephone((String) data.get("telephone"));
        setFiscalCode((String) data.get("fiscalCode"));
    }

    public int getId() {
        return Integer.parseInt((String) data.get("id"));
    }

    public TextField getName() {
        return name;
    }

    public void setName(TextField name) {
        this.name = name;
    }

    public void setName(String name) {
        if (name != null) this.name.setText(name);
    }

    public String getStringName() {
        return name.getText();
    }
    
    public TextField getLastName() {
        return lastName;
    }

    public void setLastName(TextField lastName) {
        this.lastName = lastName;
    }

    public void setLastName(String lastName) {
        if (lastName != null) this.lastName.setText(lastName);
    }

    public String getStringLastName() {
        return lastName.getText();
    }
    
    public TextField getTelephone() {
        return telephone;
    }

    public void setTelephone(TextField telephone) {
        this.telephone = telephone;
    }

    public void setTelephone(String telephone) {
        if (telephone != null) this.telephone.setText(telephone);
    }

    public String getStringTelephone() {
        return telephone.getText();
    }
    
    public TextField getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(TextField fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    public void setFiscalCode(String fiscalCode) {
        if (fiscalCode != null) this.fiscalCode.setText(fiscalCode);
    }

    public String getStringFiscalCode() {
        return fiscalCode.getText();
    }
}
