package zakhargoryainov.todolist.authentication;

import io.reactivex.Completable;


public class AuthInteractor {

    private AuthService authService;

    public AuthInteractor(AuthService authService) {
        this.authService = authService;
    }

    public Completable signIn(String email, String password){
        return authService.signIn(email, password);
    }

    public Completable signUp(String email, String password){
        return authService.signUp(email,password);
    }
}
