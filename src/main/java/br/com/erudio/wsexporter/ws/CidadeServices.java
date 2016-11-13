package br.com.erudio.wsexporter.ws;

import br.com.erudio.utils.database.beans.BeanCidade;
import br.com.erudio.utils.service.interfaces.ICidadeUtilServices;
import br.com.erudio.utils.service.ws.interfaces.ICidadeRestService;
import br.com.erudio.wsexporter.factory.Factory;
import java.util.List;
import javax.inject.Named;
import org.apache.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;


@Named
public class CidadeServices implements ICidadeRestService {

    private Logger logger = Logger.getLogger(this.getClass());
    
    private static ICidadeUtilServices serviceCidade = Factory.getServiceCidade();    

    public CidadeServices() {
        this.logger.info("Acessando o web-service de cidade.");
    }

    @Override
    public Integer saveCidade(BeanCidade cidade) {
        return serviceCidade.saveCidade(cidade);
    }

    @Override
    public void updateCidade(BeanCidade cidade) {
        serviceCidade.updateCidade(cidade);
    }

    @Override
    public void deleteCidade(BeanCidade cidade) {
        serviceCidade.deleteCidade(cidade);
    }
    
    @Override
    public List<BeanCidade> findAllCidades(){ 
        return serviceCidade.findAllCidades();
    }

    @Override
    //@PreAuthorize("hasRole('ROLE_USER')") 
    public BeanCidade findCidadeById(Integer id) {
        return serviceCidade.findCidadeById(id);
    }    
}