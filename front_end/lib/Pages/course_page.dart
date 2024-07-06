import 'package:flutter/material.dart';
import '../components/classBox.dart';
import '../components/methods.dart';

class CoursePage extends StatefulWidget {
  const CoursePage({super.key});

  @override
  State<CoursePage> createState() => _CoursePageState();
}

class _CoursePageState extends State<CoursePage> {

  void _showAddCourse(context) {
    showModalBottomSheet(
        context: context,
        builder: (BuildContext context) {
          String? input;
          return Padding(
            padding: const EdgeInsets.all(0.0).copyWith(bottom: MediaQuery.of(context).viewInsets.bottom),
            child: SingleChildScrollView(
              child: Container(
                decoration: const BoxDecoration(
                    color: Colors.yellow,
                    borderRadius: BorderRadius.only(
                      topLeft: Radius.circular(25),
                      topRight: Radius.circular(25),
                    )),
                height: MediaQuery.of(context).size.height * .5,
                child: Column(
                  children: [
                    const Padding(
                      padding: EdgeInsets.only(top: 25, left: 25),
                      child: Row(
                        children: [
                          Padding(
                            padding: EdgeInsets.only(right: 8.0),
                            child: Icon(
                              Icons.school_rounded,
                              color: Colors.black,
                            ),
                          ),
                          Text(
                            'Add new Course',
                            style: TextStyle(
                                fontSize: 20,
                                fontWeight: FontWeight.bold,
                                color: Colors.black),
                          ),
                        ],
                      ),
                    ),
                    const Padding(
                      padding: EdgeInsets.only(top: 20, right: 242),
                      child: Text(
                        'Course code',
                        style: TextStyle(
                            fontSize: 20,
                            fontWeight: FontWeight.bold,
                            color: Colors.black),
                      ),
                    ),
                    Container(
                      margin: const EdgeInsets.only(bottom: 10, top: 5),
                        child: Padding(
                            padding: const EdgeInsets.symmetric(
                              horizontal: 20.0,
                            ),
                            child: TextFormField(
                              keyboardType: TextInputType.name,
                              obscureText: false,
                              decoration: InputDecoration(
                                contentPadding: const EdgeInsets.symmetric(
                                    horizontal: 26, vertical: 17.5),
                                enabledBorder: OutlineInputBorder(
                                  borderSide:
                                      const BorderSide(color: Colors.yellow),
                                  borderRadius: BorderRadius.circular(20.0),
                                ),
                                focusedBorder: OutlineInputBorder(
                                    borderRadius: BorderRadius.circular(20.0),
                                    borderSide: const BorderSide(
                                        color: Colors.green, width: 2)),
                                fillColor: const Color(0XFFDB98D4),
                                filled: true,
                                hintText: "Enter the Course code...",
                              ),
                              validator: (value){
                                input = value;
                                  return null;
                              },
                              autovalidateMode: AutovalidateMode.onUserInteraction,
                            )),

                    ),
                    Padding(
                      padding: const EdgeInsets.only(
                          right: 28, left: 28, top: 15, bottom: 5),
                      child: ConstrainedBox(
                        constraints: const BoxConstraints.tightFor(
                          width: double.infinity,
                          height: 56,
                        ),
                        child: ElevatedButton(
                          style: ElevatedButton.styleFrom(
                            backgroundColor: Colors.deepPurpleAccent,
                            shadowColor: Colors.black,
                            elevation: 8,
                            shape: RoundedRectangleBorder(
                              borderRadius: BorderRadius.circular(25),
                            ),
                          ),
                          onPressed: () {
                            if (input != null) {
                              Navigator.pop(context);
                              //TODO
                              customToast(
                                  "Course has been successfully add to your courses", context);
                            }
                          },
                          child: const Text(
                            'ADD',
                            style: TextStyle(
                              fontSize: 24,
                              color: Colors.black,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ),
                      ),
                    ),
                  ],
                ),
              ),
            ),
          );
        });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.yellow[600],
      appBar: AppBar(
        backgroundColor: Colors.yellow[600],
        toolbarHeight: 0,
      ),
      body: Stack(
        children: [
          Column(
            children: [
              Row(
                children: [
                  Container(
                    alignment: Alignment.topLeft,
                    margin: const EdgeInsets.only(left: 10, top: 15),
                    child: const Text(
                      'Courses',
                      style: TextStyle(
                        color: Colors.black,
                        fontSize: 25,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.only(left: 110, top: 20),
                    child: ConstrainedBox(
                      constraints: const BoxConstraints.tightFor(
                        width: 180,
                        height: 45,
                      ),
                      child: ElevatedButton(
                        style: ElevatedButton.styleFrom(
                          backgroundColor: Colors.deepPurpleAccent,
                          shadowColor: Colors.black,
                          elevation: 10,
                          shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(25),
                          ),
                        ),
                        onPressed: () {
                          _showAddCourse(context);
                        },
                        child: const Padding(
                          padding: EdgeInsets.only(left: 3),
                          child: Row(
                            children: [
                              Text(
                                'Add Course',
                                style: TextStyle(
                                  fontSize: 20,
                                  color: Colors.black,
                                  fontWeight: FontWeight.bold,
                                ),
                              ),
                              Icon(
                                Icons.add_outlined,
                                size: 22,
                                color: Colors.black,
                              )
                            ],
                          ),
                        ),
                      ),
                    ),
                  ),
                ],
              ),
              Container(
                alignment: Alignment.topLeft,
                margin: const EdgeInsets.only(left: 12),
                child: const Text(
                  'Spring semester 1403',
                  style: TextStyle(
                    color: Colors.black,
                    fontSize: 16,
                  ),
                ),
              ),
            ],
          ),
          Padding(
            padding: const EdgeInsets.only(top: 100),
            child: Stack(
              children: [
                Padding(
                  padding: const EdgeInsets.only(bottom: 80),
                  child: Container(child: buildListView()),
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}

