import 'package:flutter/material.dart';
import 'package:url_launcher/url_launcher.dart';

import '../Pages/news_pages.dart';
import 'news2.dart';

class CourseBox extends StatefulWidget {
  const CourseBox({super.key});

  @override
  State<CourseBox> createState() => _CourseBoxState();
}

class _CourseBoxState extends State<CourseBox> {
  @override
  Widget build(BuildContext context) {
    return const Placeholder();
  }
}

Widget buildListView() => ListView.builder(
      physics: const BouncingScrollPhysics(),
      itemCount: 10, //TODO
      itemBuilder: (context, index) {
        List<Color> colors = const [
          Colors.deepPurpleAccent,
          Color(0XFFDB98D4),
          Color(0XFF9B59B6),
          Color(0XFF3498DB),
          Color(0XFFE74C3C),
          Colors.deepOrange,
          Color(0XFF1ABC9C),
          Color(0XFF2ECC71),
          Color(0XFF95A5A6),
          Color(0XFF34495E),
        ];
        return SizedBox(
          width: double.infinity,
          height: 186,
          child: Card(
            color: colors[index],
            margin:
                const EdgeInsets.only(top: 10, left: 20, right: 20, bottom: 10),
            shadowColor: Colors.black,
            shape: const RoundedRectangleBorder(
                borderRadius: BorderRadius.all(Radius.circular(25))),
            child: Padding(
              padding: const EdgeInsets.all(16),
              child: Column(
                children: [
                  const Row(
                    children: [
                      Padding(
                        padding: EdgeInsets.only(right: 8.0),
                        child: Icon(
                          Icons.school_rounded,
                          color: Colors.yellow,
                        ),
                      ),
                      Padding(
                        padding: EdgeInsets.only(right: 20),
                        child: Text(
                          //TODO
                          'Advanced Programing',
                          style: TextStyle(
                              fontSize: 16,
                              fontWeight: FontWeight.bold,
                              color: Colors.black),
                        ),
                      ),
                      Text(
                        //TODO
                        'Teacher: ',
                        style: TextStyle(fontSize: 16, color: Colors.black),
                      ),
                    ],
                  ),
                  Container(
                    margin: const EdgeInsets.only(top: 8, bottom: 8),
                    width: 330,
                    decoration: ShapeDecoration(
                      shape: RoundedRectangleBorder(
                        side: BorderSide(
                          width: 1,
                          strokeAlign: BorderSide.strokeAlignCenter,
                          color: Colors.black.withOpacity(0.6800000071525574),
                        ),
                      ),
                    ),
                  ),
                  const Padding(
                    padding: EdgeInsets.only(bottom: 8.0),
                    child: Row(
                      children: [
                        Padding(
                          padding: EdgeInsets.only(right: 8.0, left: 5),
                          child: SizedBox(
                            child: Image(
                              image: AssetImage('lib/Images/Numbers.png'),
                              color: Colors.yellow,
                            ),
                          ),
                        ),
                        Padding(
                          padding: EdgeInsets.only(right: 20),
                          child: Text(
                            //TODO
                            'Number of Units: ',
                            style: TextStyle(fontSize: 16),
                          ),
                        ),
                      ],
                    ),
                  ),
                  const Padding(
                    padding: EdgeInsets.only(bottom: 8.0),
                    child: Row(
                      children: [
                        Padding(
                          padding: EdgeInsets.only(right: 8.0, left: 5),
                          child: SizedBox(
                            child: Image(
                              image: AssetImage('lib/Images/Inscription.png'),
                              color: Colors.yellow,
                            ),
                          ),
                        ),
                        Padding(
                          padding: EdgeInsets.only(right: 20),
                          child: Text(
                            //TODO
                            'Remaining assignments: ',
                            style: TextStyle(fontSize: 16),
                          ),
                        ),
                      ],
                    ),
                  ),
                  const Padding(
                    padding: EdgeInsets.only(bottom: 8.0),
                    child: Row(
                      children: [
                        Padding(
                          padding: EdgeInsets.only(right: 8.0, left: 5),
                          child: SizedBox(
                            child: Image(
                              image: AssetImage(
                                  'lib/Images/First Place Ribbon.png'),
                              color: Colors.yellow,
                            ),
                          ),
                          // child: Icon(Icons.pla),
                        ),
                        Padding(
                          padding: EdgeInsets.only(right: 20),
                          child: Text(
                            //TODO
                            'Outstanding student of the week: ',
                            style: TextStyle(fontSize: 16),
                          ),
                        ),
                      ],
                    ),
                  ),
                ],
              ),
            ),
          ),
        );
      },
    );

Widget buildListView2() => ListView.builder(
      scrollDirection: Axis.horizontal,
      // shrinkWrap: true,
      physics: const BouncingScrollPhysics(),
      itemCount: 5, //TODO
      itemBuilder: (context, index) {
        List<String> texts = [
          "News",
          "Events",
          "Reminders",
          "Birthdays today",
          "Extensions"
        ];
        return Padding(
          padding: const EdgeInsets.all(5.0),
          child: Row(
            children: [
              ElevatedButton(
                  style: ElevatedButton.styleFrom(
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(30),
                    ),
                    backgroundColor: Colors.yellow[400],
                  ),
                  onPressed: () {
                    if (texts[index] == "News") {
                      Navigator.push(
                        context,
                        MaterialPageRoute(
                          builder: (context) => const News(),
                        ),
                      );}else{
                      Navigator.push(
                        context,
                        MaterialPageRoute(
                          builder: (context) => const News2(),
                        ),
                      );
                    }
                    // } else if (texts[index] == "Events") {
                    // } else if (texts[index] == "Reminders") {
                    // } else if (texts[index] == "Birthdays today") {
                    // } else if (texts[index] == "Extensions") {}
                  },
                  child: Text(
                    texts[index],
                    style: const TextStyle(
                      color: Colors.deepPurpleAccent,
                    ),
                  )),
            ],
          ),
        );
      },
    );

Widget buildListView3() => ListView.builder(
      physics: const BouncingScrollPhysics(),
      itemCount: 3, //TODO
      itemBuilder: (context, index) {
        List<String> urls = ["/fa/w/basketball-8?redirect=%2F%25D8%25AF%25D8%25A7%25D9%2586%25D8%25B4%25D8%25AC%25D9%2588%25DB%258C%25DB%258C"
          ,"/fa/w/bargh-4?redirect=%2F%25D8%25AF%25D8%25A7%25D9%2586%25D8%25B4%25D8%25AC%25D9%2588%25DB%258C%25DB%258C",
        "/fa/w/dorm-3?redirect=%2F%25D8%25AF%25D8%25A7%25D9%2586%25D8%25B4%25D8%25AC%25D9%2588%25DB%258C%25DB%258C",
        ];
        List<Color> colors = const [
          Colors.deepPurpleAccent,
          Color(0XFF9B59B6),
          Color(0XFFDB98D4),
          Color(0XFF3498DB),
          Color(0XFFE74C3C),
          Colors.deepOrange,
          Color(0XFF1ABC9C),
          Color(0XFF2ECC71),
          Color(0XFF95A5A6),
          Color(0XFF34495E),
        ];
        List<String> strs = [
          "   تیم بسکتبال سه نفره دانشگاه شهید   ",
          "بهشتی با پیروزی برابر تیم چین تایپه",
          "به جمع هشت تیم برتر آسیا راه یافت...",
          "    مجموعه کارگاه های آموزشی ویژه   ",
          "    دانشجویان رشته برق دانشگاه شهید  ",
          "   بهشتی در شرکت توزیع نیروی برق ... ",
"    برنامه های دانشگاه برای           ",
    "       هفته گرامیداشت خوابگاه های       ",
        "      دانشجویی...                              ",
        ];
        return SizedBox(
          width: double.infinity,
          height: 185,
          child: Card(
            color: colors[index],
            margin:
                const EdgeInsets.only(top: 10, left: 20, right: 20, bottom: 10),
            shadowColor: Colors.black,
            shape: const RoundedRectangleBorder(
                borderRadius: BorderRadius.all(Radius.circular(20))),
            child: Row(
              children: [
                Column(
                  children: [
                    const Padding(
                      padding: EdgeInsets.only(top: 8.0, left: 25),
                      child: Text(
                        "اطلاعیه آموزشی",
                        textDirection: TextDirection.rtl,
                        style: TextStyle(
                            fontSize: 25,
                            color: Colors.yellow,
                            fontWeight: FontWeight.bold),
                      ),
                    ),
                    Text(
                      strs[index * 3],
                      textDirection: TextDirection.rtl,
                    ),
                    Text(
                      strs[1 + index * 3],
                      textDirection: TextDirection.rtl,
                    ),
                    Text(
                      strs[2 + index * 3],
                      textDirection: TextDirection.rtl,
                    ),
                    Padding(
                      padding: const EdgeInsets.only(right: 90),
                      child: TextButton(
                        onPressed: () async {
                          var url = Uri.https("news.sbu.ac.ir",
                              urls[index]);
                          if (await canLaunchUrl(url)) {
                            await launchUrl(url);
                          }
                        },
                        child: const Text(
                          "مطالعه بیشتر...",
                          style: TextStyle(
                              color: Colors.yellow,
                              fontWeight: FontWeight.bold),
                          textDirection: TextDirection.rtl,
                        ),
                      ),
                    ),
                  ],
                ),
                const Image(
                  height: 170,
                  image: AssetImage('lib/Images/Sbu.jpg'),
                  fit: BoxFit.cover,
                ),
              ],
            ),
          ),
        );
      },
    );
