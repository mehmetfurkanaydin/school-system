var app = angular.module('bfCodeTest', []);

app.controller('studentCtrl', function($scope, $location, $http) {
	console.log("StudentCtrl loaded.");

	$scope.assignSubject = function (student) {
      $http.post('http://localhost:8080/api/assignSubject?studentId=' + student.studentID + '&subjectId=' + student.assignSubject.id)
      	.then(response => {
      	    if (response.data) {
      	       $scope.fetchFunction();
      	    }
      	})
	}


	$scope.fetchFunction = function () {
        $http.get('http://localhost:8080/api/getStudents')
        .then(response => {
            $scope.students = response.data;
        })
        .then(() => {
            $http.get('http://localhost:8080/api/getSubjects')
            .then(response => {
                $scope.students.forEach(std => {
                    std.availableSubjects = response.data.filter(
                        function(e) {
                          return this.indexOf(e.subjectID) < 0;
                        },
                        std.subjects.map(sub => sub.subjectID)
                    );
                    std.assignSubject =  { id: '' };
                });
            })
        })
	}
	$scope.fetchFunction();
});