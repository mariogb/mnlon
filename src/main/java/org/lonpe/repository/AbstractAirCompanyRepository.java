
        
package org.lonpe.repository;            

            


import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;   
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;
    
@Repository
public abstract class AbstractAirCompanyRepository implements CrudRepository<AirCompany, Long> {
    
    private final EntityManager entityManager;
    
    AbstractAirCompanyRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public abstract Optional<AirCompany> findById(Long id);

    abstract Optional<AirCompany> findByPkey(String pkey);

   } 
    
        