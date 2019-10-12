'use strict';
describe('studentCtrl Controller test suite', function () {

  describe('Student Controller', function () {
    let $scope;
    let $httpBackend;
    let $controller;
    let $rootScope;

    beforeEach(module('bfCodeTest'));

    beforeEach(inject(function ($rootScope, $controller, $injector) {
      $httpBackend = $injector.get('$httpBackend');
      $scope = $rootScope.$new();
      $controller('studentCtrl', {
        $scope: $scope
      });
    }));

    const stubStudentResponse = [
      {
        studentID: 1,
        firstName: "Mary",
        lastName: "Smith",
        subjects: [
          {
            subjectID: 2,
            subjectName: "Math"
          }
        ],
        grades: [
          {
            gradeID: 1,
            examID: 1,
            examName: "Math-Exam-1",
            studentID: 1,
            grade: "70"
          }
        ],
        exams: [
          {
            examID: 1,
            examName: "Math-Exam-1",
            studentID: 1,
            subjectID: 2
          },
          {
            examID: 3,
            examName: "Math-Exam-2",
            studentID: 1,
            subjectID: 2
          }
        ]
      },
      {
        studentID: 2,
        firstName: "Jose",
        lastName: "Rodriguez",
        subjects: [
          {
            subjectID: 3,
            subjectName: "English"
          },
          {
            subjectID: 5,
            subjectName: "Databases"
          }
        ],
        grades: [
          {
            gradeID: 2,
            examID: 2,
            examName: "Databases-Exam-1",
            studentID: 2,
            grade: "90"
          }
        ],
        exams: [
          {
            examID: 2,
            examName: "Databases-Exam-1",
            studentID: 2,
            subjectID: 5
          }
        ]
      }
    ];

    const stubSubjectResponse = [
      {
        subjectID: 1,
        subjectName: "Classics"
      },
      {
        subjectID: 2,
        subjectName: "Math"
      },
      {
        subjectID: 3,
        subjectName: "English"
      },
      {
        subjectID: 4,
        subjectName: "Spanish"
      },
      {
        subjectID: 5,
        subjectName: "Databases"
      }
    ]

    it('should build student list correctly and should assign subject to student', function () {
      $httpBackend.expect('GET', 'http://localhost:8080/api/getStudents').respond(200, stubStudentResponse);
      $httpBackend.expect('GET', 'http://localhost:8080/api/getSubjects').respond(200, stubSubjectResponse);
      $httpBackend.flush();

      expect($scope.students.length).toBe(2);
      expect($scope.students[0].availableSubjects.length).toBe(4);
      expect($scope.students[0].exams.length).toBe(2);
      expect($scope.students[0].grades.length).toBe(1);


      expect($scope.students[1].availableSubjects.length).toBe(3);
      expect($scope.students[1].exams.length).toBe(1);
      expect($scope.students[1].grades.length).toBe(1);

      $httpBackend.expect('GET', 'http://localhost:8080/api/getStudents').respond(200, stubStudentResponse);
      $httpBackend.expect('GET', 'http://localhost:8080/api/getSubjects').respond(200, stubSubjectResponse);

      expect($scope.students.length).toBe(2);
      expect($scope.students[0].availableSubjects.length).toBe(4);
      expect($scope.students[0].exams.length).toBe(2);
      expect($scope.students[0].grades.length).toBe(1);


      expect($scope.students[1].availableSubjects.length).toBe(3);
      expect($scope.students[1].exams.length).toBe(1);
      expect($scope.students[1].grades.length).toBe(1);

      const mockStudent = {
        studentID: 1,
        assignSubject: {
          id: 3
        }
      }

      $httpBackend.resetExpectations();
      const stubStudentAfterAssignResponse = stubStudentResponse.slice();
      stubStudentAfterAssignResponse[0].subjects.push({
        subjectID: 3,
        subjectName: "English"
      });

      $httpBackend.expect('POST', 'http://localhost:8080/api/assignSubject?studentId=1&subjectId=3').respond(200, { data: true });
      $httpBackend.expect('GET', 'http://localhost:8080/api/getStudents').respond(200, stubStudentAfterAssignResponse);
      $httpBackend.expect('GET', 'http://localhost:8080/api/getSubjects').respond(200, stubSubjectResponse);
      
      $scope.assignSubject(mockStudent);
      $httpBackend.flush();

      expect($scope.students.length).toBe(2);
      expect($scope.students[0].availableSubjects.length).toBe(3);
      expect($scope.students[0].subjects[0].subjectID).toBe(2);
      expect($scope.students[0].subjects[1].subjectID).toBe(3);

    });
  });
});