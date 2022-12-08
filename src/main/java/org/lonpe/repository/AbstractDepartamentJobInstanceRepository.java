
        
package org.lonpe.repository;            

            


import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;   
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;
    
@Repository
public abstract class AbstractDepartamentJobInstanceRepository implements CrudRepository<DepartamentJobInstance, Long> {
    
    private final EntityManager entityManager;
    
    AbstractDepartamentJobInstanceRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public abstract Optional<DepartamentJobInstance> findById(Long id);

    abstract Optional<DepartamentJobInstance> findByPkey(String pkey);

   } 
    
        