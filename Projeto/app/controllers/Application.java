package controllers;

import play.api.mvc.Session;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {

    public static Result index() {
        if (session().get("email") == null) {
        	return redirect(routes.Login.show());
        } 
       return ok(index.render("Your new application is ready."));
       // return redirect(routes.Login.show());
    }

}
