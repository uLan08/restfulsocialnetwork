package pages

import geb.Page

class LoginPage extends Page{
     static url = "http://localhost:8080/restsocnet/login"

     static at = {
        title ==~/Login/
     }

     static content = {
        txtLogin    { $("input[name=username]") }
        txtPassword { $("input[name=password]") }

        btnLogin { $("button", type: "submit") }
     }

     def login(String name, String password){
          txtLogin = name
          txtPassword = password

          btnLogin.click()
     }

}