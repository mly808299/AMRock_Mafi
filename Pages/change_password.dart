import 'package:flutter/material.dart';
import '../components/methods.dart';
import 'profile_page.dart';

class ChangePassword extends StatefulWidget {
  const ChangePassword({super.key});

  @override
  State<ChangePassword> createState() => _ChangePasswordState();
}

class _ChangePasswordState extends State<ChangePassword> {
  bool obscureText = false;
  final currentPasswordController = TextEditingController();
  final newPasswordController = TextEditingController();
  final reNewPasswordController = TextEditingController();
  final _formKey1 = GlobalKey<FormState>();
  final _formKey2 = GlobalKey<FormState>();
  final _formKey3 = GlobalKey<FormState>();

  String usernameInput = '';
  String passwordInput = '';


  void _showDialog2() {
    showDialog(
        context: context,
        builder: (context) {
          return AlertDialog(
            backgroundColor: Colors.yellow,
            title: const Text(
              'Change Password',
              style: TextStyle(
                color: Colors.red,
                fontWeight: FontWeight.bold,
              ),
            ),
            content: const Text(
              'Are you sure about changing the Password?',
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
                      "Your Password changed Successfully", context);
                  Navigator.push(
                      context,
                      MaterialPageRoute(
                          builder: (context) => const ProfilePage()));
                },
                child: const Text(
                  'Change',
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
        title: const Text('Change Password',style: TextStyle(fontWeight: FontWeight.bold),),
      ),
      body: SafeArea(
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
                        key: _formKey1,
                        child: Padding(
                            padding: const EdgeInsets.symmetric(horizontal: 20.0),
                            child: TextFormField(
                                controller: currentPasswordController,
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
                                  prefixIcon: const Icon(Icons.lock_open_outlined),
                                  prefixIconColor: Colors.grey.shade900,
                                  fillColor: Colors.yellow[500],
                                  filled: true,
                                  hintText: "Enter Current Password",
                                ),
                                validator: (value) {
                                  usernameInput = value!;
                                  if (value.isEmpty) {
                                    return 'This field can\'t be empty !!!';
                                  } else {
                                    return null;
                                  }
                                },
                              autovalidateMode: AutovalidateMode.onUserInteraction,
                            )),
                      ),
                    ),
                    Container(
                      margin: const EdgeInsets.only(top: 15, bottom: 15),
                      child: Form(
                        key: _formKey2,
                        child: Padding(
                          padding: const EdgeInsets.symmetric(horizontal: 20.0),
                          child: TextFormField(
                            controller: newPasswordController,
                            keyboardType: TextInputType.name,
                            obscureText: obscureText,
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
                              suffixIcon: IconButton(
                                  onPressed: () {
                                    setState(() {
                                      if (obscureText == false) {
                                        obscureText = true;
                                      } else {
                                        obscureText = false;
                                      }
                                    });
                                  },
                                  icon: obscureText
                                      ? const Icon(Icons.visibility)
                                      : const Icon(Icons.visibility_off)),
                              suffixIconColor: Colors.grey.shade900,
                              prefixIcon: const Icon(Icons.lock_outline),
                              prefixIconColor: Colors.grey.shade900,
                              fillColor: Colors.yellow[500],
                              filled: true,
                              hintText: "Enter New Password",
                            ),
                            validator: (value) {
                              passwordInput = value!;
                              if (value.isEmpty) {
                                return 'This field can\'t be empty !!!';
                              } else if (
                                  !RegExp(r'^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$')
                                      .hasMatch(value)) {
                                return '''Incorrect Password!
                          Your Password must have numbers and...''';
                              }
                              return null;
                            },
                            autovalidateMode: AutovalidateMode.onUserInteraction,
                          ),
                        ),
                      ),
                    ),
                    Container(
                      margin: const EdgeInsets.only(top: 15, bottom: 15),
                      child: Form(
                        key: _formKey3,
                        child: Padding(
                          padding: const EdgeInsets.symmetric(horizontal: 20.0),
                          child: TextFormField(
                            controller: reNewPasswordController,
                            keyboardType: TextInputType.name,
                            obscureText: obscureText,
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
                              suffixIcon: IconButton(
                                  onPressed: () {
                                    setState(() {
                                      if (obscureText == false) {
                                        obscureText = true;
                                      } else {
                                        obscureText = false;
                                      }
                                    });
                                  },
                                  icon: obscureText
                                      ? const Icon(Icons.visibility)
                                      : const Icon(Icons.visibility_off)),
                              suffixIconColor: Colors.grey.shade900,
                              prefixIcon: const Icon(Icons.lock_reset_outlined),
                              prefixIconColor: Colors.grey.shade900,
                              fillColor: Colors.yellow[500],
                              filled: true,
                              hintText: "Re-Enter New Password",
                            ),
                            validator: (value) {
                              if (value!.isEmpty) {
                                return 'This field can\'t be empty !!!';
                              } else if (value != passwordInput) {
                                return 'New Password and Renew Password is\'nt same';
                              }
                              return null;
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
                            bool key1 = _formKey1.currentState!.validate();
                            bool key2 = _formKey2.currentState!.validate();
                            bool key3 = _formKey3.currentState!.validate();
                            if (key1 && key2 && key3) {
                              _showDialog2();
                            }
                          },
                          child: const Text(
                            'Change',
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
    );
  }
}
