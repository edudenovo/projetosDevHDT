$(document).ready(function() {
	$("#numero_paciente").keypress(function(e){			
		 if(e.which == 13) {
			 buscar();
		}
	});
	$('tr').click(function () { 
		var numero_paciente = $('td:eq(0)',this).html();
		var data = $('td:eq(1)',this).html();
		var hora = $('td:eq(2)',this).html();
		var profissional = $('td:eq(3)',this).html();
		var newWin=window.open('','Print-Window');
		newWin.document.open();
		newWin.document.write('<html> <body onload="window.print()"> <img alt="Portal Ebserh" height="60" src="http://www.ebserh.gov.br/image/layout_set_logo?img_id=14179&amp;t=1493120790262" width="177"> <center><p><h5>Unidade de Regulação Assistencial</h5></p></center> <center><p><b><h2>Protocolo de Agendamento</h2></b></p></center> <p><b>Número do Paciente:</b> '+numero_paciente+'</p> <p><b>Data da Consulta:</b> '+data+'</p> <p><b>Hora:</b> '+hora+'</p> <p><b>Profissional:</b> '+profissional+'</p> <center><p>Assinatura:__________________________________</p></center> <center><p><u><h1>ORIENTAÇÕES AO PACIENTE</h1></u></p></center> <p>- Favor chegar ao hospital <b>30 minutos</b> antes do horário marcado.</p> <p>- O comparecimento após o horário acima poderá causar o <b>cancelamento e reagendamento</b> da consulta;</p> <p>- Traga o <b>encaminhamento original;</b> <p>- É indispensável apresentação de <b>documento oficial com foto</b> para atendimento. Será permitida a entrada de apenas 01 (um) acompanhante (maior de 18 anos), também com identificação</p> <p>- <b>FAVOR COMUNICAR COM ANTECEDÊNCIA MÍNIMA DE 48 HORAS CASO NÃO SEJA POSSÍVEL COMPARECER À CONSULTA.</b></p> <p>- <b>A DATA E O HORÁRIO DA CONSULTA PODEM SOFRER ALTERAÇÕES, PORTANTO, É DE RESPONSABILIDADE DO PACIENTE MANTER SEUS DADOS (<u>TELEFONE E ENDEREÇO</u>) ATUALIZADOS, A FIM DE SEREM INFORMADOS DE EVENTUAIS MUDANÇAS. (63) 3413-8602 / 3413-8605.</b></p>   <center><p><b><h6>HOSPITAL DE DOENÇAS TROPICAIS-UFT</b><br/> Rua José de Brito Soares, nº 1015, Setor Anhanguera.<br/> Araguaína/TO - CEP: 77.818-530<br/> <b>E-MAIL:regulacao.hdt@ebserh.gov.br</h6></b></p></center>  </body> </html>');
		newWin.document.close();
		//setTimeout(function(){newWin.close();},10);
	});
});

function buscar(){
	if($('#numero_paciente').val() === ''){
		alert('Necessário preencher o número do paciente');
		return false;
	}
	formulario.submit();
}

function voltar(){
	form.action="protocolo_agendamento";
	form.submit();
}

