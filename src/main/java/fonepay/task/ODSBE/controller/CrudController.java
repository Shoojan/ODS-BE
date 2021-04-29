package fonepay.task.ODSBE.controller;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CrudController<T> {

    ResponseEntity<List<T>> getAllData();

    ResponseEntity<T> getDataById(long id);

    ResponseEntity<T> addData(T t);

    ResponseEntity<T> updateData(T t);

    ResponseEntity<T> deleteDataById(long id);

}
