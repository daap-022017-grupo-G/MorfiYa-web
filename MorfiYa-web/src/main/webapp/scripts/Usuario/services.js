app.factory('Clientes', function($resource) {
    return $resource('/Clientes/:nombreUsuario', {'nombreUsuario': '@nombreUsuario'}, {
    	'query': { method: 'GET', isArray: true},
        'save': { method:'POST' }
    });
});

app.factory('ClientesBuscar', function($resource) {
    return $resource('/ClientesBuscar', {}, {
    	'queryCliente': { method: 'GET'},
    });
});