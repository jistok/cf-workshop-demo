/*
 * JS file for all of the Angular controllers in the app
 */
'use strict';

var cfWorkshopControllers = angular.module('cfWorkshopControllers', []);

//Attendee list controller
cfWorkshopApp.controller('AttendeeListController', function($scope, $http) {

	//Handles the delete request function
	$scope.delete = function(attendeeId) {
		$http.delete("/attendees/" + attendeeId).success(function(data, status, headers, config) {
			$scope.getAttendees();
			$scope.message = "Successfully deleted the attendee.";
			$scope.error = ""
		}).error(function(data, status, headers, config) {
			$scope.message = "";
			$scope.error = "There was an error deleting the attendee.";
		});
	};

	//Reloads the data
	$scope.getAttendees = function() {
		$http.get('/attendees/').success(function(data) {
			$scope.attendees = data;
		})
	};

	//Initial page load
	$scope.getAttendees();
});

// Edit attendee controller
cfWorkshopApp.controller('EditAttendeeController', function($scope, $http, $routeParams) {

	//Handles the update request function
	$scope.update = function(attendee) {
		$http.post("/attendees/", attendee).success(function(data, status, headers, config) {
			$scope.attendee = data;
			$scope.message = "Successfully saved the attendee.";
			$scope.error = "";
		}).error(function(data, status, headers, config) {
			$scope.message = "";
			$scope.error = "There was an error saving the attendee.";
		});
	};

	//Handles the reset request function and the initial load of the attendee
	$scope.reset = function(attendee) {
		$http.get('/attendees/' + $routeParams.attendeeId).success(function(data) {
			$scope.attendee = data;
			$scope.message = "";
			$scope.error = "";
		});
	}

	//Initial load
	$scope.reset();
});

//Environment controller
cfWorkshopApp.controller('EnvironmentController', function($scope, $http, $routeParams) {

	//Handles the environment function
	$scope.getEnvironment = function() {
		$http.get("/environment/info").success(function(data) {
			$scope.message = "";
			$scope.environment = data;
		});
	};
	
	//Handles the kill function
	$scope.kill = function() {
		$http.get("/environment/kill").success(function(data) {
			$scope.message = "Successfully killed the app instance.";
		}).error(function(data) {
			$scope.message = "Successfully killed the app instance.";			
		});
	}

	//Initial load
	$scope.getEnvironment();
});