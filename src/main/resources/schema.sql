create table student (
   studentID integer auto_increment not null,
   firstName varchar(255) not null,
   lastName varchar(255) not null,
   primary key(studentID)
);

create table subject (
   subjectID integer auto_increment not null,
   subjectName varchar(255) not null,
   primary key(subjectID)
);

create table faculty (
   facultyID integer auto_increment not null,
   facultyName varchar(255) not null,
   primary key(facultyID)
);

create table studentSubjects (
   studentID integer not null references student(studentID),
   subjectID integer not null references subject(subjectID),
   primary key(studentID, subjectID)
);

create table exam (
   examID integer auto_increment not null,
   examName varchar(255),
   studentID integer not null references student(studentID),
   subjectID integer not null references subject(subjectID),
   primary key(examID)
);

create table grade (
   gradeID integer auto_increment not null,
   examID integer not null references exam(examID),
   studentID integer not null references student(studentID),
   grade varchar(3) not null,
   primary key(examID, studentID)
);
