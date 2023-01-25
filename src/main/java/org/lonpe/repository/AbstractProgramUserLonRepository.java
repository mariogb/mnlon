
        
package org.lonpe.repository;            

            


import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;   
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;
    
@Repository
public abstract class AbstractProgramUserLonRepository implements CrudRepository<ProgramUserLon, Long> {
    
    private final EntityManager entityManager;
    
    AbstractProgramUserLonRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public abstract Optional<ProgramUserLon> findById(Long id);

    abstract Optional<ProgramUserLon> findByPkey(String pkey);

   } 
    
        