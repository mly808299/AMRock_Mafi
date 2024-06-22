import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:mafi2/Pages/Assinment_page.dart';
import 'Profile_page.dart';

import 'Tasks.dart';
import 'list_model.dart';

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  int currentIndex = 2;
  List<Practices> listWorks = [];

  @override
  Widget build(BuildContext context) {
    listWorks = Practices.getPeractices();
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.yellow,
        title: const Text(
          "Home page",
          style: TextStyle(
            fontWeight: FontWeight.bold,
            color: Colors.black,
          ),
        ),
        centerTitle: true,
        leading: IconButton(
          iconSize: 30,
          icon: const Icon(Icons.account_circle_outlined),
          onPressed: () {
            Navigator.push(
              context,
              MaterialPageRoute(builder: (context) => const ProfilePage()),
            );
          },
        ),
      ),
      body: buildbody(),
      bottomNavigationBar: buildBottomNavigationBar(),
    );
  }

  Column buildbody() {
    return Column(
      mainAxisAlignment: MainAxisAlignment.start,
      children: [
        const SizedBox(height: 10),
        Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            const Padding(
              padding: EdgeInsets.only(left: 20),
              child: Text(
                "Summery",
                style: TextStyle(
                    color: Colors.black,
                    fontWeight: FontWeight.bold,
                    fontSize: 18),
              ),
            ),
            SizedBox(
                height: 80,
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.spaceAround,
                  children: [
                    Container(
                      width: 115,
                      height: 68,
                      decoration: BoxDecoration(
                        color: const Color(0xFFFFE178),
                        shape: BoxShape.rectangle,
                        borderRadius: BorderRadius.circular(10),
                      ),
                      child: Column(
                        mainAxisAlignment: MainAxisAlignment.spaceAround,
                        children: [
                          SvgPicture.asset(
                            'Icons/homework.svg',
                            height: 25,
                            width: 25,
                          ),
                          const Text(
                            "practises",
                            style: TextStyle(fontSize: 11),
                          )
                        ],
                      ),
                    ),
                    Container(
                      width: 115,
                      height: 68,
                      decoration: BoxDecoration(
                        color: const Color(0xFFFFE178),
                        shape: BoxShape.rectangle,
                        borderRadius: BorderRadius.circular(10),
                      ),
                      child: Column(
                        mainAxisAlignment: MainAxisAlignment.spaceAround,
                        children: [
                          SvgPicture.asset(
                            'Icons/exam-a-plus-svgrepo-com.svg',
                            height: 25,
                            width: 25,
                          ),
                          const Text(
                            "exam",
                            style: TextStyle(fontSize: 11),
                          )
                        ],
                      ),
                    ),
                    Container(
                      width: 115,
                      height: 68,
                      decoration: BoxDecoration(
                        color: const Color(0xFFFFE178),
                        shape: BoxShape.rectangle,
                        borderRadius: BorderRadius.circular(10),
                      ),
                      child: Column(
                        mainAxisAlignment: MainAxisAlignment.spaceAround,
                        children: [
                          SvgPicture.asset(
                            'Icons/deadline-svgrepo-com.svg',
                            height: 25,
                            width: 25,
                          ),
                          const Text(
                            "finished deadline",
                            style: TextStyle(fontSize: 11),
                          )
                        ],
                      ),
                    )
                  ],
                )),
            SizedBox(
                height: 80,
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.start,
                  children: [
                    Container(
                      margin: const EdgeInsets.only(left: 8, right: 15),
                      width: 115,
                      height: 68,
                      decoration: BoxDecoration(
                        color: const Color(0xFFFFE178),
                        shape: BoxShape.rectangle,
                        borderRadius: BorderRadius.circular(10),
                      ),
                      child: Column(
                        mainAxisAlignment: MainAxisAlignment.spaceAround,
                        children: [
                          SvgPicture.asset(
                            'Icons/award-svgrepo-com.svg',
                            height: 30,
                            width: 30,
                          ),
                          const Text(
                            "first score",
                            style: TextStyle(fontSize: 14),
                          )
                        ],
                      ),
                    ),
                    Container(
                      width: 115,
                      height: 68,
                      decoration: BoxDecoration(
                        color: const Color(0xFFFFE178),
                        shape: BoxShape.rectangle,
                        borderRadius: BorderRadius.circular(10),
                      ),
                      child: Column(
                        mainAxisAlignment: MainAxisAlignment.spaceAround,
                        children: [
                          SvgPicture.asset(
                            'Icons/cry-emoji-emoticon-svgrepo-com.svg',
                            height: 25,
                            width: 25,
                          ),
                          const Text(
                            "last score",
                            style: TextStyle(fontSize: 14),
                          )
                        ],
                      ),
                    ),
                  ],
                )),
            const SizedBox(
              height: 45,
            ),
            const Padding(
              padding: EdgeInsets.only(left: 20),
              child: Text(
                "Current works",
                style: TextStyle(
                    color: Colors.black,
                    fontWeight: FontWeight.bold,
                    fontSize: 18),
              ),
            ),
            SizedBox(
              height: 10,
            ),
            Container(
              decoration: BoxDecoration(
                shape: BoxShape.rectangle,
                borderRadius: BorderRadius.circular(10),
              ),
              height: 150,
              child: ListView.separated(
                  itemCount: listWorks.length,
                  itemBuilder: (context, index) {
                    return Container(
                      margin: const EdgeInsets.only(left: 20, right: 20),
                      height: 50,
                      width: 50,
                      decoration: BoxDecoration(
                        color: listWorks[index].colorBox.withOpacity(0.8),
                        borderRadius: BorderRadius.circular(10),
                      ),
                      child: Row(
                        mainAxisAlignment: MainAxisAlignment.start,
                        children: [
                          const Padding(padding: EdgeInsets.only(left: 10)),
                          Text(
                            listWorks[index].practiceName,
                            style: const TextStyle(
                                fontSize: 15, fontWeight: FontWeight.bold),
                          ),
                          const Padding(padding: EdgeInsets.only(left: 190)),
                          SvgPicture.asset(
                            'Icons/task_alt_FILL0_wght400_GRAD0_opsz24 1.svg',
                            height: 30,
                            width: 30,
                          ),
                          SvgPicture.asset(
                            'Icons/cancel_FILL0_wght400_GRAD0_opsz24 1.svg',
                            height: 30,
                            width: 30,
                          ),
                        ],
                      ),
                    );
                  },
                  separatorBuilder: (context, index) => const SizedBox(
                        height: 25,
                      )),
            ),
            const SizedBox(
              height: 20,
            ),
            const Padding(
              padding: EdgeInsets.only(left: 20),
              child: Text(
                "Practises That have done",
                style: TextStyle(
                    color: Colors.black,
                    fontWeight: FontWeight.bold,
                    fontSize: 18),
              ),
            ),
            const SizedBox(
              height: 30,
            ),
            Container(
              decoration: BoxDecoration(
                shape: BoxShape.rectangle,
                borderRadius: BorderRadius.circular(10),
              ),
              height: 120,
              child: ListView.separated(
                  itemCount: listWorks.length,
                  scrollDirection: Axis.horizontal,
                  itemBuilder: (context, index) {
                    return Container(
                      margin: const EdgeInsets.only(left: 20, right: 20),
                      height: 50,
                      width: 100,
                      decoration: BoxDecoration(
                        color: listWorks[index].colorBox.withOpacity(0.8),
                        borderRadius: BorderRadius.circular(10),
                      ),
                      child: Column(
                        children: [
                          SvgPicture.asset(
                            'Icons/check_circle_FILL1_wght400_GRAD0_opsz24 1.svg',
                            height: 30,
                            width: 30,
                          ),
                          Container(
                            margin: EdgeInsets.only(top: 30),
                            child: Text(
                              listWorks[index].practiceName,
                              style: const TextStyle(
                                  fontSize: 12, fontWeight: FontWeight.bold),
                            ),
                          )
                        ],
                      ),
                    );
                  },
                  separatorBuilder: (context, index) => const SizedBox(
                        height: 25,
                      )),
            ),
          ],
        ),
      ],
    );
  }

  BottomNavigationBar buildBottomNavigationBar() {
    return BottomNavigationBar(
      unselectedItemColor: Colors.black87,
      selectedItemColor: Colors.green,
      showUnselectedLabels: true,
      currentIndex: currentIndex,
      items: [
        BottomNavigationBarItem(
          label: 'News',
          icon: SvgPicture.asset(
            'Icons/announcement.svg',
            height: 25,
            width: 25,
          ),
          backgroundColor: Colors.yellow,
        ),
        BottomNavigationBarItem(
            label: 'Practices',
            icon: SvgPicture.asset(
              'Icons/homework.svg',
              height: 25,
              width: 25,
            ),
            backgroundColor: Colors.yellow),
        BottomNavigationBarItem(
          icon: SvgPicture.asset(
            'Icons/website-home-page-svgrepo-com.svg',
            height: 25,
            width: 25,
          ),
          label: 'Home',
          backgroundColor: Colors.yellow,
        ),
        BottomNavigationBarItem(
          icon: SvgPicture.asset(
            'Icons/class.svg',
            height: 25,
            width: 25,
          ),
          label: 'Classes',
          backgroundColor: Colors.yellow,
        ),
        BottomNavigationBarItem(
          label: 'Tasks',
          icon: SvgPicture.asset(
            'Icons/work-order-type-svgrepo-com.svg',
            height: 25,
            width: 25,
          ),
          backgroundColor: Colors.yellow,
        )
      ],
      onTap: (index) {
        setState(() {
          currentIndex = index;
          switch (index) {
            case 0:
              Navigator.pushReplacement(
                  context, MaterialPageRoute(builder: (context) => Scaffold()));
              break;
            case 1:
              Navigator.push(
                  context,
                  MaterialPageRoute(
                      builder: (context) => const AssignmentPage()));
              break;
            case 2:
              Navigator.pushReplacement(
                  context, MaterialPageRoute(builder: (context) => HomePage()));
              break;
            case 3:
              Navigator.pushReplacement(
                  context, MaterialPageRoute(builder: (context) => Scaffold()));
              break;
            case 4:
              Navigator.pushReplacement(
                  context, MaterialPageRoute(builder: (context) => const TasksPage()));
              break;
          }
        });
      },
    );
  }
}
