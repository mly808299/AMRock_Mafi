import 'package:flutter/material.dart';
import 'package:mafi2/components/my_button.dart';
import '../components/classBox.dart';
import 'news_pages.dart';

class CoursePage extends StatefulWidget {
  const CoursePage({super.key});

  @override
  State<CoursePage> createState() => _CoursePageState();
}

class _CoursePageState extends State<CoursePage> {
  void _showAddCourse(context) {
    showModalBottomSheet(
        context: context,
        builder: (BuildContext bc) {
          final formKey = GlobalKey<FormState>();
          final codeController = TextEditingController();
          return SingleChildScrollView(
            child: Container(
              decoration: const BoxDecoration(
                  color: Colors.yellow,
                  borderRadius: BorderRadius.only(
                    topLeft: Radius.circular(25),
                    topRight: Radius.circular(25),
                  )),
              height: MediaQuery.of(context).size.height * .45,
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
                    child: Form(
                      key: formKey,
                      child: Padding(
                          padding: const EdgeInsets.symmetric(
                            horizontal: 20.0,
                          ),
                          child: TextFormField(
                            controller: codeController,
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
                            validator: (value) => value!.isEmpty
                                ? 'Course code can\'t be empty !!!'
                                : null,
                            autovalidateMode:
                                AutovalidateMode.onUserInteraction,
                          )),
                    ),
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
                          bool key1 = formKey.currentState!.validate();
                          if (key1) {
                            Navigator.pop(context);
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
                Padding(
                  padding: const EdgeInsets.only(top: 500),
                  child: Stack(
                    children: [
                      IconButton(
                        onPressed: () {
                          Navigator.push(
                            context,
                            MaterialPageRoute(
                              builder: (context) => const SecondRoute(),
                            ),
                          );
                        },
                        icon: const Icon(
                          Icons.calendar_month_outlined,
                          size: 30,
                        ),
                      ),
                    ],
                  ),
                ),
                Align(
                  alignment: Alignment.bottomCenter,
                  child: Container(
                    height: 80,
                    color: Colors.cyanAccent,
                    child: Row(
                      children: [
                        const SizedBox(
                          width: 5,
                        ),
                        Column(
                          children: [
                            IconButton(
                                onPressed: () {
                                  Navigator.push(
                                    context,
                                    MaterialPageRoute(
                                      builder: (context) => const SecondRoute(),
                                    ),
                                  );
                                },
                                icon: const Icon(Icons.edit_note_sharp)),
                            const Text(
                              'Assignments',
                              style: TextStyle(fontWeight: FontWeight.bold),
                            ),
                          ],
                        ),
                        const SizedBox(
                          width: 25,
                        ),
                        Column(
                          children: [
                            IconButton(
                                onPressed: () {
                                  Navigator.push(
                                    context,
                                    MaterialPageRoute(
                                      builder: (context) => const News(),
                                    ),
                                  );
                                },
                                icon: const Icon(Icons.newspaper)),
                            const Text(
                              'News',
                              style: TextStyle(fontWeight: FontWeight.bold),
                            ),
                          ],
                        ),
                        const SizedBox(width: 25),
                        Column(
                          children: [
                            IconButton(
                                onPressed: () {},
                                icon: const Icon(Icons.school_rounded)),
                            const Text(
                              'Courses',
                              style: TextStyle(fontWeight: FontWeight.bold),
                            ),
                          ],
                        ),
                        const SizedBox(
                          width: 25,
                        ),
                        Column(
                          children: [
                            IconButton(
                                onPressed: () {
                                  Navigator.push(
                                    context,
                                    MaterialPageRoute(
                                      builder: (context) => const SecondRoute(),
                                    ),
                                  );
                                },
                                icon: const Icon(Icons.work)),
                            const Text(
                              'Jobs',
                              style: TextStyle(fontWeight: FontWeight.bold),
                            ),
                          ],
                        ),
                        const SizedBox(
                          width: 25,
                        ),
                        Column(
                          children: [
                            IconButton(
                                onPressed: () {
                                  Navigator.push(
                                    context,
                                    MaterialPageRoute(
                                      builder: (context) => const SecondRoute(),
                                    ),
                                  );
                                },
                                icon: const Icon(Icons.home_outlined)),
                            const Text(
                              'Sera',
                              style: TextStyle(fontWeight: FontWeight.bold),
                            ),
                          ],
                        ),
                      ],
                    ),
                  ),
                ),
              ],
            ),
          ),
        ],
      ),

      floatingActionButton: Padding(
        padding: const EdgeInsets.only(left:25,bottom: 75),
        child: Row(
          crossAxisAlignment: CrossAxisAlignment.end,
          children: [
            FloatingActionButton(
              onPressed: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (context) => const SecondRoute(),
                  ),
                );
              },
              backgroundColor: Colors.yellow[600],
              child: const Icon(
                Icons.calendar_month_outlined,
                size: 30,
              ),
            ),
          ],
        ),
      ),
    );
  }
}
