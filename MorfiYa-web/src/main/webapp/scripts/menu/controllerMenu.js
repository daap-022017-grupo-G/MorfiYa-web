app.controller('TodosLosMenusCtrl', function ($resource, $timeout, Menus,Clientes ,ClientesBuscar ,Comprar,ClientesAgregar) {
	'use strict';
	
	console.log("Inicializando");
	var self = this;
	self.menus = [];
	this.menuSeleccionado = null;
	
	this.actualizarLista = function() {
		Menus.query(function(data) {
            self.menus = data;
        });
    };
    
  this.actualizarLista();
	
	
	 this.verDescripcion = function(menu) {
	        self.menuSeleccionado = menu;
	        console.log(self.menuSeleccionado.descripcion);
	        $("#verDescripcionMenuModal").modal({});
	    };    
	//////////////////
	    
	    console.log("InicializandoControllerUsuario");
		
		//var self = this;
		self.nombreUser = '';
		self.contrasenha = '';
		self.clientes = [];
		//var nuevoCliente
		self.usuarioIniciado = null;
		self.usuario = null;
		
		this.actualizarListaC = function() {
			Clientes.query(function(data) {
	            self.clientes = data;
	        });
	    };
	    
	    this.actualizarListaC();
		 // AGREGAR
	    this.agregarCliente = function() {
	    	ClientesAgregar.save(this.nuevoCliente, function(data) {
	    		//console.log(this.nuevoCliente.nombre);
	    		 //self.notificarMensaje('Gracias Por Registrarse');
	            self.actualizarListaC();
	          //  console.log("usuarioSeAgrego" + data.nombreUsuario);
	            self.nuevoCliente = null;
	        });
	        $("#registroModal").modal('toggle');
	    };
	    
	   //Iniciar Session
	    this.aceptar = function(){
	    	if(self.nombreUser == ''|| self.contrasenha == '' ){
	    		alert("Completa los campos de Inicio de Sesion")
	    	}else{
	    		ClientesBuscar.queryCliente({"nombreUsuario":self.nombreUser},function(data){
	    		console.log("UsaurioNombreData:" + data.nombreUsuario +"Pass:"+data.password );
	    		if(self.contrasenha != data.password){alert("Password Incorrecta")}else{
	    		self.usuarioIniciado = data;
	    		console.log("UsuarioNombreSelf:" + self.usuarioIniciado.nombreUsuario +"Pass:"+self.usuarioIniciado.password );
	    		self.nombreUser = '';
	    		self.contrasenha = '';	
	    		$("#myModalInicioDeSesion").modal('toggle')
	    		
	    		}
	    	});	
	    	console.log("UsaurioNombre:" +self.nombreUser +"Pass:"+self.contrasenha );
	    	//$("#myModalInicioDeSesion").modal('toggle');
	    	}
		};
		
	    this.seguirComprando = function(){
	    	//console.log("UsuarioNombreSelf:" + self.usuarioIniciado.nombreUsuario +"Pass:"+ self.usuarioIniciado.password );
	    	$("#myModalCarrito").modal('toggle');
	    };
	    
	    this.comprar = function(){
	    	//console.log("UsuarioNombreSelf:" + self.usuarioIniciado.nombreUsuario +"Pass:"+ self.usuarioIniciado.password );
	    	if(this.usuarioIniciado.saldoDisponible < this.usuarioIniciado.carrito.montoAcumulado ){
	    		alert("Saldo Insuficiente");
	    	}else{
	    	Comprar.buy(this.usuarioIniciado,function(data) {
	           	self.usuarioIniciado = data;
	           	self.actualizarLista()
	           	console.log("UsuarioNombreSaldo"+ data.saldoDisponible );
	        });
	    	alert("Gracias por Comprar, Vuelva pronto");
	    	$("#myModalCarrito").modal('toggle');
	    	}
	    };
		
	    this.verDescripcionC = function(cliente) {
	        this.usuario = cliente;
	        console.log(self.usuario.nombreUsuario);
	        $("#verDescripcionClienteModal").modal({});
	    };
	    
	    this.seleccionarMenuYAgregarloAlCarrito = function(menu) {
	  	  if(self.usuarioIniciado == null){
	  		alert("Debes Iniciar Sesion");
	  	  }else{
	  		  	if(menu.stockDisponible == 0){
	  		  		alert("No hay Stock Disponible De Este Menu")
	  		  	}else{
	  		  	
	  		  	console.log("Seleccionando " + menu);
	  		  	this.menuSeleccionado = menu;
	  		  	console.log("Seleccionando " + this.menuSeleccionado.marca );
	  		  	this.usuarioIniciado.carrito.menusAcumulados.push(menu);
	  		  	console.log("Seleccionando " + this.usuarioIniciado.carrito.montoAcumulado );
	  		  	this.usuarioIniciado.carrito.montoAcumulado  = this.usuarioIniciado.carrito.montoAcumulado  + menu.precio;
			//this.guardarCliente();
	  		  	}
	  		
	  	  }
	  	};
	  	
	  	this.guardarCliente  = function() {
	  		Clientes.updates(this.usuarioIniciado,function(){
	  			console.log("UsuarioAActualizar " + self.usuarioIniciado.nombreUsuario );
	            self.actualizarListaC();
	        });
	    };
	    
	    this.borrarMenuDelCarrito = function(menu){
	    	this.usuarioIniciado.carrito.montoAcumulado  = this.usuarioIniciado.carrito.montoAcumulado  - menu.precio;
	    	console.log("Seleccionando para borrar " + menu.marca );
			this.usuarioIniciado.carrito.menusAcumulados =this.usuarioIniciado.carrito.menusAcumulados.filter(
				function(it) {
					return it.marca!=menu.marca;
				})
	    }
	    
	    this.verCarritoModal = function() {
	    	if(self.usuarioIniciado == null){
	    		alert("Debes iniciar Sesion")
	    	}else{
	        console.log(self.usuarioIniciado.nombreUsuario);
	        $("#myModalCarrito").modal({});
	    	}
	    };
	    
	    this.cargarSaldo = function() {
	    	if(self.saldoACargar == 0){
	    		alert("Debes colocar una cantidad para agregar")
	    	}else{
	        console.log(self.usuarioIniciado.saldoDisponible);
	        console.log(self.saldoACargar);
	        this.usuarioIniciado.saldoDisponible = this.usuarioIniciado.saldoDisponible + self.saldoACargar;
	        self.saldoACargar = 0;
	    	}
	    };
	    
	    this.cerrarSesion = function() {
	    	if(self.usuarioIniciado == null){
	    		alert("Debes iniciar Sesion")
	    	}else{
	        console.log(self.usuarioIniciado.nombreUsuario);
	        self.usuarioIniciado = null;
	        $("#myModalCarrito").modal('toggle');
	    	}
	    };
	
});