<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Cadastrar Cliente</title>

<style>
.col-md-12 {
	display: flex;
}

.col-md-4 {
	padding: 50px;
	margin-right: 150px;
	padding-top: 10px;
}

.col-md-4-3 {
	padding: 50px;
	padding-top: 10px;
}

.DDD {
	width: 40px;
}

.Telefone {
	width: 100px;
}

fieldset {
	background-color: #f7f7f7;
}

p {
	font-weight: bolder;
}

label {
	font-size: 10px;
}

button {
	float: right;
	margin: 10px;
}
</style>

<script type="text/javascript">
        	// mascara para a data de nascimento
        	function formatar(src, mask,e) 
			{
				var tecla =""
				if (document.all) // Internet Explorer
					tecla = event.keyCode;
				else
					tecla = e.which;
				//code = evente.keyCode;
				if(tecla != 8){


				if (src.value.length == src.maxlength){
				return;
				}
			  var i = src.value.length;
			  var saida = mask.substring(0,1);
			  var texto = mask.substring(i);
			  if (texto.substring(0,1) != saida) 
			  {
				src.value += texto.substring(0,1);
			  }
			}

        </script>

</head>

<body>
	<jsp:include page="MenuCli.jsp" />

	<h3>Cadastrar Cliente</h3>

	<form action="/Ecommerc/CadastrarClienteResposta" method="post">
		<fieldset name="fieldset">
			<div class="col-md-12">
				<div class="col-md-4">
					<p>Nome</p>
					<input type="text" name="NomeCliente" id="NomeCliente" />

					<p>Data de Nascimento</p>
					<input type="number" name="DataNascimento" size="10" maxlength="10"
						placeholder="dd/mm/yyyy"
						onkeyup="formatar(this,'##/##/####',event)">

					<p>CPF</p>
					<input type="number" name="CPF" id="CPF" maxlength="11"
						placeholder="Digite apenas números" />

					<!-- Falta puxar o Editora em LIVRODAO -->
					<p>Telefone</p>
					<table>
						<tr>
							<th><input type="number" name="DDD" id="DDD" class="DDD"
								maxlength="2" placeholder="11" size="2" /></th>
							<th><input type="number" name="Telefone" id="Telefone"
								class="Telefone" maxlength="9" placeholder="998793812" size="10" />
							</th>
						</tr>
					</table>


					<p>Email</p>
					<input type="text" name="Email" id="Email" />

					<p>Senha</p>
					<input type="password" name="Senha" id="Senha" minlength="8" />

					<p>Senha novamente</p>
					<input type="password" name="SenhaNovamente" id="SenhaNovamente"
						minlength="8" />
				</div>

				<div class="col-md-4">
					<p>Endereço</p>

					<select name="Endereco" id="Endereco">
						<option value="Casa">Casa</option>
						<option value="Apartamento">Apartamento</option>
						<option value="Trabalho">Trabalho</option>
					</select>

					<p>Logradouro</p>

					<input type="text" name="Logradouro" id="Logradouro">


					<p>CEP</p>

					<input name="cep" type="number" id="cep" size="10" maxlength="9" /></input><br />

					<p>Rua</p>
					<input name="Rua" type="text" id="Rua" size="40" />
					<p>Bairro</p>
					<input name="Bairro" type="text" id="Bairro" size="40" />

					<p>Estado</p>
					<select name="Estado" id="Estado">
						<option value="EspiritoSanto">ES</option>
						<option value="Minas Gerais">MG</option>
						<option value="RioDeJaneiro">RJ</option>
						<option value="SaoPaulo">SP</option>
					</select>


					<p>Cidade</p>
					<select name="Cidade" id="Cidade">
						<option value="BeloHorizonte">Belo Horizonte</option>
						<option value="RioDeJaneiro">Rio de Janeiro</option>
						<option value="SaoPaulo">São Paulo</option>
						<option value="Vitoria">Vitória</option>
					</select>
					<!-- </form> -->
				</div>

				<div class="col-md-4-3">
					<p>Cartão de Crédito</p>
					<br>

					<p>Número</p>
					<input type="number" name="NumeroCartao" id="NumeroCartao">

					<p>Nome no Cartão</p>
					<input type="text" name="NomeCartao" id="NomeCartao">

					<p>Bandeira</p>
					<select name="Bandeira" id="Bandeira">
						<option value="MasterCard">Master Card</option>
						<option value="Visa">Visa</option>
						<option value="Elo">Elo</option>
						<option value="Alelo">Alelo</option>
					</select>

					<p>Código de Segurança</p>
					<input type="password" name="CodigoCartao" id="CodigoCartao">
					<label> *O cartão será utilizado inicialmente como seu
						preferencial, mas poderá ser alterado no perfil.</label> <br>
					<button type="submit" name="operacao" id="botao" value="salvar">Confirmar</button>
				</div>
			</div>

		</fieldset>
	</form>
</body>
</html>