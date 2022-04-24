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
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.Map;

@ManagedBean
@SessionScoped
@FacesValidator("timeValidator")
@Getter
@Setter
@NoArgsConstructor
public class TimeValidator implements Validator {

    private LocalTime deliveryStartTime;

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {

        LocalTime localTime = (LocalTime) o;

        Map<String, String> parameterMap = facesContext.getExternalContext().getRequestParameterMap();
        String startTime = parameterMap.get("orderForm:startTime_input");
        deliveryStartTime = LocalTime.parse(startTime);

        if (deliveryStartTime.isAfter(localTime)) {
            FacesMessage msg = new FacesMessage(
                    "Конечное время не может быть меньше начального");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

        int endMinutesDay = localTime.get(ChronoField.MINUTE_OF_DAY);
        int startMinutesDay = deliveryStartTime.get(ChronoField.MINUTE_OF_DAY);

        if (endMinutesDay - startMinutesDay > 60) {
            FacesMessage msg = new FacesMessage(
                    "Конечное время не может быть больше начального на 60 минут");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
