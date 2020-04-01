/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import dao.UsuarioDAO;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import model.Usuario;

/**
 *
 * @author compumax
 */
public class LoginController {

    private final static Logger LOGGER=Logger.getLogger("UsuarioController");
    private Usuario usuario;
    private Usuario usuarioAutenticado = null;
    List<Usuario> listado;
    
    @EJB
    private UsuarioDAO ejbDao;
    
    
    public LoginController() {
        
        usuario=new Usuario();
    }
    
    public void login() throws IOException{
        
        FacesContext ctx= FacesContext.getCurrentInstance();
        usuarioAutenticado = ejbDao.encontrarUsuarioPorLogin(usuario.getCorreo(),usuario.getClave());
        
        if(usuarioAutenticado !=null){
            LOGGER.log(Level.INFO,"BIENVENIDO");
            ctx.getExternalContext().redirect("home.xhtml");
        }else{
            LOGGER.log(Level.INFO,"NO ENCONTRADO");
            ctx.getExternalContext().redirect("index.xhtml");
        }
                
    }
    
    public List<Usuario> getListado(){
        
        listado=ejbDao.listar();
        return listado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
}
