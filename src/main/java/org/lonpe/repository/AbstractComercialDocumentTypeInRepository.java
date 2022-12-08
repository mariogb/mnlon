
        
package org.lonpe.repository;            

            


import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;   
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;
    
@Repository
public abstract class AbstractComercialDocumentTypeInRepository implements CrudRepository<ComercialDocumentTypeIn, Long> {
    
    private final EntityManager entityManager;
    
    AbstractComercialDocumentTypeInRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public abstract Optional<ComercialDocumentTypeIn> findById(Long id);

    abstract Optional<ComercialDocumentTypeIn> findByPkey(String pkey);

   } 
    
        