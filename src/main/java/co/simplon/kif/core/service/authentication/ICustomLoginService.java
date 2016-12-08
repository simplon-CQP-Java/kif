package co.simplon.kif.core.service.authentication;

import co.simplon.kif.core.model.User;

public interface ICustomLoginService {

	User autoLogin(User user);

}