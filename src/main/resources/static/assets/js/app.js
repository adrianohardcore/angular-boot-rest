angular.module('CrudApp', []).
  config(['$routeProvider', function($routeProvider) {
  $routeProvider.
      when('/', {templateUrl: 'assets/tpl/lists.html', controller: ListCtrl}).
      when('/add-user', {templateUrl: 'assets/tpl/add-new.html', controller: AddCtrl}).
      when('/edit/:id', {templateUrl: 'assets/tpl/edit.html', controller: EditCtrl}).
      otherwise({redirectTo: '/'});
}]);

function ListCtrl($scope, $http) {
  $http.get('usuarios').success(function(data) {
    $scope.usuarios = data;
  });
}

//Salvar o novo usuário no banco
function AddCtrl($scope, $http, $location) {
  $scope.master = {};
  $scope.activePath = null;

  $scope.add_new = function(usuario, AddNewForm) {
	  {{usuario}}

    $http.post('usuarios', usuario).success(function(){
      $scope.reset();
      $scope.activePath = $location.path('/');
    });

    $scope.reset = function() {
      $scope.usuario = angular.copy($scope.master);
    };

    $scope.reset();

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