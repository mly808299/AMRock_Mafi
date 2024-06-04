import 'dart:ffi';

class Student {
  String firstName, lastName, username, password, id;
  int numberOfCurrentSemester;
  Double totalAverage = 0.0 as Double;
  Double averageOfRegisteredSemester = 0.0 as Double;

  Student(this.firstName, this.lastName, this.username, this.password, this.id,
      this.numberOfCurrentSemester);
}
