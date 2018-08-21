package com.gelerion.cd.notepad.notepad.acceptance.tests.note.page.object;

import com.gelerion.cd.notepad.notepad.domain.model.Note;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

/**
 * Created by denis.shuvalov on 05/08/2018.
 *
 * Interacting directly with the Selenium WebDriver in the test code may lead to a test that is hard to read and
 * understand. In order to avoid that, a common pattern implemented is the Page Object. Basically, it encapsulates
 * the web page functions and states in a separated component that exposes a simple and easy-to-use API for your tests.
 *
 * Below is NewNotePage.java class, which is the page object used to encapsulate the web page that allow users to
 * create new notes.
 */
public class NewNotePage {

    @FindBy(id="newNote")
    private WebElement newNoteModal;

    @FindBy(id="newNoteTitle")
    private WebElement title;

    @FindBy(id="newNoteContent")
    private WebElement content;

    @FindBy(id="btnCreateNewNote")
    private WebElement createNoteButton;

    private long sleep = 2000L;

    private WebDriver driver;

    public NewNotePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void create(Note newNote) throws InterruptedException {
        newNoteModal.click();
        sleep(sleep);

        title.sendKeys(newNote.getTitle());
        content.sendKeys(newNote.getContent());
        createNoteButton.click();
        sleep(sleep);
    }

    public String getMessage() {
        return driver.findElement(By.className("noty_text")).getText();
    }

}
