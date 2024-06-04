import 'Teacher.dart';

class Course {
  String courseName;
  int courseCode, unit;
  bool isActive;
  Teacher teacher;

  Course(
      this.courseName, this.courseCode, this.isActive, this.unit, this.teacher);
}
