/*
 * JS file for all of the Angular controllers in the app
 */
'use strict';

var cfWorkshopControllers = angular.module('cfWorkshopControllers', []);

//Attendee list controller
cfWorkshopApp.controller('NoteListController', function($scope, $http) {

	//Handles the delete request function
	$scope.delete = function(noteId) {
		$http.delete("/notes/" + noteId).success(function(data, status, headers, config) {
			$scope.getNotes();
			$scope.message = "Successfully deleted the note.";
			$scope.error = ""
		}).error(function(data, status, headers, config) {
			$scope.message = "";
			$scope.error = "There was an error deleting the note.";
		});
	};

	//Reloads the data
	$scope.getNotes = function() {
		$http.get('/notes/').success(function(data) {
			$scope.notes = data;
		})
	};

	//Initial page load
	$scope.getNotes();
});

// Edit note controller
cfWorkshopApp.controller('EditNoteController', function($scope, $http, $routeParams) {

	//Handles the update request function
	$scope.update = function(note) {
		$http.post("/notes/", note).success(function(data, status, headers, config) {
			$scope.note = data;
			$scope.message = "Successfully saved the note.";
			$scope.error = "";
		}).error(function(data, status, headers, config) {
			$scope.message = "";
			$scope.error = "There was an error saving the note.";
		});
	};

	//Handles the reset request function and the initial load of the attendee
	$scope.reset = function(note) {
		$http.get('/notes/' + $routeParams.noteId).success(function(data) {
			$scope.note = data;
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
			$scope.message = "Failed to kill the app instance.";
		}).error(function(data) {
			$scope.message = "Successfully killed the app instance.";			
		});
	}

	//Initial load
	$scope.getEnvironment();
});