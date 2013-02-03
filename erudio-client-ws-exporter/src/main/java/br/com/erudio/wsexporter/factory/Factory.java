package br.com.erudio.wsexporter.factory;

import br.com.erudio.utils.service.interfaces.ICidadeUtilServices;
import br.com.erudio.utils.service.interfaces.IPessoaUtilServices;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class Factory {

    private static ICidadeUtilServices serviceCidade;
    private static IPessoaUtilServices servicePessoa;

    @Inject
    public Factory(ICidadeUtilServices serviceCidade,
                   IPessoaUtilServices servicePessoa) {
        Factory.serviceCidade = serviceCidade;
        Factory.servicePessoa = servicePessoa;
    }

    public static ICidadeUtilServices getServiceCidade() {
        return serviceCidade;
    }

    public static IPessoaUtilServices getServicePessoa() {
        return servicePessoa;
    }
        
}