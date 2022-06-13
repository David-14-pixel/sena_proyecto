$(function(){
	 $.validator.addMethod("formEmail", function (value, element) {
	     var pattern = /^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\.([a-zA-Z])+([a-zA-Z])+/;
	     return this.optional(element) || pattern.test(value);
	  }, "Formato del correo incorrecto");
	$('form[id="registro"]').validate({
		rules:{
			cedula: {required:true, number: true},
			nombre : {required:true, number: false},
			celular : {required:true, number: true},
			correo : {required:true,email: true, formEmail:true},
			estrato : {required:true},
			edad : {required: true, number: true},
			fecha_nacimiento : {required: true},
			turno : {required: true},
			sueldo : {required: true, number: true}

		},
	 messages:{
		cedula: {required:'Por favor ingrese su número de identificación', number: 'Este campo sólo permite números'},
        nombre : {required:'Por favor ingrese su nombre', number: 'Este campo sólo permite letras'},
        celular : {required:'Por favor ingrese su número de celular', number: 'Este campo sólo permite números'},
        correo : {required:'Por favor ingrese su correo electronico',email:'Formato del correo incorrecto'},
        estrato : {required:'Por favor seleccione un estrato'},
        edad : {required: 'Por favor ingrese su edad ', number: 'Este campo sólo permite letras'},
        fecha_nacimiento : {required: 'Por favor seleccione su fecha de nacimiento'},
        turno : {required: 'Por favor seleccione su turno'},
        sueldo : {required: 'Por favor ingrese su salario', number: 'Este campo sólo permite números'}
	},
	submitHandler : function(form){
		form.submit();
	}
	});

    $("#correo").blur(function(){
            let correo = $('#correo').val();
    		$.ajax({
    			url:"/empleados/"+correo+"/correo",
    			success: function(respuesta){
    				if(respuesta=='Existe'){
    				$("#msgcorreo").html("El correo "+correo+ " ya existe dentro de nuestro sistema");
    				$("#correo").focus();
    				$("#btnconfirmar").prop("disabled", true);
    				}else{
    					$("#msgcorreo").html("");
    					$("#btnconfirmar").prop("disabled", false);
    				}
    			}
    		});
    	});
    	$("#cedula").blur(function(){
                    let cedula = $('#cedula').val();
            		$.ajax({
            			url:"/empleados/"+cedula+"/cedula",
            			success: function(respuesta){
            				if(respuesta=='Existe'){
            				$("#msg_cedula").html("La cedula "+cedula+ " ya existe dentro de nuestro sistema");
            				$("#cedula").focus();
            				$("#btnconfirmar").prop("disabled", true);
            				}else{
            					$("#msg_cedula").html("");
            					$("#btnconfirmar").prop("disabled", false);
            				}
            			}
            		});
            	});

});