import 'dart:math';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/painting.dart';
import 'package:flutter/widgets.dart';
import 'package:flutter_svg/svg.dart';
import 'package:mafi2/components/methods.dart';

import 'Tasks.dart';
import 'home_page.dart';
import 'list_model.dart';

class AssignmentPage extends StatefulWidget {
  const AssignmentPage({super.key});

  @override
  State<AssignmentPage> createState() => _AssignmentPageState();
}

class _AssignmentPageState extends State<AssignmentPage> {
  int currentIndex = 1;
  int numberOfClickListBottom = 0;
  List<Practices> listWorks = Practices.getPeractices();
  OverlayEntry? entry;
  TextEditingController textController = TextEditingController();
  String describe = '';
  void hideOverlay() {
    entry!.remove();
    entry = null;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      resizeToAvoidBottomInset: false,
      appBar: AppBar(
        backgroundColor: Colors.yellow,
        title: const Text(
          "Assignments Page",
          style: TextStyle(
            fontWeight: FontWeight.bold,
            color: Colors.black,
          ),
        ),
        centerTitle: true,
      ),
      body: bodyBuilder(),
      bottomNavigationBar: buildBottomNavigationBar(),
    );
  }

  Column bodyBuilder() {
    return Column(
      children: [
        Center(
          child: Padding(
              padding: EdgeInsets.all(10),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  GestureDetector(
                    onTap: () {
                      if(entry != null){
                        hideOverlay();
                        numberOfClickListBottom = 0;
                      }
                      selectDate(context , (context) => const AssignmentPage());
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
        const SizedBox(
          height: 30,
        ),
        Container(
          decoration: BoxDecoration(
            shape: BoxShape.rectangle,
            borderRadius: BorderRadius.circular(10),
          ),
          height: 525,
          child: ListView.separated(
              itemCount: listWorks.length,
              itemBuilder: (context, index) {
                return GestureDetector(
                  onTap: () {
                    if (numberOfClickListBottom != 0) {
                      hideOverlay();
                      showOverlay();
                    } else {
                      showOverlay();
                      numberOfClickListBottom++;
                    }
                  },
                  child: Container(
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
                        Text('deadLine')
                      ],
                    ),
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


  void showOverlay() {
    entry = OverlayEntry(
        builder: (context) => Positioned(
          top: 150,
          left: 26,
          child: Container(
            width: 340,
            height: 352,
            decoration: BoxDecoration(
              color: Color(0xFFD0FFD5),
              borderRadius: BorderRadius.circular(10),
            ),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              mainAxisAlignment: MainAxisAlignment.start,
              children: [
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    SizedBox(
                      height: 30,
                      width: 30,
                      child: GestureDetector(
                        onTap: () {
                          hideOverlay();
                          numberOfClickListBottom = 0;
                        },
                        child:
                            SvgPicture.asset('Icons/fail-svgrepo-com.svg'),
                      ),
                    ),
                    const Text(
                      'detail of assignment',
                      style: TextStyle(
                          fontSize: 13,
                          color: Colors.black,
                          fontWeight: FontWeight.bold,
                          decoration: TextDecoration.none),
                    ),
                    Container(
                        margin: const EdgeInsets.only(right: 5),
                        child: const Icon(Icons.announcement)),
                  ],
                ),
                const SizedBox(
                  height: 10,
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.start,
                  children: [
                    const Padding(
                      padding: EdgeInsets.only(left: 8),
                      child: Text(
                        'Title: ',
                        style: TextStyle(
                            fontSize: 9,
                            color: Colors.black,
                            fontWeight: FontWeight.bold,
                            decoration: TextDecoration.none),
                      ),
                    ),
                    Container(
                      height: 30,
                      width: 100,
                      decoration: BoxDecoration(
                        color: Colors.white70,
                        borderRadius: BorderRadius.circular(10),
                      ),
                      child: const Center(
                        child: Text(
                          "title",
                          style: TextStyle(
                              fontSize: 9,
                              color: Colors.black,
                              fontWeight: FontWeight.bold,
                              decoration: TextDecoration.none),
                        ),
                      ),
                    ),
                    const Padding(
                      padding: EdgeInsets.only(left: 33),
                      child: Text(
                        'Deadline: ',
                        style: TextStyle(
                            fontSize: 9,
                            color: Colors.black,
                            fontWeight: FontWeight.bold,
                            decoration: TextDecoration.none),
                      ),
                    ),
                    Container(
                      height: 30,
                      width: 100,
                      decoration: BoxDecoration(
                        color: Colors.white70,
                        borderRadius: BorderRadius.circular(10),
                      ),
                      child: const Center(
                        child: Text(
                          "deadline",
                          style: TextStyle(
                              fontSize: 9,
                              color: Colors.black,
                              fontWeight: FontWeight.bold,
                              decoration: TextDecoration.none),
                        ),
                      ),
                    ),
                  ],
                ),
                const SizedBox(
                  height: 10,
                ),
                Row(
                  children: [
                    const Padding(
                      padding: EdgeInsets.only(left: 8),
                      child: Text(
                        'Estimated time remaining: ',
                        style: TextStyle(
                            fontSize: 9,
                            color: Colors.black,
                            fontWeight: FontWeight.bold,
                            decoration: TextDecoration.none),
                      ),
                    ),
                    Container(
                      height: 30,
                      width: 175,
                      decoration: BoxDecoration(
                        color: Colors.white70,
                        borderRadius: BorderRadius.circular(10),
                      ),
                      child: const Center(
                        child: Text(
                          "estimated time remaining",
                          style: TextStyle(
                              fontSize: 9,
                              color: Colors.black,
                              fontWeight: FontWeight.bold,
                              decoration: TextDecoration.none),
                        ),
                      ),
                    ),
                  ],
                ),
                const SizedBox(
                  height: 10,
                ),
                const Padding(
                  padding: EdgeInsets.only(left: 8),
                  child: Text(
                    'Description :',
                    style: TextStyle(
                        fontSize: 9,
                        color: Colors.black,
                        fontWeight: FontWeight.bold,
                        decoration: TextDecoration.none),
                  ),
                ),
                const SizedBox(
                  height: 10,
                ),
                Row(
                  children: [
                    Expanded(
                      child: SingleChildScrollView(
                        child: Container(
                          margin: const EdgeInsets.only(left: 20, right: 20),
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
                              height: 80,
                              child: TextFormField(
                                controller: textController,
                                textAlign: TextAlign.start,
                                maxLines: null,
                                minLines: null,
                                style: const TextStyle(fontSize: 9),
                                decoration: const InputDecoration(
                                  hintText: 'Please enter description',
                                  contentPadding: EdgeInsets.all(15),
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
                  height: 10,
                ),
                Row(
                  children: [
                    const Padding(
                      padding: EdgeInsets.only(left: 8),
                      child: Text(
                        'Score :',
                        style: TextStyle(
                            fontSize: 9,
                            color: Colors.black,
                            fontWeight: FontWeight.bold,
                            decoration: TextDecoration.none),
                      ),
                    ),
                    Container(
                      height: 30,
                      width: 100,
                      decoration: BoxDecoration(
                        color: Colors.white70,
                        borderRadius: BorderRadius.circular(10),
                      ),
                      child: const Center(
                        child: Text(
                          "not set yet",
                          style: TextStyle(
                              fontSize: 9,
                              color: Colors.black,
                              fontWeight: FontWeight.bold,
                              decoration: TextDecoration.none),
                        ),
                      ),
                    ),
                  ],
                ),
                const SizedBox(
                  height: 10,
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceAround,
                  children: [
                    const Padding(
                      padding: EdgeInsets.only(left: 8),
                      child: Text(
                        'upload assignment :',
                        style: TextStyle(
                            fontSize: 9,
                            color: Colors.black,
                            fontWeight: FontWeight.bold,
                            decoration: TextDecoration.none),
                      ),
                    ),
                    Expanded(
                      child: SingleChildScrollView(
                        child: Card(
                              margin: EdgeInsets.only(left : 5),
                            child: Container(
                              height: 30,
                              child: Center(
                                child: TextFormField(
                                  textAlign: TextAlign.start,
                                  maxLines: null,
                                  minLines: null,
                                  style: const TextStyle(fontSize: 9),
                                  decoration: const InputDecoration(
                                    hintStyle: TextStyle(fontSize: 9),
                                    hintText: 'Delivery details',
                                    contentPadding: EdgeInsets.only(bottom: 17  , left: 10 , right: 10),
                                    border: InputBorder.none,
                                  ),
                                ),
                              ),
                            ),
                          ),
                        ),
                      ),
                    const Padding(
                      padding: EdgeInsets.only(right: 5),
                      child: Icon(Icons.upload_outlined),
                    )
                  ],
                ),
              const Spacer(),
              Center(
                child: GestureDetector(
                  onTap: (){
                    if(entry != null){
                      hideOverlay();
                    }
                    numberOfClickListBottom = 0;
                    customToast('assignment uploaded successfully', context, CupertinoColors.activeGreen);
                  },
                  child: Container(
                    height: 20,
                    width: 150,
                    margin: const EdgeInsets.only(bottom: 5),
                    decoration: BoxDecoration(
                      borderRadius: BorderRadius.circular(20),
                      shape: BoxShape.rectangle,
                      color: Colors.green
                    ),
                    child: const Center(
                      child: Text(
                        'record',
                        style: TextStyle(
                          fontSize:9 ,
                          fontWeight: FontWeight.bold,
                          color: Colors.black,
                            decoration: TextDecoration.none,
                        ),
                      ),
                    ),
                  ),
                ),
              )
              ],
            ),
          ),
        ));
    final overlay = Overlay.of(context);
    overlay.insert(entry!);
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
          if(entry != null){
          hideOverlay();
          }
          currentIndex = index;
          switch (index) {
            case 0:
              Navigator.pushReplacement(
                  context, MaterialPageRoute(builder: (context) => Scaffold()));
              break;
            case 1:
              Navigator.push(context,
                  MaterialPageRoute(builder: (context) => const AssignmentPage()));
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
// onTap: (){
// showModalBottomSheet(context: context,
// isDismissible : true,
// backgroundColor: Colors.blue,
// shape: RoundedRectangleBorder(
// borderRadius: BorderRadius.only(topLeft:Radius.circular(10), topRight: Radius.circular(10))
// ),
// builder: (context)=> AnimatedContainer(
// duration: Duration(seconds: 1),
// curve: Curves.fastOutSlowIn,
// height: 250,
//
// child: Container(
// height: 200,
// ),
// ) );
// },
