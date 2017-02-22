var cfWorkshopApp = angular.module('cfWorkshopApp', ['ngRoute', 'cfWorkshopControllers']);

cfWorkshopApp.config(['$routeProvider',
    function($routeProvider) {
		$routeProvider.when('/notes', {
			templateUrl: '/partials/noteList.html',
			controller: 'NoteListController'
		}).when('/notes/:noteId', {
			templateUrl: '/partials/editNote.html',
			controller: 'EditNoteController'
		}).when('/environment', {
			templateUrl: '/partials/environment.html',
			controller: 'EnvironmentController'
		}).when('/environment/kill', {
			templateUrl: '/partials/environment.html',
			controller: 'EnvironmentController'
    }).otherwise({
			redirectTo: '/environment'
		})
}])
