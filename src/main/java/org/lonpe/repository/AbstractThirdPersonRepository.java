
        
package org.lonpe.repository;            

            


import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;   
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;
    
@Repository
public abstract class AbstractThirdPersonRepository implements CrudRepository<ThirdPerson, Long> {
    
    private final EntityManager entityManager;
    
    AbstractThirdPersonRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public abstract Optional<ThirdPerson> findById(Long id);

    abstract Optional<ThirdPerson> findByPkey(String pkey);

   } 
    
        