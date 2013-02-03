package br.com.erudio.wsexporter.security;

import br.com.erudio.utils.database.beans.BeanPessoa;
import br.com.erudio.utils.service.interfaces.IPessoaUtilServices;
import br.com.erudio.wsexporter.factory.Factory;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("customUserDetails")
public class CustomUserDetails implements UserDetailsService, Serializable {

    private static final long serialVersionUID = 1L;
    
    @Autowired
    private Assembler assembler;
    
    private IPessoaUtilServices servicePessoa = Factory.getServicePessoa();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username != null && username.isEmpty()) {
            throw new UsernameNotFoundException("Usuario nao encontrado!");
        } else {
            try {
                List usuarios = servicePessoa.findPessoaByLogin(username);
                if (usuarios != null && usuarios.isEmpty()) {
                    throw new UsernameNotFoundException("Usuario nao encontrado!");
                }
                return assembler.buildUserFromUserEntity((BeanPessoa) usuarios.get(0));
            } catch (Exception e) {
                return null;
            }
        }

    }
}