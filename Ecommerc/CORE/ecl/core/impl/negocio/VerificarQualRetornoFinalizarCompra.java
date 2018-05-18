package ecl.core.impl.negocio;
import ecl.core.*;
import ecl.dominio.EntidadeDominio;
import ecl.dominio.Pedidos;

public class VerificarQualRetornoFinalizarCompra implements IStrategy{

	String msg;
	@Override
	public String processar(EntidadeDominio entidade) {
		System.out.println("Eu cheguei na regra de negocio");
		msg = "";
		if(entidade instanceof Pedidos){
			Pedidos pedido = (Pedidos)entidade;
;
			if(pedido.getTotal() > pedido.getDesconto())
			{
				 if(pedido.getCartoes().size() == 0)
				 {
					 this.msg = "1";//Valor invalido e sem uso de cartao(Refazer escolhe de pagamento)
				 }else
				 {
					 this.msg = "2";//Valor invalido e com uso de cartao(Escolher o valor a ser pago em cada cartao)
				 }
				 
			}
			if(pedido.getTotal() <= pedido.getDesconto())
			{
				this.msg = "";//Valor valido independendo do uso de cartoes
			}
			if(msg == null || msg == "")
				return null;
			else return msg;
		}
	
		
		
		return null;
	}
}
