package RedbusBooking;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class bookingTest {

	public static void main(String[] args) {
		WebDriver driver;
		String browserName ="chrome"
		if(browserName.equals("chrome")){
			ChromeOptions options = new ChromeOptions();
			driver = new ChromeDriver(options);
		}
		driver.get("https://www.redbus.in/");
		driver.manage().window().setDimension(new Dimension(1444,990));
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.id("src")).sendKeys("Kovilpatti");
		List<WebElement> Suggestions = driver.findElements(By.cssSelector(".sc-iwsKbI"));
		for(int i=0;i<Suggestions.size();++i) {
			String city = Suggestions.get(i).findElement(By.xpath("//div/text[1]")).getText();
			if(city.equalsIgnoreCase("Kovilpatti")) {
				Suggestions.get(i).findElement(By.xpath("//div/text[1]")).click();
				break;
			}
		}
		driver.findElement(By.cssSelector("#dest")).sendKeys("Chennai");
		Suggestions = driver.findElements(By.cssSelector(".sc-iwsKbI"));
		for(int i=0;i<Suggestions.size();++i) {
			String city = Suggestions.get(i).findElement(By.xpath("//div/text[1]")).getText();
			if(city.equalsIgnoreCase("Chennai")) {
				Suggestions.get(i).findElement(By.xpath("//div/text[1]")).click();
				break;
			}
		}
		
		String month = driver.findElement(By.cssSelector("div[class*='CalendarHeader'] div:nth-child(2)")).getText();
		while(month!="May") {
			driver.findElement(By.cssSelector("div[class*='CalendarHeader'] div:nth-child(3) svg")).click();
		}
		
		List<WebElement> days = driver.findElements(By.cssSelector("span[class*='CalendarDaysSpan']"));
		for(int i=0;i<days.size();++i) {
			if(days.get(i).getText().equals("20")) {
				days.get(i).click();
				break;
			}
		}
		driver.findElement(By.xpath("//button[text()='SEARCH BUSES']"));
		
		int min=99999,index=0;
		List<WebElement> prices = driver.findElements(By.cssSelector("div[class='seat-fare '] div:nth-child(3) span"));
		for(int i=0;i<prices.size();++i) {
			String price = prices.get(i).getText();
			if(Integer.parseInt(price)<min) {
				min=Integer.parseInt(price);
				index=i;
			}
		}
		List<WebElement> busesList = driver.findElements(By.xpath("//div/li/div/div[2]/div"));
		busesList.get(index).click();
	}

}
