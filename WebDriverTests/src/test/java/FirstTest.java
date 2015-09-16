import org.openqa.selenium.firefox.FirefoxDriver;

public class FirstTest {
public static void main(String args[])
{
	FirefoxDriver driver = new FirefoxDriver(); 
	driver.get("http://www.facebook.com");
	
	driver.close(); 
}
}