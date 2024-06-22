class Student {
  static Student ?student = Student(firstName: '', lastName: '', unit: 0, totalAverage: 0.0, numberOfCurrentSemester: 0, id: '');
  String firstName = '', lastName ='';
  String ?username;
  String ?password;
  String id ='';
  int numberOfCurrentSemester = 0;
  double totalAverage = 0.0;
  int unit = 0;
  Student({required this.firstName ,  required this.lastName ,  required this.unit ,  required this.totalAverage,
   required this.numberOfCurrentSemester , required this.id});
  factory Student.fromJson(Map<String, dynamic> json) {
    return Student(
      firstName : json['firstName'],
      lastName: json['lastName'],
      id : json['id'],
      numberOfCurrentSemester : json['numberOfCurrentSemester'],
      totalAverage : json['totalAverage'],
      unit : json['unit']
    );
  }
}
