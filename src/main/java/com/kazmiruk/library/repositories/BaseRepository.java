package com.kazmiruk.library.repositories;

import com.kazmiruk.library.model.exception.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import static com.kazmiruk.library.type.ErrorMessageType.NOT_FOUND;

@NoRepositoryBean
public interface BaseRepository <T, ID extends Number> extends JpaRepository<T, ID> {

    default T getOneById(ID id) {
        return this.findById(id).orElseThrow(() ->
                new NotFoundException(NOT_FOUND, id)
        );
    }
}
