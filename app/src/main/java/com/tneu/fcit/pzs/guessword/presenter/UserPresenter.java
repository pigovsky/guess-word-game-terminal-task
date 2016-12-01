package com.tneu.fcit.pzs.guessword.presenter;

import com.tneu.fcit.pzs.guessword.model.Gender;
import com.tneu.fcit.pzs.guessword.model.User;
import com.tneu.fcit.pzs.guessword.service.UserService;
import com.tneu.fcit.pzs.guessword.utils.Utils;

public class UserPresenter {
    private UserService userService;

    public UserPresenter(UserService userService) {
        this.userService = userService;
    }

    public void updateProfile(User user) {
        user.printShortInfo();

        updateName(user);
        updateSurname(user);
        updateBirthYear(user);
        updateGender(user);
        saveUser(user);

        user.printShortInfo();
    }

    private void updateName(User user) {
        System.out.println("Enter new name: ");
        String nameInput = Utils.SCANNER.nextLine();
        user.setName(nameInput);
    }

    private void updateSurname(User user) {
        System.out.println("Enter new surname: ");
        String surnameInput = Utils.SCANNER.nextLine();
        user.setSurname(surnameInput);
    }

    private void updateBirthYear(User user) {
        System.out.println("Enter new birth year: ");
        String birthYearInput = Utils.SCANNER.nextLine();
        user.setBirthYear(Integer.parseInt(birthYearInput));
    }

    private void updateGender(User user) {
        System.out.println("Enter new gender (male, female, other): ");
        Gender genderInput = Gender.parse(Utils.SCANNER.nextLine());
        user.setGender(genderInput);
    }

    private void saveUser(User user) {
        userService.save(user);
        System.out.println("Update Profile SUCCESS!");
    }
}
