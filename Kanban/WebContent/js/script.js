$(document).ready(function() {
		$('input#observacao').characterCounter();
		$('select').material_select();		
		$('#telefone').mask("(00) 0000-00009");	
		$("#password").keypress(function(e){			
			 if(e.which == 13) {
				 login();
		    }
		});
		$('#leito').change(function(){			
			$.ajax({
	            url: "carregar_leito",
	            type: "POST",
	            data: "leito=" + $(this).val(),
	            success: function(responseText){	            	
	            	var obj = JSON.parse(responseText);
	            	$('#prontuario').focus();
	            	$('#prontuario').val(obj.prontuario);
	            	$('#nome').focus();
	            	$('#nome').val(obj.nome);	            	
	            }
	        });
		});
	});


	
	function sair(){
		formulario.action='login.jsp';
		formulario.submit();
	}
	
	function kanban(){
		formulario.action='kanban';
		formulario.submit();
	}
	
	function login(){
		if($('#usuario').val() === ''){
			alert('Necessário preencher o campo usuário');
			return false;
		}
		if($('#password').val() === ''){
			alert('Necessário preencher o campo senha');
			return false;
		}
		formulario.submit();
	}
	
	function gravar(){
		if($('#observacao').val().length > 30){
			alert('Por favor, a observação não pode ter mais de 30 caracteres');
			return false;
		}
		if($('#leito').val() === null){
			alert('Por favor, escolher o leito');
			return false;
		}
		if($('#nome').val() === ''){
			alert('Este leito está sem paciente');
			return false;
		}
		if ($('#observacao').val() === ''){
			alert('Por favor, preencher a observação');
			return false;
		}
		if($('#fugulin').val() === ''){
			alert('Por favor, preencher o campo fugulin');
			return false;
		}				
		formulario.submit();
	}
	
	function inserir(){		
		if($('#telefone').val() === '' || $('#nome').val() === ''){
			alert('Necessário preencher nome e telefone.');
		}else if($('#telefone').val().length < 14){
			alert('O telefone precisa ter no mínimo 10 números.');
		}else if($('#nome').val().length > 100){
			alert('O nome não pode ultrapassar 100 caracteres.');
		} else {
			$.ajax({
	            url: "cadastrar_telefone",
	            type: "POST",
	            data: "telefone=" + $("#telefone").val() + "&nome="+ $("#nome").val(),
	            success: function(responseText){
	            	if(responseText != 0){
	            		var i = responseText;
	            		tabela = $('#linha_agenda').html();
	            		var tabela = tabela +  '<tr id="linha'+i+'" onclick="preenche_form(this)"><td>'+i+'</td><td>'+$("#telefone").val()+'</td><td>'+$("#nome").val()+'</td><td><a href="#" onclick="remove_linha('+i+')"><i class="material-icons">remove</i></a></td></tr>';          		
	            		$('#linha_agenda').html(tabela);
	            		$("#telefone").val('');
	            		$("#nome").val('');
	            		$('#btnEditar').removeClass();			
	            		$('#btnEditar').addClass('btn-floating btn-large waves-light green disabled');
	            	} else {
	            		alert('Este telefone '+$("#telefone").val()+' para a pessoa '+$("#nome").val()+' já existe!');
	            	}            	
	            }
	        });
		}
		/*
			*/
		//tabela = tabela + '<tr id="linha'+i+'" onclick="preenche_form(this)" class="card-panel grey lighten-2"><td>'+i+'</td><td>00:00</td><td>00:00</td><td>00:00</td><td>00:00</td><td>00:00</td><td>00:00</td><td>00:00</td><td>00:00</td><td>00:00</td></tr>';
	}
	
	function preenche_form(obj){		
		$('#telefone').val($('#'+obj.id+' td')[1].innerHTML);
		$('#nome').val($('#'+obj.id+' td')[2].innerHTML);
		$('#telefone').focus();
		$('#nome').focus();
		$('#id').val($('#'+obj.id+' td')[0].innerHTML);
		$('#btnEditar').removeClass();			
		$('#btnEditar').addClass('btn-floating btn-large waves-light green');
	}
	
	function editar(){	
		if($('#telefone').val() === '' || $('#nome').val() === ''){
			alert('Necessário preencher nome e telefone.');
		}else if($('#telefone').val().length < 14){
			alert('O telefone precisa ter no mínimo 10 números.');
		}else if($('#nome').val().length > 100){
			alert('O nome não pode ultrapassar 100 caracteres.');
		}else {
			$.ajax({
	            url: "atualizar_telefone",
	            type: "POST",
	            data: "telefone=" + $("#telefone").val() + "&nome="+ $("#nome").val() + "&agenda_id="+$("#id").val(),
	            success: function(responseText){
	            	if(responseText != 0){
	            		var id = $('#id').val();
	            		$('#linha'+id+' td')[1].innerHTML = $('#telefone').val();
	            		$('#linha'+id+' td')[2].innerHTML = $('#nome').val();
	            		$('#id').val('');	
	            		$("#telefone").val('');
	            		$("#nome").val('');	
	            		$('#btnEditar').removeClass();			
	            		$('#btnEditar').addClass('btn-floating btn-large waves-light green disabled');
	            	} else {
	            		alert('Este telefone '+$("#telefone").val()+' para a pessoa '+$("#nome").val()+' já existe!');
	            	}            	
	            }
	        });
		}
		
	}
	
	function remove_linha(id){
		var resposta = confirm('Tem certeza que deseja excluir o telefone '+$('#linha'+id+' td')[1].innerHTML+' de '+$('#linha'+id+' td')[2].innerHTML);
		if (resposta) {			
			$.ajax({
	            url: "excluir_telefone",
	            type: "POST",
	            data: "agenda_id=" + id,
	            success: function(responseText){	            		
	            	$('#linha'+id).remove();         	
	            }
	        });
		}
				
	}