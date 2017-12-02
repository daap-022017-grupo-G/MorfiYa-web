app.factory('Menus', function($resource) {
    return $resource('/Menus/:id', {'id': '@id'}, {
    	'query': { method: 'GET', isArray: true},
        'remove': { method:'DELETE' }
    });
});
app.factory('Clientes', function($resource) {
    return $resource('/Clientes/:id', {'id': '@id'}, {
    	'query': { method: 'GET', isArray: true},
    	'updates': { method: 'PUT' },
    });
});

app.factory('ClientesBuscar', function($resource) {
    return $resource('/ClientesBuscar', {}, {
    	'queryCliente': { method: 'GET'},
    });
});
//ClientesAgregar
app.factory('ClientesAgregar', function($resource) {
    return $resource('/ClientesAgregar', {}, {
        'save': { method: 'POST' },
    });
});
app.factory('Comprar', function($resource) {
    return $resource('/comprar', {}, {
        'buy': { method: 'POST' },
    });
});