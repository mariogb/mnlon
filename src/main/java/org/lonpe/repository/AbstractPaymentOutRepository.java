
        
package org.lonpe.repository;            

            


import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;   
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;
    
@Repository
public abstract class AbstractPaymentOutRepository implements CrudRepository<PaymentOut, Long> {
    
    private final EntityManager entityManager;
    
    AbstractPaymentOutRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public abstract Optional<PaymentOut> findById(Long id);

    abstract Optional<PaymentOut> findByPkey(String pkey);

   } 
    
        