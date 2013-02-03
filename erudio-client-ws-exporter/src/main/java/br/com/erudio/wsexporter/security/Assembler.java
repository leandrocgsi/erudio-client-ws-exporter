package br.com.erudio.wsexporter.security;

import br.com.erudio.utils.database.beans.BeanPessoa;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("assembler")
public class Assembler {

    public Assembler() {
    }

    @Transactional(readOnly = true)
    User buildUserFromUserEntity(BeanPessoa userBean) {

        String username = userBean.getLogin();
        String password = userBean.getSenha();
        boolean enabled = true; //userBean.isActive()
        boolean accountNonExpired = true;//userBean.isActive()
        boolean credentialsNonExpired = true; //userBean.isActive()
        boolean accountNonLocked = true; //userBean.isActive()

        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(userBean.getPermissao()));      

        User user = new User(
                username,
                password,
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                authorities);
        return user;
    }
}