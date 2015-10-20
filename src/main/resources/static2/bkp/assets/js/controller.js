function AddCtrl($scope, $http, $location) {
    $scope.add_new = function (usuario) {
        //usuario.data = new Date();
        $http.post("/usuarios", usuario).success(function (data) {
            delete $scope.usuario;
            $scope.contatoForm.$setPristine();
            carregarContatos();
        });
    };
}

function EditCtrl($scope, $http, $location, $routeParams) {
  var id = $routeParams.id;
  $scope.activePath = null;

  $http.get('/usuarios/'+ id).success(function(data) {
    $scope.usuarios = data;
  });

  $scope.update = function(usuario){
	  alert(usuario);
    $http.put('usuarios', usuario).success(function(data) {
      $scope.usuarios = data;
      $scope.activePath = $location.path('/');
    });
  };

  $scope.delete = function(usuario) {
    console.log(usuario);

    var deleteUser = confirm('Are you absolutely sure you want to delete?');
    if (deleteUser) {
      $http.delete('/usuarios/'+usuario.id);
      $scope.activePath = $location.path('/');
    }
  };
}