package be.digitalcity.projetfinal.mappers;

import be.digitalcity.projetfinal.models.form.typeForm.DateForm;
import be.digitalcity.projetfinal.models.form.typeForm.StatusForm;
import be.digitalcity.projetfinal.util.enums.StatusEnum;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UtilMapper {
    public LocalDate fromDateFormToDate(DateForm date){
        if (date == null) return null;

        return date.getDate();
    };

    public StatusEnum fromStatusFormToStatus(StatusForm status){
        if (status == null) return null;

        return status.getStatus();
    };
}
