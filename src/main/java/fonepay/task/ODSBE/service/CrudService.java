package fonepay.task.ODSBE.service;

import java.util.List;

public interface CrudService<T> {

    List<T> findAllData();

    T findDataById(long id);

    T addData(T customer);

    T updateData(T customer);

    void deleteData(long id);
}
