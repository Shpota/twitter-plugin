package com.sashashpota.twitter;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Editor;
import org.junit.Test;

import static com.intellij.openapi.actionSystem.CommonDataKeys.EDITOR;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class PostTweetActionTest {
    @Test
    public void shouldUpdateVisibility() {
        PostTweetAction action = new PostTweetAction();
        AnActionEvent event = mock(AnActionEvent.class);
        Editor editor = mock(Editor.class);
        given(event.getData(EDITOR)).willReturn(editor);
        CaretModel caretModel = mock(CaretModel.class);
        given(editor.getCaretModel()).willReturn(caretModel);
        Caret currentCaret = mock(Caret.class);
        given(caretModel.getCurrentCaret()).willReturn(currentCaret);
        given(currentCaret.getSelectedText()).willReturn("selected text");
        Presentation presentation = new Presentation();
        given(event.getPresentation()).willReturn(presentation);

        action.update(event);

        assertThat(presentation.isVisible(), is(true));
    }

    @Test
    public void shouldUpdateVisibilityGivenBlankSelectedText() {
        PostTweetAction action = new PostTweetAction();
        AnActionEvent event = mock(AnActionEvent.class);
        Editor editor = mock(Editor.class);
        given(event.getData(EDITOR)).willReturn(editor);
        CaretModel caretModel = mock(CaretModel.class);
        given(editor.getCaretModel()).willReturn(caretModel);
        Caret currentCaret = mock(Caret.class);
        given(caretModel.getCurrentCaret()).willReturn(currentCaret);
        given(currentCaret.getSelectedText()).willReturn("     ");
        Presentation presentation = new Presentation();
        given(event.getPresentation()).willReturn(presentation);

        action.update(event);

        assertThat(presentation.isVisible(), is(false));
    }

    @Test
    public void shouldUpdateVisibilityGivenNullEditor() {
        PostTweetAction action = new PostTweetAction();
        AnActionEvent event = mock(AnActionEvent.class);
        Presentation presentation = new Presentation();
        given(event.getPresentation()).willReturn(presentation);

        action.update(event);

        assertThat(presentation.isVisible(), is(false));
    }
}
