class InactiveCourseException extends Exception {
}

class DoublicateStudentException extends Exception {
}

class DoublicateCourseException extends Exception {
}

class DoublicateTeacherException extends Exception {
}

class NotFindCourseOfSemester extends Exception {
}

class NotFindCurrentCourseException extends Exception {
}

class InvalidCourseException extends Exception {
}

class NotFindStudentIdException extends Exception {
}

class DoublicateAssignmentException extends Exception {
}

class NoSuchAccountException extends Exception {
}

class DoublicateException extends Exception {
}

class TooManyAttemptsException extends Exception {
}

class InvalidOptionException extends Exception {

    public InvalidOptionException(String message) {
        super(message);
    }
}

class ThereIsNotAnyAccountException extends Exception {
    public ThereIsNotAnyAccountException(String message) {
        super(message);
    }
}

class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}

class CourseISEmptyException extends Exception {
}

class NotFindTeacherException extends Exception {
}

class TeacherIsEmptyException extends Exception {
}

class StudentIsEmptyException extends Exception {
}

class NotFindAssignmentException extends Exception {
}

class AssignmentIsEmptyException extends Exception {
}
class ThisCourseDoseNotHaveAnyTeacherException extends Exception{}
class NotFindStudentInCourseException extends Exception{}
