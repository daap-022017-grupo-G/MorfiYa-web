package APIRest



@Controller
class MorfiYaAPIRest {
	
	extension JSONUtils = new JSONUtils;
	
	App morfi;

	new MorfiYaApp(App morfi){
		this.morfi = morfi;
	}
	
	@Get("/Menus")
	public void getMenu(){
		response.contentType = ContentType.APPLICATION_JSON;
		ok(this.morfi.productos.toJson);
	}
	
	@Delete("/Menus/:id")
	public void eliminarMenu(){
		response.contentType = ContentType.APPLICATION_JSON;
		try{
			this.morfi.eliminarMenu(Integer.valueOf(id));
			ok();
		};
		catch(NumberFormatException e){
			badRequest(getErrorJson("el id debe ser un numero entero"));
		};
		
	}
	
	@Put("/Menu/:id")
	public void modificarMenu(@Body String body){
		response.contentType = ContentType.APPLICATION_JSON;
        try {
	        val Menu p = body.fromJson(Menu);
	        if (Integer.parseInt(id) != p.id) {
			 badRequest('{ "error" : "Id en URL distinto del cuerpo" }');
			 };
		
	        try {
				this.morfi.updateMenu(p);
				ok();
	        };
	        catch (UserException exception) {
	        	badRequest(getErrorJson(exception.message));
	        };
        
        };
        catch (UnrecognizedPropertyException exception) {
        	badRequest(getErrorJson("El body debe ser un producto"));
        	};

	}
	
	
	
//	@Post("/Menus")
//	public crearMenu(@Body String body){
//		response.contentType = ContentType.APPLICATION_JSON
//		try{
//			val Menu p = body.fromJson(Menu)
//			try{
//				this.morfi.addMenu(p)
//				ok()
//			}
//			catch(UserException e) {
//				badRequest(getErrorJson(e.message))
//				
//			}
//		}
//		catch (UnrecognizedPropertyException exception) {
//			badRequest(getErrorJson("El body debe ser un Villano"))
//		}
//	}
	
/************************************
 * 				Users
 ************************************/	
	
//	@Get("/Users")
//	public getUser(String string){
//		response.contentType = ContentType.APPLICATION_JSON
//		var res = this.morfi.usuarios.filter[it.id.toString.contains(string)]
//	}
//	

	@Get("/Clients")
	public Result getUser(){
		response.contentType = ContentType.APPLICATION_JSON;
		var appr = new  AppRest(this.morfi);
		ok(appr.clientes.toJson);
	}
	@Get("/Clients/:id")
    public Client getClientById() {
        response.contentType = ContentType.APPLICATION_JSON;
        try {        	
            var cliente = this.morfi.getClient(Integer.valueOf(id));
            if (cliente == null) {
            	notFound(getErrorJson("No existe Client con ese id"));
            } else {
            	var ClientRest = new ClientRest(cliente);
            	ok(ClientRest.toJson);
            }
        }
        catch (NumberFormatException ex) {
        	badRequest(getErrorJson("El id debe ser un numero entero"));
        }
    }
	@Get("/ClientsBuscar")
	public Result getUserClient(String nameUser ){
		try {
		val name = String.valueOf(nameUser);
		response.contentType = ContentType.APPLICATION_JSON;
		var appr = new  AppRest(this.morfi);
		ok(appr.findClient(name).toJson);
		
		} catch (UserException e) {
			return notFound("No existe User con ese name '" + nameUser + "'");
		}
	}
	@Put("/Clients/:id")
    public Result updateClient(@Body String body) {
        response.contentType = ContentType.APPLICATION_JSON;
	        val ClientRest c = body.fromJson(ClientRest)
	        var Client cfin = new Client (c,this.morfi);
	        
	        if (Integer.parseInt(id) != c.id) {
			 badRequest('{ "error" : "Id en URL distinto del cuerpo" }');
			 }
			
	        try {
				this.morfi.updateClient(cfin)
				ok()	        	
	        } 
	        catch (UserException exception) {
	        	badRequest(getErrorJson(exception.message))
	        }
        
        }

	@Post("/ClientsAgregar")
	public Result createClient(@Body String body){
		//response.contentType = ContentType.APPLICATION_JSON
				try {
					val ClientRest c  = body.fromJson(ClientRest)
					c.validate
					var Client cfin = new Client (c,this.morfi);
					cfin.carritoVirtual = new CarritoVirtual();
					cfin.saldoDisponible = 0
					this.morfi.validateNameClientUser(cfin);
					this.morfi.addClient(cfin)
					ok('''{ "id" : "«cfin.id»" }''')
					} 
					catch (UserException e) {
					// badRequest(''' { "error" : "«e.message»" }''')
						badRequest(e.message)
					}
	}
	@Post("/buy")
    public emitirOrden(@Body String body) {
        response.contentType = ContentType.APPLICATION_JSON
        try {
	        val ClientRest c = body.fromJson(ClientRest)
	        System.out.println(c.id);
	        val Client cl = new Client(c,this.morfi);
	        try {
	        	 var cli = this.morfi.buy(cl);
	        	 var ClientRest c1 =  new ClientRest(cli);
				 ok(c1.toJson);        	
	           } 
	        catch (UserException exception) {
	        	badRequest(getErrorJson(exception.message))
	        	   }
           } 
        catch (UnrecognizedPropertyException exception) {
        	badRequest(getErrorJson("El body debe ser un OrdenRequest"))
        }
    }	
	@Post("/Users")
	public createEmployee(@Body String body){
		response.contentType = ContentType.APPLICATION_JSON
		try{
			val Provider u  = body.fromJson(Provider)
			try{
				this.morfi.addProvider(u)
				ok()
			}
			catch(UserException e){
				badRequest(getErrorJson(e.message))
			}
		}
		catch (UnrecognizedPropertyException exception) {
			badRequest(getErrorJson("El body debe ser un Villano"))
		}
	}
	private  getErrorJson(String message) {
        '{ "error": "' + message + '" }'
	}		
//	
}