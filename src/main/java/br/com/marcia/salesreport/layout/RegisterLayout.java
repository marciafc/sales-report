package br.com.marcia.salesreport.layout;

import br.com.marcia.salesreport.eumeration.RegisterTypeEnum;
import lombok.Data;

@Data
public abstract class RegisterLayout {

    private RegisterTypeEnum registerType;

}
