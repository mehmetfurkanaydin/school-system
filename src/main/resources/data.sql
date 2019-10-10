INSERT INTO student VALUES(1, 'Mary', 'Smith');
INSERT INTO student VALUES(2, 'Jose', 'Rodriguez');

INSERT INTO subject VALUES(1, 'Classics');
INSERT INTO subject VALUES(2, 'Math');
INSERT INTO subject VALUES(3, 'English');
INSERT INTO subject VALUES(4, 'Spanish');
INSERT INTO subject VALUES(5, 'Databases');

INSERT INTO faculty VALUES(1, 'Humanities');
INSERT INTO faculty VALUES(2, 'Math');
INSERT INTO faculty VALUES(3, 'Engineering');

INSERT INTO studentSubjects VALUES(1, 2);
INSERT INTO studentSubjects VALUES(2, 5);
INSERT INTO studentSubjects VALUES(2, 3);

INSERT INTO exam VALUES(1, 'Math-Exam-1', 1, 2);
INSERT INTO exam VALUES(2, 'Databases-Exam-1', 2, 5);
INSERT INTO exam VALUES(3, 'Math-Exam-2', 1, 2);

INSERT INTO grade VALUES(1, 1, 1, '70');
INSERT INTO grade VALUES(2, 2, 2, '90');