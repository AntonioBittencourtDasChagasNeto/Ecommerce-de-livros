
package ecl.controle.web.vh;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ecl.core.aplicacao.*;
import ecl.dominio.EntidadeDominio;


public interface IViewHelper {

	public EntidadeDominio getEntidade(HttpServletRequest request);
	
	public void setView(Result resultado, 
			HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException;
	
}
