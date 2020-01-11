package pom.vencidos;

import Auxiliar.MetodosGenerales;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Angel Torrre
 */
public class LoginVencidos extends MetodosGenerales {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "a[href = '/vencidos/index.php/site/login']")
    WebElement ulIniciarSession;

    @FindBy(css = "#LoginForm_username")
    WebElement inputUserName;

    @FindBy(css = "#LoginForm_password")
    WebElement inputPassword;

    @FindBy(css = "#yw0")
    WebElement botonLogin;

    public LoginVencidos(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 10);
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(this.driver, 10);
        PageFactory.initElements(factory, this);
    }

    public boolean existeUlIniciarSession(){
        return existeWebElement(ulIniciarSession,driver,wait);
    }

    public void clickUlIniciarSession(){
        click(ulIniciarSession,driver,wait);
    }

    public void ingresarUsername(String user){
        ingresarTexto(inputUserName,user,driver,wait);
    }

    public void ingresarPassword(String password){
        ingresarTexto(inputPassword,password,driver,wait);
    }

    public void clickBotonLogin(){
        click(botonLogin,driver,wait);
    }
}
