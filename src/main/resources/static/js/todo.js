(function () {
	'use strict';
	angular.module('TodoApp', []).controller('TodoCtrl', Controller);
	Controller.$inject = ['$scope', '$http'];

	function Controller($scope, $http) {
		var vm = this;
		vm.works = [];
		$http.get("/api/todo").then(
			function successCallback(response) {
				$scope.response = response;
				vm.works = response.data.body;
			},
			function errorCallback(response) {
				console.log("Unable to perform get request");
			}
		);

		vm.currentWork = "";
		vm.deleteWork = deleteWork;
		vm.addWork = addWork;
		vm.deleteAllWork = deleteAllWork;
		vm.checkEnter = checkEnter;
		vm.selectAllTodo = selectAllTodo;

		function addWork() {
			var data = {
				"name": vm.currentWork
			};
			vm.currentWork = "";
			$http.post("/api/todo", data).then(
				function successCallback(response) {
					vm.works.push({
						name: angular.copy(response.data.body.name),
						id: angular.copy(response.data.body.id)

					});
				},
				function errorCallback(response) {
					console.log("POST-ing of data failed");
				}
			);
		}
		vm.currentWork = "";

		function deleteWork() {
			var temp = angular.copy(vm.works);
			var count = 0;
			vm.works = [];
			vm.worker = [];

			angular.forEach(temp, function (elem, index) {
				if (!elem.isSelect) {
					vm.works.push(elem);
				} else {
					vm.worker.push(elem.id);
					count++;
				}
			});
			if (vm.worker.length > 0) {
				$http.post("/api/todo/delete", vm.worker).then(
					function successCallback(response) {
						console.log("Successfully deleted ");
					},
					function errorCallback(response) {
						console.log("Failed to delete");

					}
				);
			}
			if (count === temp.length) {
				vm.checkAll = false;
			}
		}

		function deleteAllWork() {
			$http.delete("/api/todo").then(
				function successCallback(response) {
					$scope.response = response;
					vm.works = [];
				},
				function errorCallback(response) {
					console.log("Unable to perform get request");
				}
			);
		}

		function checkEnter(event) {
			if (event.key === "Enter") {
				vm.addWork();
			}
		}

		function selectAllTodo(bool) {
			var count = 0;
			angular.forEach(vm.works, function (elem, index) {
				if (bool) {
					if (vm.checkAll) {
						elem.isSelect = true;
					} else {
						elem.isSelect = false;
					}
				} else {
					if (elem.isSelect) {
						count++;
					}
				}
			});
			if (!bool) {
				if (count === vm.works.length) {
					vm.checkAll = true;
				} else {
					vm.checkAll = false;
				}
			}

		}

	}

})();