import 'dart:io';

import 'package:flutter/material.dart';
import 'package:mafi2/Pages/Student.dart';
import '../components/methods.dart';
import 'Profile_page.dart';

class EditProfile extends StatefulWidget {
  const EditProfile({super.key});

  @override
  State<EditProfile> createState() => _EditProfileState();
}

class _EditProfileState extends State<EditProfile> {
  final firstNameController = TextEditingController();
  final lastNameController = TextEditingController();
  final _formKey = GlobalKey<FormState>();
  final _formKey2 = GlobalKey<FormState>();
  String firstName = '';
  String lastName = '';

  void _showDialog2(String firstNme , String lastName) {
    showDialog(
        context: context,
        builder: (context) {
          return AlertDialog(
            backgroundColor: Colors.yellow,
            title: const Text(
              'Edit Profile',
              style: TextStyle(
                color: Colors.red,
                fontWeight: FontWeight.bold,
              ),
            ),
            content: const Text(
              'Are you sure about Editing the Profile?',
              style: TextStyle(
                fontSize: 21,
              ),
            ),
            actions: [
              MaterialButton(
                color: Colors.green,
                onPressed: () {
                  Navigator.pop(context);
                },
                child: const Text(
                  'Cancel',
                  style: TextStyle(
                    fontSize: 18,
                    color: Colors.black,
                    fontWeight: FontWeight.bold,
                  ),
                ),
              ),
              MaterialButton(
                color: Colors.red[700],
                onPressed: () async {
                  Student.student?.firstName = firstName;
                  Student.student?.lastName = lastName;
                  try{
                    Socket socket = await Socket.connect('10.0.2.2', 8084);
                      socket.write(
                          'edit profile\n${Student.student?.username}\n'
                          '$firstName\n$lastName\n'
                      );
                      await socket.close();
                  }catch(e) {
                    print('Error: $e');
                  }
                  customToast("Your Profile Edited Successfully", context,
                      Colors.green);
                  sleep(Duration(milliseconds: 100));
                  Navigator.push(
                      context,
                      MaterialPageRoute(
                          builder: (context) =>const ProfilePage()));
                },
                child: const Text(
                  'Edit',
                  style: TextStyle(
                    fontSize: 18,
                    color: Colors.black,
                    fontWeight: FontWeight.bold,
                  ),
                ),
              ),
            ],
          );
        });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.yellow,
      appBar: AppBar(
        backgroundColor: Colors.yellow,
        title: const Text(
          'Edit Profile',
          style: TextStyle(fontWeight: FontWeight.bold),
        ),
      ),
      body: SafeArea(
        child: SingleChildScrollView(
          child: Column(
            children: [
              Center(
                child: Container(
                  margin: const EdgeInsets.only(top: 40),
                  width: MediaQuery.of(context).size.width - 30,
                  height: MediaQuery.of(context).size.height - 180,
                  // height: 436.428,
                  decoration: const BoxDecoration(
                    color: Colors.black,
                    borderRadius: BorderRadius.only(
                      topRight: Radius.circular(140),
                      topLeft: Radius.circular(140),
                      bottomLeft: Radius.circular(140),
                      bottomRight: Radius.circular(140),
                    ),
                  ),
                  child: Column(
                    children: [
                      Container(
                        margin: const EdgeInsets.only(top: 140, bottom: 15),
                        child: Form(
                          key: _formKey,
                          child: Padding(
                              padding:
                                  const EdgeInsets.symmetric(horizontal: 20.0),
                              child: TextFormField(
                                controller: firstNameController,
                                keyboardType: TextInputType.name,
                                obscureText: false,
                                decoration: InputDecoration(
                                  contentPadding: const EdgeInsets.symmetric(
                                      horizontal: 25.0, vertical: 17.5),
                                  enabledBorder: OutlineInputBorder(
                                    borderSide:
                                        const BorderSide(color: Colors.yellow),
                                    borderRadius: BorderRadius.circular(50.0),
                                  ),
                                  focusedBorder: OutlineInputBorder(
                                      borderRadius: BorderRadius.circular(50.0),
                                      borderSide: const BorderSide(
                                          color: Colors.green, width: 2)),
                                  prefixIcon: const Icon(
                                      Icons.drive_file_rename_outline_sharp),
                                  prefixIconColor: Colors.grey.shade900,
                                  fillColor: Colors.yellow[500],
                                  filled: true,
                                  hintText: "Enter Your Name",
                                ),
                                validator: (value) {
                                  firstName = value!;
                                  if (value.isEmpty) {
                                    return 'this filled can\'t be empty';
                                  } else {
                                    return null;
                                  }
                                },
                              )),
                        ),
                      ),
                      Container(
                        margin: const EdgeInsets.only(top: 15, bottom: 15),
                        child: Form(
                          key: _formKey2,
                          child: Padding(
                            padding:
                                const EdgeInsets.symmetric(horizontal: 20.0),
                            child: TextFormField(
                              controller: lastNameController,
                              keyboardType: TextInputType.name,
                              decoration: InputDecoration(
                                contentPadding: const EdgeInsets.symmetric(
                                    horizontal: 25.0, vertical: 17.5),
                                enabledBorder: OutlineInputBorder(
                                  borderSide:
                                      const BorderSide(color: Colors.yellow),
                                  borderRadius: BorderRadius.circular(50.0),
                                ),
                                focusedBorder: OutlineInputBorder(
                                    borderRadius: BorderRadius.circular(50.0),
                                    borderSide: const BorderSide(
                                        color: Colors.green, width: 2)),
                                prefixIcon: const Icon(
                                    Icons.drive_file_rename_outline_outlined),
                                prefixIconColor: Colors.grey.shade900,
                                fillColor: Colors.yellow[500],
                                filled: true,
                                hintText: "Enter Your Lastname",
                              ),
                              validator: (value) {
                                lastName = value!;
                                if (value.isEmpty) {
                                  return 'this filled can\'t be empty';
                                } else {
                                  return null;
                                }
                              },
                            ),
                          ),
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.only(
                            right: 24, left: 24, top: 30, bottom: 5),
                        child: ConstrainedBox(
                          constraints: const BoxConstraints.tightFor(
                            width: double.infinity,
                            height: 56,
                          ),
                          child: ElevatedButton(
                            style: ElevatedButton.styleFrom(
                              backgroundColor: Colors.yellow[500],
                              shadowColor: Colors.yellow[500],
                              elevation: 8,
                              shape: RoundedRectangleBorder(
                                borderRadius: BorderRadius.circular(25),
                              ),
                            ),
                            onPressed: () {
                              bool key1 = _formKey.currentState!.validate();
                              bool key2 = _formKey2.currentState!.validate();
                              if (key1 && key2) {
                                _showDialog2(firstName , lastName);
                              }
                            },
                            child: const Text(
                              'Edit',
                              style: TextStyle(
                                fontSize: 26,
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
            ],
          ),
        ),
      ),
    );
  }
}
