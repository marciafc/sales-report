package br.com.marcia.salesreport.repository.projection;

import br.com.marcia.salesreport.eumeration.RegisterTypeEnum;

public interface RegisterTypeCountProjection {

    RegisterTypeEnum getRegisterType();

    Long getCount();
}
