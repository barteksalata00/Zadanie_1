package pl.edu.wszib.store.paints.core;

import org.apache.commons.codec.digest.DigestUtils;
import pl.edu.wszib.store.paints.database.ProductsDB;
import pl.edu.wszib.store.paints.database.UserDB;
import pl.edu.wszib.store.paints.gui.GUI;
import pl.edu.wszib.store.paints.model.Product;
import pl.edu.wszib.store.paints.model.User;

public class Authenticator {
    final UserDB userDB = UserDB.getInstance();
    final ProductsDB productsDB = ProductsDB.getInstance();
    private User loggedUser = null;
    private final String seed = "razdwa!3nie3trzyTYLKO2two!";
    private static final Authenticator instance = new Authenticator();

    private Authenticator() {

    }
    public void authenticate(User user) {
        User userFromDB = this.userDB.findByLogin(user.getLogin());
        //System.out.println(DigestUtils.md5Hex(user.getPassword() + this.seed));
        if(userFromDB != null &&
                userFromDB.getPassword().equals(
                        DigestUtils.md5Hex(user.getPassword() + this.seed))) {
            this.loggedUser = userFromDB;
        }
    }
    public boolean register(User user) {
        if(this.userDB.findByLogin(user.getLogin()) == null) {
            user.setPassword(DigestUtils.md5Hex(user.getPassword() + this.seed));
            user.setRole(User.Role.USER);
            userDB.addUser(user);
            return true;
        }
        else return false;
    }

    public String checkProduct(int order) {
        if (productsDB.findById(order)) {
            return "Nie udało się znaleźć produktu.";
        }
        else {
            return "Znaleziono";
        }
    }

    public String UserToAdmin(String login) {
        String result;
        switch (userDB.checkRoleToAdmin(login)) {
            case "0":
                result = "Rola admina została przyznana";
                break;
            case "1":
                result = "Podany user jest już Adminem";
                break;
            case "2":
                result = "Nie znaleziono usera";
                break;
            default:
                result = "Nieznany błąd";
                break;
        }
        return result;
    }

    public String validateQuantity(int givenId, int addValue) {
        if (productsDB.findById(givenId))
        {
            int fetchedQuantity = productsDB.getProduct(givenId).getQuantity();
            if (addValue >= 0)
            {
                if (addValue <= fetchedQuantity)
                {
                    productsDB.getProduct(givenId).addQuantity(addValue);
                    return "Pomyślnie zakończono";
                }
                else
                {
                    return String.format(
                            "Wprowadzona liczba nie może być większa niż %d",
                            fetchedQuantity);
                }
            }
            else
            {
                return "Liczba nie może być mniejsza niż zero";
            }
        }
        else
        {
            return "Nie znaleziono przedmiotu";
        }
    }

    public static Authenticator getInstance() {
        return instance;
    }
    public User getLoggedUser() {
        return loggedUser;
    }

    public void unmountLoggedUser() { this.loggedUser = null; }

    public String getSeed() {
        return seed;
    }
}
