
        
package org.lonpe.repository;            

            


import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;   
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;
    
@Repository
public abstract class AbstractDepartamentJobRepository implements CrudRepository<DepartamentJob, Long> {
    
    private final EntityManager entityManager;
    
    AbstractDepartamentJobRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public abstract Optional<DepartamentJob> findById(Long id);

    abstract Optional<DepartamentJob> findByPkey(String pkey);

   } 
    
        