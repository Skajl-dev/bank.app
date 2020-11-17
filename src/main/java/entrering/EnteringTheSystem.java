package entrering;

import dao.Dao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class EnteringTheSystem {
    public Dao dao;

    private final Pattern PHONE_NUMBER_REGEX = Pattern.compile("^(039|050|063|066|067|068|091|092|093|094|095|096|097" +
            "|098|099|031|032|033|034|035|036|037|038|041|042|043|044|046|047" +
            "|048|049|051|052|053|054|055|056|057|058|059|061|062|064|065|069)\\d{7}");
    public final Pattern EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public final Pattern PASSWORD_REGEX = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,15}$");
    public final Pattern NAME_AND_SURNAME_REGEX = Pattern.compile("^[A-Z]{1}\\w{1,29}");

    public EnteringTheSystem(Dao dao) {
        this.dao = dao;
    }

    public boolean validateEmail(String emailStr) {
        Matcher matcher = EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public boolean validateNumber(String number) {
        Matcher matcher = PHONE_NUMBER_REGEX.matcher(number);
        return matcher.find() && (dao.findByNumber(number) == null);
    }

    public boolean validatePassword(String password) {
        Matcher matcher = PASSWORD_REGEX.matcher(password);
        return matcher.find();
    }

    public boolean validateNameAndSurname(String nameOrSurname) {
        Matcher matcher = NAME_AND_SURNAME_REGEX.matcher(nameOrSurname);
        return matcher.find();
    }
}
