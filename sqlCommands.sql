-- Creating students table 
create table students(
	MIS int primary key,
	firstName varchar(100) not null,
    lastName varchar(100) not null
);  

desc students; 



-- Creating subjects table
create table subjects (
	id int primary key auto_increment,
    Name varchar(100) not null, 
    totalMarks float not null
); 


-- Creating marks table 
create table marks (
	student_MIS int references students(MIS),
	subject_id int references subjects(id),
    marks int not null,
    primary key (student_MIS, subject_id)
); 

-- Adding Students

insert into students values ( 1, 'Izuku', 'Midoriya');
insert into students values ( 2, 'Katsuki', 'Bakugo');
insert into students values ( 3, 'Shoto', 'Todoroki');
insert into students values ( 4, 'Ochaco', 'Ururaka');
insert into students values ( 5, 'Tenya', 'Ida');
insert into students values ( 6, 'Tsuyu', 'Asui');
insert into students values ( 7, 'Eijiro', 'Kirishima');
insert into students values ( 8, 'Momo', 'Yaoyorozu');

-- Cuurently handling only 3 students
delete from students where MIS between 4 and 8;

SELECT * FROM class_1a.students;

-- Adding subjects

insert into subjects  values (1, 'Japanese', 100);
insert into subjects values (2, 'English', 100);
insert into subjects  values (3, 'History', 100);
insert into subjects  values (4, 'Hero Training', 100);
insert into subjects values (5, 'Quirk Mastery', 100);
insert into subjects  values (6, 'Search & Rescue', 100);

-- Currently handling only 2 subjects
delete from subjects where id between 3 and 6;

SELECT * FROM class_1a.subjects;

-- Adding marks

-- Student 1
insert into class_1a.marks values (1, 1, 97);
insert into class_1a.marks values (1, 2, 88);

-- Studemt 2
insert into class_1a.marks values (2, 1, 97);
insert into class_1a.marks values (2, 2, 83);

-- Student 3
insert into class_1a.marks values (3, 1, 96);
insert into class_1a.marks values (3, 2, 90);

SELECT * FROM class_1a.marks;

-- Getting student report
SELECT MIS, firstName, lastName, Name AS subjectName, marks, totalMarks
	FROM marks m
	JOIN students stud
		ON stud.MIS = m.student_MIS
	JOIN subjects sub
		ON sub.id = m.subject_id
	WHERE MIS = 1;
