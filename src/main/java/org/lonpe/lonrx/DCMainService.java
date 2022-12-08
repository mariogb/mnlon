
        
package org.lonpe.lonrx;            



import io.micronaut.context.ApplicationContext;
import io.reactivex.Single;
import jakarta.inject.Inject;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.lonpe.lonrx.impl.*;
import org.lonpe.dcmodel.DCModel;

public class DCMainService {

    final Map<String, AbstractLon> m;
    private static LinkedHashMap<String,DCModel> lsModel;

    public DCMainService() {
        this.m = new HashMap<>();
    }

    public Single<LinkedHashMap> listModels(final Long userLon_id) {

        if (lsModel == null) {
            init0();
        }

        LinkedHashMap respuesta = new LinkedHashMap();
        return Single.just(respuesta);
    }


    @Inject
    public ApplicationContext applicationContext;

    private void init0() {
        lsModel = new LinkedHashMap<>();
        
            setIn(applicationContext.getBean(UserLonServiceLon.class));
            
            setIn(applicationContext.getBean(RoleServiceLon.class));
            
            setIn(applicationContext.getBean(UserRoleServiceLon.class));
            
            setIn(applicationContext.getBean(UserThirdPersonServiceLon.class));
            
            setIn(applicationContext.getBean(ThirdPersonServiceLon.class));
            
            setIn(applicationContext.getBean(EntityPermisionRoleServiceLon.class));
            
            setIn(applicationContext.getBean(BaseServiceLon.class));
            
            setIn(applicationContext.getBean(TimePeriodServiceLon.class));
            
            setIn(applicationContext.getBean(BaseTimePeriodServiceLon.class));
            
            setIn(applicationContext.getBean(WorkSpaceGroupServiceLon.class));
            
            setIn(applicationContext.getBean(WorkSpaceServiceLon.class));
            
            setIn(applicationContext.getBean(DepartamentServiceLon.class));
            
            setIn(applicationContext.getBean(DepartamentJobServiceLon.class));
            
            setIn(applicationContext.getBean(DepartamentJobInstanceServiceLon.class));
            
            setIn(applicationContext.getBean(ProgramServiceLon.class));
            
            setIn(applicationContext.getBean(ProgramJobServiceLon.class));
            
            setIn(applicationContext.getBean(DepartamentUserLonServiceLon.class));
            
            setIn(applicationContext.getBean(ProgramUserLonServiceLon.class));
            
            setIn(applicationContext.getBean(BaseUserLonServiceLon.class));
            
            setIn(applicationContext.getBean(DepartamentBaseTimePeriodServiceLon.class));
            
            setIn(applicationContext.getBean(ProgramBaseTimePeriodServiceLon.class));
            
            setIn(applicationContext.getBean(ContractOutServiceLon.class));
            
            setIn(applicationContext.getBean(ContractInServiceLon.class));
            
            setIn(applicationContext.getBean(ComercialDocumentTypeOutServiceLon.class));
            
            setIn(applicationContext.getBean(ComercialDocumentTypeInServiceLon.class));
            
            setIn(applicationContext.getBean(MonetaryAccountServiceLon.class));
            
            setIn(applicationContext.getBean(PaymentOutTypeServiceLon.class));
            
            setIn(applicationContext.getBean(PaymentInTypeServiceLon.class));
            
            setIn(applicationContext.getBean(PaymentOutFormServiceLon.class));
            
            setIn(applicationContext.getBean(PaymentInFormServiceLon.class));
            
            setIn(applicationContext.getBean(PaymentOutServiceLon.class));
            
            setIn(applicationContext.getBean(PaymentInServiceLon.class));
            
            setIn(applicationContext.getBean(ComercialDocumentOutServiceLon.class));
            
            setIn(applicationContext.getBean(ComercialDocumentInServiceLon.class));
            
            setIn(applicationContext.getBean(ProductTypeServiceLon.class));
            
            setIn(applicationContext.getBean(StockRackServiceLon.class));
            
            setIn(applicationContext.getBean(StockRackProductServiceLon.class));
            
            setIn(applicationContext.getBean(ProductServiceLon.class));
            
            setIn(applicationContext.getBean(InvoiceLineInServiceLon.class));
            
            setIn(applicationContext.getBean(InvoiceLineOutServiceLon.class));
            
            setIn(applicationContext.getBean(AppointmentServiceLon.class));
            
            setIn(applicationContext.getBean(AccountServiceLon.class));
            
            setIn(applicationContext.getBean(AirportServiceLon.class));
            
            setIn(applicationContext.getBean(FligthServiceLon.class));
            
            setIn(applicationContext.getBean(FligthInstanceServiceLon.class));
            
            setIn(applicationContext.getBean(AirCompanyServiceLon.class));
            
            setIn(applicationContext.getBean(PlaneServiceLon.class));
            
            setIn(applicationContext.getBean(MeUsrInterfaceServiceLon.class));
            
            setIn(applicationContext.getBean(FormLonServiceLon.class));
            
            setIn(applicationContext.getBean(FormLonFieldServiceLon.class));
            
                
    }

    private void setIn(final IServiceLon s) {
        final DCModel dcModel = s.getdCModel();
        lsModel.put(dcModel.getDc(),dcModel);
    }
    

}



        