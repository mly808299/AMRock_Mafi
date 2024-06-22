import 'package:flutter/material.dart';
import '../Pages/news_pages.dart';
import '../components/classBox.dart';
import '../components/my_button.dart';

class News2 extends StatelessWidget {
  const News2({super.key});

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
                      'News',
                      style: TextStyle(
                        color: Colors.black,
                        fontSize: 25,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                  ),
                  const Padding(
                    padding: EdgeInsets.only(left: 290, top: 20),
                    child: Icon(
                      Icons.newspaper,
                      size: 30,
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
              SizedBox(
                  height: 80,
                  child: Padding(
                    padding: const EdgeInsets.all(5.0),
                    child: buildListView2(),
                  )),
              const Padding(
                padding: EdgeInsets.only(left: 5.0,bottom: 5),
                child: Text("---------------------------- Today( 19 khordad ) ----------------------------"),
              ),
            ],
          ),
          Padding(
            padding: const EdgeInsets.only(top: 190),
            child: Stack(
              children: [
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
    );
  }
}
