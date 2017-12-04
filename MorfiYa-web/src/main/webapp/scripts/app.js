angular
 .module("morfiYaApp",["pascalprecht.translate","tmh.dynamicLocale"]);

angular
 .module( "morfiYaApp" )
 .config( ["$translateProvider", function( $translateProvider ){
	 
	 $translateProvider.translations( "es-es",{
		 "index": {
			 "title": "estamos construyendo la pagina de morfiYaApp",
			 "subtitle": "Usando angular-translate en nuestra aplicacion AngularJS",
			 "legend": "Una forma sencilla de implementar internacionalizacion"
		 }
	 });
	 
	 $translateProvider.translations( "en-us",{
		 "index": {
			 "title": "morfiYaApp page is under construction",
			 "subtitle": "Using angular-translate in our AngularJS aplication",
			 "legend": "A simply way to implement internationalization"
		 }
	 });
	 
 }]);

angular
 .module( "morfiYaApp" )
 .run( ["$window","$translate", function( $window, $translate ){
	 
	 var languaje = ( $window.navigator.userLanguaje || $window.navigator.languaje ).indexOf( "en" )== 0 ? "en-us" : "es-es";
	 
	 $translate.use( languaje );
	 
 }]);