package com.softclub.model.validation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@ManagedBean
@SessionScoped
@FacesValidator("amountValidator")
@Getter
@Setter
@NoArgsConstructor
public class AmountValidator implements Validator {

    private int gramsAmount;

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        Integer model = (Integer) o;

        if (model < 100) {
            FacesMessage msg = new FacesMessage(
                    "Минимум 100гр.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
