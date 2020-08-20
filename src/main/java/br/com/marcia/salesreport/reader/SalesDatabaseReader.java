package br.com.marcia.salesreport.reader;

import br.com.marcia.salesreport.repository.RegisterRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public class SalesDatabaseReader extends InvokeDelegateItemReader<Long> {

    private boolean read = false;

    public SalesDatabaseReader(RegisterRepository registerRepository,
                               String methodName,
                               Object[] arguments) {

        super(registerRepository, methodName, arguments);

    }


}
