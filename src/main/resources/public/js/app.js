var cfWorkshopApp = angular.module('cfWorkshopApp', ['ngRoute', 'cfWorkshopControllers']);

cfWorkshopApp.config(['$routeProvider',
    function($routeProvider) {
		$routeProvider.when('/attendees', {
			templateUrl: '/partials/attendeeList.html',
			controller: 'AttendeeListController'
		}).when('/attendees/:attendeeId', {
			templateUrl: '/partials/editAttendee.html',
			controller: 'EditAttendeeController'
		}).when('/environment', {
      templateUrl: '/partials/environment.html',
      controller: 'EnvironmentController'
    }).otherwise({
			redirectTo: '/attendees'
		})
}])
