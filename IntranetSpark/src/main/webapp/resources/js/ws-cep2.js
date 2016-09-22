$(document).ready(
		function() {
			function limpa_formulário_cep() {
				// Limpa valores do formulário de cep.
				$("[id='frmDtlhAltCandidato:accordion:rua']").val("");
				$("[id='frmDtlhAltCandidato:accordion:bairro']").val("");
				$("[id='frmDtlhAltCandidato:accordion:cidadeAux']").val("");
				$("[id='frmDtlhAltCandidato:accordion:zonaAux']").val("");
			}

			// Quando o campo cep perde o foco.
			$("[id='frmDtlhAltCandidato:accordion:cep']").blur(
					function() {
						// Nova variável "cep" somente com dígitos.
						var cep = $(this).val().replace(/\D/g, '');

						// Verifica se campo cep possui valor informado.
						if (cep != "") {

							// Expressão regular para validar o CEP.
							var validacep = /^[0-9]{8}$/;

							// Valida o formato do CEP.
							if (validacep.test(cep)) {

								// Preenche os campos com "..." enquanto
								// consulta webservice.
								$("[id='frmDtlhAltCandidato:accordion:rua']").val("...")
								$("[id='frmDtlhAltCandidato:accordion:bairro']").val("...")
								$("[id='frmDtlhAltCandidato:accordion:cidadeAux']").val("...")
								$("[id='frmDtlhAltCandidato:accordion:zonaAux']").val("...");

								// Consulta o webservice viacep.com.br/
								$.getJSON("//viacep.com.br/ws/" + cep
										+ "/json/?callback=?", function(dados) {

									if (!("erro" in dados)) {
										// Atualiza os campos com os
										// valores da consulta.
										$("[id='frmDtlhAltCandidato:accordion:rua']").val(dados.logradouro);
										$("[id='frmDtlhAltCandidato:accordion:bairro']").val(dados.bairro);
										$("[id='frmDtlhAltCandidato:accordion:cidadeAux']").val(dados.localidade);
									} // end if.
									else {
										// CEP pesquisado não foi
										// encontrado.
										limpa_formulário_cep();
										alert("CEP não encontrado.");
									}
								});
							} // end if.
							else {
								// cep é inválido.
								limpa_formulário_cep();
								alert("Formato de CEP inválido.");
							}
						} // end if.
						else {
							// cep sem valor, limpa formulário.
							limpa_formulário_cep();
						}
					});
		});
