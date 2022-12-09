package pl.edu.wszib.store.paints.core;

import pl.edu.wszib.store.paints.database.ProductsDB;
import pl.edu.wszib.store.paints.gui.GUI;
import pl.edu.wszib.store.paints.model.User;

public class Core {
    final ProductsDB productsDB = ProductsDB.getInstance();
    final Authenticator authenticator = Authenticator.getInstance();
    final GUI gui = GUI.getInstance();
    private static final Core instance = new Core();

    private Core() {

    }
    public void start() {

        boolean isLogged = false;
        boolean running = true;

        while (running)
        {
            while (!isLogged)
            {
                switch (this.gui.showMenu())
                {
                    case "1":
                        this.authenticator.authenticate(this.gui.readLoginAndPassword());
                        isLogged = this.authenticator.getLoggedUser() != null;
                        if (!isLogged) System.out.println("Autentykacja niepowodzenie");
                        break;

                    case "2":
                        if (this.authenticator.register(this.gui.readLoginAndPassword()))
                            System.out.println("Zarejstrowano pomyslnie");
                        else System.out.println("Rejestracja niepowodzenie");
                        break;

                    case "3":
                        System.out.println("Bye bye..");
                        this.authenticator.unmountLoggedUser();
                        running = false;
                        isLogged = true;
                        break;

                    default:
                        System.out.println("Nie ma takiej opcji!");
                        break;
                }
            }

            if(running == false) break;

            if (this.authenticator.getLoggedUser().getRole() == User.Role.ADMIN)
            {
                while (isLogged)
                {
                    switch (this.gui.showAdminPanel())
                    {
                        case "1": // Show product list
                            this.gui.listProduct();
                            break;

                        case "2": // Buy product
                            System.out.println(
                                    this.authenticator.checkProduct(
                                            this.gui.readId(),
                                            this.gui.readQuantity()
                                    ));
                            break;

                        case "3": // Increase product quantity
                            System.out.println(
                                    this.authenticator.magazineManager(
                                        this.gui.readId(),this.gui.readQuantity()
                                    ));
                            break;

                        case "4": // Give user admin permission
                            System.out.println(
                                    this.authenticator.UserToAdmin(
                                            this.gui.readUserLogin()));
                            break;

                        case "5": // Logout
                            System.out.println("Wylogowano");
                            this.authenticator.unmountLoggedUser();
                            isLogged = false;
                            break;

                        default:
                            System.out.println("Nie ma takiej opcji");
                            break;
                    }
                }
            }
            else if (this.authenticator.getLoggedUser().getRole() == User.Role.USER)
            {
                while (isLogged)
                {
                    switch (this.gui.showUserPanel())
                    {
                        case "1": // Show product list
                            this.gui.listProduct();
                            break;

                        case "2": // Buy product
                            System.out.println(
                                    this.authenticator.checkProduct(
                                            this.gui.readId(),
                                            this.gui.readQuantity()
                                    ));
                            break;

                        case "3": // Logout
                            System.out.println("Wylogowano");
                            this.authenticator.unmountLoggedUser();
                            isLogged = false;
                            break;

                        default:
                            System.out.println("Nie ma takiej opcji");
                            break;
                    }
                }
            }
            else System.out.println("Nieznana rola! Nie wybrano panelu.");
        }
    }
    public static Core getInstance() {
        return instance;
    }
}
