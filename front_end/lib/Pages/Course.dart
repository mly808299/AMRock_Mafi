import 'Assignment.dart';
import 'Teacher.dart';

class Course {
  String courseName;
  int courseCode;
  int unit;
  bool isActive;
  Teacher teacher;
  List<Assignment> assignments = [];
  Course ({required this.courseName , required this.unit , required this.courseCode , required this.isActive , required this.teacher} );
  factory Course.fromJson(Map<String, dynamic> json) {
    return Course(
      courseName : json['courseName'],
      courseCode: json['courseCode'],
      unit: json['unit'],
      isActive: json['isActive'],
      teacher: json['teacher'],
    );
  }
}
