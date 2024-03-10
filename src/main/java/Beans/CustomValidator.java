package Beans;

import Dao.IdolDao;
import Dao.IdolDaoImpl;
import Model.Idol;
import Services.IdolService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
import java.util.ResourceBundle;

@FacesValidator("customValidator")
public class CustomValidator implements Validator {
    private final IdolDao idolDao = new IdolDaoImpl();
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {
        String fieldValue = value.toString();
        String componentId = uiComponent.getId();
        ResourceBundle bundle = ResourceBundle.getBundle("message", facesContext.getViewRoot().getLocale());


        if (componentId.equals("email")) {
            // Email validation
            if (!fieldValue.matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$")) {
                String emailErrorMessage = bundle.getString("validator_email3");
                FacesMessage message = new FacesMessage(emailErrorMessage);
                throw new ValidatorException(message);
            }
            Idol existingIdol = new Idol();
            existingIdol.setEmail(fieldValue);
            String result = idolDao.testEmail(existingIdol);
            if (result != null) {
                String emailErrorMessage = bundle.getString("validator_email2");
                FacesMessage message = new FacesMessage(emailErrorMessage);

                throw new ValidatorException(message);
            }


        } else if (componentId.equals("passwd")) {
            // Password validation
            if (!fieldValue.matches("^(?=.*[A-Z])(?=.*\\d).+$")) {
                String PassErrorMessage = bundle.getString("validator_password2");
                FacesMessage message = new FacesMessage(PassErrorMessage);
                throw new ValidatorException(message);
            }
        }
    }
}
