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
import java.util.Map;

@ManagedBean
@SessionScoped
@FacesValidator("priceValidator")
@Getter
@Setter
@NoArgsConstructor
public class PriceValidation implements Validator {

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {

        Map<String, String> parameterMap = facesContext.getExternalContext().getRequestParameterMap();
        String startTime = parameterMap.get("orderForm:startTime_input");

        if (!startTime.isEmpty()) {
            FacesMessage msg = new FacesMessage(
                    "Число не может быть уже прошедшим");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
