
        
package org.lonpe.repository;            

            


import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;   
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;
    
@Repository
public abstract class AbstractBaseRepository implements CrudRepository<Base, Long> {
    
    private final EntityManager entityManager;
    
    AbstractBaseRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public abstract Optional<Base> findById(Long id);

    abstract Optional<Base> findByPkey(String pkey);

   } 
    
        