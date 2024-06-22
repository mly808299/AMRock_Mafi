import 'package:flutter/material.dart';
import '../components/methods.dart';
import '../components/my_button.dart';
import 'login_page.dart';

class Signup extends StatefulWidget {
  const Signup({super.key});

  @override
  State<Signup> createState() => _SignupState();
}

class _SignupState extends State<Signup> {
  bool obscureText = false;
  final usernameController = TextEditingController();
  final passwordController = TextEditingController();
  final rePasswordController = TextEditingController();
  final _formKey = GlobalKey<FormState>();
  final _formKey2 = GlobalKey<FormState>();
  final _formKey3 = GlobalKey<FormState>();

  String usernameInput = '';
  String passwordInput = '';


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.yellow[600],
        toolbarHeight: 0,
      ),
      backgroundColor: Colors.yellow[600],
      body: SafeArea(
      child:Column(
          children: [
            Column(
              children: [
                Container(
                  width: double.infinity,
                  margin: const EdgeInsets.only(top: 40, left: 16),
                  child: const Text(
                    'Existing User ?',
                    style: TextStyle(
                      color: Colors.black,
                      fontSize: 18,
                      //fontWeight: FontWeight.bold,
                    ),
                  ),
                ),
                Padding(
                  padding:
                      const EdgeInsets.symmetric(horizontal: 20, vertical: 15),
                  child: ConstrainedBox(
                    constraints: const BoxConstraints.tightFor(
                      width: double.infinity,
                      height: 56,
                    ),
                    child: ElevatedButton(
                      style: ElevatedButton.styleFrom(
                        shadowColor: Colors.black,
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(30),
                        ),
                        backgroundColor: Colors.black,
                        elevation: 20,
                      ),
                      onPressed: () {
                        Navigator.push(
                            context,
                            MaterialPageRoute(
                                builder: (context) => const LoginPage()));
                      },
                      child: const Text(
                        "Login",
                        style: TextStyle(
                          color: Colors.yellow,
                          fontSize: 24,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                    ),
                  ),
                ),
                Container(
                  margin: const EdgeInsets.only(top: 25),
                  width: double.infinity,
                  height: MediaQuery
                      .of(context)
                      .size
                      .height - 201,
                  // height: 436.428,
                  decoration: const BoxDecoration(
                    color: Colors.black,
                    borderRadius: BorderRadius.only(
                      topRight: Radius.circular(100),
                      topLeft: Radius.circular(100),
                    ),
                  ),
                  child: Column(
                    children: [
                      Container(
                        width: double.infinity,
                        margin: const EdgeInsets.only(top: 50, left: 20),
                        child: Text(
                          'Sign Up With',
                          style: TextStyle(
                            color: Colors.yellow[600],
                            fontSize: 16,
                          ),
                        ),
                      ),
                      Container(
                        width: double.infinity,
                        margin:
                            const EdgeInsets.only(top: 5, left: 20, bottom: 5),
                        child: Text(
                          'AMRock_Mafi Project',
                          style: TextStyle(
                            color: Colors.yellow[600],
                            fontSize: 30,
                            fontWeight: FontWeight.bold,
                          ),
                        ),
                      ),

                      Container(
                        margin: const EdgeInsets.only(top: 5, bottom: 5),
                        child: Form(
                          key: _formKey,
                          child: Padding(
                              padding:
                                  const EdgeInsets.symmetric(horizontal: 20.0),
                              child: TextFormField(
                                  controller: usernameController,
                                  keyboardType: TextInputType.name,
                                  obscureText: false,
                                  decoration: InputDecoration(
                                    contentPadding: const EdgeInsets.symmetric(
                                        horizontal: 25.0, vertical: 17.5),
                                    enabledBorder: OutlineInputBorder(
                                      borderSide: const BorderSide(
                                          color: Colors.yellow),
                                      borderRadius: BorderRadius.circular(50.0),
                                    ),
                                    focusedBorder: OutlineInputBorder(
                                        borderRadius:
                                            BorderRadius.circular(50.0),
                                        borderSide: const BorderSide(
                                            color: Colors.green, width: 2)),
                                    prefixIcon: const Icon(Icons.person),
                                    prefixIconColor: Colors.grey.shade900,
                                    fillColor: Colors.yellow[500],
                                    filled: true,
                                    hintText: "Enter Username",
                                  ),
                                  validator: (value) {
                                    usernameInput = value!;
                                    if (value.isEmpty) {
                                      return 'This field can\'t be empty !!!';
                                    } else {
                                      return null;
                                    }
                                  })),
                        ),
                      ),
                      Container(
                        margin: const EdgeInsets.only(top: 5, bottom: 5),
                        child: Form(
                          key: _formKey2,
                          child: Padding(
                            padding:
                                const EdgeInsets.symmetric(horizontal: 20.0),
                            child: TextFormField(
                              controller: passwordController,
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
                                hintText: "Password",
                              ),
                              validator: (value) {
                                passwordInput = value!;
                                if (value.isEmpty) {
                                  return 'This field can\'t be empty !!!';
                                } else if (value.contains(usernameInput) ||
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
                        margin: const EdgeInsets.only(top: 5, bottom: 5),
                        child: Form(
                          key: _formKey3,
                          child: Padding(
                            padding:
                                const EdgeInsets.symmetric(horizontal: 20.0),
                            child: TextFormField(
                              controller: rePasswordController,
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
                                prefixIcon:
                                    const Icon(Icons.lock_reset_outlined),
                                prefixIconColor: Colors.grey.shade900,
                                fillColor: Colors.yellow[500],
                                filled: true,
                                hintText: "Re-Enter Password",
                              ),
                              validator: (value) {
                                if (value!.isEmpty) {
                                  return 'This field can\'t be empty !!!';
                                } else if (value != passwordInput) {
                                  return 'Password and RePassword is\'nt same';
                                }
                                return null;
                              },
                            ),
                          ),
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.only(
                            right: 25, left: 25, top: 15, bottom: 5),
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
                              bool key3 = _formKey3.currentState!.validate();
                              if (key1 && key2 && key3) {
                                customToast('You have successfully registered',context);
                                Navigator.push(
                                    context,
                                    MaterialPageRoute(
                                        builder: (context) =>
                                            const SecondRoute()));
                              }
                            },
                            child: const Text(
                              'Sign Up',
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
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }
}
