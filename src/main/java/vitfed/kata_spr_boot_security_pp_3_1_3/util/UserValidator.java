package vitfed.kata_spr_boot_security_pp_3_1_3.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import vitfed.kata_spr_boot_security_pp_3_1_3.models.User;
import vitfed.kata_spr_boot_security_pp_3_1_3.repository.UserRepository;

@Component
public class UserValidator implements Validator {
      private final  UserRepository userRepository;

      @Autowired
    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User)target;
        if(userRepository.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "", "This username already exist");
        }
    }
}
