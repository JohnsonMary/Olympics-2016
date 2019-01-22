package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class sortTests extends TestBase {

    public void website() {
        driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table.");

    }

    @Test
    public void test1() {

        website();
        List<WebElement> countryNames = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th"));

        countryWithMedalType(countryNames,"Total");
        countriesWithSilverMedalCount(18);


        System.out.println(getRowColOfCountry(" China"));
        System.out.println(getSum(18));


    }

    /*
    1. Go to website
    https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table.
    2. Verify that by default the Medal table is sorted by rank. To do that you need
    to capture all the cells in the Rank column and check if they are in
    ascending order (highlighted in the picture).
     */

    @Test
    public void sortTest() throws InterruptedException {

        website();

        List<WebElement> rankNumbers = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//tr/td[1]"));

        for (int i = 0; i < rankNumbers.size() - 3; i++) {

            System.out.println(rankNumbers.get(i).getText() + " is compared to " + rankNumbers.get(i + 1).getText());

            Assert.assertTrue(Integer.parseInt(rankNumbers.get(i).getText())< Integer.parseInt(rankNumbers.get(i+1).getText()));


        }


/*
3. Click link NOC.
4. Now verify that the table is now sorted by the country names. To do that you
need to capture all the names in the NOC column and check if they are in
ascending/alphabetical order (highlighted in the picture).
 */
        WebElement noc = driver.findElement(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//tr/th[2]"));
        noc.click();
        Thread.sleep(2000);

        List<WebElement> countryNames = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th"));

        for (int i = 0; i < countryNames.size() - 1; i++) {

            System.out.println(countryNames.get(i).getText() + " is compared to " + countryNames.get(i + 1).getText());

            Assert.assertTrue(countryNames.get(i).getText().compareTo(countryNames.get(i + 1).getText()) <= 0);


        }
//5. Verify that Rank column is not in ascending order anymore.


        List<WebElement> numbers = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//tr/td[1]"));


        WebElement[] countryArray = countryNames.toArray(new WebElement[countryNames.size()]);
        WebElement[] numbersArray = numbers.toArray(new WebElement[numbers.size()]);

        Assert.assertFalse(Arrays.equals(countryArray, numbersArray));
    }
/*
1. Write a method that returns the name of the country with the greatest
number of gold medals.
2. Write a method that returns the name of the country with the greatest
number of silver medals.
3. Write a method that returns the name of the country with the greatest
number of bronze medals.
4. Write a method that returns the name of the country with the greatest
number of medals.
 */


    public void countryWithMedalType(List<WebElement> countryNames,String type) {


        List<WebElement> typeList = new ArrayList<WebElement>();

        if ("Gold".equals(type)) {
            List<WebElement> goldNumbers = driver.findElements(By.xpath("table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[2]"));
            typeList.addAll(goldNumbers);

        } else if ("Silver".equals(type)) {
            List<WebElement> silverNumbers = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[3]"));
            typeList.addAll(silverNumbers);


        }else if("Bronze".equals((type))) {
            List<WebElement> bronzeNumbers = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[4]"));
            typeList.addAll(bronzeNumbers);
        }else if("Total".equals(type)){
            List<WebElement> totalMedals = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[5]"));
            typeList.addAll(totalMedals);

        }else {
            System.out.println("ERROR: Invalid madal type");
        }

            countryNames = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th"));

            for (int i = 0; i < countryNames.size() - 1; i++) {
                System.out.println(countryNames.get(i).getText());

            }

            int max = 0;
            int k = 0;


            for (int i = 0; i < typeList.size() - 2; i++) {

                if (Integer.parseInt(typeList.get(i).getText()) > max) {

                    max = Integer.parseInt(typeList.get(i).getText());
                    k = i;

                }
            }
            System.out.println("The greatest number  medal belongs to: " + countryNames.get(k).getText());
        }



    public String countriesWithSilverMedalCount(int silverNumber) {

        String str = " ";

        List<WebElement> countryNames = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th"));


        List<WebElement> silverNumbers = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[3]"));


        for (int i = 0; i < silverNumbers.size() - 1; i++) {

            if (Integer.parseInt(silverNumbers.get(i).getText()) == (silverNumber)) {

                str = "The countries by their silver medal count: " + countryNames.get(i).getText() + " ";


            }

        }return str;
    }

    public String getRowColOfCountry(String country) {


        int r = 0;
        int c = 0;

        List<WebElement> rows = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr"));


        for (int i = 0; i < rows.size() - 2; i++) {

            if (rows.get(i).getText().contains(country)) {

                r = i + 1;

            }
        }
        return "Column: 2, row " + r;


    }

    public List<String> getSum(int num) {

        List<WebElement> bronzeNumbers = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[4]"));

        List<WebElement> countryNames = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th"));
        List<String> countriessum2 = new ArrayList<String>();


        for (int i = 1; i < bronzeNumbers.size()-2; i++) {

            for (int k = 1; k < bronzeNumbers.size()-2; k++) {

                if (Integer.parseInt(bronzeNumbers.get(i).getText()) + Integer.parseInt(bronzeNumbers.get(k).getText()) == num && i != k) {


                    countriessum2.add(countryNames.get(i).getText());

                }

            }
        }
        return countriessum2;
    }
}

