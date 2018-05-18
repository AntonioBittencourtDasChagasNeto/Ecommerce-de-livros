
package ecl.controle.web.command.impl;
import ecl.controle.web.command.ICommand;
import ecl.core.impl.controle.Fachada;
import ecl.core.IFachada;





public abstract class AbstractCommand implements ICommand {

	protected IFachada fachada = new Fachada();

}
