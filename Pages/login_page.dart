import 'package:flutter/material.dart';
import 'package:mafi2/Pages/signup_page.dart';
import '../components/methods.dart';
import '../components/my_button.dart';

class LoginPage extends StatefulWidget {
  const LoginPage({super.key});

  @override
  State<LoginPage> createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  bool obscureText = false;
  final usernameController = TextEditingController();
  final passwordController = TextEditingController();
  final _formKey = GlobalKey<FormState>();
  final _formKey2 = GlobalKey<FormState>();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          backgroundColor: Colors.yellow[600],
          toolbarHeight: 0,
        ),
        backgroundColor: Colors.black,
      body:SafeArea(
        child:Column(
          children: [
            Container(
              width: double.infinity,
              height: 500,
              decoration: BoxDecoration(
                  color: Colors.yellow[600],
                  borderRadius: const BorderRadius.only(
                    bottomLeft: Radius.circular(165),
                    bottomRight: Radius.circular(165),
                  )),
              child: Column(children: [
                Container(
                  width: double.infinity,
                  margin: const EdgeInsets.only(top: 40, left: 15, bottom: 5),
                  child: Text(
                    'Welcome to',
                    style: TextStyle(
                      color: Colors.grey[900],
                      fontSize: 20,
                    ),
                  ),
                ),
                Container(
                  width: double.infinity,
                  margin: const EdgeInsets.only(left: 15, bottom: 5),
                  child: Text(
                    'AMRock_Mafi Project',
                    style: TextStyle(
                      color: Colors.grey[900],
                      fontSize: 30,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                ),
                Container(
                  width: double.infinity,
                  margin: const EdgeInsets.only(left: 15, bottom: 15),
                  child: Text(
                    'Please Login to Continue...',
                    style: TextStyle(
                      color: Colors.grey[900],
                      fontSize: 20,
                    ),
                  ),
                ),
                Container(
                  margin: const EdgeInsets.symmetric(vertical: 10),
                  child: Form(
                    key: _formKey,
                    child: Padding(
                        padding: const EdgeInsets.symmetric(
                          horizontal: 20.0,
                        ),
                        child: TextFormField(
                          controller: usernameController,
                          keyboardType: TextInputType.name,
                          obscureText: false,
                          decoration: InputDecoration(
                            contentPadding: const EdgeInsets.symmetric(
                                horizontal: 26, vertical: 17.5),
                            enabledBorder: OutlineInputBorder(
                              borderSide:
                                  const BorderSide(color: Colors.yellow),
                              borderRadius: BorderRadius.circular(50.0),
                            ),
                            focusedBorder: OutlineInputBorder(
                                borderRadius: BorderRadius.circular(50.0),
                                borderSide: const BorderSide(
                                    color: Colors.green, width: 2)),
                            prefixIcon: const Icon(Icons.person),
                            prefixIconColor: Colors.grey.shade900,
                            fillColor: Colors.yellow[400],
                            filled: true,
                            hintText: "Username",
                          ),
                          validator: (value) => value!.isEmpty
                              ? 'Username can not be empty !!!'
                              : null,
                          autovalidateMode: AutovalidateMode.onUserInteraction,
                        )),
                  ),
                ),
                Container(
                  margin: const EdgeInsets.symmetric(vertical: 10),
                  child: Form(
                    key: _formKey2,
                    child: Padding(
                      padding: const EdgeInsets.symmetric(horizontal: 20.0),
                      child: TextFormField(
                        controller: passwordController,
                        keyboardType: TextInputType.name,
                        obscureText: obscureText,
                        decoration: InputDecoration(
                          contentPadding: const EdgeInsets.symmetric(
                              horizontal: 25.0, vertical: 17.5),
                          enabledBorder: OutlineInputBorder(
                            borderSide: const BorderSide(color: Colors.yellow),
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
                          fillColor: Colors.yellow[400],
                          filled: true,
                          hintText: "Password",
                        ),
                        validator: (value) => value!.isEmpty
                            ? 'Password can not be empty !!!'
                            : null,
                        autovalidateMode: AutovalidateMode.onUserInteraction,
                      ),
                    ),
                  ),
                ),
                Padding(
                  padding:
                      const EdgeInsets.symmetric(horizontal: 20, vertical: 5),
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
                        bool key1 = _formKey.currentState!.validate();
                        bool key2 = _formKey2.currentState!.validate();
                        if (key2 && key1) {
                          customToast('You have successfully logged in',context);
                          Navigator.push(
                              context,
                              MaterialPageRoute(
                                  builder: (context) => const SecondRoute()));
                        }
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
              ]),
            ),
            Column(
              children: [
                Padding(
                  padding: const EdgeInsets.only(top: 40,bottom: 15),
                  child: Text(
                    'OR',
                    style: TextStyle(
                      color: Colors.yellow[600],
                      fontWeight: FontWeight.bold,
                      fontSize: 30,
                    ),
                  ),
                ),
                Padding(
                  padding: const EdgeInsets.only(
                      right: 30, left: 30, top: 15, bottom: 5),
                  child: ConstrainedBox(
                    constraints: const BoxConstraints.tightFor(
                      width: double.infinity,
                      height: 56,
                    ),
                    child: ElevatedButton(
                      style: ElevatedButton.styleFrom(
                        backgroundColor: Colors.yellow[600],
                        shadowColor: Colors.yellow[500],
                        elevation: 8,
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(25),
                        ),
                      ),
                      onPressed: () {
                        Navigator.push(
                          context,
                          MaterialPageRoute(
                            builder: (context) => const Signup(),
                          ),
                        );
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
          ],
        ),
      ),
      // ),
    );
  }
}
