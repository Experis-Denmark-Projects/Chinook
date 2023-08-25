package experis.chinookspring.repository;

import java.util.List;

/** This generic interface serves the purpose of being the super repository interface which follows the repository pattern.
 * Therefore, the interface defines the four methods findAll, findById, insert and delete that all use the generic types
 * that the interface specifies.
 * These generic types refer to object, identifier and value respectively and must be non-primitive data types.
 * **/

public interface CRUDRepository<T,U,S> {

    List<T> findAll();
    T findById(U id);
    int insert(T object);

    /** Update entry
     * @param id this is the identifier of the entry.
     * @param parameter this is the name of the column to
     * @param value this is the new value to replace the older value.
     * */
    void update(U id, S parameter, S value);


}

