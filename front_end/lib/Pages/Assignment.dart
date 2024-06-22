enum AssignmentType { practice, project }

class Assignment {
  String assignmentName, id;
  AssignmentType assignmentType;
  int deadline;
  bool isActive;

  Assignment(this.assignmentName, this.id, this.assignmentType, this.deadline,
      this.isActive);
}
