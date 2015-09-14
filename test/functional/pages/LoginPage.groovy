package pages

import geb.Page

class LoginPage extends Page{
     static url = "http://localhost:8090/restsocnet/login/auth"

     static at = {
        title ==~/Login/
     }

     static content = {
        txtLogin    { $("input[name=j_username]") }
        txtPassword { $("input[name=j_password]") }

        btnLogin { $("input", type: "submit") }
     }

     def login(String name, String password){
          txtLogin = name
          txtPassword = password

          btnLogin.click()
     }

}