package com.gelerion.cd.notepad.notepad.acceptance.tests.note;

import com.gelerion.cd.notepad.notepad.acceptance.tests.configuration.AcceptanceTestsConfiguration;
import com.gelerion.cd.notepad.notepad.acceptance.tests.note.page.object.NewNotePage;
import com.gelerion.cd.notepad.notepad.domain.model.Note;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by denis.shuvalov on 06/08/2018.
 *
 * 1. Note server must be up and listen on port 8080
 * 2. Chrome driver should have been launched (https://github.com/SeleniumHQ/selenium/wiki/ChromeDriver)
 * 3. Optional. Customize driver setting by passing -Dacceptance.notepad.url=${NOTEPAD_URL} -Dselenium.browser=firefox command to test phase
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AcceptanceTestsConfiguration.class})
public class CreateNoteTest {

    @Autowired
    private WebDriver driver;

    @Autowired
    private URI notepadBaseUri;

    private NewNotePage newNotePage;
    private final String newNoteSuccessMessage = "Your note was successfully saved!";
    private final String newNoteFailMessage = "Title and Content cannot be empty";

    @Before
    public void setUp() {
        driver.get(notepadBaseUri.toString());
        newNotePage = new NewNotePage(driver);
    }

    @Test
    public void shouldCreateNewNoteWithTitleAndContent() throws InterruptedException {
        Note newNote = new Note("Acceptance Test", "Creating note from the acceptance test");
        newNotePage.create(newNote);
        assertThat(newNotePage.getMessage(), equalTo(newNoteSuccessMessage));
    }

    @Test
    public void shouldNotCreateNewNoteWhenTitleIsEmpty() throws InterruptedException {
        Note newNote = new Note("", "Creating note from the acceptance test");
        newNotePage.create(newNote);
        assertThat(newNotePage.getMessage(), equalTo(newNoteFailMessage));
    }

    @Test
    public void shouldNotCreateNewNoteWhenContentIsEmpty() throws InterruptedException {
        Note newNote = new Note("Acceptance Test", "");
        newNotePage.create(newNote);
        assertThat(newNotePage.getMessage(), equalTo(newNoteFailMessage));
    }
}