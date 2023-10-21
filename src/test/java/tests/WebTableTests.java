package tests;

import driver.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class WebTableTests {
    public static void main(String[] args) {
        ChromeDriver driver = createDriverAndGetPage();
        updateTable(driver);
        getTableDetails(driver); //se pot inversa aceste 2 metode in executia lor si sa urmarim ce se afiseaza
        driver.quit();
    }

    public static ChromeDriver createDriverAndGetPage() {
        ChromeDriver driver = WebDriverManager.createChromeDriver(); //o instantiere de driver
        driver.get("https://testpages.eviltester.com/styled/tag/dynamic-table.html");//accesare de URL
        return driver;
    }


    public static void getTableDetails(ChromeDriver driver) {
        WebElement tableCaption = driver.findElement(By.cssSelector("#tablehere table caption"));
        System.out.println("Caption: " + tableCaption.getText());

        WebElement table = driver.findElement(By.cssSelector("#tablehere table"));//o referinta la elementul de table
        System.out.println("ID attribute: " + table.getAttribute("id"));
        System.out.println();

        //select table rows and columns
        List<WebElement> tableRows = driver.findElements(By.cssSelector("#tablehere table tr"));

        for (int i = 0; i < tableRows.size(); i++) {
            WebElement currentRows = tableRows.get(i);
            if (i == 0) {
                List<WebElement> firstRowsColumns = currentRows.findElements(By.cssSelector("th"));
                System.out.println("Headerul din prima coloana este: " + firstRowsColumns.get(0).getText());
                System.out.println("Headerul din a doua coloana este: " + firstRowsColumns.get(1).getText());
                System.out.println();
            } else {
                List<WebElement> currentColumns = currentRows.findElements(By.cssSelector("td"));
                System.out.println("Textul din randul " + (i + 1) + ", coloana 1 este: " + currentColumns.get(0).getText());
                System.out.println("Textul din randul " + (i + 1) + ", coloana 2 este: " + currentColumns.get(1).getText());
            }
        }
    }

    public static void updateTable(ChromeDriver driver) {
        WebElement summary = driver.findElement(By.cssSelector("details summary"));
        //am stocat o referinta la WebElement in variabila summary
        summary.click();
        //update caption
        WebElement caption = driver.findElement(By.id("caption"));
        caption.clear();
        caption.sendKeys("test introdus la lectie");

        //update table id
        WebElement tableId = driver.findElement(By.id("tableid"));
        tableId.clear();
        tableId.sendKeys("altCustomId");

        //update table content (textarea)
        WebElement jsonData = driver.findElement(By.id("jsondata"));

        jsonData.clear();
        jsonData.sendKeys("[\n" +
                "    {\n" +
                "        \"name\": \"Cristian\",\n" +
                "        \"age\": 47\n" +
                "    },\n" +
                "    {\n" +
                "        \"name\": \"Monica\",\n" +
                "        \"age\": 46\n" +
                "    }\n" +
                "]");
        //escape character
        System.out.println(jsonData.getAttribute("value")) /*nu functioneaza getText()*/;

        //press refresh button
        WebElement refreshTableButton = driver.findElement(By.id("refreshtable"));
        refreshTableButton.click();

        /*
        WebElement tableCaption = driver.findElement(By.cssSelector("table#dynamictable caption"));
        //eroare NoSuchElementException deoarece vrea sa localizeze caption-ul prin vechiul id
        WebElement tableCaption = driver.findElement(By.cssSelector("#tablehere table caption"));
        System.out.println(tableCaption.getText());
        //o regasim in cealalte metoda getTableDetails
        */

        /*
        WebElement table = driver.findElement(By.cssSelector("#tablehere table"));//o referinta la elementul de table
        System.out.println(table.getAttribute("id"));
        //mutam aceasta bucata de cod in metoda getTableDetails
        */
    }
}

