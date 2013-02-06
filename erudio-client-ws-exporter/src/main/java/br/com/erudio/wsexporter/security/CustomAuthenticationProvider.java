package br.com.erudio.wsexporter.security;

import br.com.erudio.utils.database.beans.BeanPessoa;
import br.com.erudio.utils.service.interfaces.IPessoaUtilServices;
import br.com.erudio.wsexporter.factory.Factory;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
 
public class CustomAuthenticationProvider implements AuthenticationProvider {
 
    
    BeanPessoa pessoa = new BeanPessoa();
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        IPessoaUtilServices servicePessoa = Factory.getServicePessoa();
        
        List usuarios = servicePessoa.findPessoaByLogin(authentication.getName());
        List<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();                
            try {
                if (usuarios != null && usuarios.isEmpty()) {
                    throw new UsernameNotFoundException("Usuario nao encontrado!");
                }
                pessoa = (BeanPessoa) usuarios.get(0);
            } catch (Exception e) {
                return null;
            }
            auth.add(new SimpleGrantedAuthority(pessoa.getPermissao()));
        if (authentication.getName().equals(pessoa.getLogin()) && authentication.getCredentials().equals(pessoa.getSenha())) {
            return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(), auth);
        } else {
            return null;
        }
 
    }
 
    @Override
    public boolean supports(Class<? extends Object> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}