import 'dart:math';
import 'dart:math';
import 'dart:math';
import 'dart:ui';

import 'package:flutter/material.dart';
import 'package:random_color/random_color.dart';

class Practices {

  static String iconLeftPath = '';
  static String iconRightPath = '';
  String practiceName = '';
  static List<Color> myColors = [
    Colors.blue,
    Colors.pinkAccent,
    Colors.orange,
    Colors.yellow,
    Colors.deepPurpleAccent,
  ];
  static Random random = Random();
  Color colorBox = myColors[random.nextInt(myColors.length)];
  Practices({required this.practiceName});
  static List<Practices> getPeractices(){
    List<Practices> listOfPractices = [];
    listOfPractices.add(
      Practices(practiceName: "sdklksdlk")
    );
    listOfPractices.add(
        Practices(  practiceName: "sdklksdlk")
    );
    listOfPractices.add(
        Practices(practiceName: "sdklksdlk")
    );
    listOfPractices.add(
        Practices( practiceName: "sdklksdlk")
    );
    listOfPractices.add(
        Practices( practiceName: "sdklksdlk")
    );
    listOfPractices.add(
        Practices( practiceName: "sdklksdlk")
    );
    listOfPractices.add(
        Practices( practiceName: "sdklksdlk")
    );
    listOfPractices.add(
        Practices( practiceName: "sdklksdlk")
    );
    return listOfPractices;
  }

}
