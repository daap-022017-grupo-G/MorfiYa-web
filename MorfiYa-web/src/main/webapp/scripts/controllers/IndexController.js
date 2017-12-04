angular
 .module( "morfiYaApp" )
 .controller ( "IndexController",["$scope", "$translate", function( $scope, $translate ){
	 
	 $scope.translations = {};
	 
	 $translate( "index.legend" ).then( function( data ){
		 $scope.translations.legend = data;
	 });
	 
 }]);