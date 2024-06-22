import 'dart:ffi';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:flutter_svg/svg.dart';
import 'package:numberpicker/numberpicker.dart';

import '../components/methods.dart';
import 'Assignment.dart';
import 'Assinment_page.dart';
import 'home_page.dart';
import 'list_model.dart';

class TasksPage extends StatefulWidget {
  const TasksPage({super.key});

  @override
  State<TasksPage> createState() => _TasksPageState();
}

class _TasksPageState extends State<TasksPage> {
  int currentIndex = 4;
  var hour = 8;
  var minute = 0;
  var timeFormat = 'AM';
  List<Practices> listWorks = Practices.getPeractices();
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      resizeToAvoidBottomInset: false,
      appBar: AppBar(
        backgroundColor: Colors.yellow,
        title: const Text(
          "Tasks Page",
          style: TextStyle(
            fontWeight: FontWeight.bold,
            color: Colors.black,
          ),
        ),
        centerTitle: true,
      ),
      body: bodyBuilder(),
      floatingActionButton: FloatingActionButton(
          onPressed: () {
            showModalBottomSheet(
                isScrollControlled: true,
                context: context,
                isDismissible: true,
                backgroundColor: Color(0xFFFFF1BD),
                shape: const RoundedRectangleBorder(
                    borderRadius: BorderRadius.only(
                        topLeft: Radius.circular(30),
                        topRight: Radius.circular(30))),
                builder: (context) => AnimatedContainer(
                      duration: const Duration(seconds: 1),
                      curve: Curves.fastOutSlowIn,
                      height: 500,
                      child: Container(
                        height: 300,
                        child: Column(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: [
                            const SizedBox(
                              height: 30,
                            ),
                            Row(
                              children: [
                                SizedBox(
                                  width: 20,
                                ),
                                SvgPicture.asset(
                                  'Icons/add-task-list-svgrepo-com.svg',
                                  height: 20,
                                  width: 20,
                                ),
                                const Padding(
                                  padding: EdgeInsets.only(left: 10),
                                  child: Text(
                                    "Add new task",
                                    style: TextStyle(
                                        color: Colors.black,
                                        fontWeight: FontWeight.bold,
                                        fontSize: 10),
                                  ),
                                ),
                              ],
                            ),
                            SizedBox(
                              height: 20,
                            ),
                            const Padding(
                              padding: EdgeInsets.only(left: 25),
                              child: Text(
                                "Title :",
                                style: TextStyle(
                                    color: Colors.black,
                                    fontWeight: FontWeight.bold,
                                    fontSize: 14),
                              ),
                            ),
                            Row(
                              children: [
                                Expanded(
                                  child: SingleChildScrollView(
                                    child: Container(
                                      margin: const EdgeInsets.only(
                                          left: 20, right: 20),
                                      decoration: BoxDecoration(
                                        color: Colors.white,
                                        borderRadius: BorderRadius.circular(15),
                                        boxShadow: [
                                          BoxShadow(
                                            color: Colors.grey.withOpacity(0.5),
                                            spreadRadius: 2,
                                            blurRadius: 3,
                                            offset: const Offset(0, 2),
                                          ),
                                        ],
                                      ),
                                      child: Card(
                                        child: Container(
                                          height: 50,
                                          child: TextFormField(
                                            textAlign: TextAlign.start,
                                            maxLines: null,
                                            minLines: null,
                                            style:
                                                const TextStyle(fontSize: 11),
                                            decoration: const InputDecoration(
                                              hintText: 'add new task',
                                              contentPadding:
                                                  EdgeInsets.all(15),
                                              border: InputBorder.none,
                                            ),
                                          ),
                                        ),
                                      ),
                                    ),
                                  ),
                                ),
                              ],
                            ),
                            const SizedBox(
                              height: 20,
                            ),
                            const Padding(
                              padding: EdgeInsets.only(left: 25),
                              child: Text(
                                "date :",
                                style: TextStyle(
                                    color: Colors.black,
                                    fontWeight: FontWeight.bold,
                                    fontSize: 12),
                              ),
                            ),
                            Row(
                              children: [
                                const SizedBox(
                                  width: 20,
                                ),
                                const Icon(Icons.timer_sharp),
                                const SizedBox(
                                  width: 10,
                                ),
                                Container(
                                  height: 30,
                                  width: 70,
                                  decoration: BoxDecoration(
                                      color: Colors.orangeAccent,
                                      borderRadius: BorderRadius.circular(10)),
                                  child: Center(
                                    child: Text(
                                      currentDate,
                                      style: const TextStyle(fontSize: 12),
                                    ),
                                  ),
                                ),
                                const Spacer(),
                                const Padding(
                                    padding: EdgeInsets.only(right: 20),
                                    child: Icon(Icons.adf_scanner)),
                              ],
                            ),
                            const SizedBox(
                              height: 20,
                            ),
                            Container(
                              margin: EdgeInsets.only(left: 10, right: 10),
                              padding: const EdgeInsets.symmetric(
                                  horizontal: 10, vertical: 10),
                              decoration: BoxDecoration(
                                  color: Colors.lightBlueAccent,
                                  borderRadius: BorderRadius.circular(10)),
                              child: Row(
                                mainAxisAlignment:
                                    MainAxisAlignment.spaceEvenly,
                                children: [
                                  TimeClock(
                                    time: hour,
                                    startValue: 0,
                                    endValue: 12,
                                  ),
                                  TimeClock(
                                    time: minute,
                                    startValue: 0,
                                    endValue: 60,
                                  ),
                                  TimeFormat(timeFormat: timeFormat),

                                ],
                              ),
                            ),
                            SizedBox(height: 30,),
                            Center(
                              child: GestureDetector(
                                onTap: (){
                                  Navigator.pushReplacement(context,
                                      MaterialPageRoute(builder: (context) => const TasksPage()));
                                },
                                child: Container(
                                  height: 60,
                                  width: 300,
                                  margin: const EdgeInsets.only(),
                                  decoration: BoxDecoration(
                                      borderRadius: BorderRadius.circular(20),
                                      shape: BoxShape.rectangle,
                                      color: Colors.green
                                  ),
                                  child: const Center(
                                    child: Text(
                                      'add',
                                      style: TextStyle(
                                        fontSize:22 ,
                                        fontWeight: FontWeight.bold,
                                        color: Colors.black,
                                        decoration: TextDecoration.none,
                                      ),
                                    ),
                                  ),
                                ),
                              ),
                            ),

                          ],
                        ),
                      ),
                    ));
          },
          backgroundColor: Color(0xFFA8E737),
          child: SvgPicture.asset(
            'Icons/add-task-list-svgrepo-com.svg',
            height: 40,
            width: 40,
          )),
      bottomNavigationBar: buildBottomNavigationBar(),
    );
  }

  Column bodyBuilder() {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Center(
          child: Padding(
              padding: EdgeInsets.all(10),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  GestureDetector(
                    onTap: () {
                      setState(() {
                        selectDate(context, (context) => const TasksPage());
                      });
                    },
                    child: Container(
                      height: 40,
                      decoration: BoxDecoration(
                          color: Colors.orangeAccent,
                          borderRadius: BorderRadius.circular(10)),
                      width: 40,
                      child: const Icon(
                        Icons.date_range,
                        size: 30,
                      ),
                    ),
                  ),
                  Container(
                    height: 40,
                    width: 100,
                    decoration: BoxDecoration(
                        color: Colors.orangeAccent,
                        borderRadius: BorderRadius.circular(10)),
                    child: Center(
                      child: Text(currentDate),
                    ),
                  )
                ],
              )),
        ),
        const Padding(
          padding: EdgeInsets.only(left: 20),
          child: Text(
            "Current Tasks : ",
            style: TextStyle(
                color: Colors.black, fontWeight: FontWeight.bold, fontSize: 18),
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
          height: 220,
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
        const Padding(
          padding: EdgeInsets.only(left: 20),
          child: Text(
            "Done Tasks : ",
            style: TextStyle(
                color: Colors.black, fontWeight: FontWeight.bold, fontSize: 18),
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
                      const Padding(padding: EdgeInsets.only(left: 220)),
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
              Navigator.pushReplacement(context,
                  MaterialPageRoute(builder: (context) => const TasksPage()));
              break;
          }
        });
      },
    );
  }
}

class TimeClock extends StatefulWidget {
  var time = 0;
  var startValue;
  var endValue;

  TimeClock(
      {super.key,
      required this.time,
      required this.startValue,
      required this.endValue});

  @override
  State<TimeClock> createState() => _TimeClockState(time, startValue, endValue);
}

class _TimeClockState extends State<TimeClock> {
  var time = 0;
  var startValue;
  var endValue;

  @override
  Widget build(BuildContext context) {
    return NumberPicker(
      minValue: startValue,
      maxValue: endValue,
      value: time,
      zeroPad: true,
      infiniteLoop: true,
      itemWidth: 40,
      itemHeight: 40,
      textStyle: const TextStyle(color: Colors.white, fontSize: 20),
      selectedTextStyle: const TextStyle(color: Colors.white, fontSize: 20),
      onChanged: (value) {
        setState(() {
          time = value;
        });
      },
      decoration: const BoxDecoration(
          border: Border(
              top: BorderSide(
                color: CupertinoColors.white,
              ),
              bottom: BorderSide(color: Colors.white54))),
    );
  }

  _TimeClockState(this.time, this.startValue, this.endValue);
}
class TimeFormat extends StatefulWidget {
  var timeFormat = 'AM';
  TimeFormat({super.key , required this.timeFormat});

  @override
  State<TimeFormat> createState() => _TimeFormatState(timeFormat);
}

class _TimeFormatState extends State<TimeFormat> {
  var timeFormat = 'AM';
  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        GestureDetector(
          onTap: () {
            setState(() {
              timeFormat = 'AM';
            });
          },
          child: Container(
            padding: const EdgeInsets.symmetric(
                horizontal: 20, vertical: 1),
            decoration: BoxDecoration(
                color: timeFormat != 'AM'
                    ? Colors.purple
                    : Colors.pinkAccent,
                border: Border.all(
                    color:
                    CupertinoColors.white)),
            child: const Text("AM",
                style: TextStyle(
                    color: Colors.white,
                    fontSize: 25)),
          ),
        ),
        const SizedBox(
          height: 10,
        ),
        GestureDetector(
          onTap: () {
            setState(() {
              timeFormat = 'PM';
            });
          },
          child: Container(
            padding: const EdgeInsets.symmetric(
                horizontal: 20, vertical: 1),
            decoration: BoxDecoration(
                color: timeFormat != 'PM'
                    ? Colors.purple
                    : Colors.pinkAccent,
                border: Border.all(
                    color:
                    CupertinoColors.white)),
            child: const Text("PM",
                style: TextStyle(
                    color: Colors.white,
                    fontSize: 25)),
          ),
        ),
      ],
    );
  }

  _TimeFormatState(this.timeFormat);
}

