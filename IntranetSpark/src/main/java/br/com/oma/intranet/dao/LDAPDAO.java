package br.com.oma.intranet.dao;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import br.com.oma.intranet.entidades.intra_usuario;
import br.com.oma.intranet.util.ADException;

public class LDAPDAO {
	
	public intra_usuario validaUsuarioAD(String usuario, String dominio, String url) throws ADException {
		LdapContext ctx = getLdapContext(usuario, dominio, url);
		intra_usuario user = null;
		if(ctx != null){
			user = getAtributoUsuarioAD(usuario, ctx);
		}
		return user;
	}

	public LdapContext getLdapContext(String usuario,  String dominio, String url) throws ADException {
		LdapContext ctx = null;
		try {
			Hashtable<String, String> env = new Hashtable<String, String>();
			env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.SECURITY_AUTHENTICATION, "Simple");
			env.put(Context.SECURITY_PRINCIPAL, "administrador@oma.local");
			env.put(Context.SECURITY_CREDENTIALS, "@M9503*");
			env.put(Context.PROVIDER_URL, url);
			ctx = new InitialLdapContext(env, null);
		} catch (javax.naming.AuthenticationException e) {
			ctx = null;
			e.printStackTrace();
		} catch (NamingException nex) {
			//Logger.getLogger(this.getClass().getName()).warning("Erreur de contexte : Dans le nom");
			ctx = null;
			nex.printStackTrace();
		}
		return ctx;
	}
	
	private intra_usuario getAtributoUsuarioAD(String username, LdapContext ctx)
			 {
		intra_usuario us = null;
		try {
			SearchControls constraints = new SearchControls();
			constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
			String[] attrIDs = { "distinguishedName", "sn", "givenname", "mail", "telephonenumber", "memberOf", "manager", "managedBy" };
			constraints.setReturningAttributes(attrIDs);
			NamingEnumeration  answer = ctx.search("ou=Users OMA,dc=OMA,dc=LOCAL", "sAMAccountName="+ username, constraints);
			
			if (answer.hasMore()) {
				us = new intra_usuario();
				Attributes attrs = ((SearchResult) answer.next())
						.getAttributes();
				String temp = "";
				if (attrs.get("givenname") != null) {
					temp = attrs.get("givenname").toString();
					temp = temp.substring(temp.indexOf(":") + 2);
					us.setNome(temp);
				}
				if (attrs.get("sn") != null) {
					temp = attrs.get("sn").toString();
					temp = temp.substring(temp.indexOf(":") + 2);
					us.setNome(us.getNome() + " " + temp);
				}
				if (attrs.get("mail") != null) {
					temp = attrs.get("mail").toString();
					temp = temp.substring(temp.indexOf(":") + 2);
					us.setEmail(temp);
				}
				
				/*if (attrs.get("memberOf") != null) {
					temp = attrs.get("memberOf").toString();
					temp = temp.substring(temp.indexOf(":") + 2);
					// System.out.println("Member Of: " + temp);
					List<String> lstAtributos = this.trataAtributosAD(temp);
					
					usuario.setGrupos(lstAtributos);
				}*/
				/*if (attrs.get("manager") != null) {
					temp = attrs.get("manager").toString();
					temp = temp.substring(temp.indexOf(":") + 2);
					List<String> lstAtributos = this.trataAtributosAD(temp);
					usuario.setManager(lstAtributos.get(0));
					// System.out.println("Manager: " + temp);
				}*/
			/*	if (attrs.get("managedBy") != null) {
					temp = attrs.get("managedBy").toString();
					temp = temp.substring(temp.indexOf(":") + 2);
					List<String> lstAtributos = this
							.trataAtributosAD(temp);
					usuario.setManagedBy(lstAtributos.get(0));
					System.out.println("Managed By: " + temp);
				}*/
			} else {
				us = null;
			}
		} catch (Exception ex) {
			us = null;
			try {
				throw new ADException("Usuário ou senha inválidos.");
			} catch (ADException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return us;
	}

	
}
