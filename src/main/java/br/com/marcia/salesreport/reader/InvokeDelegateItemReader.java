package br.com.marcia.salesreport.reader;


import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.batch.item.adapter.ItemReaderAdapter;

@NoArgsConstructor
public class InvokeDelegateItemReader<T> extends ItemReaderAdapter<T> {

    private boolean read = false;

    public InvokeDelegateItemReader(Object targetObject, String targetMethod, Object[] arguments) {
        setTargetObject(targetObject);
        setTargetMethod(targetMethod);
        setArguments(arguments);
    }

    @SneakyThrows
    @Override
    public T read() {
        if(read) {
            return null;
        }
        T item = invokeDelegateMethod();
        read = true;
        return item;
    }

}