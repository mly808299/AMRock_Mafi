import 'package:flutter/material.dart';
import '../components/methods.dart';
import '../components/my_button.dart';
import 'change_password.dart';
import 'edit_profile.dart';

class ProfilePage extends StatefulWidget {
  const ProfilePage({super.key});

  @override
  State<ProfilePage> createState() => _ProfilePageState();
}

class _ProfilePageState extends State<ProfilePage> {
  void _showDialog() {
    showDialog(
        context: context,
        builder: (context) {
          return AlertDialog(
            backgroundColor: Colors.yellow,
            title: const Text(
              'Delete Account',
              style: TextStyle(
                color: Colors.red,
                fontWeight: FontWeight.bold,
              ),
            ),
            content: const Text(
              'Are you sure about deleting the user Account?',
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
                onPressed: () {
                  customToast(
                      "Your account has been successfully deleted", context);
                  Navigator.push(
                      context,
                      MaterialPageRoute(
                          builder: (context) => const SecondRoute()));
                },
                child: const Text(
                  'Delete',
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
    return SafeArea(
        child: Scaffold(
      backgroundColor: Colors.yellow[600],
      body: Column(
        children: [
           Stack(children: [
              Container(
                  alignment: Alignment.center,
                  margin: const EdgeInsets.only(bottom: 10, top: 40),
                  width: 110,
                  height: 110,
                  decoration: BoxDecoration(
                      color: Colors.white,
                      border: Border.all(color: Colors.black, width: 2),
                      shape: BoxShape.circle,
                      image: const DecorationImage(
                        image: AssetImage('lib/Images/pngtree-man-avatar.png'),
                      ))
              ),
             Container(
                 alignment: Alignment.center,
                 margin: const EdgeInsets.only(bottom: 10, top: 110,left: 100),
                 width: 20,
                 height: 20,
                 decoration: BoxDecoration(
                     color: Colors.white,
                     border: Border.all(color: Colors.black, width: 2),
                     shape: BoxShape.circle,
                     image: const DecorationImage(
                       image: AssetImage('lib/Images/cameraIcons.png'),
                     )),
          //     child:ElevatedButton(
          //     onPressed: () {},
          //     style: ElevatedButton.styleFrom(
          //       padding: const EdgeInsets.only(top: 120, left: 70),
          //       backgroundColor: Colors.white,
          //       fixedSize: const Size.fromHeight(10),
          //       shape: const CircleBorder(
          //       ),
          //     ),
          // child: null,
          //    ),
              // Container(
              //   margin: const EdgeInsets.only(top: 115, left: 73),
              //   width: 40,
              //   decoration: BoxDecoration(
              //     border: Border.all(color: Colors.black, width: 2),
              //     shape: BoxShape.circle,
              //     color: Colors.cyanAccent,
              //   ),
              //   child:
              //         Center(
              //             child:IconButton(
              //                   iconSize: 30,
              //                   color: Colors.black,
              //                   onPressed: () {},
              //                   icon: const Icon(Icons.camera_alt),
              //                 ),
              //         ),
              //
              ),
            ]),

          Container(
            alignment: Alignment.center,
            width: double.infinity,
            child: const Text(
              'Mohammadali Felfelani',
              style: TextStyle(
                color: Colors.black,
                fontSize: 18,
                fontWeight: FontWeight.bold,
              ),
            ),
          ),
          Container(
            alignment: Alignment.center,
            width: double.infinity,
            child: const Text(
              'Student',
              style: TextStyle(
                color: Colors.black,
                fontSize: 16,
              ),
            ),
          ),
          Container(
            margin: const EdgeInsets.only(top: 20),
            width: double.infinity,
            height: MediaQuery.of(context).size.height - 236,
            // height: 436.428,
            decoration: const BoxDecoration(
              color: Colors.black,
              borderRadius: BorderRadius.only(
                topRight: Radius.circular(50),
                topLeft: Radius.circular(50),
              ),
            ),
            child: Column(
              children: [
                Container(
                  margin: const EdgeInsets.only(top: 45, bottom: 20),
                  width: 354,
                  height: 175,
                  decoration: ShapeDecoration(
                    color: Colors.yellow,
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(27),
                    ),
                  ),
                  child: Column(
                    children: [
                      Container(
                        margin: const EdgeInsets.only(
                            top: 10, right: 225, bottom: 10),
                        child: const Text(
                          'Student ID',
                          style: TextStyle(
                            color: Colors.black,
                            fontSize: 16,
                          ),
                        ),
                      ),
                      Container(
                        width: 300,
                        decoration: ShapeDecoration(
                          shape: RoundedRectangleBorder(
                            side: BorderSide(
                              width: 1,
                              strokeAlign: BorderSide.strokeAlignCenter,
                              color:
                                  Colors.black.withOpacity(0.10000000149011612),
                            ),
                          ),
                        ),
                      ),
                      Container(
                        margin: const EdgeInsets.only(
                            top: 10, right: 176, bottom: 10),
                        child: const Text(
                          'Current Semester',
                          style: TextStyle(
                            color: Colors.black,
                            fontSize: 16,
                          ),
                        ),
                      ),
                      Container(
                        width: 300,
                        decoration: ShapeDecoration(
                          shape: RoundedRectangleBorder(
                            side: BorderSide(
                              width: 1,
                              strokeAlign: BorderSide.strokeAlignCenter,
                              color:
                                  Colors.black.withOpacity(0.10000000149011612),
                            ),
                          ),
                        ),
                      ),
                      Container(
                        margin: const EdgeInsets.only(
                            top: 10, right: 182, bottom: 10),
                        child: const Text(
                          'Number of Units',
                          style: TextStyle(
                            color: Colors.black,
                            fontSize: 16,
                          ),
                        ),
                      ),
                      Container(
                        width: 300,
                        decoration: ShapeDecoration(
                          shape: RoundedRectangleBorder(
                            side: BorderSide(
                              width: 1,
                              strokeAlign: BorderSide.strokeAlignCenter,
                              color:
                                  Colors.black.withOpacity(0.10000000149011612),
                            ),
                          ),
                        ),
                      ),
                      Container(
                        margin: const EdgeInsets.only(
                            top: 10, right: 204, bottom: 10),
                        child: const Text(
                          'Total Average',
                          style: TextStyle(
                            color: Colors.black,
                            fontSize: 16,
                          ),
                        ),
                      ),
                    ],
                  ),
                ),
                Container(
                  margin: const EdgeInsets.only(bottom: 10, top: 5, left: 2),
                  width: 355,
                  height: 120,
                  decoration: ShapeDecoration(
                    color: Colors.yellow,
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(27),
                    ),
                  ),
                  child: Column(
                    children: [
                      Padding(
                        padding:
                            const EdgeInsets.only(left: 25, top: 5, bottom: 5),
                        child: Row(
                          children: [
                            const Icon(
                              (Icons.edit),
                              size: 25,
                            ),
                            ElevatedButton(
                              onPressed: () {
                                Navigator.push(
                                    context,
                                    MaterialPageRoute(
                                        builder: (context) =>
                                            const EditProfile()));
                              },
                              style: ElevatedButton.styleFrom(
                                backgroundColor: Colors.yellow[500],
                              ),
                              child: const Text(
                                'Edit Profile',
                                style: TextStyle(
                                  color: Colors.black,
                                  fontSize: 16,
                                ),
                              ),
                            ),
                          ],
                        ),
                      ),
                      Container(
                        width: 300,
                        decoration: ShapeDecoration(
                          shape: RoundedRectangleBorder(
                            side: BorderSide(
                              width: 1,
                              strokeAlign: BorderSide.strokeAlignCenter,
                              color:
                                  Colors.black.withOpacity(0.10000000149011612),
                            ),
                          ),
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.only(left: 25, top: 5),
                        child: Row(
                          children: [
                            const Icon(
                              (Icons.lock_reset),
                              size: 30,
                            ),
                            ElevatedButton(
                              onPressed: () {
                                Navigator.push(
                                    context,
                                    MaterialPageRoute(
                                        builder: (context) =>
                                            const ChangePassword()));
                              },
                              style: ElevatedButton.styleFrom(
                                backgroundColor: Colors.yellow[500],
                              ),
                              child: const Text(
                                'Change Password',
                                style: TextStyle(
                                  color: Colors.black,
                                  fontSize: 16,
                                ),
                              ),
                            ),
                          ],
                        ),
                      ),
                    ],
                  ),
                ),
                Padding(
                  padding: const EdgeInsets.only(right: 26, left: 26, top: 45),
                  child: ConstrainedBox(
                    constraints: const BoxConstraints.tightFor(
                      width: double.infinity,
                      height: 70,
                    ),
                    child: ElevatedButton(
                      style: ElevatedButton.styleFrom(
                        backgroundColor: Colors.red[700],
                        shadowColor: Colors.red,
                        elevation: 10,
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(25),
                        ),
                      ),
                      onPressed: _showDialog,
                      child: const Text(
                        'Delete Account',
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
          )
        ],
      ),
    ));
  }
}
