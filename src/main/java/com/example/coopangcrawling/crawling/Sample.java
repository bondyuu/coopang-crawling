package com.example.coopangcrawling.crawling;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class Sample {

    //    @BeforeEach
//    void setUp() {
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--remote-allow-origins=*");
//
//        seleniumUtil.setUp(new ChromeDriver(options));
//    }
//
//    @Test
//    void defaultSettingTest() {
//        seleniumUtil.openLoginPage();
//        seleniumUtil.loginWithAdmin();
//        seleniumUtil.moveToAdminPage();
//        seleniumUtil.createUser();
//        seleniumUtil.moveToAdminPage();
//        seleniumUtil.createRepo();
//        seleniumUtil.moveToMenu("폴더");
//        seleniumUtil.createFolder();
//    }
//
//    @AfterEach
//    void tearDown() {
//        seleniumUtil.tearDown();
//    }

//    public void loginWithAdmin() {
//
//        String username = "admin";
//        String password = "1";
//
//        driver.findElement(By.name("userName")).sendKeys(username);
//        driver.findElement(By.name("password")).sendKeys(password);
//        driver.findElement(By.cssSelector(".login_btn")).click();
//
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'공지사항')]")));
//    }
//
//    public void moveToAdminPage() {
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[1]/div[1]/div[2]/ul/li[3]/a"))).click();
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div/div[1]/div/input"))).click();
//    }
//
//    public void moveToMenu(String name) {
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='mainMenuAnchor'][contains(text(),'" + name + "')]"))).click();
//    }
//
//    public void createUser() {
//        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("사용자"))).click();
//
//        String mainHandle = driver.getWindowHandle();
//
//        String email = "hshin@redbeansoft.com";
//        String password = "asdf1!";
//
//        for (int i = 0; i < USER_NUM; i++) {
//            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='생성']"))).click();
//
//            for (String handle: driver.getWindowHandles()) {
//                if (!handle.equals(mainHandle)) {
//                    driver.switchTo().window(handle);
//                }
//            }
//
//            int idx = i + 1;
//            String id = "user" + idx;
//            String username = "사용자" + idx;
//
//            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='확인']")));
//
//            driver.findElement(By.xpath("//input[@name='userName']")).sendKeys(id);
//            driver.findElement(By.xpath("//input[@name='fullName']")).sendKeys(username);
//            driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
//            driver.findElement(By.xpath("//input[@name='passwordConfirm']")).sendKeys(password);
//            driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
//
//            driver.findElement(By.xpath("//input[@value='확인']")).click();
//            driver.switchTo().window(mainHandle);
//
//        }
//
//    }
//
//    public void createRepo() {
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), '저장소')]"))).click();
//
//
//        for (int i = 0; i < REPO_LIST.size(); i++) {
//            String repo = REPO_LIST.get(i);
//            List<String> userList = USER_LIST.get(i);
//
//            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='생성']"))).click();
//
//            String mainHandle = driver.getWindowHandle();
//            System.out.println(driver.getWindowHandles().size());
//            for (String handle: driver.getWindowHandles()) {
//                if (!handle.equals(mainHandle)) {
//                    driver.switchTo().window(handle);
//                }
//            }
//
//
//            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='저장']")));
//
//            driver.findElement(By.xpath("//input[@name='containerName']")).sendKeys(repo);
//
//            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'관리자')]/../../.."))).click();
//            driver.findElement(By.xpath("//input[@class='dhxcombo_input']")).sendKeys(userList.get(0));
//            driver.findElement(By.xpath("//input[@class='dhxcombo_input']")).sendKeys(Keys.ENTER);
//
//            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'멤버')]/../../.."))).click();
//            driver.findElement(By.xpath("//input[@class='dhxcombo_input']")).sendKeys(userList.get(1));
//            driver.findElement(By.xpath("//input[@class='dhxcombo_input']")).sendKeys(Keys.ENTER);
//
//            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'작성자')]/../../.."))).click();
//            driver.findElement(By.xpath("//input[@class='dhxcombo_input']")).sendKeys(userList.get(2));
//            driver.findElement(By.xpath("//input[@class='dhxcombo_input']")).sendKeys(Keys.ENTER);
//
//            driver.findElement(By.xpath("//input[@value='저장']")).click();
//
//            driver.switchTo().window(mainHandle);
//        }
//
//    }
//
//    public void createFolder() {
//        WebElement repoNameInput = wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("//input[@class='dhxcombo_input']")));
//
//        for (String repo : REPO_LIST) {
//            repoNameInput.sendKeys(Keys.LEFT_CONTROL + "A");
//            repoNameInput.sendKeys(repo);
//            repoNameInput.sendKeys(Keys.ENTER);
//
//            for (String folder: FOLDER_LIST) {
//                WebElement root = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='" + repo + "']")));
//
//                Actions action = new Actions(driver);
//                action.contextClick(root).build().perform();
//
//                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div['하위 폴더 생성'][@class='sub_item_text']"))).click();
//
//                wait.until(ExpectedConditions.numberOfWindowsToBe(2));
//
//                String mainHandle = driver.getWindowHandle();
//                for (String handle : driver.getWindowHandles()) {
//                    if (!handle.equals(mainHandle)) {
//                        driver.switchTo().window(handle);
//                    }
//                }
//
//                WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='확인']")));
//
//                driver.findElement(By.xpath("//input[@id='folderName']")).sendKeys(folder);
//                confirmBtn.click();
//
//                driver.switchTo().window(mainHandle);
//            }
//        }
//
//    }
//
//    private WebElement findElementByTypeAndText(String type, String text) {
//        String xpath = "//" + type + "[contains(text(),'" + text + "')]";
//
//        return driver.findElement(By.xpath(xpath));
//    }
}
