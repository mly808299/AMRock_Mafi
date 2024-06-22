import 'package:flutter/material.dart';
import 'package:flutter_styled_toast/flutter_styled_toast.dart';


void customToast(String massage, BuildContext context) {
  showToast(massage,
      textStyle: const TextStyle(
        fontSize: 16,
        fontWeight: FontWeight.bold,
        color: Colors.black,
      ),
      textPadding: const EdgeInsets.all(23),
      fullWidth: true,
      toastHorizontalMargin: 25,
      borderRadius: BorderRadius.circular(15),
      backgroundColor: Colors.green,
      alignment: Alignment.bottomLeft,
      duration: const Duration(seconds: 5),
      context: context);
}


